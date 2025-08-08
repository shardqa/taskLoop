import { useTaskFormData } from './useTaskFormData';
import { useTaskFormValidation } from './useTaskFormValidation';
import { useTaskFormHandlers } from './useTaskFormHandlers';

export const useTaskFormState = (task, onSubmit) => {
  const formData = useTaskFormData(task);
  const validation = useTaskFormValidation(formData.formData);
  const handlers = useTaskFormHandlers(formData, validation, onSubmit);

  return {
    formData: formData.formData,
    errors: validation.errors,
    handleChange: handlers.handleChange,
    handleRecurrenceTypeChange: handlers.handleRecurrenceTypeChange,
    validateForm: validation.validateForm,
    handleSubmit: handlers.handleSubmit
  };
}; 