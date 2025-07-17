import React from 'react';

const TaskItemActions = ({ task, isDeleting, isRestoring, onEdit, onDelete, onRestore }) => {
  if (task.state.deleted) {
    return (
      <div className="task-actions">
        <button
          className="btn btn-success btn-sm"
          onClick={onRestore}
          disabled={isRestoring}
        >
          {isRestoring ? 'Restoring...' : 'Restore'}
        </button>
      </div>
    );
  }

  return (
    <div className="task-actions">
      <button
        className="btn btn-primary btn-sm"
        onClick={() => onEdit(task)}
      >
        Edit
      </button>
      <button
        className="btn btn-danger btn-sm"
        onClick={onDelete}
        disabled={isDeleting}
      >
        {isDeleting ? 'Deleting...' : 'Delete'}
      </button>
    </div>
  );
};

export default TaskItemActions; 