package miniprojectswingit2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MSBInterfaceSwing {
    private static TaskServer taskServer = new TaskServer(); // Use the TaskServer instance

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Music Sub-Team");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(0, 1));

            JButton viewTasksButton = new JButton("View Tasks");
            JButton makeBudgetRequestButton = new JButton("Make Additional Budget Request");
            JButton setStatusOpenButton = new JButton("Set Application Status to Open");
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

            setStatusOpenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setStatusOpen();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame.add(viewTasksButton);
            frame.add(makeBudgetRequestButton);
            frame.add(setStatusOpenButton);
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
        String requestDetails = JOptionPane.showInputDialog("Enter details for the additional budget request:");
        if (requestDetails != null && !requestDetails.trim().isEmpty()) {
            // Logic to handle budget request
            JOptionPane.showMessageDialog(null, "Budget request submitted: " + requestDetails);
        } else {
            JOptionPane.showMessageDialog(null, "Budget request cannot be empty.");
        }
    }

    private static void setStatusOpen() {
        // Logic to set the application status to open
        JOptionPane.showMessageDialog(null, "Application status set to open.");
    }
}
