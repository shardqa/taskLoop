# API Endpoints

## Authentication Endpoints
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User authentication

## Task Management Endpoints
- `GET /api/tasks` - List tasks with pagination and filtering
- `POST /api/tasks` - Create new task
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Soft delete task
- `POST /api/tasks/{id}/restore` - Restore deleted task
- `POST /api/tasks/{id}/toggle-completion` - Toggle completion
- `DELETE /api/tasks/{id}/permanent` - Permanent deletion

## Task Query Endpoints
- `GET /api/tasks/category/{category}` - Get tasks by category
- `GET /api/tasks/recurrent` - Get recurrent tasks
- `GET /api/tasks/deleted` - Get deleted tasks

## Task Action Endpoints
- `PUT /api/tasks/reorder` - Reorder tasks
- `POST /api/tasks/{id}/complete` - Complete task

## Pagination Endpoints
- `GET /api/tasks/paginated` - Paginated task list
- `GET /api/tasks/completed/paginated` - Paginated completed tasks
- `GET /api/tasks/recurrent/paginated` - Paginated recurrent tasks
- `GET /api/tasks/category/{category}/paginated` - Paginated tasks by category

## Related Documents
- [Overview](overview.md) - Project overview and architecture
- [Features](features.md) - Key features implemented
- [Structure](structure.md) - Project structure and organization
- [Guidelines](guidelines.md) - Development guidelines and preferences
- [Status](status.md) - Current status and next steps
- [Dependencies](dependencies.md) - Project dependencies 