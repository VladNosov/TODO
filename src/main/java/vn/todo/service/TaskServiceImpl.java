package vn.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vn.todo.domain.Task;
import vn.todo.repository.TaskRepository;
import vn.todo.to.TaskTo;
import vn.todo.util.TaskUtil;
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
    public Task update(TaskTo taskTo, int todoId) throws NotFoundException {
        Task task = get(taskTo.getId(), todoId);
        return checkNotFoundWithId(repository.save(TaskUtil.updateFromTo(task, taskTo), todoId), task.getId());
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

    @Override
    public Task getWithTodo(int id, int todoId) {
        return checkNotFoundWithId(repository.getWithTodo(id, todoId), id);
    }

    @Override
    @Transactional
    public void complete(int todoId, int taskId, boolean enabled) {
        Task task = get(taskId, todoId);
        task.setComplete(enabled);
        repository.save(task, todoId);
    }
}