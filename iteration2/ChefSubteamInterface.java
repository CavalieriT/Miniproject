package miniproject;
import java.util.HashMap;
import java.util.Scanner;

public class ChefSubteamInterface {
    private static applicationServer taskServer = new applicationServer();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exitCondition = true;

        while(exitCondition){
            System.out.println("1. View Task");
            System.out.println("2. Add comments");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    viewTask();
                    break;
                case 2:
                    addComments();
                    break;
                case 3:
                    exitCondition = false;
                    System.out.println("Exiting the system");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void viewTask(){
        HashMap<String, Task> tasks = applicationServer.getMusicSubteamTasks();
        if(tasks.isEmpty()){
            System.out.println("No tasks found");
        }
        else{
            for(Task task : tasks.values()){
                System.out.println("Task ID: " + task.getTaskID());
                System.out.println("Task description: " + task.getApplication().getApplicationText());
            }
        }
    }

    private static void sendComments(String taskID){
        boolean success = applicationService.sendtoComment(taskID);
        if (success) {
            System.out.println("Comment sent successfully");
        } else {
            System.out.println("Comment to send application");
        }
    }
    public static void addComments(){
        System.out.println("Enter the task ID: ");
        String taskID = scanner.nextLine();
        System.out.println("Enter the comments: ");
        String comments = scanner.nextLine();

        boolean success = applicationServer.addComments(taskID, comments);
        if(success){
            System.out.println("Comments added successfully");
            sendComments(taskID);
        }
        else{
            System.out.println("Failed to add comments");
        }
    }
}
