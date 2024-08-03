import React from "react";
import './styles.css'; // Stil dosyasını import edin

const TodoList = ({ todos, toggleTodo, editTodo, deleteTodo, deleteCompletedTodos, deleteAllTodos, setFilter }) => {
  const handleEdit = (id) => {
    const newTask = prompt("Enter new task:");
    if (newTask) {
      editTodo(id, newTask);
    }
  };

  return (
    <div className="todo-list-container">
      <h2>TodoList</h2> {/* "TodoList" başlığı */}
      <div className="filter-buttons">
        <button className="filter" onClick={() => setFilter('all')}>All</button>
        <button className="filter" onClick={() => setFilter('done')}>Done</button>
        <button className="filter" onClick={() => setFilter('todo')}>Todo</button>
      </div>
      <ul className="todo-list">
        {todos.length > 0 ? (
          todos.map((todo) => (
            <li key={todo.id} className={`todo-item ${todo.completed ? 'completed' : ''}`}>
              <input
                type="checkbox"
                checked={todo.completed}
                onChange={() => toggleTodo(todo.id)}
              />
              <span>{todo.task}</span>
              <div className="todo-actions">
                <button className="edit" onClick={() => handleEdit(todo.id)}>
                  <i className="fas fa-edit"></i> {/* Edit simgesi */}
                </button>
                <button className="delete" onClick={() => deleteTodo(todo.id)}>
                  <i className="fas fa-trash"></i> {/* Çöp kutusu simgesi */}
                </button>
              </div>
            </li>
          ))
        ) : (
          <li>No todos available</li> /* Eğer liste boşsa bu mesajı gösterir */
        )}
      </ul>
      <div className="delete-buttons">
        <button className="delete-tasks" onClick={() => deleteCompletedTodos()}>
          Delete done tasks
        </button>
        <button className="delete-tasks" onClick={() => deleteAllTodos()}>
          Delete all tasks
        </button>
      </div>
    </div>
  );
};

export default TodoList;


