package vn.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import vn.todo.AuthorizedUser;

@Controller
@RequestMapping("/users")
public class UsersController {

    @RequestMapping(method = RequestMethod.GET)
    public String users() {
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView add(@RequestParam(name="userId", required = true) int userId) {
        AuthorizedUser.setId(userId);
        return new RedirectView("todoLists");
    }
}