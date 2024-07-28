import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>(List.of(new Task("task 1", "new", 1),
                new Task("task 2", "new", 2),
                new Task("task 3", "in progress", 3),
                new Task("task 4", "complete", 4),
                new Task("task 5", "new", 5)));

        tasks.forEach(task -> System.out.println(task.getId() + " " + task.getName() + " " + task.getStatus()));

        getTasksByStatus(tasks, "complete").forEach(task -> System.out.println(task.getId() + " " + task.getName() + " " + task.getStatus()));

        System.out.println(isTaskByIDExists(tasks, 4));

        getSortedListByStatus(tasks).forEach(task -> System.out.println(task.getId() + " " + task.getName() + " " + task.getStatus()));

//        getSortedListByStatus(tasks).stream().peek(task -> System.out.println(task.getId() + " " + task.getName() + " " + task.getStatus()));

        System.out.println(getNumberTasksByStatus(tasks, "new"));
    }

    public static List<Task> getTasksByStatus(List<Task> tasks, String status) {
        return tasks.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
    }

    public static boolean isTaskByIDExists(List<Task> tasks, int id) {
        return tasks.stream().anyMatch(t -> t.getId() == id);
    }

    public static List<Task> getSortedListByStatus(List<Task> tasks) {
        return tasks.stream().sorted((t1, t2) -> {
            List<String> status = Arrays.asList("new", "in progress", "complete");
            return status.indexOf(t1.getStatus()) - status.indexOf(t2.getStatus());
        }).collect(Collectors.toList());
    }

    public static long getNumberTasksByStatus(List<Task> tasks, String status){
        return tasks.stream().filter(task -> task.getStatus().equals(status)).count();
    }
}
