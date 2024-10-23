package miniprojectswing;
import java.util.HashMap;
import java.util.Date;

public class RequestService {
    private HashMap<String, Request> SCSOrequests = new HashMap<>();  // To store requests
    private HashMap<String, Request> CSOrequests = new HashMap<>();  // To store requests
    private HashMap<String, Request> FMrequests = new HashMap<>();
    private HashMap<String, Request> AMrequests = new HashMap<>();
    
    // Create a new request
    public Request createRequest(int recordNumber, String clientName, String eventType, Date from, Date to, int expectedAttendees, Preferences preferences, int budget, RequestStatus status, UserRole userRole) {
        String requestNumber = "REQ" + (CSOrequests.size() + 1);  // Simple unique ID generation
        Request newRequest = new Request(recordNumber, clientName, eventType, from, to, expectedAttendees, preferences, budget,status, userRole);
        CSOrequests.put(requestNumber, newRequest);
        return newRequest;
    }

    // Send request to Senior Customer Service Officer (SCSO)
    public boolean sendToSCSO(String requestId) {
        Request request = CSOrequests.get(requestId);
        if (request != null) {
            String requestNumber = "REQ" + (SCSOrequests.size() + 1);
            request.setUserRole(UserRole.SENIOR_CUSTOMER_SERVICE);
            SCSOrequests.put(requestNumber, request);
            CSOrequests.remove(requestNumber);
            System.out.println("Request sent to Senior Customer Service Officer.");
            return true;
        } else {
            System.out.println("Invalid request");
            return false;
        }
    }

    public boolean acceptRequest(String requestID){
        Request request = AMrequests.get(requestID);
        if(request != null){
            request.setRequestStatus(RequestStatus.ACCEPTED);
            AMrequests.remove(requestID);
            request.setUserRole(UserRole.SENIOR_CUSTOMER_SERVICE);
            SCSOrequests.put(requestID,request);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean rejectRequest(String requestID){
        if(SCSOrequests.containsKey(requestID)){
            Request request = SCSOrequests.get(requestID);
            if(request != null && request.getUserRole().equals(UserRole.SENIOR_CUSTOMER_SERVICE)){
              request.setRequestStatus(RequestStatus.REJECTED);
              return true;
            }
        }
        else if(AMrequests.containsKey(requestID)){
            Request request = AMrequests.get(requestID);
            if(request != null && request.getUserRole().equals(UserRole.ADMIN_MANAGER)){
              request.setRequestStatus(RequestStatus.REJECTED);
              return true;
            }
        }
            return false;
    }

    public boolean redirectToFinancialManager(String requestID){
        Request request = SCSOrequests.get(requestID);
        if(request != null){
            request.setUserRole(UserRole.FINANCIAL_MANAGER);
            FMrequests.put(requestID, request);
            SCSOrequests.remove(requestID);
            return true;
        } else{
            return false; //invalid request ID
        }
    }

    public boolean writeFeedback(String requestID, String feedback){
        Request request = FMrequests.get(requestID);
        if(request != null){
            request.setFeedback(feedback);
            return true;
        }else{
            return false; //problem with the requestID
        }
    }

    public boolean passToAdministrationManager(String requestID){
        Request request = FMrequests.get(requestID);
        if(request != null){
            request.setUserRole(UserRole.ADMIN_MANAGER);
            AMrequests.put(requestID,request);
            FMrequests.remove(requestID);
            return true;
        }
        else{
            return false;
        }
    }

    public HashMap<String, Request> getSCSOrequests(){
        return SCSOrequests;
    }

    public HashMap<String, Request> getCSOrequests(){
        return CSOrequests;
    }

    public HashMap<String, Request> getFMrequests(){
        return FMrequests;
    }

    public HashMap<String, Request> getAMrequests(){
        return FMrequests;
    }
}
