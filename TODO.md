# TODO - Task Management App

## 🔥 CRITICAL - Infrastructure Setup (Do First)
- [x] 1. Create Docker Compose file for MongoDB + MongoDB Express (admin UI)
- [x] 2. Configure MongoDB connection in application.properties
- [ ] 3. Add JWT dependencies to pom.xml (spring-boot-starter-security, jjwt)
- [ ] 4. Create project package structure (config/, model/, repository/, service/, controller/, dto/, security/)
- [ ] 5. Configure CORS for future frontend integration

## 🔥 CRITICAL - Security Foundation
- [ ] 6. Create JWT utility class (generate, validate, extract claims)
- [ ] 7. Configure Spring Security (disable CSRF, configure JWT filter)
- [ ] 8. Create security configuration (public endpoints, protected endpoints)

## 🔥 CRITICAL - User Management
- [ ] 9. Create User model with MongoDB annotations (@Document, @Id, etc.)
- [ ] 10. Create UserRepository interface extending MongoRepository
- [ ] 11. Create UserService (register, findByEmail, password encoding)
- [ ] 12. Create AuthController (register, login endpoints)
- [ ] 13. Create DTOs (LoginRequest, RegisterRequest, AuthResponse)

## 🟡 HIGH PRIORITY - Task Management Core
- [ ] 14. Create Task model with all fields (userId, description, isRecurrent, position, etc.)
- [ ] 15. Create TaskRepository with custom queries (findByUserIdOrderByPosition)
- [ ] 16. Create TaskService (CRUD + business logic for recurrent tasks)
- [ ] 17. Create TaskController (all REST endpoints)
- [ ] 18. Create Task DTOs (TaskRequest, TaskResponse)

## 🟡 HIGH PRIORITY - Business Logic
- [ ] 19. Implement task completion logic (normal tasks delete, recurrent go to end)
- [ ] 20. Implement task reordering (update positions)
- [ ] 21. Add input validation (@Valid, @NotBlank, etc.)
- [ ] 22. Create custom exceptions (UserNotFoundException, TaskNotFoundException)
- [ ] 23. Add global exception handler (@ControllerAdvice)

## 🟠 MEDIUM PRIORITY - Polish & Refinement
- [ ] 24. Add logging configuration (logback-spring.xml)
- [ ] 25. Create application configuration (@ConfigurationProperties)
- [ ] 26. Add API documentation (Swagger/OpenAPI)
- [ ] 27. Implement soft delete for tasks (optional)
- [ ] 28. Add pagination for task lists (optional)

## 🟢 FUTURE - Frontend & Beyond
- [ ] 29. Create React frontend (separate phase)
- [ ] 30. Mobile development (separate phase)
- [ ] 31. MCP integration (separate phase)

---

## Technical Decisions Made
- **Database**: MongoDB with Docker for development
- **Authentication**: JWT tokens (stateless, secure, mobile-ready)
- **Security**: Spring Security with JWT filter
- **Architecture**: Standard Spring Boot layered architecture
- **Testing**: TDD approach (tests written separately)

## Development Notes
- Tasks 1-5 are prerequisites for everything else
- Work through User Management (6-13) before Task Management (14-18)
- Each task is specific and actionable
- Focus on MVP functionality first, polish later
- Tests to be written following TDD principles

## API Endpoints (Reference)
### Authentication
- POST /auth/register - User registration
- POST /auth/login - User login

### Tasks
- GET /tasks - List user's tasks (ordered by position)
- POST /tasks - Create new task
- PUT /tasks/{id} - Update task
- DELETE /tasks/{id} - Delete task
- POST /tasks/{id}/complete - Mark task as completed
- PUT /tasks/reorder - Reorder tasks

## Database Schema (Reference)
### User Collection
```json
{
  "_id": "ObjectId",
  "email": "user@email.com",
  "password": "hashed_password",
  "createdAt": "2025-01-01T00:00:00Z"
}
```

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
