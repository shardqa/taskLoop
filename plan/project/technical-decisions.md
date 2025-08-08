# Key Technical Decisions & Patterns

## Backend Architecture

### Entity Design
- **Lombok Integration**: Used `@Data` and `@NoArgsConstructor` for entities
- **Composition over Inheritance**: TaskState and TaskMetadata as separate classes
- **Type Safety**: Created enums for RecurrenceType and TaskState
- **Builder Pattern**: Implemented TaskBuilder for complex object creation

### Service Layer Design
- **Single Responsibility**: Each service handles one specific domain
- **Dependency Injection**: Spring-managed services with clear interfaces
- **Transaction Management**: Proper transaction boundaries per service
- **Exception Handling**: Custom exceptions with meaningful error messages

### API Design
- **RESTful Conventions**: Consistent endpoint naming and HTTP methods
- **Pagination**: Standardized pagination with TaskPageRequest/Response
- **DTO Pattern**: Separate request/response objects from entities
- **Validation**: Input validation with proper error responses

## Frontend Architecture

### Component Design
- **Functional Components**: Modern React with hooks
- **Props Interface**: Clear prop definitions and validation
- **State Management**: Context API for global state (AuthContext)
- **Event Handling**: Consistent event handling patterns

### Styling Strategy
- **Modular CSS**: Separate files by functionality
- **Mobile-first**: Responsive design starting from mobile
- **CSS Variables**: Consistent theming and colors
- **Utility Classes**: Reusable utility classes for common patterns

### User Experience
- **Modal Forms**: Better UX for creation/editing tasks
- **Color Coding**: Visual organization with category colors
- **Loading States**: Visible loading indicators
- **Error Handling**: User-friendly error messages

## Data Flow Patterns

### Authentication Flow
- **JWT Tokens**: Stored in localStorage for persistence
- **Protected Routes**: PrivateRoute component for authentication
- **Token Refresh**: Automatic token validation and refresh
- **Logout Handling**: Proper cleanup on logout

### State Management
- **Local State**: Component-level state for UI interactions
- **Global State**: Context for authentication and user data
- **Service Layer**: API calls abstracted in service modules
- **Error Boundaries**: Graceful error handling

## Development Patterns

### Code Organization
- **Package Structure**: Clear package hierarchy by functionality
- **Naming Conventions**: Consistent naming across layers
- **Import Organization**: Logical import grouping
- **File Structure**: Predictable file organization

### Testing Strategy
- **Unit Tests**: Service layer testing with mocks
- **Integration Tests**: API endpoint testing
- **Component Tests**: React component testing
- **E2E Tests**: Full application flow testing 