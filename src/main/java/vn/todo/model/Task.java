package vn.todo.model;

public class Task extends BaseEntity {
    private String title;
    private boolean isComplete;

    public Task(String title) {
        this(null, title, false);
    }

    public Task(Integer id, String title) {
        this(id, title, false);
    }

    public Task(Integer id, String title, boolean isComplete) {
        super(id);
        this.title = title;
        this.isComplete = isComplete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
