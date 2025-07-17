import React from 'react';

const TaskListFilters = ({ filters, onFilterChange, tasks }) => {
  const getFilteredTasksCount = () => {
    if (filters.showDeleted) {
      return tasks.filter(task => task.state.deleted).length;
    }
    return tasks.filter(task => !task.state.deleted).length;
  };

  const getCompletedTasksCount = () => {
    return tasks.filter(task => task.state.completed && !task.state.deleted).length;
  };

  const getPendingTasksCount = () => {
    return tasks.filter(task => !task.state.completed && !task.state.deleted).length;
  };

  return (
    <>
      <div className="task-stats">
        <div className="stat-item">
          <span className="stat-label">Total:</span>
          <span className="stat-value">{getFilteredTasksCount()}</span>
        </div>
        <div className="stat-item">
          <span className="stat-label">Completed:</span>
          <span className="stat-value">{getCompletedTasksCount()}</span>
        </div>
        <div className="stat-item">
          <span className="stat-label">Pending:</span>
          <span className="stat-value">{getPendingTasksCount()}</span>
        </div>
      </div>

      <div className="task-filters">
        <div className="filter-group">
          <label>
            <input
              type="checkbox"
              checked={filters.showCompleted}
              onChange={(e) => onFilterChange('showCompleted', e.target.checked)}
            />
            Show completed tasks
          </label>
        </div>
        
        <div className="filter-group">
          <label>
            <input
              type="checkbox"
              checked={filters.showDeleted}
              onChange={(e) => onFilterChange('showDeleted', e.target.checked)}
            />
            Show deleted tasks
          </label>
        </div>
        
        <div className="filter-group">
          <select
            value={filters.category}
            onChange={(e) => onFilterChange('category', e.target.value)}
            className="form-control"
          >
            <option value="">All categories</option>
            <option value="work">Work</option>
            <option value="personal">Personal</option>
            <option value="health">Health</option>
            <option value="finance">Finance</option>
            <option value="education">Education</option>
          </select>
        </div>
        
        <div className="filter-group">
          <select
            value={filters.recurrence}
            onChange={(e) => onFilterChange('recurrence', e.target.value)}
            className="form-control"
          >
            <option value="">All tasks</option>
            <option value="recurrent">Recurrent only</option>
            <option value="non-recurrent">Non-recurrent only</option>
          </select>
        </div>
      </div>
    </>
  );
};

export default TaskListFilters; 