package vn.todo.web.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import vn.todo.AuthorizedUser;
import vn.todo.domain.Todo;
import vn.todo.service.TodoService;
import java.util.List;
import static vn.todo.util.ValidationUtil.assureIdConsistent;
import static vn.todo.util.ValidationUtil.checkNew;

public abstract class AbstractTodoController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TodoService service;

    public Todo get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get todo {} for userId={}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete todo {} for userId={}", id, userId);
        service.delete(id, userId);
    }

    public List<Todo> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for userId={}", userId);
        return service.getAll(userId);
    }

    public Todo create(Todo todo) {
        int userId = AuthorizedUser.id();
        log.info("create {} for userId={}", todo, userId);
        checkNew(todo);
        return service.create(todo, userId);
    }

    public void update(Todo todo, int id) {
        int userId = AuthorizedUser.id();
        log.info("update {} with id={} for userId={}", todo, id, userId);
        assureIdConsistent(todo, id);
        service.update(todo, userId);
    }
}