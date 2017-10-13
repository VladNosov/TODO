package vn.todo.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vn.todo.domain.Todo;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudTodoRepository extends JpaRepository<Todo, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE t.id=:id AND t.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Todo save(Todo item);

    @Query("SELECT t FROM Todo t WHERE t.user.id=:userId ORDER BY t.title DESC")
    List<Todo> getAll(@Param("userId") int userId);

    @Query("SELECT t FROM Todo t JOIN FETCH t.user WHERE t.id = ?1 and t.user.id = ?2")
    Todo getWithUser(int id, int userId);
}