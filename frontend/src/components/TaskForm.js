import React from 'react';
import TaskFormModal from './TaskFormModal';
import TaskFormFields from './TaskFormFields';
import { useTaskFormState } from '../hooks/useTaskFormState';

const TaskForm = ({ task, onSubmit, onCancel, isSubmitting }) => {
  const {
    formData,
    errors,
    handleChange,
    handleRecurrenceTypeChange,
    handleSubmit
  } = useTaskFormState(task, onSubmit);

  return (
    <TaskFormModal onCancel={onCancel} isSubmitting={isSubmitting}>
      <TaskFormFields
        formData={formData}
        errors={errors}
        isSubmitting={isSubmitting}
        task={task}
        onCancel={onCancel}
        onChange={handleChange}
        onRecurrenceTypeChange={handleRecurrenceTypeChange}
        onSubmit={handleSubmit}
      />
    </TaskFormModal>
  );
};

export default TaskForm; 