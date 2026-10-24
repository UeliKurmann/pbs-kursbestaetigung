#!/usr/bin/env bash
# Starts the version staged by stage.sh as production instance (ports 8080/8081), without building.
# With --rollback, the previous production version is started again.
set -euo pipefail
cd "$(dirname "$0")"

STATE=.deploy

case "${1:-}" in
	"")
		TAG=$(cat "$STATE/staged" 2>/dev/null) || { echo "Keine gestagte Version. Zuerst ./stage.sh ausführen." >&2; exit 1; }
		;;
	--rollback)
		TAG=$(cat "$STATE/previous" 2>/dev/null) || { echo "Keine vorherige Version für einen Rollback vorhanden." >&2; exit 1; }
		;;
	*)
		echo "Aufruf: $0 [--rollback]" >&2
		exit 2
		;;
esac

if ! docker image inspect "benevole-renderer:$TAG" > /dev/null 2>&1; then
	echo "Image benevole-renderer:$TAG nicht gefunden." >&2
	exit 1
fi

CURRENT=$(cat "$STATE/current" 2>/dev/null || true)
if [[ "$TAG" == "$CURRENT" ]]; then
	echo "$TAG ist bereits produktiv."
	exit 0
fi

start() {
	TAG=$1 docker compose -f docker-compose.yml up -d --wait --wait-timeout 120
}

echo "Stelle benevole-renderer:$TAG produktiv (bisher: ${CURRENT:-keine}) ..."
if ! start "$TAG"; then
	echo "$TAG ist nicht gesund geworden." >&2
	if [[ -n "$CURRENT" ]]; then
		echo "Starte wieder $CURRENT ..." >&2
		start "$CURRENT"
	fi
	exit 1
fi
docker tag "benevole-renderer:$TAG" benevole-renderer:prod

if [[ -n "$CURRENT" ]]; then
	echo "$CURRENT" > "$STATE/previous"
fi
echo "$TAG" > "$STATE/current"

echo "Produktiv läuft jetzt $TAG auf Port 8080 (Admin 8081)."
