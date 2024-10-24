package miniproject;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;

public class PMInterface {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolen exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Production Manager");
            System.out.println("1. Check availability");
            System.out.println("2. Write HR request");
            System.out.println("3. Check HR requests");
            System.out.println("4. Fill application");
            System.out.println("5. Check comments");
            System.out.println("6. Set application status to OPEN");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    checkAvailability();
                    break;
                case 2:
                    writeHRRequest();
                    break;
                case 3:
                    fillApplication();
                    break;
                case 4:
                    acceptHRRequest();
                    break;
                case 5:
                    checkComments();
                    break;
                case 6:
                    setApplicationStatusOPEN();
                    break;
                case 7:
                    exitCondition = false;
                    System.out.println("Exiting the system");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    
    }
    
    private static void checkHRRequests(){
        Map<String, HRRequest> hrRequests = HRRequestService.getHRRequests_accepted();
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
            System.out.println("Request accepted successfully");
        }
        
    }
    private static void sendApplication(String appID){
        boolean success = applicationService.sendtoMusicSubteam(appID);
        if (success) {
            System.out.println("Application sent successfully");
        } else {
            System.out.println("Failed to send application");
        }
    }
    private static void fillApplication(){
        System.out.println("Enter the application ID: ");
        int applicationID = Integer.parseINT(scanner.nextLine());
        System.out.println("Fill the application: ");
        String applicationText = scanner.nextLine();
        System.out.println("Choose the subteam:");
        String subteam = scanner.nextLine();

        applicationService.createApplication(applicationID, applicationText, subteam, ApplicationStatus.DEFAULT, Department.PRODUCTION);
        sendApplication(String.valueOf(applicationID));
    }

    private static void sendHRRequest(){
        boolean success = HRRequestService.sendtoHR(String.valueOf(hrRequestID));
        if (success) {
            System.out.println("Request sent successfully");
        } else {
            System.out.println("Failed to send request");
        }
    }

    private static void writeHRRequest(){
        System.out.println("Write HR request: ");
        System.out.println("Enter the HR request ID: ");
        int hrRequestID = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the contract type: ");
        String contractType = scanner.nextLine();
        System.out.println("Enter the requestind department: ");
        Department department = Department.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Enter the Year of experience: ");
        String yearOfExperience = scanner.nextLine();
        System.out.println("Job title: ");
        String jobTitle = scanner.nextLine();
        System.out.println("Enter the job description: ");
        String jobDescription = scanner.nextLine();

        HRRequestService.createHRRequest(hrRequestID, contractType, department, yearOfExperience, jobTitle, jobDescription);
        System.out.println("HR request created successfully");
        sendHRRequest();
    }

    private static void checkComments(){
        Hashmap<String, String> comment_to_check = applicationService.getComments_out();
        if(comment_to_check.isEmpty()){
            System.out.println("No comments found");
        }else {
            for(String comment : comment_to_check.values()){
                System.out.println("Comment: " + comment);
            }
        }
    }

        private static void setApplicationStatusOPEN(){
        System.out.println("Enter the application ID: ");
        int applicationID = Integer.parseInt(scanner.nextLine());
        applicationServer.setApplicationStatus(String.valueOf(applicationID), ApplicationStatus.OPEN);
        System.out.println("Application status set to OPEN");
    }
}

