import React, { useState } from "react";

function TodoInput({ addTodo }) {
  const [newTask, setNewTask] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (newTask.trim() === '') return;
    addTodo({
      id: Date.now(),
      task: newTask,
      completed: false
    });
    setNewTask('');
  };

  return (
    <form className="todo-input" onSubmit={handleSubmit}>
      <input
        type="text"
        value={newTask}
        onChange={(e) => setNewTask(e.target.value)}
        placeholder="New Todo"
      />
      <button type="submit">Add new task</button>
    </form>
  );
}

export default TodoInput;

