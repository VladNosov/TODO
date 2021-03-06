package vn.todo.service;

import vn.todo.domain.User;
import vn.todo.to.UserTo;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    void evictCache();

    List<User> getAll();

    void enable(int id, boolean enable);

    User getWithTodos(int id);
}