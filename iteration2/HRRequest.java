package miniproject;

public class HRRequest {
    private String contractType;
    private Department department;
    private String yearOfExperience;
    private String jobTitle;
    private String jobDescription;

    public HRRequest(int hrRequestID, String contractType, Department department, String yearOfExperience, String jobTitle, String jobDescription){
        this.hrRequestID = hrRequestID;
        this.contractType = contractType;
        this.department = department;
        this.yearOfExperience = yearOfExperience;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

    public int getHRRequestID(){
        return hrRequestID;
    }
    
    public String getContractType(){
        return contractType;
    }   
    public Department getDepartment(){
        return department;
    }
    public String getYearOfExperience(){
        return yearOfExperience;
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public String getJobDescription(){
        return jobDescription;
    }
}
