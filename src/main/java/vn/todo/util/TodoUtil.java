package vn.todo.util;

import vn.todo.domain.Todo;
import vn.todo.to.TodoTo;

public class TodoUtil {
    public static Todo createNewFromTo(TodoTo newTodo) {
        return new Todo(newTodo.getTitle());
    }

    public static Todo updateFromTo(Todo todo, TodoTo todoTo) {
        todo.setTitle(todoTo.getTitle());
        return todo;
    }
}