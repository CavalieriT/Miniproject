package miniproject;
import java.util.HashMap;
import java.util.Date;

public class applicationServer {
    private HashMap<String, Application> applications = new HashMap<>();
    private HashMap<String, Task> musicSubteamTasks = new HashMap<>();

    public Application createApplication(int applicationID, String applicationText, String subteam){
        Application newApplication = new Application(applicationID, applicationText, subteam);
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

    public HashMap<String, Application> getApplicatio(){
        return applications;
    }

    public HashMap<String, Task> getMusicSubteamTasks(){
        return musicSubTeamTasks;
    }
}
