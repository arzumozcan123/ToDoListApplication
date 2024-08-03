import React from 'react';

const DeleteButtons = ({ deleteCompletedTodos, deleteAllTodos }) => {
  return (
    <div>
      <button onClick={deleteCompletedTodos}>Delete done tasks</button>
      <button onClick={deleteAllTodos}>Delete all tasks</button>
    </div>
  );
};

export default DeleteButtons;
