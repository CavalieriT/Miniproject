package miniprojectswingit2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class FMInterfaceSwing {
    private static RequestService requestService = new RequestService();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Financial Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(0, 1));

            JButton viewRequestsButton = new JButton("View Requests");
            JButton writeFeedbackButton = new JButton("Provide Feedback for a Request");
            JButton redirectRequestButton = new JButton("Redirect Request to Administration Manager");
            JButton exitButton = new JButton("Exit");

            viewRequestsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewRequests();
                }
            });

            writeFeedbackButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writeFeedback();
                }
            });

            redirectRequestButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    redirectRequestToAM();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame.add(viewRequestsButton);
            frame.add(writeFeedbackButton);
            frame.add(redirectRequestButton);
            frame.add(exitButton);

            frame.setVisible(true);
        });
    }

    private static void viewRequests() {
        Map<String, Request> FMrequests = requestService.getSCSOrequests();
        StringBuilder requests = new StringBuilder();
        if (FMrequests.isEmpty()) {
            requests.append("No new requests.");
        } else {
            requests.append("Requests for Financial Manager:\n");
            for (Request request : FMrequests.values()) {
                requests.append("Request ID: ").append(request.getRecordNumber()).append("\n");
                requests.append("Client name: ").append(request.getClientName()).append("\n");
                requests.append("Event type: ").append(request.getEventType()).append("\n");
                requests.append("Starting date: ").append(request.getFrom()).append("\n");
                requests.append("Ending date: ").append(request.getTo()).append("\n");
                requests.append("Expected attendees: ").append(request.getExpectedAttendees()).append("\n");
                requests.append("Preferences: ").append(request.getPreferences()).append("\n");
                requests.append("Budget: ").append(request.getBudget()).append("\n");
                requests.append("Status: ").append(request.getRequestStatus()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, requests.toString());
    }

    private static void writeFeedback() {
        String requestID = JOptionPane.showInputDialog("Enter Request ID to provide feedback:");
        String feedback = JOptionPane.showInputDialog("Enter feedback:");

        boolean success = requestService.writeFeedback(requestID, feedback);
        if (success) {
            JOptionPane.showMessageDialog(null, "Feedback has been added to request " + requestID + ".");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add feedback, check the request ID.");
        }
    }

    private static void redirectRequestToAM() {
        String requestID = JOptionPane.showInputDialog("Enter Request ID to redirect:");
        boolean success = requestService.passToAdministrationManager(requestID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Request " + requestID + " has been redirected to the Administration Manager.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to pass request, check the request ID.");
        }
    }
}