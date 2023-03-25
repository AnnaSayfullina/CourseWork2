import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Выберите пункт меню:");
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
//                    menu();
                    switch (menu) {
                        case 1:
                            ScannerService.createNewTask();
                            break;
                        case 2:
                            ScannerService.editTask();
                            break;
                        case 3:
                            TaskService.removeTask();
                            break;
//                        case 4:
//                            MyCalendar.getTasksByDay(scanner);
//                            break;
//                        case 5:
//                            MyCalendar.printArchivedTasks();
//                            break;
//                        default:
//                            System.out.println("Не то");
//                            return;
                    default:
                        System.out.println("Не то");
                        return;
                    }

                }
            }
        }
    }

//    public static void menu() {
//        if (ScannerService.scanner.hasNextInt()) {
//            int menu = ScannerService.scanner.nextInt();
//            switch (menu) {
//                case 1:
//                    ScannerService.createNewTask();
//                    break;
////                        case 2:
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
//                default:
//                    System.out.println("Не то");
//                    return;
//
//            }
//        }
//    }
    public static void printMenu() {
        System.out.println("Введите:\n 1 - Добавить задачу\n 2 - Редактировать задачу\n 3 - Удалить задачу\n 4 - Получить задачи на указанный день\n 5 - Получить архивные задачи\n 6 - Получить сгруппированные по датам задачи\n 0 - Выход");
    }
}
