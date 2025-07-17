.PHONY: help count-files count-all clean build test

help:
	@echo "Available commands:"
	@echo "  count-files  - Count files in each directory (excluding .git, node_modules, etc.)"
	@echo "  count-all    - Count all files in the project"
	@echo "  clean        - Clean build artifacts"
	@echo "  build        - Build the project"
	@echo "  test         - Run tests"

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