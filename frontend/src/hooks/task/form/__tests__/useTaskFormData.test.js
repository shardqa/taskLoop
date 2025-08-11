import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { useTaskFormData } from '../useTaskFormData';

function DataTester({ task }) {
  const { formData, updateFormData, updateRecurrence } = useTaskFormData(task);
  return (
    <div>
      <span data-testid="description">{formData.description}</span>
      <span data-testid="recurrenceType">{String(formData.recurrence.type)}</span>
      <span data-testid="recurrenceInterval">{String(formData.recurrence.interval)}</span>
      <button onClick={() => updateFormData({ description: 'abc' })}>updDesc</button>
      <button onClick={() => updateRecurrence({ type: 'DAILY', interval: 2 })}>updRec</button>
    </div>
  );
}

test('initializes and updates form data', () => {
  const task = { description: 't1', category: 'c1', recurrenceType: 'WEEKLY', recurrenceInterval: 1 };
  render(<DataTester task={task} />);
  expect(screen.getByTestId('description').textContent).toBe('t1');
  expect(screen.getByTestId('recurrenceType').textContent).toBe('WEEKLY');
  expect(screen.getByTestId('recurrenceInterval').textContent).toBe('1');
  fireEvent.click(screen.getByText('updDesc'));
  fireEvent.click(screen.getByText('updRec'));
  expect(screen.getByTestId('description').textContent).toBe('abc');
  expect(screen.getByTestId('recurrenceType').textContent).toBe('DAILY');
  expect(screen.getByTestId('recurrenceInterval').textContent).toBe('2');
});


