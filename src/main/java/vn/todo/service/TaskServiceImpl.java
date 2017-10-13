package vn.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.todo.domain.Task;
import vn.todo.repository.TaskRepository;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;
import static vn.todo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task update(Task task, int todoId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(task, todoId), task.getId());
    }

    @Override
    public Task create(Task task, int todoId) {
        Assert.notNull(task, "tasks must not be null");
        return repository.save(task, todoId);
    }

    @Override
    public void delete(int id, int todoId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, todoId), id);
    }

    @Override
    public Task get(int id, int todoId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, todoId), id);
    }

    @Override
    public List<Task> getAll(int todoId) {
        return repository.getAll(todoId);
    }
}