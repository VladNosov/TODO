package vn.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:todos";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/todos")
    public String todos() {
        return "todos";
    }

    @GetMapping("/todo/*")
    public String todo() {
        return "tasks";
    }
}