import React from 'react';

const FilterButtons = ({ setFilter, currentFilter }) => {
  return (
    <div>
      <button onClick={() => setFilter('all')} className={currentFilter === 'all' ? 'active' : ''}>All</button>
      <button onClick={() => setFilter('done')} className={currentFilter === 'done' ? 'active' : ''}>Done</button>
      <button onClick={() => setFilter('todo')} className={currentFilter === 'todo' ? 'active' : ''}>Todo</button>
    </div>
  );
};

export default FilterButtons;
