package vn.todo.web.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.todo.domain.Todo;
import java.util.Map;

@Controller
@RequestMapping("/todos")
public class JspTodosController extends AbstractTodoController {

    @GetMapping("/delete")
    public String deleteTodo(@RequestParam(name = "id") int id) {
        delete(id);
        return "redirect:/todos";
    }

    @GetMapping("/update")
    public String updateTodo(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("todo", get(id));
        return "todoForm";
    }

    @GetMapping("/create")
    public String createTodo(Model model) {
        final Todo todo = new Todo("");
        model.addAttribute("todo", todo);
        return "todoForm";
    }

    @PostMapping
    public String updateOrCreate(@RequestParam Map<String, String> params) {
        final Todo todo = new Todo(params.get("title"));
        if (params.get("id").isEmpty()) {
            create(todo);
        } else {
            update(todo, Integer.parseInt(params.get("id")));
        }
        return "redirect:/todos";
    }
}