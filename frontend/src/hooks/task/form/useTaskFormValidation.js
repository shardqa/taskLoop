import { useState } from 'react';

export const useTaskFormValidation = (formData) => {
  const [errors, setErrors] = useState({});

  const validateForm = () => {
    const newErrors = {};
    
    if (!formData.description.trim()) {
      newErrors.description = 'Description is required';
    }
    
    if (formData.recurrence.type && formData.recurrence.interval < 1) {
      newErrors.interval = 'Interval must be at least 1';
    }
    
    if (formData.recurrence.type === 'WEEKLY' && formData.recurrence.dayOfWeek === null) {
      newErrors.dayOfWeek = 'Day of week is required for weekly recurrence';
    }
    
    if (formData.recurrence.type === 'MONTHLY' && formData.recurrence.dayOfMonth === null) {
      newErrors.dayOfMonth = 'Day of month is required for monthly recurrence';
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const clearError = (fieldName) => {
    if (errors[fieldName]) {
      setErrors(prev => ({
        ...prev,
        [fieldName]: ''
      }));
    }
  };

  return {
    errors,
    validateForm,
    clearError
  };
}; 