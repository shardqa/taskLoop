import React from 'react';

const TaskFormModal = ({ children, onCancel, isSubmitting }) => {
  return (
    <div className="task-form-overlay">
      <div className="task-form-modal">
        <div className="card">
          {children}
        </div>
      </div>
    </div>
  );
};

export default TaskFormModal; 