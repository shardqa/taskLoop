import React from 'react';
import TaskListHeader from './TaskListHeader';
import TaskListFilters from './TaskListFilters';
import TaskListContent from './TaskListContent';
import TaskForm from './TaskForm';
import { useTaskList } from '../hooks/useTaskList';

const TaskList = () => {
  const {
    tasks,
    loading,
    error,
    showForm,
    editingTask,
    isSubmitting,
    filters,
    pagination,
    handleCreateTask,
    handleUpdateTask,
    handleToggleComplete,
    handleDeleteTask,
    handleRestoreTask,
    handleEditTask,
    handleCancelForm,
    handleFilterChange,
    handlePageChange,
    setShowForm,
    setError
  } = useTaskList();

  if (loading && tasks.length === 0) {
    return (
      <div className="container">
        <div className="card">
          <div className="loading">Loading tasks...</div>
        </div>
      </div>
    );
  }

  return (
    <div className="container">
      <TaskListHeader 
        onAddTask={() => setShowForm(true)}
      />

      {error && (
        <div className="alert alert-danger">
          {error}
          <button 
            className="btn btn-link"
            onClick={() => setError(null)}
          >
            Dismiss
          </button>
        </div>
      )}

      <TaskListFilters 
        filters={filters}
        onFilterChange={handleFilterChange}
        tasks={tasks}
      />

      <TaskListContent
        tasks={tasks}
        loading={loading}
        pagination={pagination}
        onToggleComplete={handleToggleComplete}
        onEdit={handleEditTask}
        onDelete={handleDeleteTask}
        onRestore={handleRestoreTask}
        onPageChange={handlePageChange}
      />

      {showForm && (
        <TaskForm
          task={editingTask}
          onSubmit={editingTask ? handleUpdateTask : handleCreateTask}
          onCancel={handleCancelForm}
          isSubmitting={isSubmitting}
        />
      )}
    </div>
  );
};

export default TaskList; 