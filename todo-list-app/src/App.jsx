import React, { useState } from 'react';
import TodoInput from './TodoInput';
import TodoList from './TodoList';
import './App.css';

function App() {
  const [todos, setTodos] = useState([]);
  const [filter, setFilter] = useState('all');

  const addTodo = (newTodo) => {
    // Aynı isimde bir görev olup olmadığını kontrol et
    const isDuplicate = todos.some((todo) => todo.task.toLowerCase() === newTodo.task.toLowerCase());
    
    if (isDuplicate) {
      alert("Bu isimde bir görev zaten mevcut. Lütfen farklı bir isim seçin.");
      return;
    }

    newTodo.id = new Date().getTime();
    setTodos([...todos, newTodo]);
  };

  const toggleTodo = (id) => {
    setTodos(
      todos.map((todo) => (todo.id === id ? { ...todo, completed: !todo.completed } : todo))
    );
  };

  const editTodo = (id, newTask) => {
    setTodos(
      todos.map((todo) => (todo.id === id ? { ...todo, task: newTask } : todo))
    );
  };

  const deleteTodo = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  const deleteCompletedTodos = () => {
    setTodos(todos.filter((todo) => !todo.completed));
  };

  const deleteAllTodos = () => {
    setTodos([]);
  };

  const filteredTodos = todos.filter((todo) => {
    if (filter === 'done') return todo.completed;
    if (filter === 'todo') return !todo.completed;
    return true;
  });

  return (
    <div className="App">
      <h1>TodoInput</h1>
      <TodoInput addTodo={addTodo} />
      <TodoList
        todos={filteredTodos}
        toggleTodo={toggleTodo}
        editTodo={editTodo}
        deleteTodo={deleteTodo}
        setFilter={setFilter}
        deleteCompletedTodos={deleteCompletedTodos}
        deleteAllTodos={deleteAllTodos}
      />
    </div>
  );
}

export default App;
