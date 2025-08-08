export const useTaskItemUtils = () => {
  const getRecurrenceText = (recurrence) => {
    if (!recurrence || !recurrence.type) return null;
    
    const { type, interval, dayOfWeek, dayOfMonth } = recurrence;
    
    switch (type) {
      case 'DAILY':
        return interval > 1 ? `Every ${interval} days` : 'Daily';
      case 'WEEKLY':
        if (dayOfWeek) {
          const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
          return `Weekly on ${days[dayOfWeek]}`;
        }
        return interval > 1 ? `Every ${interval} weeks` : 'Weekly';
      case 'MONTHLY':
        if (dayOfMonth) {
          return `Monthly on day ${dayOfMonth}`;
        }
        return interval > 1 ? `Every ${interval} months` : 'Monthly';
      case 'YEARLY':
        return interval > 1 ? `Every ${interval} years` : 'Yearly';
      default:
        return null;
    }
  };

  const getCategoryColor = (category) => {
    const colors = {
      'work': '#007bff',
      'personal': '#28a745',
      'health': '#dc3545',
      'finance': '#ffc107',
      'education': '#17a2b8',
      'default': '#6c757d'
    };
    return colors[category?.toLowerCase()] || colors.default;
  };

  return {
    getRecurrenceText,
    getCategoryColor
  };
}; 