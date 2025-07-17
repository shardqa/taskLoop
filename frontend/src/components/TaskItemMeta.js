import React from 'react';
import { format } from 'date-fns';
import { useTaskItemUtils } from '../hooks/useTaskItemUtils';

const TaskItemMeta = ({ task }) => {
  const { getRecurrenceText, getCategoryColor } = useTaskItemUtils();

  return (
    <div className="task-meta">
      {task.category && (
        <span 
          className="task-category"
          style={{ backgroundColor: getCategoryColor(task.category) }}
        >
          {task.category}
        </span>
      )}
      
      {getRecurrenceText(task.recurrence) && (
        <span className="task-recurrence">
          🔄 {getRecurrenceText(task.recurrence)}
        </span>
      )}
      
      {task.metadata.createdAt && (
        <span className="task-date">
          Created: {format(new Date(task.metadata.createdAt), 'MMM dd, yyyy')}
        </span>
      )}
      
      {task.state.completed && task.metadata.completedAt && (
        <span className="task-completed-date">
          Completed: {format(new Date(task.metadata.completedAt), 'MMM dd, yyyy')}
        </span>
      )}
    </div>
  );
};

export default TaskItemMeta; 