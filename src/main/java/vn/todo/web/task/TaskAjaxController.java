package vn.todo.web.task;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.todo.domain.Task;
import vn.todo.to.TaskTo;
import vn.todo.util.TaskUtil;
import javax.validation.Valid;
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
    @GetMapping(value = "/{todoId}/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task get(@PathVariable("todoId") int todoId, @PathVariable("taskId") int taskId) {
        return super.get(taskId, todoId);
    }

    @Override
    @DeleteMapping(value = "/{todoId}/{taskId}")
    public void delete(@PathVariable("todoId") int todoId,
                       @PathVariable("taskId") int taskId) {
        super.delete(taskId, todoId);
    }

    @PostMapping(value = "/{todoId}")
    public void createOrUpdate(@PathVariable("todoId") Integer todoId,
                                                 @Valid TaskTo taskTo, BindingResult result) {
        if (taskTo.isNew()) {
            super.create(TaskUtil.createNewFromTo(taskTo), todoId);
        } else {
            super.update(taskTo, taskTo.getId(), todoId);
        }
    }

    @Override
    @PostMapping(value = "/{todoId}/{taskId}")
    public void complete(@PathVariable("todoId") int todoId, @PathVariable("taskId") int taskId, @RequestParam("enabled") boolean enabled) {
        super.complete(todoId, taskId, enabled);
    }
}