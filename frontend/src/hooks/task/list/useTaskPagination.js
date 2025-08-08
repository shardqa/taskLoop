import { useState } from 'react';

export const useTaskPagination = () => {
  const [pagination, setPagination] = useState({
    page: 0,
    size: 20,
    totalPages: 0,
    totalElements: 0
  });

  const handlePageChange = (newPage) => {
    setPagination(prev => ({ ...prev, page: newPage }));
  };

  const updatePagination = (totalPages, totalElements) => {
    setPagination(prev => ({
      ...prev,
      totalPages,
      totalElements
    }));
  };

  const resetPage = () => {
    setPagination(prev => ({ ...prev, page: 0 }));
  };

  return {
    pagination,
    handlePageChange,
    updatePagination,
    resetPage
  };
}; 