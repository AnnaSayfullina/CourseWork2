import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task{

    public OneTimeTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate date = LocalDate.from(getDateTime());
        return localDate.isEqual(date);
    }

    @Override
    public String toString() {
        return super.toString() + ". Повторяемость - разовая";
    }
}
