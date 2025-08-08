import { useState, useEffect, useCallback } from 'react';
import { useTaskData } from './useTaskData';
import { useTaskForm } from '../form';
import { useTaskFilters } from './useTaskFilters';
import { useTaskPagination } from './useTaskPagination';

export const useTaskList = () => {
  const [error, setError] = useState(null);
  
  const taskData = useTaskData(setError);
  const taskForm = useTaskForm(taskData.loadTasks, setError);
  const taskFilters = useTaskFilters();
  const taskPagination = useTaskPagination();
  
  const { loadTasks: loadTasksFromData, handleToggleComplete: toggleComplete, handleDeleteTask: deleteTask, handleRestoreTask: restoreTask } = taskData;
  const { filters } = taskFilters;
  const { pagination, updatePagination } = taskPagination;
  
  const loadTasks = useCallback(async () => {
    const result = await loadTasksFromData(filters, pagination);
    if (result) {
      updatePagination(result.totalPages, result.totalElements);
    }
  }, [loadTasksFromData, filters, pagination, updatePagination]);

  useEffect(() => {
    console.log('useTaskList: Carregando tarefas...');
    loadTasks();
  }, []);

  const handleCreateTask = async (taskData) => {
    const success = await taskForm.handleCreateTask(taskData);
    if (success) {
      loadTasks();
    }
  };

  const handleUpdateTask = async (taskData) => {
    const success = await taskForm.handleUpdateTask(taskData);
    if (success) {
      loadTasks();
    }
  };

  const handleToggleComplete = async (taskId, completed) => {
    const success = await toggleComplete(taskId, completed);
    if (success) {
      loadTasks();
    }
  };

  const handleDeleteTask = async (taskId) => {
    const success = await deleteTask(taskId);
    if (success) {
      loadTasks();
    }
  };

  const handleRestoreTask = async (taskId) => {
    const success = await restoreTask(taskId);
    if (success) {
      loadTasks();
    }
  };

  const handleFilterChange = (filterName, value) => {
    taskFilters.handleFilterChange(filterName, value);
    taskPagination.resetPage();
  };

  return {
    tasks: taskData.tasks,
    loading: taskData.loading,
    error,
    showForm: taskForm.showForm,
    editingTask: taskForm.editingTask,
    isSubmitting: taskForm.isSubmitting,
    filters: taskFilters.filters,
    pagination: taskPagination.pagination,
    handleCreateTask,
    handleUpdateTask,
    handleToggleComplete,
    handleDeleteTask,
    handleRestoreTask,
    handleEditTask: taskForm.handleEditTask,
    handleCancelForm: taskForm.handleCancelForm,
    handleFilterChange,
    handlePageChange: taskPagination.handlePageChange,
    setShowForm: taskForm.setShowForm,
    setError
  };
}; 