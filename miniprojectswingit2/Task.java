package miniprojectswingit2;

public class Task {
    private String taskID;
    private String taskDescription;
    private String worker;
    private Priority priority;
    private String manager;
   

    public Task(String taskID, String taskDescription, String worker, Priority priority, String manager){
        this.taskID = taskID;
        this.taskDescription = taskDescription;
        this.worker = worker;
        this.priority = priority;
        this.manager = manager;

    }

    public String getTaskID(){
        return taskID;
    }
    public String getTaskDescription(){
        return taskDescription;
    }
    public String getWorker(){
        return worker;
    }
    public Priority getPriority(){
        return priority;
    }   
    public String getManager(){
        return manager;
    }

    public void setPriority(Priority priority){
        this.priority = priority;
    }

    public void setWorker(String worker){
        this.worker = worker;
    }
}

