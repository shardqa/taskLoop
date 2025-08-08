# TaskLoop Project Overview

## Project Overview
TaskLoop is a comprehensive task management application with a Java Spring Boot backend and React frontend, featuring advanced task management capabilities including recurrence, categorization, and soft delete functionality.

## Architecture

### Backend (Java Spring Boot + MongoDB)
- **Framework**: Spring Boot with Spring Security
- **Database**: MongoDB with Spring Data MongoDB
- **Authentication**: JWT-based with Bearer token scheme
- **Documentation**: OpenAPI/Swagger
- **Logging**: Logback with console and rolling file appenders

### Frontend (React)
- **Framework**: React 18 with React Router v6
- **Styling**: Modular CSS with separate files (base.css, buttons.css, forms.css, components.css)
- **HTTP Client**: Axios for API communication
- **Date Handling**: date-fns for date formatting
- **State Management**: React Context for authentication

## Related Documents
- [Features](features.md) - Key features implemented
- [Structure](structure.md) - Project structure and organization
- [API](api.md) - API endpoints documentation
- [Guidelines](guidelines.md) - Development guidelines and preferences
- [Status](status.md) - Current status and next steps
- [Dependencies](dependencies.md) - Project dependencies 