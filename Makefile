.PHONY: help count-files count-all clean build test

help:
    @echo "Available commands:"
    @echo "  count-files    - Count files in each directory"
    @echo "  count-all      - Count all files (and source-only)"
    @echo "  clean          - Clean build artifacts"
    @echo "  build          - Build backend and frontend"
    @echo "  test           - Run backend and frontend tests"
    @echo "  dev            - Start infra + backend + frontend (from makefiles/dev.mk)"
    @echo "  run            - Start backend + frontend (from makefiles/dev.mk)"
    @echo "  run-backend    - Start backend only (from makefiles/dev.mk)"
    @echo "  run-frontend   - Start frontend only (from makefiles/dev.mk)"
    @echo "  stop|kill-ports- Stop common dev ports (from makefiles/dev.mk)"
    @echo "  infra-up/down  - Infra controls (from makefiles/infra.mk)"

# Defaults (overridable)
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

include makefiles/dev.mk
include makefiles/infra.mk