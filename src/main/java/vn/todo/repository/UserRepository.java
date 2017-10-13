package vn.todo.repository;

import vn.todo.domain.User;
import java.util.List;

public interface UserRepository {
    User save(User user);

    /**
     * @return false if not found
     */
    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    default User getWithTodos(int id){
        throw new UnsupportedOperationException();
    }
}