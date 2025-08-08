import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

let unauthorizedInterceptorId = null;

const authService = {
  setToken(token) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  },

  removeToken() {
    // Remove default auth header
    delete axios.defaults.headers.common['Authorization'];
  },

  async login(email, password) {
    return axios.post(`${API_URL}/auth/login`, { email, password });
  },

  async register(name, email, password) {
    return axios.post(`${API_URL}/auth/register`, { name, email, password });
  },

  setupInterceptors(onUnauthorized) {
    // Eject existing interceptor to avoid duplicates
    if (unauthorizedInterceptorId !== null) {
      axios.interceptors.response.eject(unauthorizedInterceptorId);
      unauthorizedInterceptorId = null;
    }

    unauthorizedInterceptorId = axios.interceptors.response.use(
      (response) => response,
      (error) => {
        const status = error?.response?.status;
        if (status === 401 || status === 403) {
          try {
            // Clear token from defaults and storage
            delete axios.defaults.headers.common['Authorization'];
            localStorage.removeItem('token');
            localStorage.removeItem('userEmail');
          } catch (_) {}
          if (typeof onUnauthorized === 'function') {
            onUnauthorized();
          }
        }
        return Promise.reject(error);
      }
    );
  }
};

export { authService }; 