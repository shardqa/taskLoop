import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const getAuthHeaders = () => {
  const token = localStorage.getItem('token');
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export const toggleTaskCompletion = async (taskId, completed) => {
  const response = await axios.post(`${API_URL}/tasks/${taskId}/toggle-completion`, { 
    completed 
  }, { 
    headers: getAuthHeaders() 
  });
  return response.data;
};

export const reorderTasks = async (taskIds) => {
  const response = await axios.put(`${API_URL}/tasks/reorder`, taskIds, { 
    headers: getAuthHeaders() 
  });
  return response.data;
}; 