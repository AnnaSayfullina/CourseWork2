import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScannerService {
    public static final Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public static void createNewTask(){
        System.out.println("Придумайте название для задачи");
        String title = createText();

        System.out.println("Кратко опишите задачу");
        String description = createText();

        System.out.println("Выберите тип задачи:");
        Type type = createType();

        System.out.println("В какой день будет выполнена задача?");
        LocalDate date = createDate();

        System.out.println("В какое время будет выполнена задача?");
        LocalTime time = createTime();

        System.out.println("Укажите повторяемость задачи");
        Task task = chooseRepeatability(title, description, type, date, time);

        TaskService.addTask(task);
        printTask(task);

    }
    public static String createText(){
        Scanner scanner = new Scanner(System.in);
        String text = null;
        while (text == null){
            System.out.println("Введите текст");
            text = scanner.nextLine();
            if (text.equals("")||text.equals(" ")) {
                text = null;
            choiceActionsAfterIncorrectArgument();
            }
        }
        return text;
    }
    public static LocalDate createDate() {
        LocalDate date = null;
        while (date == null) {
            System.out.println("Введите дату в формате дд.мм.гггг (Например, 19.04.2023)");
            try {
                return LocalDate.parse(scanner.next(), formatterDate);
            } catch (DateTimeParseException e) {
                choiceActionsAfterIncorrectArgument();
            }
        }
        return date;
    }

    public static void  choiceActionsAfterIncorrectArgument(){
        printIncorrect();
        printChoiceRepeatOrExit();
        switch (scanner.nextInt()) {
            case 1:
                break;
            case 2:
            default:
                System.out.println("Вы вышли из программы");
                System.exit(1);
                break;
        }
    }

    public static LocalTime createTime() {
        LocalTime time = null;
        while (time == null){
             System.out.println("Введите время в формате чч:мм (Например, 15:30)");
             try {
            return LocalTime.parse(scanner.next(), formatterTime);
            } catch (DateTimeParseException e) {
                choiceActionsAfterIncorrectArgument();
            }
        }
        return time;
    }
    public static LocalDateTime createLocalDateTime(LocalDate date, LocalTime time){
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        return localDateTime;
    }

    public static Type createType(){
        Type type = null;
        while (type == null){
        System.out.println("Введите: \n 1 - если задача рабочая \n 2 - если задача личная");
            switch (scanner.nextInt()) {
                 case 1:
                     type = Type.WORK;
                     break;
                 case 2:
                    type = Type.PERSONAL;
                    break;
                 default:
                    choiceActionsAfterIncorrectArgument();
            }
        }
        return type;
    }

    public static Task chooseRepeatability(String title, String description, Type type, LocalDate date, LocalTime time){
        Task task = null;
        while (task == null){
        System.out.println("Введите \n 1 - разовая\n 2 - ежедневная\n 3 - еженедельная\n 4 - ежемесячная\n 5 - ежегодная");
            switch (scanner.nextInt()){
                case 1:
                    task = new OneTimeTask(title, description, type, createLocalDateTime(date,time));
                    break;
                case 2:
                    task = new DailyTask(title, description, type, createLocalDateTime(date,time));
                    break;
                case 3:
                    task = new WeeklyTask(title, description, type, createLocalDateTime(date,time));
                    break;
                case 4:
                    task = new MonthlyTask(title, description, type, createLocalDateTime(date,time));
                    break;
                case 5:
                    task = new YearlyTask(title, description, type, createLocalDateTime(date,time));
                    break;
                default:
                    choiceActionsAfterIncorrectArgument();
                }
        }
        return task;
    }

    public static void printTask(Task task){
        System.out.println("Задача создана и добавлена в календарь\n" + task);
    }

    public static void printIncorrect(){
        System.err.println("Данные введены некорректно");
    }

    public static void printChoiceRepeatOrExit(){
        System.out.println("Введите\n 1 - попробовать еще раз\n 2- выход из программы");
    }

    public static void editTask() {
        System.out.println("Редактирование задачи");
        Task task = findTaskScanner();
        editTitleOrDescription(task);
    }

    public static void editTitleOrDescription(Task task){

        System.out.println("Что будем редактировать?\nВведите:\n 1 - заголовок\n 2 - описание");
            switch (scanner.nextInt()){
                case 1:
                    System.out.println("Введите новый заголовок");
                    String newTitle = createText();
                    System.out.println("Задача успешно изменена\n"+TaskService.updateTitle(task,newTitle));
                    break;
                case 2:
                    System.out.println("Введите новое описание");
                    String newDescription = createText();
                    System.out.println("Задача успешно изменена\n"+TaskService.updateDescription(task,newDescription));
                    break;
                default:
                    printIncorrect();
                    printChoiceRepeatOrExit();
                    switch (scanner.nextInt()){
                        case 1:
                            editTitleOrDescription(task);
                            break;
                        default:
                            System.exit(1);
                    }
        }
    }
    public static Task findTaskScanner(){

           Task task = null;
           while (task == null) {
               try {
                   System.out.println("Введите id задачи");
                   task = TaskService.findTaskId(scanner.nextInt());
               } catch (TaskNotFoundException e) {
                   System.err.println("Задача не найдена");
                   choiceActionsAfterIncorrectArgument();
               }
           }
        return task;
    }
}
