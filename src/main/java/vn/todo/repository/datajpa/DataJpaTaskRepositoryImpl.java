package vn.todo.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.todo.domain.Task;
import vn.todo.repository.TaskRepository;
import java.util.List;

@Primary
@Repository
public class DataJpaTaskRepositoryImpl implements TaskRepository {

    @Autowired
    private CrudTaskRepository crudTaskRepository;

    @Autowired
    private CrudTodoRepository crudTodoRepository;

    @Override
    @Transactional
    public Task save(Task task, int todoId) {
        if (!task.isNew() && get(task.getId(), todoId) == null) {
            return null;
        }
        task.setTodo(crudTodoRepository.getOne(todoId));
        return crudTaskRepository.save(task);
    }

    @Override
    public boolean delete(int id, int todoId) {
        return crudTaskRepository.delete(id, todoId) != 0;
    }

    @Override
    public Task get(int id, int todoId) {
        Task task = crudTaskRepository.findOne(id);
        return task != null && task.getTodo().getId() == todoId ? task : null;
    }

    @Override
    public List<Task> getAll(int todoId) {
        return crudTaskRepository.getAll(todoId);
    }
}