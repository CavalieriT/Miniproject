package miniprojectswingit3;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();

    public static void main(String[] args) {
        System.out.println("Login:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loggedInUser = authService.login(username, password);
        if (loggedInUser != null) {
            System.out.println("Welcome, " + loggedInUser.getUsername());
            showMenu(loggedInUser.getRole());
        } else {
            System.out.println("Invalid credentials");
        }
    }

    private static void showMenu(UserRole role) {
        switch (role) {
            case CUSTOMER_SERVICE:
                System.out.println("1. Register Client");
                System.out.println("2. Create New Request");
                break;

            case SENIOR_CUSTOMER_SERVICE:
                System.out.println("1. Approve/Reject Request");
                break;

            case FINANCIAL_MANAGER:
                System.out.println("1. Review Financial Request");
                System.out.println("2. Provide Feedback");
                break;

            case ADMIN_MANAGER:
                System.out.println("1. Approve/Reject Request");
                System.out.println("2. Finalize Event");
                break;

            default:
                System.out.println("No permissions for this role.");
        }
    }
}
