package miniproject;

public class Application {
    private int applicationID;
    private String applicationText;
    private String subteam;

    public Application(int applicationID, String applicationText, String subteam){
        this.applicationID = applicationID;
        this.applicationText = applicationText;
        this.subteam = subteam;
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

    
}
