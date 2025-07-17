import { useState } from 'react';

export const useTaskFilters = () => {
  const [filters, setFilters] = useState({
    showCompleted: true,
    showDeleted: false,
    category: '',
    recurrence: ''
  });

  const handleFilterChange = (filterName, value) => {
    setFilters(prev => ({
      ...prev,
      [filterName]: value
    }));
  };

  return {
    filters,
    handleFilterChange
  };
}; 