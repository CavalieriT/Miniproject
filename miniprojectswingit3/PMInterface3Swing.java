package miniprojectswingit3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PMInterface3Swing {
    private static TaskServer taskServer = new TaskServer();
    private static BudgetRequestService budgetRequestService = new BudgetRequestService();
    private static List<MSBemployee> employees = new ArrayList<>();

    private static void addEmployees() {
        employees.add(new MSBemployee("Thomas", true));
        employees.add(new MSBemployee("Muller", false));
        // Add more employees as needed
    }

    public static void main(String[] args) {
        addEmployees();
        JFrame frame = new JFrame("Production Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton checkAvailabilityButton = new JButton("Check Availability");
        JButton writeHRRequestButton = new JButton("Write HR Request");
        JButton checkHRRequestsButton = new JButton("Check HR Requests");
        JButton sendTaskButton = new JButton("Send Task");
        JButton checkCommentsButton = new JButton("Check Comments");
        JButton writeBudgetRequestButton = new JButton("Write Budget Request");
        JButton checkBudgetRequestsButton = new JButton("Check Budget Requests");
        JButton exitButton = new JButton("Exit");

        checkAvailabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAvailability();
            }
        });

        writeHRRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeHRRequest();
            }
        });

        checkHRRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkHRRequests();
            }
        });

        sendTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendTask();
            }
        });

        checkCommentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkComments();
            }
        });

        writeBudgetRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeBudgetRequest();
            }
        });

        checkBudgetRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBudgetRequests();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.add(checkAvailabilityButton);
        panel.add(writeHRRequestButton);
        panel.add(checkHRRequestsButton);
        panel.add(sendTaskButton);
        panel.add(checkCommentsButton);
        panel.add(writeBudgetRequestButton);
        panel.add(checkBudgetRequestsButton);
        panel.add(exitButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void checkAvailability() {
        StringBuilder availabilityList = new StringBuilder("Employee Availability:\n");
        for (MSBemployee employee : employees) {
            availabilityList.append(employee.getName()) // Assuming you have a getName() method
                            .append(" is ")
                            .append(employee.isAvailable() ? "available" : "not available") // Assuming you have an isAvailable() method
                            .append("\n");
        }
        JOptionPane.showMessageDialog(null, availabilityList.toString());
    }

    private static void writeHRRequest() {
        String hrRequestID = JOptionPane.showInputDialog("Enter the HR request ID:");
        String contractType = JOptionPane.showInputDialog("Enter the contract type:");
        String departmentInput = JOptionPane.showInputDialog("Enter the department:");
        Department department = Department.valueOf(departmentInput.toUpperCase());
        String yearOfExperience = JOptionPane.showInputDialog("Enter the year of experience:");
        String jobTitle = JOptionPane.showInputDialog("Enter the job title:");
        String jobDescription = JOptionPane.showInputDialog("Enter the job description:");

        HRRequestService.createHRRequest(Integer.parseInt(hrRequestID), contractType, department, yearOfExperience, jobTitle, jobDescription);
        JOptionPane.showMessageDialog(null, "HR request created successfully.");
    }

    private static void checkHRRequests() {
        Map<String, HRRequest> hrRequests = HRRequestService.getHRRequests_accepted();
        StringBuilder requests = new StringBuilder("HR Requests:\n");
        if (hrRequests.isEmpty()) {
            requests.append("No HR requests found.");
        } else {
            for (HRRequest hrRequest : hrRequests.values()) {
                requests.append("HR Request ID: ").append(hrRequest.getHRRequestID()).append("\n");
                requests.append("Contract Type: ").append(hrRequest.getContractType()).append("\n");
                requests.append("Department: ").append(hrRequest.getDepartment()).append("\n");
                requests.append("Year of Experience: ").append(hrRequest.getYearOfExperience()).append("\n");
                requests.append("Job Title: ").append(hrRequest.getJobTitle()).append("\n");
                requests.append("Job Description: ").append(hrRequest.getJobDescription()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, requests.toString());
    }

    private static void sendTask() {
        String manager = "Production Manager";
        String taskID = JOptionPane.showInputDialog("Enter the task ID:");
        String taskDescription = JOptionPane.showInputDialog("Enter the task description:");
        String worker = JOptionPane.showInputDialog("Enter the worker's name:");
        String priorityInput = JOptionPane.showInputDialog("Enter the priority (HIGH, MEDIUM, LOW):");
        Priority priority = Priority.valueOf(priorityInput.toUpperCase());

        taskServer.createTask(taskID, taskDescription, worker, priority, manager);
        JOptionPane.showMessageDialog(null, "Task sent successfully.");
    }

    private static void checkComments() {
        HashMap<String, String> comments = taskServer.getComments_out();
        StringBuilder commentsList = new StringBuilder("Comments:\n");
        if (comments.isEmpty()) {
            commentsList.append("No comments found.");
        } else {
            for (String comment : comments.values()) {
                commentsList.append("Comment: ").append(comment).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, commentsList.toString());
    }

    private static void writeBudgetRequest() {
        String departmentInput = JOptionPane.showInputDialog("Enter Department:");
        Department department = Department.valueOf(departmentInput.toUpperCase());
        String projectID = JOptionPane.showInputDialog("Enter Project ID:");
        String requiredAmountInput = JOptionPane.showInputDialog("Enter Required Amount:");
        double requiredAmount = Double.parseDouble(requiredAmountInput);
        String reason = JOptionPane.showInputDialog("Enter Reason:");

        budgetRequestService.createBudgetRequest(department, projectID, requiredAmount, reason);
        JOptionPane.showMessageDialog(null, "Budget request created successfully.");
    }

    private static void checkBudgetRequests() {
        HashMap<String, BudgetRequest> budgetRequests = budgetRequestService.getBudgetRequests_out();
        StringBuilder requests = new StringBuilder("Budget Requests:\n");
        if (budgetRequests.isEmpty()) {
            requests.append("No budget requests found.");
        } else {
            for (BudgetRequest budgetRequest : budgetRequests.values()) {
                if (budgetRequest.getDepartment() == Department.PRODUCTION) {
                    requests.append("Budget Request ID: ").append(budgetRequest.getProjectID()).append("\n");
                    requests.append("Department: ").append(budgetRequest.getDepartment()).append("\n");
                    requests.append("Required Amount: ").append(budgetRequest.getRequiredAmount()).append("\n");
                    requests.append("Reason: ").append(budgetRequest.getReason()).append("\n\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, requests.toString());
    }
}
