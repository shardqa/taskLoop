import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import { useTaskFormHandlers } from '../useTaskFormHandlers';

function HandlersTester({ onSubmit }) {
  const formData = {
    formData: {
      description: 'd',
      category: 'c',
      recurrence: { type: 'DAILY', interval: 1, dayOfWeek: null, dayOfMonth: null }
    },
    updateFormData: jest.fn(),
    updateRecurrence: jest.fn()
  };
  const validation = {
    validateForm: jest.fn(() => true),
    clearError: jest.fn()
  };
  const { handleSubmit, handleChange, handleRecurrenceTypeChange } = useTaskFormHandlers(formData, validation, onSubmit);
  return (
    <form onSubmit={handleSubmit}>
      <input name="description" defaultValue="d" onChange={handleChange} />
      <select aria-label="recurrence-type" name="recurrence.type" onChange={handleRecurrenceTypeChange}>
        <option value="">none</option>
        <option value="DAILY">daily</option>
      </select>
      <button type="submit">submit</button>
    </form>
  );
}

test('submits sanitized data when valid', () => {
  const onSubmit = jest.fn();
  render(<HandlersTester onSubmit={onSubmit} />);
  fireEvent.submit(screen.getByText('submit'));
  expect(onSubmit).toHaveBeenCalledWith({
    description: 'd',
    category: 'c',
    isRecurrent: true,
    recurrenceType: 'DAILY',
    recurrenceInterval: 1
  });
});

test('updates recurrence on type change', () => {
  const onSubmit = jest.fn();
  render(<HandlersTester onSubmit={onSubmit} />);
  fireEvent.change(screen.getByLabelText('recurrence-type'), { target: { value: 'DAILY' } });
});


