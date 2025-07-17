import { useState, useCallback } from 'react';
import { getTasks, deleteTask, restoreTask, toggleTaskCompletion } from '../services/taskService';

export const useTaskData = (setError) => {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadTasks = useCallback(async (filters, pagination) => {
    setLoading(true);
    setError(null);
    
    try {
      const params = {
        page: pagination.page,
        size: pagination.size,
        completed: filters.showCompleted ? null : false,
        deleted: filters.showDeleted,
        category: filters.category || null,
        recurrence: filters.recurrence || null
      };
      
      const response = await getTasks(params);
      setTasks(response.content);
      return {
        totalPages: response.totalPages,
        totalElements: response.totalElements
      };
    } catch (err) {
      setError('Failed to load tasks');
      console.error('Error loading tasks:', err);
      return { totalPages: 0, totalElements: 0 };
    } finally {
      setLoading(false);
    }
  }, [setError]);

  const handleToggleComplete = async (taskId, completed) => {
    try {
      await toggleTaskCompletion(taskId, completed);
      return true;
    } catch (err) {
      setError('Failed to update task completion');
      console.error('Error toggling task completion:', err);
      return false;
    }
  };

  const handleDeleteTask = async (taskId) => {
    try {
      await deleteTask(taskId);
      return true;
    } catch (err) {
      setError('Failed to delete task');
      console.error('Error deleting task:', err);
      return false;
    }
  };

  const handleRestoreTask = async (taskId) => {
    try {
      await restoreTask(taskId);
      return true;
    } catch (err) {
      setError('Failed to restore task');
      console.error('Error restoring task:', err);
      return false;
    }
  };

  return {
    tasks,
    loading,
    loadTasks,
    handleToggleComplete,
    handleDeleteTask,
    handleRestoreTask
  };
}; 