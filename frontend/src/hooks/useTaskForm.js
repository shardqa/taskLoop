import { useState } from 'react';
import { createTask, updateTask } from '../services/taskService';

export const useTaskForm = (loadTasks, setError) => {
  const [showForm, setShowForm] = useState(false);
  const [editingTask, setEditingTask] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleCreateTask = async (taskData) => {
    setIsSubmitting(true);
    
    try {
      await createTask(taskData);
      setShowForm(false);
      return true;
    } catch (err) {
      setError('Failed to create task');
      console.error('Error creating task:', err);
      return false;
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleUpdateTask = async (taskData) => {
    setIsSubmitting(true);
    
    try {
      await updateTask(editingTask.id, taskData);
      setShowForm(false);
      setEditingTask(null);
      return true;
    } catch (err) {
      setError('Failed to update task');
      console.error('Error updating task:', err);
      return false;
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleEditTask = (task) => {
    setEditingTask(task);
    setShowForm(true);
  };

  const handleCancelForm = () => {
    setShowForm(false);
    setEditingTask(null);
  };

  return {
    showForm,
    editingTask,
    isSubmitting,
    handleCreateTask,
    handleUpdateTask,
    handleEditTask,
    handleCancelForm,
    setShowForm
  };
}; 