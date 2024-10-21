package miniproject;
import java.util.Map;
import java.util.Scanner;

public class HRInterface {
    private static HRRequestService hrRequestService = new HRRequestService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitCondition = true;

        while(exitCondition){
            System.out.println("\nWelcome, HR");
            System.out.println("1. Check HR requests");
            System.out.println("2. Accept HR request");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice){
                case 1:
                    checkHRRequests();
                    break;
                case 2:
                    acceptHRRequest();
                    break;
                case 3:
                    exitCondition = false;
                    System.out.println("Exiting the system");
                    break;
            }
        }
    }

    private static void checkHRRequests(){
        Map<String, HRRequest> hrRequests = HRRequestService.getHRRequests_hr();
        if(hrRequests.isEmpty()){
            System.out.println("No HR requests found");
        } else {
            for(HRRequest hrRequest : hrRequests.values()){
                System.out.println("HR Request ID: " + hrRequest.getHrRequestID());
                System.out.println("Contract Type: " + hrRequest.getContractType());
                System.out.println("Department: " + hrRequest.getDepartment());
                System.out.println("Year of Experience: " + hrRequest.getYearOfExperience());
                System.out.println("Job Title: " + hrRequest.getJobTitle());
                System.out.println("Job Description: " + hrRequest.getJobDescription());
            }
        }
    }

    private static void acceptHRRequest(){
        System.out.println("Enter the HR request ID to accept: ");
        String hrRequestID = scanner.nextLine();
        if(hrRequestService.acceptHRRequest(String.valueOf(hrRequestID))){
            System.out.println("HR request accepted successfully");
        } else {
            System.out.println("Failed to accept HR request");
        }
    }
}