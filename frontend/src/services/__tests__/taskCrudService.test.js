import axios from 'axios';
import { getTasks, createTask, updateTask, deleteTask, restoreTask } from '../../services/taskCrudService';

jest.mock('axios', () => ({
  __esModule: true,
  default: {
    get: jest.fn(),
    post: jest.fn(),
    put: jest.fn(),
    delete: jest.fn(),
  },
}));

const token = 'test.jwt.token';

beforeEach(() => {
  localStorage.setItem('token', token);
  jest.clearAllMocks();
});

test('getTasks builds query and returns data', async () => {
  axios.get.mockResolvedValue({ data: [{ id: '1' }] });
  const data = await getTasks({ page: 1, size: 10, completed: true, deleted: false, category: 'Work', recurrence: 'recurrent' });
  expect(axios.get).toHaveBeenCalled();
  expect(Array.isArray(data)).toBe(true);
});

test('createTask posts body and returns data', async () => {
  axios.post.mockResolvedValue({ data: { id: 't1' } });
  const data = await createTask({ description: 'Test' });
  expect(axios.post).toHaveBeenCalledWith(expect.stringContaining('/tasks'), expect.any(Object), expect.any(Object));
  expect(data.id).toBe('t1');
});

test('updateTask puts body and returns data', async () => {
  axios.put.mockResolvedValue({ data: { id: 't1', description: 'X' } });
  const data = await updateTask('t1', { description: 'X' });
  expect(axios.put).toHaveBeenCalledWith(expect.stringContaining('/tasks/t1'), expect.any(Object), expect.any(Object));
  expect(data.id).toBe('t1');
});

test('deleteTask deletes and returns data', async () => {
  axios.delete.mockResolvedValue({ data: {} });
  const data = await deleteTask('t1');
  expect(axios.delete).toHaveBeenCalledWith(expect.stringContaining('/tasks/t1'), expect.any(Object));
  expect(data).toEqual({});
});

test('restoreTask posts and returns data', async () => {
  axios.post.mockResolvedValue({ data: { id: 't1' } });
  const data = await restoreTask('t1');
  expect(axios.post).toHaveBeenCalledWith(expect.stringContaining('/tasks/t1/restore'), {}, expect.any(Object));
  expect(data.id).toBe('t1');
});


