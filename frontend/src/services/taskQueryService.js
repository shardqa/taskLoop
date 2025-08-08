import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const getAuthHeaders = () => {
  const token = localStorage.getItem('token');
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export const getTasksByCategory = async (category, params = {}) => {
  const queryParams = new URLSearchParams();
  
  if (params.page !== undefined) queryParams.append('page', params.page);
  if (params.size !== undefined) queryParams.append('size', params.size);
  
  const url = `${API_URL}/tasks/category/${category}${queryParams.toString() ? `?${queryParams.toString()}` : ''}`;
  const response = await axios.get(url, { headers: getAuthHeaders() });
  return response.data;
};

export const getRecurrentTasks = async (params = {}) => {
  const queryParams = new URLSearchParams();
  
  if (params.page !== undefined) queryParams.append('page', params.page);
  if (params.size !== undefined) queryParams.append('size', params.size);
  
  const url = `${API_URL}/tasks/recurrent${queryParams.toString() ? `?${queryParams.toString()}` : ''}`;
  const response = await axios.get(url, { headers: getAuthHeaders() });
  return response.data;
}; 