package vn.todo.web.todo;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.todo.View;
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
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Validated(View.ValidatedRestUI.class) Todo todo) {
        if (todo.isNew()) {
            super.create(todo);
        } else {
            super.update(todo, todo.getId());
        }
    }
}