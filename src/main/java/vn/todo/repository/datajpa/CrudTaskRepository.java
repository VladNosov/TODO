package vn.todo.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vn.todo.domain.Task;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudTaskRepository extends JpaRepository<Task, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.id=:id AND t.todo.id=:todoId")
    int delete(@Param("id") int id, @Param("todoId") int todoId);

    @Override
    Task save(Task item);

    @Query("SELECT t FROM Task t WHERE t.todo.id=:todoId ORDER BY t.title DESC")
    List<Task> getAll(@Param("todoId") int todoId);
}