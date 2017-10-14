package vn.todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.todo.AuthorizedUser;
import vn.todo.service.TaskService;
import vn.todo.service.TodoService;
import vn.todo.service.UserService;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(@RequestParam(name="userId") int userId) {
        AuthorizedUser.setId(userId);
        return "redirect:todos";
    }

    @GetMapping("/todos")
    public String todos(Model model) {
        model.addAttribute("todos", todoService.getAll(AuthorizedUser.id()));
        return "todos";
    }

    @GetMapping("/todo/{todoId}")
    public String todo(@PathVariable int todoId, Model model) {
        model.addAttribute("tasks", taskService.getAll(todoId));
        return "tasks";
    }
}