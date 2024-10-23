package miniproject;
import java.util.HashMap;
import java.util.Date;

public class applicationServer {
    private HashMap<String, Application> applications = new HashMap<>();
    private HashMap<String, Task> musicSubteamTasks = new HashMap<>();
    private HashMap<String, String> comments_in = new HashMap<>();
    private HashMap<String, String> comments_out = new HashMap<>();

    public Application createApplication(int applicationID, String applicationText, String subteam, ApplicationStatus status){
        Application newApplication = new Application(applicationID, applicationText, subteam, status);
        applications.put(String.valueOf(applicationID), newApplication);
        return newApplication;
    }

    public boolean sendtoMusicSubteam(String applicationID){
        Application application = applications.get(String.valueOf(applicationID));
        if(application != null){
            String taskID = "TASK" + String.valueOf(applicationID);
            musicSubteamTasks.put(taskID, application);
            applications.remove(String.valueOf(applicationID));
            return true;
        }
        System.out.println("Application not found");
        return false;
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

    public boolean setApplicationStatus(String applicationID, ApplicationStatus status){
        Application application = applications.get(String.valueOf(applicationID));
        if(application != null){
            application.setStatus(status);
            return true;
        }
        return false;
    }

    public HashMap<String, Application> getApplications(){
        return applications;
    }

    public HashMap<String, Task> getMusicSubteamTasks(){
        return musicSubteamTasks;
    }

    public HashMap<String, String> getComments_in(){
        return comments_in;
    }

    public HashMap<String, String> getComments_out(){
        return comments_out;
    }
}
