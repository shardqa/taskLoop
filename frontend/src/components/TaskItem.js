import React from 'react';
import TaskItemContent from './TaskItemContent';
import TaskItemActions from './TaskItemActions';
import { useTaskItemHandlers } from '../hooks/useTaskItemHandlers';

const TaskItem = ({ task, onToggleComplete, onEdit, onDelete, onRestore }) => {
  const {
    isDeleting,
    isRestoring,
    handleToggleComplete,
    handleDelete,
    handleRestore
  } = useTaskItemHandlers(task, onToggleComplete, onDelete, onRestore);

  return (
    <div className={`task-item ${task.recurrence ? 'task-recurrent' : 'task-normal'}`}>
      <TaskItemContent
        task={task}
        onToggleComplete={handleToggleComplete}
      />
      
      <TaskItemActions
        task={task}
        isDeleting={isDeleting}
        isRestoring={isRestoring}
        onEdit={onEdit}
        onDelete={handleDelete}
        onRestore={handleRestore}
      />
    </div>
  );
};

export default TaskItem; 