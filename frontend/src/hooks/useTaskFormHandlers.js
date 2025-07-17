export const useTaskFormHandlers = (formData, validation, onSubmit) => {
  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!validation.validateForm()) {
      return;
    }
    
    const submitData = {
      ...formData.formData,
      recurrence: formData.formData.recurrence.type ? formData.formData.recurrence : null
    };
    
    onSubmit(submitData);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    
    if (name.startsWith('recurrence.')) {
      const field = name.split('.')[1];
      formData.updateRecurrence({
        [field]: value === '' ? null : value
      });
    } else {
      formData.updateFormData({
        [name]: value
      });
    }
    
    validation.clearError(name);
  };

  const handleRecurrenceTypeChange = (e) => {
    const type = e.target.value || null;
    formData.updateRecurrence({
      type,
      interval: 1,
      dayOfWeek: null,
      dayOfMonth: null
    });
  };

  return {
    handleSubmit,
    handleChange,
    handleRecurrenceTypeChange
  };
}; 