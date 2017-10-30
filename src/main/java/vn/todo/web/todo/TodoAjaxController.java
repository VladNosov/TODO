package vn.todo.web.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.todo.domain.Todo;
import vn.todo.to.TodoTo;
import vn.todo.util.TodoUtil;
import vn.todo.util.Util;
import javax.validation.Valid;
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
    public ResponseEntity<String> createOrUpdate(@Valid TodoTo todoTo, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(Util.getHttpErrorMessage(result), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (todoTo.isNew()) {
            super.create(TodoUtil.createNewFromTo(todoTo));
        } else {
            super.update(todoTo, todoTo.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}