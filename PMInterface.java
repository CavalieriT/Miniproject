package miniproject;
import java.util.Scanner;
import java.util.HashMap;
import java.sql.Date;

public class PMInterface {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolen exitCondition = true;

        while (exitCondition) {
            System.out.println("\nWelcome, Production Manager");
            System.out.println("1. Check availability");
            System.out.println("2. Write HR request");
            System.out.println("3. Fill application");
            System.out.println("4. Exit");
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
                    exitCondition = false;
                    System.out.println("Exiting the system");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    
    }

    private static void sendApplication(){
        boolean success = applicationService.sendtoMusicSubteam(application app);
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

        applicationService.createApplication(applicationID, applicationText, subteam);
        sendApplication();
    }
}
