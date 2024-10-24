package miniproject;

public class Task {
    private String taskID;
    private Application application;
   

    public Task(String taskID, Application application){
        this.taskID = taskID;
        this.application = application;
    }

    public int getTaskID(){
        return taskID;
    }
    public Application getApplication(){
        return application;
    }   
}

