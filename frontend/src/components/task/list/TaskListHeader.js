import React from 'react';

const TaskListHeader = ({ onAddTask }) => {
  return (
    <div className="task-header">
      <h2>My Tasks</h2>
      <button 
        className="btn btn-primary"
        onClick={onAddTask}
      >
        Add New Task
      </button>
    </div>
  );
};

export default TaskListHeader; 