import axios from 'axios';
import { toggleTaskCompletion, reorderTasks } from '../../services/taskActionService';

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

test('toggleTaskCompletion posts payload and returns data', async () => {
  axios.post.mockResolvedValue({ data: { id: 't1', state: { completed: true } } });
  const data = await toggleTaskCompletion('t1', true);
  expect(axios.post).toHaveBeenCalledWith(expect.stringContaining('/tasks/t1/toggle-completion'), { completed: true }, expect.any(Object));
  expect(data.state.completed).toBe(true);
});

test('reorderTasks puts array and returns data', async () => {
  axios.put.mockResolvedValue({ data: {} });
  const data = await reorderTasks(['a', 'b']);
  expect(axios.put).toHaveBeenCalledWith(expect.stringContaining('/tasks/reorder'), ['a', 'b'], expect.any(Object));
  expect(data).toEqual({});
});


