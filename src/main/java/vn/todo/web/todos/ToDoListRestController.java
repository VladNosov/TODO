package vn.todo.web.todos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.todo.AuthorizedUser;
import vn.todo.domain.ToDoList;
import vn.todo.service.ToDoListService;
import static vn.todo.util.ValidationUtil.checkIdConsistent;
import static vn.todo.util.ValidationUtil.checkNew;

@Controller
@RequestMapping("/todos")
public class ToDoListRestController {
    private static final Logger log = LoggerFactory.getLogger(ToDoListRestController.class);

    private final ToDoListService service;

    @Autowired
    public ToDoListRestController(ToDoListService service) {
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
        final ToDoList toDoList = service.get(id, userId);
        model.addAttribute("todo", toDoList);
        return "redirect:/todo/update";
    }

    //==================================================================================================================

    public ToDoList get(int id) {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public ToDoList create(ToDoList toDoList) {
        int userId = AuthorizedUser.id();
        checkNew(toDoList);
        return service.create(toDoList, userId);
    }

    public void update(ToDoList toDoList, int id) {
        int userId = AuthorizedUser.id();
        checkIdConsistent(toDoList, id);
        service.update(toDoList, userId);
    }
}