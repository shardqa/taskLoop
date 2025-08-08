# Task Management Core Tasks

## 🟡 HIGH PRIORITY - Task Management Core

- [x] 14. Create Task model with all fields (userId, description, isRecurrent, position, etc.)
- [x] 15. Create TaskRepository with custom queries (findByUserIdOrderByPosition)
- [x] 16. Create TaskService (CRUD + business logic for recurrent tasks)
- [x] 17. Create TaskController (all REST endpoints)
- [x] 18. Create Task DTOs (TaskRequest, TaskResponse)

## 🟡 HIGH PRIORITY - Business Logic

- [x] 19. Implement task completion logic (normal tasks delete, recurrent go to end)
- [x] 20. Implement task reordering (update positions)
- [x] 21. Add input validation (@Valid, @NotBlank, etc.)
- [x] 22. Create custom exceptions (UserNotFoundException, TaskNotFoundException)
- [x] 23. Add global exception handler (@ControllerAdvice)

## API Endpoints
### Tasks
- GET /tasks - List user's tasks (ordered by position)
- POST /tasks - Create new task
- PUT /tasks/{id} - Update task
- DELETE /tasks/{id} - Delete task
- POST /tasks/{id}/complete - Mark task as completed
- PUT /tasks/reorder - Reorder tasks

## Database Schema
### Task Collection
```json
{
  "_id": "ObjectId",
  "userId": "ObjectId_reference",
  "description": "Task description",
  "isRecurrent": true,
  "recurrenceType": "unlimited",
  "completed": false,
  "position": 1,
  "createdAt": "2025-01-01T00:00:00Z",
  "completedAt": null,
  "category": null
}
``` 