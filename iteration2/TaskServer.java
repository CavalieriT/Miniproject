package miniproject;
import java.util.HashMap;
import java.util.Date;

public class TaskServer {
    private HashMap<String, Task> musicSubteamTasks = new HashMap<>();
    private HashMap<String, Task> chefSubteamTasks = new HashMap<>();
    private HashMap<String, String> comments_in = new HashMap<>();
    private HashMap<String, String> comments_out = new HashMap<>();

    public Task createTask(int taskID, String taskDescription, String worker, Priority priority, String manager){
        Task newTask = new Task(taskID, taskDescription, worker, priority, manager);
        if(manager == "Production Manager"){
            musicSubteamTasks.put(String.valueOf(taskID), newTask);
        }
        else if(manager == "Service Manager"){
            chefSubteamTasks.put(String.valueOf(taskID), newTask);
        }
        return newTask;
    }

    public boolean addComments(String taskID, String comment){
        comments_in.put(taskID, comment);
        return true;
    }

    public boolean sendComments(String taskID, String comment){
        comments_out.put(taskID, comment);
        comments_in.remove(taskID);
        return true;
    }

    public HashMap<String, Task> getMusicSubteamTasks(){
        return musicSubteamTasks;
    }

    public HashMap<String, Task> getChefSubteamTasks(){
        return chefSubteamTasks;
    }

    public HashMap<String, String> getComments_in(){
        return comments_in;
    }

    public HashMap<String, String> getComments_out(){
        return comments_out;
    }
}