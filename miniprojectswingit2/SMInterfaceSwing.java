package miniprojectswingit2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SMInterfaceSwing {
    private static TaskServer taskServer = new TaskServer();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Service Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton checkAvailabilityButton = new JButton("Check Availability");
        JButton writeHRRequestButton = new JButton("Write HR Request");
        JButton checkHRRequestsButton = new JButton("Check HR Requests");
        JButton sendTaskButton = new JButton("Send Task");
        JButton checkCommentsButton = new JButton("Check Comments");
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
        panel.add(exitButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void checkAvailability() {
        // Implementation for checking availability
        JOptionPane.showMessageDialog(null, "Availability checked.");
    }

    private static void writeHRRequest() {
        String hrRequestID = JOptionPane.showInputDialog("Enter the HR request ID:");
        String contractType = JOptionPane.showInputDialog("Enter the contract type:");
        String departmentInput = JOptionPane.showInputDialog("Enter the requesting department:");
        Department department = Department.valueOf(departmentInput.toUpperCase());
        String yearOfExperience = JOptionPane.showInputDialog("Enter the Year of experience:");
        String jobTitle = JOptionPane.showInputDialog("Job title:");
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
                if (hrRequest.getDepartment() == Department.SERVICES) {
                    requests.append("HR Request ID: ").append(hrRequest.getHRRequestID()).append("\n");
                    requests.append("Contract Type: ").append(hrRequest.getContractType()).append("\n");
                    requests.append("Department: ").append(hrRequest.getDepartment()).append("\n");
                    requests.append("Year of Experience: ").append(hrRequest.getYearOfExperience()).append("\n");
                    requests.append("Job Title: ").append(hrRequest.getJobTitle()).append("\n");
                    requests.append("Job Description: ").append(hrRequest.getJobDescription()).append("\n\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, requests.toString());
    }

    private static void sendTask() {
        String manager = "Service Manager";
        String taskID = JOptionPane.showInputDialog("Enter the task ID:");
        String taskDescription = JOptionPane.showInputDialog("Fill the task description:");
        String worker = JOptionPane.showInputDialog("Choose the worker:");
        String priorityInput = JOptionPane.showInputDialog("Choose the priority:");
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
}