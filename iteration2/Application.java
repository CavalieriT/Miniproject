package miniproject;

public class Application {
    private int applicationID;
    private String applicationText;
    private String subteam;
    private ApplicationStatus status;
    private Department department;

    public Application(int applicationID, String applicationText, String subteam, ApplicationStatus status, Department department){
        this.applicationID = applicationID;
        this.applicationText = applicationText;
        this.subteam = subteam;
        this.status = ApplicationStatus.DEFAULT;
        this.department = Department.DEFAULT;
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

    public Department getDepartment(){
        return department;
    }

    public void setDepartment(Department department){
        this.department = department;
    }
}

