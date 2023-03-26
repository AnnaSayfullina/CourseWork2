import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Выберите пункт меню:");
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();

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
                        case 4:
                            TaskService.getAllByDate();
                            break;
                        case 5:
                            TaskService.printRemovedTasks();
                            break;
                        case 6:
                            TaskService.printAllGroupByDate();
                            break;
                        case 0:
                            return;
                        default:
                        System.out.println("Вы ввели некорректные данные");
                            break;
                    }

                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Введите:\n 1 - Добавить задачу\n 2 - Редактировать задачу\n 3 - Удалить задачу\n 4 - Получить задачи на указанный день\n 5 - Получить архивные задачи\n 6 - Получить сгруппированные по датам задачи\n 0 - Выход");
    }
}
