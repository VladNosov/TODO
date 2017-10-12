package vn.todo.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.todo.domain.Todo;
import vn.todo.repository.TodoRepository;
import java.util.List;

@Primary
@Repository
public class DataJpaTodoRepositoryImpl implements TodoRepository {

    @Autowired
    private CrudTodoRepository crudTodoRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Todo save(Todo todo, int userId) {
        if (!todo.isNew() && get(todo.getId(), userId) == null) {
            return null;
        }
        todo.setUser(crudUserRepository.getOne(userId));
        return crudTodoRepository.save(todo);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudTodoRepository.delete(id, userId) != 0;
    }

    @Override
    public Todo get(int id, int userId) {
        Todo todo = crudTodoRepository.findOne(id);
        return todo != null && todo.getUser().getId() == userId ? todo : null;
    }

    @Override
    public List<Todo> getAll(int userId) {
        return crudTodoRepository.getAll(userId);
    }
}