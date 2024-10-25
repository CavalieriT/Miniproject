package miniprojectswingit3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MSBInterfaceSwing {
    private static TaskServer taskServer = new TaskServer();
    private static BudgetRequestService budgetRequestService = new BudgetRequestService();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Music Sub-Team");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(0, 1));

            JButton viewTasksButton = new JButton("View Tasks");
            JButton makeBudgetRequestButton = new JButton("Make Additional Budget Request");
            JButton exitButton = new JButton("Exit");

            viewTasksButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewTasks();
                }
            });

            makeBudgetRequestButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    makeBudgetRequest();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame.add(viewTasksButton);
            frame.add(makeBudgetRequestButton);
            frame.add(exitButton);

            frame.setVisible(true);
        });
    }

    private static void viewTasks() {
        Map<String, Task> tasks = taskServer.getMusicSubteamTasks(); // Use the instance method
        StringBuilder taskList = new StringBuilder("Tasks for Music Sub-Team:\n");
        if (tasks.isEmpty()) {
            taskList.append("No tasks available.");
        } else {
            for (Task task : tasks.values()) {
                taskList.append("Task ID: ").append(task.getTaskID()).append("\n");
                taskList.append("Description: ").append(task.getTaskDescription()).append("\n");
                taskList.append("Assigned To: ").append(task.getWorker()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, taskList.toString());
    }

    private static void makeBudgetRequest() {
        try {
            String departmentInput = JOptionPane.showInputDialog("Enter Department:");
            Department department = Department.valueOf(departmentInput.toUpperCase());
            String projectID = JOptionPane.showInputDialog("Enter Project ID:");
            String requiredAmountInput = JOptionPane.showInputDialog("Enter Required Amount:");
            double requiredAmount = Double.parseDouble(requiredAmountInput);
            String reason = JOptionPane.showInputDialog("Enter Reason:");

            BudgetRequest budgetRequest = budgetRequestService.createBudgetRequest(department, projectID, requiredAmount, reason);
            JOptionPane.showMessageDialog(null, "Budget request created successfully: " + budgetRequest.getProjectID());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount entered. Please enter a number.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid department entered.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while creating the budget request: " + e.getMessage());
        }
    }
}
