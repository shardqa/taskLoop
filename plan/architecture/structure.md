# Project Structure

## Directory Structure
```
taskLoop/
├── src/main/java/com/todoapp/
│   ├── controller/          # REST controllers
│   ├── model/              # Entity models
│   ├── repository/         # Data access layer
│   ├── service/            # Business logic services
│   ├── security/           # JWT and security config
│   ├── dto/                # Data transfer objects
│   └── exception/          # Custom exceptions
├── frontend/
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── pages/          # Page components
│   │   ├── services/       # API services
│   │   ├── context/        # React context
│   │   ├── styles/         # Modular CSS files
│   │   └── utils/          # Utility functions
│   └── public/             # Static assets
└── docker-compose.yml      # MongoDB container
```

## Backend Structure
- **Controllers**: REST endpoints for tasks and authentication
- **Models**: Entity classes with MongoDB annotations
- **Repositories**: Data access interfaces extending MongoRepository
- **Services**: Business logic organized into specialized services
- **Security**: JWT configuration and authentication filters
- **DTOs**: Data transfer objects for API requests/responses

## Frontend Structure
- **Components**: Reusable UI components
- **Pages**: Main page components with routing
- **Services**: API communication layer
- **Context**: React context for state management
- **Styles**: Modular CSS files organized by functionality
- **Utils**: Helper functions and utilities

## Related Documents
- [Overview](overview.md) - Project overview and architecture
- [Features](features.md) - Key features implemented
- [API](api.md) - API endpoints documentation
- [Guidelines](guidelines.md) - Development guidelines and preferences
- [Status](status.md) - Current status and next steps
- [Dependencies](dependencies.md) - Project dependencies 