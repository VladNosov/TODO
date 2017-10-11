package vn.todo.service;

import vn.todo.model.User;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}