package miniproject;

public class Application {
    private int applicationID;
    private String applicationText;
    private String subteam;
    private ApplicationStatus status;

    public Application(int applicationID, String applicationText, String subteam){
        this.applicationID = applicationID;
        this.applicationText = applicationText;
        this.subteam = subteam;
        this.status = ApplicationStatus.DEFAULT;
    }

    public int getApplicationID(){
        return applicationID;
    }
    public String getApplicationText(){
        return applicationText;
    }
    public String getSubteam(){
        return subteam;
    }

    public ApplicationStatus getStatus(){
        return status;
    }

    public void setStatus(ApplicationStatus status){
        this.status = status;
    }   
}
