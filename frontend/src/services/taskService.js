import { getTasks, createTask, updateTask, deleteTask, restoreTask } from './taskCrudService';
import { toggleTaskCompletion, reorderTasks } from './taskActionService';
import { getDeletedTasks, permanentlyDeleteTask } from './taskLifecycleService';
import { getTasksByCategory, getRecurrentTasks } from './taskQueryService';

export {
  getTasks,
  createTask,
  updateTask,
  deleteTask,
  restoreTask,
  toggleTaskCompletion,
  reorderTasks,
  getDeletedTasks,
  permanentlyDeleteTask,
  getTasksByCategory,
  getRecurrentTasks
}; 