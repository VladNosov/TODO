package vn.todo.web.todo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.todo.domain.Todo;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/todos")
public class TodoAjaxController extends AbstractTodoController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("title") String title) {
        Todo todo = new Todo(id, title);
        if (todo.isNew()) {
            super.create(todo);
        }
    }
}