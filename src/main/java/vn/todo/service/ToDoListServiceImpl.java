package vn.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.todo.model.ToDoList;
import vn.todo.repository.ToDoListRepository;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;
import static vn.todo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListRepository repository;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public ToDoList update(ToDoList toDoList, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(toDoList, userId), toDoList.getId());
    }

    @Override
    public ToDoList save(ToDoList toDoList, int userId) {
        return repository.save(toDoList, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public ToDoList get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<ToDoList> getAll(int userId) {
        return repository.getAll(userId);
    }
}
