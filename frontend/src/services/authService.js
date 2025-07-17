import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const authService = {
  setToken(token) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  },

  removeToken() {
    delete axios.defaults.headers.common['Authorization'];
  },

  async login(email, password) {
    return axios.post(`${API_URL}/auth/login`, { email, password });
  },

  async register(email, password) {
    return axios.post(`${API_URL}/auth/register`, { email, password });
  }
};

export { authService }; 