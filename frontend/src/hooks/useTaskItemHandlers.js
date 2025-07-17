import { useState } from 'react';

export const useTaskItemHandlers = (task, onToggleComplete, onDelete, onRestore) => {
  const [isDeleting, setIsDeleting] = useState(false);
  const [isRestoring, setIsRestoring] = useState(false);

  const handleToggleComplete = async () => {
    try {
      await onToggleComplete(task.id, !task.state.completed);
    } catch (error) {
      console.error('Error toggling task completion:', error);
    }
  };

  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete this task?')) {
      setIsDeleting(true);
      try {
        await onDelete(task.id);
      } catch (error) {
        console.error('Error deleting task:', error);
      } finally {
        setIsDeleting(false);
      }
    }
  };

  const handleRestore = async () => {
    setIsRestoring(true);
    try {
      await onRestore(task.id);
    } catch (error) {
      console.error('Error restoring task:', error);
    } finally {
      setIsRestoring(false);
    }
  };

  return {
    isDeleting,
    isRestoring,
    handleToggleComplete,
    handleDelete,
    handleRestore
  };
}; 