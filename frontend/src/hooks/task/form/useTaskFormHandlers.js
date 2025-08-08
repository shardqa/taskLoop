export const useTaskFormHandlers = (formData, validation, onSubmit) => {
  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!validation.validateForm()) {
      return;
    }
    
    const submitData = {
      description: formData.formData.description,
      category: formData.formData.category,
      isRecurrent: formData.formData.recurrence.type ? true : false,
      recurrenceType: formData.formData.recurrence.type,
      recurrenceInterval: formData.formData.recurrence.interval
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