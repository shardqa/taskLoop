import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';

jest.mock('../useTaskData', () => ({
  useTaskData: () => ({
    tasks: [{ id: '1', description: 't1' }],
    loading: false,
    loadTasks: jest.fn(async () => ({ totalPages: 1, totalElements: 1 })),
    handleToggleComplete: jest.fn(async () => true),
    handleDeleteTask: jest.fn(async () => true),
    handleRestoreTask: jest.fn(async () => true)
  })
}));

jest.mock('../../../../services/taskService', () => ({
  createTask: jest.fn(async () => ({})),
  updateTask: jest.fn(async () => ({}))
}));

const { useTaskList } = require('../useTaskList');

function ListTester() {
  const hook = useTaskList();
  return (
    <div>
      <span data-testid="count">{hook.tasks.length}</span>
      <button onClick={() => hook.handleFilterChange('category', 'work')}>filter</button>
      <button onClick={() => hook.handlePageChange(1)}>page</button>
    </div>
  );
}

test('loads tasks and updates pagination', async () => {
  render(<ListTester />);
  await waitFor(() => expect(screen.getByTestId('count').textContent).toBe('1'));
  fireEvent.click(screen.getByText('filter'));
  fireEvent.click(screen.getByText('page'));
});


