package miniproject;
import java.util.Scanner;
import java.util.Map;

public class FMInterface {
    private static RequestService requestService = new RequestService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Financial Manager");
            System.out.println("1. View requests");
            System.out.println("2. Provide feedback for a request");
            System.out.println("3. Redirect request to administration manager");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewRequests();
                    break;
                case 2:
                    writeFeedback();
                    break;
                case 3:
                    redirectRequestToAM();
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
        Map<String,Request> FMrequests = requestService.getSCSOrequests();
        if(FMrequests.isEmpty()) System.out.println("No new requests.");
        else{
            System.out.println("Requests for Financial Manager: ");
            for(Request request: FMrequests.values()){
                System.out.println("Request ID: "+ request.getRecordNumber());
                System.out.println("Client name" + request.getClientName());
                System.out.println("Event type" + request.getEventType());
                System.out.println("Starting date" + request.getFrom());
                System.out.println("Ending date:" + request.getTo());
                System.out.println("Expected attendees" + request.getExpectedAttendees());
                System.out.println("Preferences: " + request.getPreferences());
                System.out.println("Budget: " + request.getBudget());
                System.out.println("Status: " + request.getRequestStatus());
            }
        }
    }

    private static void writeFeedback(){
        System.out.print("Enter Request ID to provide feedback: ");
        String requestID = scanner.nextLine();
        System.out.print("Enter feedback: ");
        String feedback = scanner.nextLine();

        boolean success = requestService.writeFeedback(requestID, feedback);
        if (success) {
            System.out.println("Feedback has been added to request " + requestID + ".");
        } else {
            System.out.println("Failed to add feedback, check the request ID.");
        }
    }

    private static void redirectRequestToAM(){
        System.out.print("Enter Request ID to redirect: ");
        String requestID = scanner.nextLine();
        boolean success = requestService.passToAdministrationManager(requestID);
        if (success) {
            System.out.println("Request " + requestID + " has been redirected to the Administration Manager.");
        } else {
            System.out.println("Failed to pass request, check the request ID.");
        }
    }
}
