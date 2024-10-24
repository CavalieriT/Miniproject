package miniproject;

public class HRRequestService {
    private static HashMap<String, HRRequest> hrRequests_pm = new HashMap<>();
    private static HashMap<String, HRRequest> hrRequests_hr = new HashMap<>();
    private static HashMap<String, HRRequest> hr_accepted = new HashMap<>();

    public HRRequest createHRRequest(int hrRequestID, String contractType, Department department, String yearOfExperience, String jobTitle, String jobDescription){
        HRRequest hrRequest = new HRRequest(hrRequestID, contractType, department, yearOfExperience, jobTitle, jobDescription);
        hrRequests_pm.put(String.valueOf(hrRequestID), hrRequest);
        return hrRequest;
    }

    public boolean sendtoHR(String hrRequestID){
        HRRequest hrRequest = hrRequests_p.get(hrRequestID);
        if(hrRequest != null){
            hrRequests_hr.put(hrRequestID, hrRequest);
            hrRequests_pm.remove(hrRequestID);
            return true;
        } else {
            System.out.println("HR request not found");
            return false;
        }
    }
    public boolean acceptHRRequest(String hrRequestID){
        HRRequest hrRequest = hrRequests_hr.get(hrRequestID);
        if(hrRequest != null){
            hr_accepted.put(String.valueOf(hrRequestID), hrRequest);
            hrRequests_hr.remove(String.valueOf(hrRequestID));
            return true;
        }else{
            return false;
        }
    }
    public HashMap<String, HRRequest> getHRRequests_pm(){
        return hrRequests_pm;
    }
    public HashMap<String, HRRequest> getHRRequests_hr(){
        return hrRequests_hr;
    }
    public static HashMap<String, HRRequest> getHRRequests_accepted(){
        return hr_accepted;
    }


