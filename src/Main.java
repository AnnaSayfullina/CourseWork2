import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    OneTimeTask oneTimeTask = new OneTimeTask("Задача", "Сделать работу", Type.PERSONAL, LocalDateTime.of(2022,3,25,15,30));
    OneTimeTask oneTimeTask1 = new OneTimeTask("Задача", "Сделать работу", Type.PERSONAL, LocalDateTime.of(2022,3,25,15,30));
    OneTimeTask oneTimeTask2 = new OneTimeTask("Задача", "Сделать работу", Type.PERSONAL, LocalDateTime.of(2022,3,25,15,30));
        System.out.println(oneTimeTask);
        List<Task> list = new ArrayList<>();
        list.add(oneTimeTask);
        list.add(oneTimeTask1);
        list.add(oneTimeTask2);
        System.out.println(list);
        LocalDate l = LocalDate.of(2022, 3, 25);
        System.out.println(oneTimeTask1.appearsIn(l));
        WeeklyTask weeklyTask1 = new WeeklyTask("Задача", "Сделать работу", Type.WORK, LocalDateTime.of(2022,3,25,15,30));
        System.out.println(weeklyTask1.appearsIn(LocalDate.of(2022, 4, 1)));
        MonthlyTask monthlyTask = new MonthlyTask("Задача", "Сделать работу", Type.WORK, LocalDateTime.of(2022,3,25,15,30));
        System.out.println(monthlyTask.appearsIn(LocalDate.of(2022, 4, 25)));
        YearlyTask yearlyTask = new YearlyTask("Задача", "Сделать работу", Type.WORK, LocalDateTime.of(2022,3,25,15,30));
        System.out.println(yearlyTask.appearsIn(LocalDate.of(2023, 4, 25)));
        DailyTask dailyTask = new DailyTask("Задача", "Сделать работу", Type.WORK, LocalDateTime.of(2022,3,25,15,30));
//        try (Scanner scanner = new Scanner(System.in)) {
//            label:
//            while (true) {
//                System.out.println("Выберите пункт меню:");
//                printMenu();
//                if (scanner.hasNextInt()) {
//                    int menu = scanner.nextInt();
//
//                    switch (menu) {
//                        case 1:
//                            MyCalendar.addTask(scanner);
//                            break;
//                        case 2:
//                            MyCalendar.editTask(scanner);
//                            break;
//                        case 3:
//                            MyCalendar.deleteTask(scanner);
//                            break;
//                        case 4:
//                            MyCalendar.getTasksByDay(scanner);
//                            break;
//                        case 5:
//                            MyCalendar.printArchivedTasks();
//                            break;
//                    }
//                }
//            }
//        }
//    }
//    private static void printMenu() {
//        System.out.println("1. Добавить задачу 2. Редактировать задачу 3. Удалить задачу4. Получить задачи на указанный день5. Получить архивные задачи6. Получить сгруппированные по датам задачи 0. Выход");
    }
}