package vn.todo.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vn.todo.domain.Task;
import vn.todo.service.TaskService;
import vn.todo.to.TaskTo;
import java.util.List;
import static vn.todo.util.ValidationUtil.*;

public abstract class AbstractTaskController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskService service;

    public Task get(int id, int todoId) {
        log.info("get task {} for todoId={}", id, todoId);
        return service.get(id, todoId);
    }

    public void delete(int id, int todoId) {
        log.info("delete task {} for todoId={}", id, todoId);
        service.delete(id, todoId);
    }

    public List<Task> getAll(int todoId) {
        log.info("getAll for todoId={}", todoId);
        return service.getAll(todoId);
    }

    public Task create(Task task, int todoId) {
        log.info("create {} for todoId={}", task, todoId);
        checkNew(task);
        return service.create(task, todoId);
    }

    public void update(Task task, int taskId, int todoId) {
        log.info("update {} with id={} for todoId={}", task, todoId);
        assureIdConsistent(task, taskId);
        service.update(task, todoId);
    }

    public void update(TaskTo taskTo, int taskId, int todoId) {
        log.info("update {} with id={} for todoId={}", taskTo,todoId);
        assureIdConsistent(taskTo, taskId);
        service.update(taskTo, todoId);
    }

    public void complete(int todoId, int taskId, boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + taskId);
        service.complete(todoId, taskId, enabled);
    }
}