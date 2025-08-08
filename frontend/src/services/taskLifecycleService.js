import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const getAuthHeaders = () => {
  const token = localStorage.getItem('token');
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export const getDeletedTasks = async (params = {}) => {
  const queryParams = new URLSearchParams();
  
  if (params.page !== undefined) queryParams.append('page', params.page);
  if (params.size !== undefined) queryParams.append('size', params.size);
  
  const url = `${API_URL}/tasks/deleted${queryParams.toString() ? `?${queryParams.toString()}` : ''}`;
  const response = await axios.get(url, { headers: getAuthHeaders() });
  return response.data;
};

export const permanentlyDeleteTask = async (taskId) => {
  const response = await axios.delete(`${API_URL}/tasks/${taskId}/permanent`, { 
    headers: getAuthHeaders() 
  });
  return response.data;
}; 