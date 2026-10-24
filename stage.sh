#!/usr/bin/env bash
# Builds the current commit as benevole-renderer:<commit> and starts it as test instance
# (ports 13090/13091). The version is remembered for promote.sh.
set -euo pipefail
cd "$(dirname "$0")"

STATE=.deploy

if [[ -n "$(git status --porcelain --untracked-files=no)" ]]; then
	echo "Abbruch: Es gibt uncommittete Änderungen. Bitte committen oder verwerfen." >&2
	exit 1
fi

TAG=$(git rev-parse --short HEAD)
export TAG

echo "Baue benevole-renderer:$TAG ..."
docker compose -f docker-compose-test.yml build

echo "Starte Test-Instanz ..."
if ! docker compose -f docker-compose-test.yml up -d --wait --wait-timeout 120; then
	echo "Test-Instanz ist nicht gesund geworden. Logs: docker compose -f docker-compose-test.yml logs" >&2
	exit 1
fi
docker tag "benevole-renderer:$TAG" benevole-renderer:test

mkdir -p "$STATE"
echo "$TAG" > "$STATE/staged"

echo "Test-Instanz läuft mit $TAG auf Port 13090 (Admin 13091)."
echo "Produktiv stellen mit: ./promote.sh"
