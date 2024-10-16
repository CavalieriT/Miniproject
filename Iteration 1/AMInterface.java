package miniproject;
import java.util.Map;
import java.util.Scanner;

public class AMInterface {
    private static RequestService requestService = new RequestService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Administration Manager");
            System.out.println("1. View requests");
            System.out.println("2. Accept a request");
            System.out.println("3. Reject a request");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewRequests();
                    break;
                case 2:
                    acceptRequest();
                    break;
                case 3:
                    rejectRequest();
                    break;
                case 4:
                    exitCondition = false;
                    System.out.println("Exiting the system");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    
    private static void viewRequests(){
        Map<String,Request> AMrequests = requestService.getAMrequests();
        if(AMrequests.isEmpty()) System.out.println("No new requests.");
        else{
            System.out.println("Requests for Administration Manager: ");
            for(Request request: AMrequests.values()){
                System.out.println("Request ID: "+ request.getRecordNumber());
                System.out.println("Client name" + request.getClientName());
                System.out.println("Event type" + request.getEventType());
                System.out.println("Starting date" + request.getFrom());
                System.out.println("Ending date:" + request.getTo());
                System.out.println("Expected attendees" + request.getExpectedAttendees());
                System.out.println("Preferences: " + request.getPreferences());
                System.out.println("Budget: " + request.getBudget());
                System.out.println("Request status: " + request.getRequestStatus());
            }
        }
    }

    private static void acceptRequest(){
        System.out.print("Enter Request ID to accept: ");
        String requestId = scanner.nextLine();
        boolean success = requestService.acceptRequest(requestId);
        if (success) {
            System.out.println("Request " + requestId + " has been accepted.");
        } else {
            System.out.println("Failed to accept request, check the request ID.");
        }
    }

    private static void rejectRequest(){
        System.out.println("Enter request ID to reject: ");
        String requestID = scanner.nextLine();
        boolean success = requestService.rejectRequest(requestID);
        if(success) System.out.println("Request " + requestID + "successfully rejected");
        else{
            System.out.println("Failure while rejecting the request, check request ID");
        }
    }
}
