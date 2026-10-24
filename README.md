# PBS Bénévole Renderer

How to start the "PBS Bénévole Renderer" application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/benevole-renderer.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Prometheus
---
Prometheus scrap url `http://localhost:8081/prometheus-metrics`

Service 
---
    GET   /benevole/check/ping (ch.pbs.benevole.renderer.resources.BenevoleHealthCheckResource)
    GET   /benevole/demo/index (ch.pbs.benevole.renderer.resources.BenevoleDemoResource)
    GET   /benevole/demo/json (ch.pbs.benevole.renderer.resources.BenevoleDemoResource)
    GET   /benevole/demo/pdf/{kurs}/{lang} (ch.pbs.benevole.renderer.resources.BenevoleDemoResource)
    POST  /kurs/renderer/pdf/{kurs}/{lang} (ch.pbs.benevole.renderer.resources.BenevoleRendererResource)

Docker / Deployment
---
Auf dem Server laufen eine Test- und eine Produktiv-Instanz nebeneinander:

| Instanz | Compose-File | Projekt | Ports (App/Admin) |
|---|---|---|---|
| Test | `docker-compose-test.yml` | `benevole-test` | 9080 / 9081 |
| Produktiv | `docker-compose.yml` | `benevole-prod` | 8080 / 8081 |

Ablauf:

1. `git pull`
1. `./stage.sh` baut den aktuellen Commit als Image `benevole-renderer:<commit>` und startet ihn als Test-Instanz.
   Bricht ab, wenn es uncommittete Änderungen gibt oder der Healthcheck nicht grün wird.
1. Test-Instanz prüfen, z.B. `http://<server>:9080/benevole/demo/index`
1. `./promote.sh` startet genau dieses Image als Produktiv-Instanz, ohne neu zu bauen.
   Wird die neue Version nicht gesund, startet das Skript automatisch wieder die bisherige.
1. Bei Problemen: `./promote.sh --rollback` startet wieder die vorherige Produktivversion.

Die Versionen merken sich die Skripte in `.deploy/` (`staged`, `current`, `previous`).
Zusätzlich zeigen die Tags `benevole-renderer:test` und `benevole-renderer:prod` auf die laufenden Versionen,
deshalb funktionieren `docker compose -f docker-compose.yml logs` usw. auch ohne `TAG`.

Voraussetzung: Docker Compose v2.17 oder neuer (für `up --wait --wait-timeout`).

Einmalige Umstellung vom alten Setup: Die bisherige Instanz läuft im Projekt `benevole` und belegt die Ports.
Vor dem ersten `./promote.sh` stoppen mit `docker compose -p benevole down`
(Projektname bei Bedarf mit `docker compose ls` prüfen).