import React from "react";
import './styles.css'; // CSS dosyasını import ediyoruz

const TodoList = ({
  todos,
  toggleTodo,
  editTodo,
  deleteTodo,
  setFilter,
  deleteCompletedTodos,
  deleteAllTodos
}) => {
  return (
    <div className="todo-list-container">
      <h2>TodoList</h2>
      <div className="filter-buttons">
        <button onClick={() => setFilter('all')}>All</button>
        <button onClick={() => setFilter('done')}>Done</button>
        <button onClick={() => setFilter('todo')}>Todo</button>
      </div>
      <ul className="todo-list">
        {todos.length === 0 ? (
          <p>No todos available</p>
        ) : (
          todos.map((todo) => (
            <li key={todo.id} className={todo.completed ? "completed" : ""}>
              <input
                type="checkbox"
                checked={todo.completed}
                onChange={() => toggleTodo(todo.id)}
              />
              <span>{todo.task}</span>
              <div className="todo-actions">
                <button
                  className="edit-btn"
                  onClick={() => editTodo(todo.id, prompt("Edit todo:", todo.task))}
                >
                  Edit
                </button>
                <button
                  className="delete-btn"
                  onClick={() => deleteTodo(todo.id)}
                >
                  Delete
                </button>
              </div>
            </li>
          ))
        )}
      </ul>
      <div className="delete-buttons">
        <button className="delete-done-tasks" onClick={deleteCompletedTodos}>
          Delete done tasks
        </button>
        <button className="delete-all-tasks" onClick={deleteAllTodos}>
          Delete all tasks
        </button>
      </div>
    </div>
  );
};

export default TodoList;

