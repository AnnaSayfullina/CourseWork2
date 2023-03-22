import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public abstract class Task {
    private int idGenerator = new Random().nextInt(1000);
    private String title;
    private final Type type;
    private final int id;
    private final LocalDateTime dateTime;
    private String description;

    public Task(String title, String description, Type type, LocalDateTime dateTime) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.id = idGenerator;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && type == task.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id);
    }

    @Override
    public String toString() {
        String formatDate = dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "Задача: " + title + ". Описание: " + description + ". ID: " + id + ". " + type + ". Дата: " + formatDate;
    }

    public abstract boolean appearsIn(LocalDate localDate);
}
