package miniproject;
import java.sql.Date;
import java.util.Scanner;
import java.util.HashMap;

public class CSOInterface {
    private static RequestService requestService = new RequestService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Customer Service Officer");
            System.out.println("1. Create a new request");
            System.out.println("2. Send request to Senior Customer Service Officer");
            System.out.println("3. View all requests");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createClientRequest();
                    break;
                case 2:
                    sendRequestToSCSO();
                    break;
                case 3:
                    viewCSORequests();
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

    private static void createClientRequest(){
        System.out.println("Enter the client's record number: ");
        int recordNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the client's name: ");
        String clientID = scanner.nextLine();
        System.out.println("Enter the event type: ");
        String eventType = scanner.nextLine();
        System.out.println("Enter the event's starting date: ");
        Date eventFromDate = Date.valueOf(scanner.nextLine());
        System.out.println("Enter the event's ending date: ");
        Date eventToDate = Date.valueOf(scanner.nextLine());
        System.out.println("Enter the expected number of attendees: ");
        int expectedAttendees = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the preferences: ");
        Preferences preferences = Preferences.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Enter the budget: ");
        int budget = Integer.parseInt(scanner.nextLine());

        RequestStatus status = RequestStatus.IN_PROGRESS; //the CSO doesnt have power to decide the status

        requestService.createRequest(recordNumber,clientID,eventType,eventFromDate,eventToDate,expectedAttendees,preferences,budget,status);
        System.out.println("Request created with request ID: "+ recordNumber);
    }

    private static void sendRequestToSCSO(){
        System.out.println("Enter the request record number: ");
        String requestID = scanner.nextLine();
        boolean success = requestService.sendToSCSO(requestID);
        if (success) {
            System.out.println("Request"+ requestID + "created successfully.");
        } else {
            System.out.println("Failed to create request.");
        }
    }

    private static void viewCSORequests(){
        HashMap<String,Request> CSOrequests = requestService.getCSOrequests();
        if(CSOrequests.isEmpty()){
            System.out.println("No new requests");
        }
        else{
            System.out.println("Requests for Customer Service Officer: ");
            for(Request request: CSOrequests.values()){
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
            
        }
