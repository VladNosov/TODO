package vn.todo.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import vn.todo.AuthorizedUser;
import vn.todo.domain.User;

@Controller
@RequestMapping("/users")
public class ProfileRestController extends AbstractUserController{

    @GetMapping
    public String users() {
        log.debug("forward to users");
        return "users";
    }

    @PostMapping
    public RedirectView add(@RequestParam(name="userId", required = true) int userId) {
        AuthorizedUser.setId(userId);
        log.debug("redirect to TODO lists");
        return new RedirectView("todos");
    }

    //==================================================================================================================

    public User get() {
        return super.get(AuthorizedUser.id());
    }

    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    public void update(User user) {
        super.update(user, AuthorizedUser.id());
    }
}