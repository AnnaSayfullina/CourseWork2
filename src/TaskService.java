import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class TaskService {
    private static Map<Integer, Task> taskMap = new HashMap<>();
    private static Collection<Task> removedTasks = new ArrayList<>();

    public static void addTask(Task task){
        taskMap.put(task.getId(), task);
    }
    public static void printMap(){
        System.out.println(taskMap);
    }
    public static Task foundTaskId(int id) throws TaskNotFoundException {
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
        Task task = ScannerService.foundTaskScanner();
        removedTasks.add(task);
        taskMap.remove(task.getId());
        System.out.println("Задача " + task.getId()+ " перенесена в архив");
    }
}
