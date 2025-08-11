import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import { useTaskFilters } from '../useTaskFilters';

function FiltersTester() {
  const { filters, handleFilterChange } = useTaskFilters();
  return (
    <div>
      <span data-testid="category">{filters.category}</span>
      <span data-testid="showCompleted">{String(filters.showCompleted)}</span>
      <button onClick={() => handleFilterChange('category', 'work')}>setCategory</button>
      <button onClick={() => handleFilterChange('showCompleted', false)}>hideCompleted</button>
    </div>
  );
}

test('updates filters via handleFilterChange', () => {
  render(<FiltersTester />);
  expect(screen.getByTestId('category').textContent).toBe('');
  expect(screen.getByTestId('showCompleted').textContent).toBe('true');

  fireEvent.click(screen.getByText('setCategory'));
  expect(screen.getByTestId('category').textContent).toBe('work');

  fireEvent.click(screen.getByText('hideCompleted'));
  expect(screen.getByTestId('showCompleted').textContent).toBe('false');
});


