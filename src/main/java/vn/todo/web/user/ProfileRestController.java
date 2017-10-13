package vn.todo.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.todo.AuthorizedUser;
import vn.todo.domain.User;

@Controller
@RequestMapping("/users")
public class ProfileRestController extends AbstractUserController{

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