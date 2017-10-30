package vn.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.todo.AuthorizedUser;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @PostMapping("/users")
    public String setUser(@RequestParam(name="userId") int userId) {
        AuthorizedUser.setId(userId);
        return "redirect:todos";
    }

    @GetMapping("/todos")
    public String todos() {
        return "todos";
    }

    @GetMapping("/todo/{todoId}")
    public String todo(@PathVariable int todoId, Model model) {
        return "tasks";
    }
}