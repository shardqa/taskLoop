import { useState, useEffect } from 'react';

export const useTaskFormData = (task) => {
  const [formData, setFormData] = useState({
    description: '',
    category: '',
    recurrence: {
      type: null,
      interval: 1,
      dayOfWeek: null,
      dayOfMonth: null
    }
  });

  useEffect(() => {
    if (task) {
      setFormData({
        description: task.description || '',
        category: task.category || '',
        recurrence: {
          type: task.recurrence?.type || null,
          interval: task.recurrence?.interval || 1,
          dayOfWeek: task.recurrence?.dayOfWeek || null,
          dayOfMonth: task.recurrence?.dayOfMonth || null
        }
      });
    }
  }, [task]);

  const updateFormData = (updates) => {
    setFormData(prev => ({
      ...prev,
      ...updates
    }));
  };

  const updateRecurrence = (updates) => {
    setFormData(prev => ({
      ...prev,
      recurrence: {
        ...prev.recurrence,
        ...updates
      }
    }));
  };

  return {
    formData,
    setFormData,
    updateFormData,
    updateRecurrence
  };
}; 