package vn.todo.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.todo.domain.Todo;
import vn.todo.domain.User;
import vn.todo.repository.ToDoListRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaToDoListRepositoryImpl implements ToDoListRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Todo save(Todo todo, int userId) {
        if (!todo.isNew() && get(todo.getId(), userId) == null) {
            return null;
        }
        todo.setUser(em.getReference(User.class, userId));
        if (todo.isNew()) {
            em.persist(todo);
            return todo;
        } else {
            return em.merge(todo);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Todo.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Todo get(int id, int userId) {
        Todo meal = em.find(Todo.class, id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<Todo> getAll(int userId) {
        return em.createNamedQuery(Todo.ALL_SORTED, Todo.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}