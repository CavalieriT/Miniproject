
package miniprojectswingit2;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class PMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskServer taskServer = new TaskServer();

    public static void main(String[] args) {
        boolean exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Production Manager");
            System.out.println("1. Check availability");
            System.out.println("2. Write HR request");
            System.out.println("3. Check HR requests");
            System.out.println("4. Send task");
            System.out.println("5. Check comments");
            System.out.println("6. Exit");
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
                    checkHRRequests();
                    break;
                case 4:
                    sendTask();
                    break;
                case 5:
                    checkComments();
                    break;
                case 6:
                    exitCondition = false;
                    System.out.println("Exiting the system");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    
    }

    private static boolean checkAvailability(){     
        return true;
    }

    private static void checkHRRequests(){
        Map<String, HRRequest> hrRequests = HRRequestService.getHRRequests_accepted();
        if(hrRequests.isEmpty()){
            System.out.println("No HR requests found");
        } else {
            for(HRRequest hrRequest : hrRequests.values()){
                if(hrRequest.getDepartment() == Department.PRODUCTION){
                    System.out.println("HR Request ID: " + hrRequest.getHRRequestID());
                    System.out.println("Contract Type: " + hrRequest.getContractType());
                    System.out.println("Department: " + hrRequest.getDepartment());
                    System.out.println("Year of Experience: " + hrRequest.getYearOfExperience());
                    System.out.println("Job Title: " + hrRequest.getJobTitle());
                    System.out.println("Job Description: " + hrRequest.getJobDescription());
                }
            }
            System.out.println("Request accepted successfully");
        }
        
    }

    private static void sendTask(){
        String manager = "Production Manager";
        System.out.println("Create the task to send");
        System.out.println("Enter the task ID: ");
        String TaskID = String.valueOf(scanner.nextLine());
        System.out.println("Fill the task description: ");
        String Taskdescription = scanner.nextLine();
        System.out.println("Choose the worker:");
        String worker = scanner.nextLine();
        System.out.println("Choose the priority: ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        taskServer.createTask(TaskID, Taskdescription, worker, priority, manager);
    }

    private static void sendHRRequest(){
        System.out.println("Write HR request: ");
        System.out.println("Enter the HR request ID: ");
        String hrRequestID = String.valueOf(scanner.nextLine());
        boolean success = HRRequestService.sendtoHR(hrRequestID);
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
        HashMap<String,String> comment_to_check;
        comment_to_check = taskServer.getComments_out();
        if(comment_to_check.isEmpty()){
            System.out.println("No comments found");
        }else {
            for(String comment : comment_to_check.values()){
                System.out.println("Comment: " + comment);
            }
        }
    }
}
