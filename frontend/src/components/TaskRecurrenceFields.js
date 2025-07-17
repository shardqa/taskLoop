import React from 'react';

const TaskRecurrenceFields = ({ formData, errors, isSubmitting, onChange }) => {
  if (!formData.recurrence.type) return null;

  return (
    <div className="recurrence-fields">
      <div className="form-group">
        <label htmlFor="interval">Interval</label>
        <input
          type="number"
          id="interval"
          name="recurrence.interval"
          className={`form-control ${errors.interval ? 'is-invalid' : ''}`}
          value={formData.recurrence.interval}
          onChange={onChange}
          min="1"
          disabled={isSubmitting}
        />
        {errors.interval && <div className="invalid-feedback">{errors.interval}</div>}
      </div>

      {formData.recurrence.type === 'WEEKLY' && (
        <div className="form-group">
          <label htmlFor="dayOfWeek">Day of Week</label>
          <select
            id="dayOfWeek"
            name="recurrence.dayOfWeek"
            className={`form-control ${errors.dayOfWeek ? 'is-invalid' : ''}`}
            value={formData.recurrence.dayOfWeek || ''}
            onChange={onChange}
            disabled={isSubmitting}
          >
            <option value="">Select day</option>
            <option value="0">Sunday</option>
            <option value="1">Monday</option>
            <option value="2">Tuesday</option>
            <option value="3">Wednesday</option>
            <option value="4">Thursday</option>
            <option value="5">Friday</option>
            <option value="6">Saturday</option>
          </select>
          {errors.dayOfWeek && <div className="invalid-feedback">{errors.dayOfWeek}</div>}
        </div>
      )}

      {formData.recurrence.type === 'MONTHLY' && (
        <div className="form-group">
          <label htmlFor="dayOfMonth">Day of Month</label>
          <select
            id="dayOfMonth"
            name="recurrence.dayOfMonth"
            className={`form-control ${errors.dayOfMonth ? 'is-invalid' : ''}`}
            value={formData.recurrence.dayOfMonth || ''}
            onChange={onChange}
            disabled={isSubmitting}
          >
            <option value="">Select day</option>
            {Array.from({ length: 31 }, (_, i) => i + 1).map(day => (
              <option key={day} value={day}>{day}</option>
            ))}
          </select>
          {errors.dayOfMonth && <div className="invalid-feedback">{errors.dayOfMonth}</div>}
        </div>
      )}
    </div>
  );
};

export default TaskRecurrenceFields; 