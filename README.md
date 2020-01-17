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
Prometheus scrap url `http://localhost:8081/prometheus`

Service 
---
    GET   /benevole/check/ping (ch.pbs.benevole.renderer.resources.BenevoleHealthCheckResource)
    GET   /benevole/demo/index (ch.pbs.benevole.renderer.resources.BenevoleDemoResource)
    GET   /benevole/demo/json (ch.pbs.benevole.renderer.resources.BenevoleDemoResource)
    GET   /benevole/demo/pdf/{kurs}/{lang} (ch.pbs.benevole.renderer.resources.BenevoleDemoResource)
    POST  /kurs/renderer/pdf/{kurs}/{lang} (ch.pbs.benevole.renderer.resources.BenevoleRendererResource)

Docker
---
Start mit `docker-compose -p benevole up -d`

Die Ports sind in docker-compose.yml definiert.