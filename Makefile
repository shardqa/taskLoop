.PHONY: help count-files count-all clean build test run run-backend run-frontend stop kill-ports infra-up infra-down infra-logs dev

help:
	@echo "Available commands:"
	@echo "  count-files  - Count files in each directory (excluding .git, node_modules, etc.)"
	@echo "  count-all    - Count all files in the project"
	@echo "  clean        - Clean build artifacts"
	@echo "  build        - Build the project"
	@echo "  test         - Run tests"
	@echo "  run          - Run backend (Spring Boot) and frontend (React) together"
	@echo "  run-backend  - Run only the backend (Spring Boot)"
	@echo "  run-frontend - Run only the frontend (React dev server)"
	@echo "  stop         - Stop processes on common dev ports (8080, 3000, 3001)"
	@echo "  kill-ports   - Alias for stop"
	@echo "  infra-up     - Start required infrastructure (MongoDB + mongo-express)"
	@echo "  infra-down   - Stop infrastructure"
	@echo "  infra-logs   - Show infrastructure logs"
	@echo "  dev          - Start infra, backend and frontend together"

# Default frontend port; override like: make run FRONTEND_PORT=3001
FRONTEND_PORT ?= 3001

count-files:
	@echo "Counting files in each directory (excluding .git, node_modules, target, etc.):"
	@find . -type d -not -path "./.git*" -not -path "./node_modules*" -not -path "./target*" -not -path "./frontend/node_modules*" -not -path "./frontend/build*" | while read dir; do count=$$(find "$$dir" -maxdepth 1 -type f | wc -l); if [ $$count -gt 0 ]; then echo "$$count files in $$dir"; fi; done | sort -k1 -nr

count-all:
	@echo "Total files in project:"
	@find . -type f | wc -l
	@echo ""
	@echo "Files excluding dependencies:"
	@find . -type f -not -path "./node_modules/*" -not -path "./target/*" -not -path "./.git/*" | wc -l
	@echo ""
	@echo "Source code files only:"
	@find src frontend/src -type f \( -name "*.js" -o -name "*.jsx" -o -name "*.ts" -o -name "*.tsx" -o -name "*.java" -o -name "*.css" -o -name "*.html" \) | wc -l

clean:
	@echo "Cleaning build artifacts..."
	rm -rf target/
	rm -rf frontend/build/
	rm -rf frontend/node_modules/
	rm -rf node_modules/

build:
	@echo "Building project..."
	cd frontend && npm install
	cd frontend && npm run build
	./mvnw clean compile

test:
	@echo "Running tests..."
	./mvnw test
	cd frontend && npm test 

run:
	@echo "Starting backend (Spring Boot) and frontend (React) ..."
	(./mvnw spring-boot:run) & \
	(cd frontend && PORT=$(FRONTEND_PORT) npm start) & \
	wait

dev:
	@$(MAKE) infra-up
	@echo "Starting full dev stack (backend + frontend) ..."
	(./mvnw spring-boot:run) & \
	(cd frontend && PORT=$(FRONTEND_PORT) npm start) & \
	wait

run-backend:
	@echo "Starting backend (Spring Boot) ..."
	./mvnw spring-boot:run

run-frontend:
	@echo "Starting frontend (React) on port $(FRONTEND_PORT) ..."
	cd frontend && PORT=$(FRONTEND_PORT) npm start

stop kill-ports:
	@echo "Stopping processes on ports 8080, 3000, 3001 (if any) ..."
	-@PID=$$(lsof -t -i:8080) && [ -n "$$PID" ] && kill -9 $$PID || true
	-@PID=$$(lsof -t -i:3000) && [ -n "$$PID" ] && kill -9 $$PID || true
	-@PID=$$(lsof -t -i:3001) && [ -n "$$PID" ] && kill -9 $$PID || true

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