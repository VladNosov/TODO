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
import static vn.todo.util.ValidationUtil.checkIdConsistent;
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
        int userId = AuthorizedUser.id();
        model.addAttribute("todos", service.getAll(userId));
        return "todos";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
        return "redirect:/todos";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam(name = "id") int id, Model model) {
        int userId = AuthorizedUser.id();
        final Todo todo = service.get(id, userId);
        model.addAttribute("todo", todo);
        return "redirect:/todo/update";
    }

    //==================================================================================================================

    public Todo get(int id) {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public Todo create(Todo todo) {
        int userId = AuthorizedUser.id();
        checkNew(todo);
        return service.create(todo, userId);
    }

    public void update(Todo todo, int id) {
        int userId = AuthorizedUser.id();
        checkIdConsistent(todo, id);
        service.update(todo, userId);
    }
}