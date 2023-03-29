import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private static Map<Integer, Task> taskMap = new HashMap<>();
    private static Collection<Task> removedTasks = new ArrayList<>();

    public static void addTask(Task task){
        taskMap.put(task.getId(), task);
    }
    public static void printMap(){
        System.out.println(taskMap);
    }
    public static Task findTaskId(int id) throws TaskNotFoundException {
         Task task = taskMap.entrySet().stream()
                 .filter(num -> num.getKey().equals(id))
                 .map(t -> t.getValue())
                 .findFirst()
                 .orElseThrow(TaskNotFoundException::new);
         return task;
    }
    public static Task updateTitle(Task task, String newTitle) {
        task.setTitle(newTitle);
        return task;
    }
    public static Task updateDescription(Task task, String newDescription) {
        task.setDescription(newDescription);
        return task;
    }

    public static void removeTask(){
        System.out.println("Удаление задачи в архив");
        Task task = ScannerService.findTaskScanner();
        removedTasks.add(task);
        taskMap.remove(task.getId());
        System.out.println("Задача " + task.getId()+ " перенесена в архив");
    }
    public static void getAllByDate() {
        System.out.println("Ищем задачи на день");
        LocalDate date = ScannerService.createDate();
        Collection<Task> allByDate = null;
        try {
            allByDate = findAllByDate(date);
            System.out.println("Задачи на день " + date);
            printAllByDate(allByDate);
        } catch (TaskNotFoundException e) {
            System.err.println("Задачи не найдены");
        }

    }

    public static Collection<Task> findAllByDate (LocalDate date) throws TaskNotFoundException {
        Collection<Task> tasksByDate = taskMap.entrySet().stream()
                .filter(t -> t.getValue().appearsIn(date))
                .map(t -> t.getValue())
                .sorted(Comparator.comparing(Task::getDateTime))
                .collect(Collectors.toList());
        if(tasksByDate.isEmpty()){
            throw new TaskNotFoundException();
        }
        return tasksByDate;
    }
    public static void printAllByDate(Collection<Task> tasks){
        tasks.stream()
                .forEach(t-> System.out.println("Задача: " + t.getTitle() + ". Описание: " + t.getDescription() + ". ID: " + t.getId() + ". " + t.getType() + ". Время: " + LocalTime.from(t.getDateTime())));
    }

    public static Collection<Task> getRemovedTasks(){
        return removedTasks;
    }

    public static void printRemovedTasks(){
        if (removedTasks.isEmpty()){
            System.out.println("В архиве нет задач\n");
        } else {
            System.out.println("Задачи в архиве");
            removedTasks.stream()
                    .forEach(System.out::println);
        }
    }

    public static Map<LocalDate, Collection<Task>> getAllGroupByDate(){
        Map<LocalDate, Collection<Task>> mapAllGroupByDate = taskMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparing(Task::getDateTime))
                .collect(Collectors.groupingBy(t -> LocalDate.from(t.getDateTime()), Collectors.toCollection(ArrayList::new)));
        return mapAllGroupByDate;
    }

    public static void printAllGroupByDate(){
        Map<LocalDate, Collection<Task>> mapAllGroupByDate = getAllGroupByDate();
        if(mapAllGroupByDate.isEmpty()){
            System.out.println("В календаре нет задач\n");
        }else {
            System.out.println("Задачи сгруппированы по датам");
            mapAllGroupByDate.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(t-> System.out.println("Дата " + t.getKey() + "\n" + t.getValue()));
        }
    }
}
