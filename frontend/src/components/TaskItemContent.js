import React from 'react';
import TaskItemMeta from './TaskItemMeta';

const TaskItemContent = ({ task, onToggleComplete }) => {
  return (
    <div className="task-content">
      <div className="task-header">
        <div className="task-checkbox">
          <input
            type="checkbox"
            checked={task.state.completed}
            onChange={onToggleComplete}
            disabled={task.state.deleted}
          />
        </div>
        
        <div className="task-info">
          <h4 className={`task-title ${task.state.completed ? 'completed' : ''}`}>
            {task.description}
          </h4>
          
          <TaskItemMeta task={task} />
        </div>
      </div>
    </div>
  );
};

export default TaskItemContent; 