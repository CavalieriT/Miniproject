package miniproject;
import java.util.Scanner;
import java.util.Map;

public class FMInterfaceGUI {
    private static RequestService requestService = new RequestService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Financial Manager");
            System.out.println("1. View requests");
            System.out.println("2. Provide feedback for a request");
            System.out.println("3. Redirect request to Administration Manager");
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

    private static void viewRequests() {
        Map<String, Request> FMrequests = requestService.getFMrequests();
        if (FMrequests.isEmpty()) System.out.println("No new requests.");
        else {
            System.out.println("Requests for Financial Manager: ");
            for (Request request : FMrequests.values()) {
                System.out.println("Request ID: " + request.getRecordNumber());
                System.out.println("Client name: " + request.getClientName());
                System.out.println("Event type: " + request.getEventType());
                System.out.println("Starting date: " + request.getFrom());
                System.out.println("Ending date: " + request.getTo());
                System.out.println("Expected attendees: " + request.getExpectedAttendees());
                System.out.println("Preferences: " + request.getPreferences());
                System.out.println("Budget: " + request.getBudget());
                System.out.println("Request status: " + request.getRequestStatus());
                System.out.println("Feedback: " + request.getFeedback());
            }
        }
    }

    private static void writeFeedback() {
        System.out.println("Enter request ID to provide feedback: ");
        String requestID = scanner.nextLine();
        System.out.println("Enter feedback: ");
        String feedback = scanner.nextLine();
        boolean success = requestService.writeFeedback(requestID, feedback);
        if (success) System.out.println("Feedback for request " + requestID + " has been recorded.");
        else System.out.println("Failed to record feedback, check request ID.");
    }

    private static void redirectRequestToAM() {
        System.out.println("Enter request ID to redirect to Administration Manager: ");
        String requestID = scanner.nextLine();
        boolean success = requestService.passToAdministrationManager(requestID);
        if (success) System.out.println("Request " + requestID + " redirected to Administration Manager.");
        else System.out.println("Failed to redirect request, check request ID.");
    }
}
