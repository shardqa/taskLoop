# User Management Tasks

## 🔥 CRITICAL - User Management

- [x] 9. Create User model with MongoDB annotations (@Document, @Id, etc.)
- [x] 10. Create UserRepository interface extending MongoRepository
- [x] 11. Create UserService (register, findByEmail, password encoding)
- [x] 12. Create AuthController (register, login endpoints)
- [x] 13. Create DTOs (LoginRequest, RegisterRequest, AuthResponse)

## Development Notes
- Work through User Management (9-13) before Task Management
- User model includes email, password, and timestamps
- Password encoding uses BCrypt for security
- Auth endpoints handle registration and login

## API Endpoints
### Authentication
- POST /auth/register - User registration
- POST /auth/login - User login

## Database Schema
### User Collection
```json
{
  "_id": "ObjectId",
  "email": "user@email.com",
  "password": "hashed_password",
  "createdAt": "2025-01-01T00:00:00Z"
}
``` 