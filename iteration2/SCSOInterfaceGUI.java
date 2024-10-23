package miniproject;
import java.util.Map;
import java.util.Scanner;

public class SCSOInterfaceGUI {
    private static RequestService requestService = new RequestService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Senior Customer Service Officer");
            System.out.println("1. View requests");
            System.out.println("2. Reject a request");
            System.out.println("3. Redirect request to financial manager");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewRequests();
                    break;
                case 2:
                    rejectRequest();
                    break;
                case 3:
                    redirectRequestToFM();
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

    private static void viewRequests() {
        Map<String, Request> SCSOrequests = requestService.getSCSOrequests();
        if (SCSOrequests.isEmpty()) System.out.println("No new requests.");
        else {
            System.out.println("Requests for Senior Customer Service Officer: ");
            for (Request request : SCSOrequests.values()) {
                System.out.println("Request ID: " + request.getRecordNumber());
                System.out.println("Client name: " + request.getClientName());
                System.out.println("Event type: " + request.getEventType());
                System.out.println("Starting date: " + request.getFrom());
                System.out.println("Ending date: " + request.getTo());
                System.out.println("Expected attendees: " + request.getExpectedAttendees());
                System.out.println("Preferences: " + request.getPreferences());
                System.out.println("Budget: " + request.getBudget());
                System.out.println("Request status: " + request.getRequestStatus());
            }
        }
    }

    private static void rejectRequest() {
        System.out.println("Enter request ID to reject: ");
        String requestID = scanner.nextLine();
        boolean success = requestService.rejectRequest(requestID);
        if (success) System.out.println("Request " + requestID + " successfully rejected.");
        else {
            System.out.println("Failure while rejecting the request, check request ID.");
        }
    }

    private static void redirectRequestToFM() {
        System.out.println("Enter request ID to redirect to financial manager: ");
        String requestID = scanner.nextLine();
        boolean success = requestService.redirectToFinancialManager(requestID);
        if (success) System.out.println("Request " + requestID + " redirected to financial manager.");
        else {
            System.out.println("Failure while redirecting request, check request ID.");
        }
    }
}
