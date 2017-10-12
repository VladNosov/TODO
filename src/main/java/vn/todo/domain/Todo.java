package vn.todo.domain;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Todo.ALL_SORTED, query = "SELECT t FROM Todo t WHERE t.user.id=:userId ORDER BY t.title DESC"),
        @NamedQuery(name = Todo.DELETE, query = "DELETE FROM Todo t WHERE t.id=:id AND t.user.id=:userId"),
})
@Entity
@Table(name = "todos")
public class Todo extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Meal.getAll";
    public static final String DELETE = "Meal.delete";

    @Column(name = "title", nullable = false)
    @NotBlank
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Todo() {
    }

    public Todo(String title) {
        this(null, title);
    }

    public Todo(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}