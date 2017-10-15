package vn.todo.web.todo;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.todo.domain.Todo;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = TodoRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoRestController extends AbstractTodoController {
    static final String REST_URL = "/rest/profile/todos";

    @Override
    @GetMapping("/{id}")
    public Todo get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<Todo> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Todo meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> createWithLocation(@RequestBody Todo meal) {
        Todo created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}