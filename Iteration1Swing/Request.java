package miniprojectswing;
import java.util.Date;

public class Request {
    private int recordNumber;
    private String clientName;
    private String eventType;
    private Date from;
    private Date to;
    private int expectedAttendees;
    private Preferences preferences;
    private int budget;
    private RequestStatus status;
    private String feedback;
    private UserRole userRole;
    
    

    public Request(int recordNumber, String clientName, String eventType, Date from, Date to, int expectedAttendees, Preferences preferences, int budget, RequestStatus status, UserRole userRole) {
        this.recordNumber = recordNumber;
        this.clientName = clientName;
        this.eventType = eventType;
        this.from = from;
        this.to = to;
        this.expectedAttendees = expectedAttendees;
        this.preferences = preferences;
        this.budget = budget;
        this.status = RequestStatus.IN_PROGRESS;
        this.feedback = ""; //initially empty
        this.userRole = userRole;
    }

    public int getRecordNumber() {return recordNumber;}
    public String getClientName() {return clientName;}
    public String getEventType() {return eventType;}
    public Date getFrom() {return from;}
    public Date getTo() {return to;}
    public int getExpectedAttendees() {return expectedAttendees;}
    public Preferences getPreferences() {return preferences;}
    public int getBudget() {return budget;}
    public RequestStatus getRequestStatus() {return status;}
    public String getFeedback() {return feedback;}
    public UserRole getUserRole() {return userRole;}

    public void setRequestStatus(RequestStatus status) {
        this.status = status;
    }
    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }

    public void setFeedback(String feedback){
        this.feedback = feedback;
    }
}

