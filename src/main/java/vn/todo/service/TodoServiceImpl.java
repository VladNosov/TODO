package vn.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.todo.domain.Todo;
import vn.todo.repository.TodoRepository;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;
import static vn.todo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    @Autowired
    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo update(Todo todo, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(todo, userId), todo.getId());
    }

    @Override
    public Todo create(Todo todo, int userId) {
        Assert.notNull(todo, "todos must not be null");
        return repository.save(todo, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Todo get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Todo> getAll(int userId) {
        return repository.getAll(userId);
    }
}
