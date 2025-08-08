import React from 'react';
import TaskRecurrenceFields from './TaskRecurrenceFields';

const TaskFormFields = ({
  formData,
  errors,
  isSubmitting,
  task,
  onCancel,
  onChange,
  onRecurrenceTypeChange,
  onSubmit
}) => {
  return (
    <>
      <h3>{task ? 'Edit Task' : 'Create New Task'}</h3>
      
      <form onSubmit={onSubmit}>
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <textarea
            id="description"
            name="description"
            className={`form-control ${errors.description ? 'is-invalid' : ''}`}
            value={formData.description}
            onChange={onChange}
            rows="3"
            disabled={isSubmitting}
            placeholder="Enter task description..."
          />
          {errors.description && <div className="invalid-feedback">{errors.description}</div>}
        </div>
        
        <div className="form-group">
          <label htmlFor="category">Category</label>
          <select
            id="category"
            name="category"
            className="form-control"
            value={formData.category}
            onChange={onChange}
            disabled={isSubmitting}
          >
            <option value="">No category</option>
            <option value="work">Work</option>
            <option value="personal">Personal</option>
            <option value="health">Health</option>
            <option value="finance">Finance</option>
            <option value="education">Education</option>
          </select>
        </div>
        
        <div className="form-group">
          <label htmlFor="recurrenceType">Recurrence</label>
          <select
            id="recurrenceType"
            name="recurrenceType"
            className="form-control"
            value={formData.recurrence.type || ''}
            onChange={onRecurrenceTypeChange}
            disabled={isSubmitting}
          >
            <option value="">No recurrence</option>
            <option value="DAILY">Daily</option>
            <option value="WEEKLY">Weekly</option>
            <option value="MONTHLY">Monthly</option>
            <option value="YEARLY">Yearly</option>
          </select>
        </div>
        
        <TaskRecurrenceFields
          formData={formData}
          errors={errors}
          isSubmitting={isSubmitting}
          onChange={onChange}
        />
        
        <div className="form-actions">
          <button type="button" className="btn btn-secondary" onClick={onCancel} disabled={isSubmitting}>
            Cancel
          </button>
          <button type="submit" className="btn btn-primary" disabled={isSubmitting}>
            {isSubmitting ? 'Saving...' : (task ? 'Update Task' : 'Create Task')}
          </button>
        </div>
      </form>
    </>
  );
};

export default TaskFormFields; 