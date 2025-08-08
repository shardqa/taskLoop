import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const getAuthHeaders = () => {
  const token = localStorage.getItem('token');
  console.log('Token from localStorage:', token);
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export const getTasks = async (params = {}) => {
  const queryParams = new URLSearchParams();
  
  if (params.page !== undefined) queryParams.append('page', params.page);
  if (params.size !== undefined) queryParams.append('size', params.size);
  if (params.completed !== undefined && params.completed !== null) queryParams.append('completed', params.completed);
  if (params.deleted !== undefined) queryParams.append('deleted', params.deleted);
  if (params.category) queryParams.append('category', params.category);
  if (params.recurrence) queryParams.append('recurrence', params.recurrence);
  
  const url = `${API_URL}/tasks${queryParams.toString() ? `?${queryParams.toString()}` : ''}`;
  const response = await axios.get(url, { headers: getAuthHeaders() });
  return response.data;
};

export const createTask = async (taskData) => {
  const response = await axios.post(`${API_URL}/tasks`, taskData, { 
    headers: getAuthHeaders() 
  });
  return response.data;
};

export const updateTask = async (taskId, taskData) => {
  const response = await axios.put(`${API_URL}/tasks/${taskId}`, taskData, { 
    headers: getAuthHeaders() 
  });
  return response.data;
};

export const deleteTask = async (taskId) => {
  const response = await axios.delete(`${API_URL}/tasks/${taskId}`, { 
    headers: getAuthHeaders() 
  });
  return response.data;
};

export const restoreTask = async (taskId) => {
  const response = await axios.post(`${API_URL}/tasks/${taskId}/restore`, {}, { 
    headers: getAuthHeaders() 
  });
  return response.data;
}; 