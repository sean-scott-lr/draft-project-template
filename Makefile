# Development
dev: dev-docker-up
	./mvnw compile quarkus:dev

clean:
	./mvnw clean

dev-docker-up:
	docker-compose -f tools/docker/docker-compose.yml up -d

dev-docker-down:
	docker-compose -f tools/docker/docker-compose.yml down

dev-docker-clean:
	make dev-docker-down
	docker volume rm docker_prometheus_data
	docker volume rm docker_grafana_data
	make dev-docker-up

# Local environment native
native-local-build: dev-docker-up
	./mvnw package -Pnative

native-local-run: dev-docker-up
	./target/quarkus-svc-1.0-SNAPSHOT-runner

# Linux native
native-linux-build:
	./mvnw package -Pnative -Dnative-image.docker-build=true
	docker build -f src/main/docker/Dockerfile.native -t quarkus/quarkus-svc .

native-docker-up:
	docker-compose -f tools/docker/docker-compose-native.yml up -d

native-docker-down:
	docker-compose -f tools/docker/docker-compose-native.yml down

native-docker-clean:
	make native-docker-down
	docker volume rm docker_prometheus_data
	docker volume rm docker_grafana_data

# Database helpers
db-dump:
	if [[ -f ~/.pgpass ]]; then mv ~/.pgpass ~/.pgpass-bak ; fi
	umask 077 && echo 'localhost:5432:example:postgres:postgres' > ~/.pgpass
	pg_dump -h localhost -p 5432 -U postgres example > dump.sql
	rm ~/.pgpass
	if [[ -f ~/.pgpass-bak ]]; then mv ~/.pgpass-bak ~/.pgpass ; fi

db-restore:
	if [[ -f ~/.pgpass ]]; then mv ~/.pgpass ~/.pgpass-bak ; fi
	umask 077 && echo 'localhost:5432:example:postgres:postgres' > ~/.pgpass
	psql -h localhost -p 5432 -U postgres example < dump.sql
	rm ~/.pgpass
	if [[ -f ~/.pgpass-bak ]]; then mv ~/.pgpass-bak ~/.pgpass ; fi

db-vacuum:
	if [[ -f ~/.pgpass ]]; then mv ~/.pgpass ~/.pgpass-bak ; fi
	umask 077 && echo 'localhost:5432:example:postgres:postgres' > ~/.pgpass
	psql -h localhost -p 5432 -U postgres example -c 'vacuum analyze'
	rm ~/.pgpass
	if [[ -f ~/.pgpass-bak ]]; then mv ~/.pgpass-bak ~/.pgpass ; fi
