import React from 'react';
import { TaskItem } from '../item';

const TaskListContent = ({ 
  tasks, 
  loading, 
  pagination, 
  onToggleComplete, 
  onEdit, 
  onDelete, 
  onRestore, 
  onPageChange 
}) => {
  if (loading) {
    return <div className="loading">Loading...</div>;
  }

  if (tasks.length === 0) {
    return (
      <div className="card">
        <div className="empty-state">
          <p>No tasks found. Create your first task to get started!</p>
        </div>
      </div>
    );
  }

  return (
    <>
      <div className="task-list">
        {tasks.map(task => (
          <TaskItem
            key={task.id}
            task={task}
            onToggleComplete={onToggleComplete}
            onEdit={onEdit}
            onDelete={onDelete}
            onRestore={onRestore}
          />
        ))}
      </div>

      {pagination.totalPages > 1 && (
        <div className="pagination">
          <button
            className="btn btn-secondary"
            onClick={() => onPageChange(pagination.page - 1)}
            disabled={pagination.page === 0}
          >
            Previous
          </button>
          
          <span className="page-info">
            Page {pagination.page + 1} of {pagination.totalPages}
          </span>
          
          <button
            className="btn btn-secondary"
            onClick={() => onPageChange(pagination.page + 1)}
            disabled={pagination.page >= pagination.totalPages - 1}
          >
            Next
          </button>
        </div>
      )}
    </>
  );
};

export default TaskListContent; 