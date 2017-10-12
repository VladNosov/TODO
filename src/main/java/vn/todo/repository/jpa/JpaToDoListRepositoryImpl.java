package vn.todo.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.todo.domain.ToDoList;
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
    public ToDoList save(ToDoList todo, int userId) {
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
        return em.createNamedQuery(ToDoList.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public ToDoList get(int id, int userId) {
        ToDoList meal = em.find(ToDoList.class, id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<ToDoList> getAll(int userId) {
        return em.createNamedQuery(ToDoList.ALL_SORTED, ToDoList.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}