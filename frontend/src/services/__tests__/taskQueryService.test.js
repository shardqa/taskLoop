import axios from 'axios';
import { getTasksByCategory, getRecurrentTasks } from '../../services/taskQueryService';

jest.mock('axios', () => ({
  __esModule: true,
  default: {
    get: jest.fn(),
    post: jest.fn(),
    put: jest.fn(),
    delete: jest.fn(),
  },
}));

beforeEach(() => {
  localStorage.setItem('token', 'test.jwt.token');
  jest.clearAllMocks();
});

test('getTasksByCategory calls endpoint and returns data', async () => {
  axios.get.mockResolvedValue({ data: [{ id: 'c1' }] });
  const data = await getTasksByCategory('Work', { page: 0, size: 5 });
  expect(axios.get).toHaveBeenCalledWith(expect.stringContaining('/tasks/category/Work'), expect.any(Object));
  expect(data[0].id).toBe('c1');
});

test('getRecurrentTasks calls endpoint and returns data', async () => {
  axios.get.mockResolvedValue({ data: [{ id: 'r1' }] });
  const data = await getRecurrentTasks({ page: 0, size: 5 });
  expect(axios.get).toHaveBeenCalledWith(expect.stringContaining('/tasks/recurrent'), expect.any(Object));
  expect(data[0].id).toBe('r1');
});


