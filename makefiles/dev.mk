.PHONY: run dev run-backend run-frontend stop kill-ports check-frontend-port

run:
	@$(MAKE) stop
	@$(MAKE) check-frontend-port
	@echo "Starting backend (Spring Boot) and frontend (React @ $(FRONTEND_PORT)) ..."
	(./mvnw spring-boot:run) & \
	(cd frontend && PORT=$(FRONTEND_PORT) npm start) & \
	wait

dev:
	@$(MAKE) stop
	@$(MAKE) infra-up
	@$(MAKE) check-frontend-port
	@echo "Starting full dev stack (backend + frontend @ $(FRONTEND_PORT)) ..."
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

check-frontend-port:
	@if lsof -t -i:$(FRONTEND_PORT) >/dev/null 2>&1; then \
		echo "Port $(FRONTEND_PORT) is in use. Set FRONTEND_PORT to a free port: make dev FRONTEND_PORT=3002"; \
		exit 1; \
	else \
		echo "Frontend port $(FRONTEND_PORT) is free"; \
	fi


