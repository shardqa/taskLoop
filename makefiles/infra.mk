.PHONY: infra-up infra-down infra-logs

# Infrastructure using Podman (fallback to Docker Compose)
COMPOSE_CMD := $$(command -v podman-compose >/dev/null 2>&1 && echo podman-compose || echo docker compose -f)

infra-up:
	@echo "Starting infrastructure (MongoDB + mongo-express) ..."
	@CMD=$$(command -v podman-compose >/dev/null 2>&1 && echo "podman-compose -f podman-compose.yml" || echo "docker compose -f podman-compose.yml"); \
	$$CMD up -d
	@echo "MongoDB at mongodb://admin:password@localhost:27017 (authSource=admin)"
	@echo "Mongo Express UI: http://localhost:8081"

infra-down:
	@echo "Stopping infrastructure ..."
	@CMD=$$(command -v podman-compose >/dev/null 2>&1 && echo "podman-compose -f podman-compose.yml" || echo "docker compose -f podman-compose.yml"); \
	$$CMD down

infra-logs:
	@CMD=$$(command -v podman-compose >/dev/null 2>&1 && echo "podman-compose -f podman-compose.yml" || echo "docker compose -f podman-compose.yml"); \
	$$CMD logs -f


