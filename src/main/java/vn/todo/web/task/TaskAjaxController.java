package vn.todo.web.task;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.todo.domain.Task;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/todo")
public class TaskAjaxController extends AbstractTaskController {

    @Override
    @GetMapping(value = "/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAll(@PathVariable("todoId") int todoId) {
        return super.getAll(todoId);
    }

    @Override
    @DeleteMapping(value = "/{todoId}/{taskId}")
    public void delete(@PathVariable("todoId") int todoId,
                       @PathVariable("taskId") int taskId) {
        super.delete(taskId, todoId);
    }

    @PostMapping(value = "/{todoId}")
    public void createOrUpdate(@PathVariable("todoId") Integer todoId,
                               @RequestParam("id") Integer taskId,
                               @RequestParam("title") String title,
                               @RequestParam(value = "complete", defaultValue = "false") Boolean isComplete) {
        Task task = new Task(taskId, title, isComplete);
        if (task.isNew()) {
            super.create(task, todoId);
        }
    }

    @Override
    @PostMapping(value = "/{todoId}/{taskId}")
    public void complete(@PathVariable("todoId") int todoId, @PathVariable("taskId") int taskId, @RequestParam("enabled") boolean enabled) {
        super.complete(todoId, taskId, enabled);
    }
}