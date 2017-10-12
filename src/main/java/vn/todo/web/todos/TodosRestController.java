package vn.todo.web.todos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.todo.AuthorizedUser;
import vn.todo.domain.Todo;
import vn.todo.service.TodoService;
import java.util.List;
import java.util.Map;
import static vn.todo.util.ValidationUtil.assureIdConsistent;
import static vn.todo.util.ValidationUtil.checkNew;

@Controller
@RequestMapping("/todos")
public class TodosRestController {
    private static final Logger log = LoggerFactory.getLogger(TodosRestController.class);

    private final TodoService service;

    @Autowired
    public TodosRestController(TodoService service) {
        this.service = service;
    }

    @GetMapping()
    public String todos(Model model) {
        model.addAttribute("todos", getAll());
        return "todos";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam(name = "id") int id) {
        delete(id);
        return "redirect:/todos";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateTodo(@RequestParam(name = "id") int id, Model model) {
        final Todo todo = get(id);
        model.addAttribute("todo", todo);
        return "todoForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTodo(Model model) {
        final Todo todo = new Todo("");
        model.addAttribute("todo", todo);
        return "todoForm";
    }

    @PostMapping
    public String doPost(@RequestParam(name = "action", required = false) String action, @RequestParam Map<String, String> params) {
        if (action == null) {
            final Todo todo = new Todo(params.get("title"));
            if (params.get("id").isEmpty()) {
                create(todo);
            } else {
                update(todo, Integer.parseInt(params.get("id")));
            }
        }
        return "redirect:/todos";
    }

    //======================================= private method ===========================================================

    private Todo get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get todo {} for userId={}", id, userId);
        return service.get(id, userId);
    }

    private void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete todo {} for userId={}", id, userId);
        service.delete(id, userId);
    }

    private List<Todo> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for userId={}", userId);
        return service.getAll(userId);
    }

    private Todo create(Todo todo) {
        int userId = AuthorizedUser.id();
        log.info("create {} for userId={}", todo, userId);
        checkNew(todo);
        return service.create(todo, userId);
    }

    private void update(Todo todo, int id) {
        int userId = AuthorizedUser.id();
        log.info("update {} with id={} for userId={}", todo, id, userId);
        assureIdConsistent(todo, id);
        service.update(todo, userId);
    }
}