package miniprojectswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class SCSOInterfaceSwing {
    private static RequestService requestService = new RequestService();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Senior Customer Service Officer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(0, 1));

            JButton viewRequestsButton = new JButton("View Requests");
            JButton rejectRequestButton = new JButton("Reject a Request");
            JButton redirectRequestButton = new JButton("Redirect Request to Financial Manager");
            JButton exitButton = new JButton("Exit");

            viewRequestsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewRequests();
                }
            });

            rejectRequestButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    rejectRequest();
                }
            });

            redirectRequestButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    redirectRequestToFM();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame.add(viewRequestsButton);
            frame.add(rejectRequestButton);
            frame.add(redirectRequestButton);
            frame.add(exitButton);

            frame.setVisible(true);
        });
    }

    private static void viewRequests() {
        Map<String, Request> SCSOrequests = requestService.getSCSOrequests();
        StringBuilder requests = new StringBuilder();
        if (SCSOrequests.isEmpty()) {
            requests.append("No new requests.");
        } else {
            requests.append("Requests for Senior Customer Service Officer:\n");
            for (Request request : SCSOrequests.values()) {
                requests.append("Request ID: ").append(request.getRecordNumber()).append("\n");
                requests.append("Client name: ").append(request.getClientName()).append("\n");
                requests.append("Event type: ").append(request.getEventType()).append("\n");
                requests.append("Starting date: ").append(request.getFrom()).append("\n");
                requests.append("Ending date: ").append(request.getTo()).append("\n");
                requests.append("Expected attendees: ").append(request.getExpectedAttendees()).append("\n");
                requests.append("Preferences: ").append(request.getPreferences()).append("\n");
                requests.append("Budget: ").append(request.getBudget()).append("\n");
                requests.append("Request status: ").append(request.getRequestStatus()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, requests.toString());
    }

    private static void rejectRequest() {
        String requestID = JOptionPane.showInputDialog("Enter request ID to reject:");
        boolean success = requestService.rejectRequest(requestID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Request " + requestID + " successfully rejected.");
        } else {
            JOptionPane.showMessageDialog(null, "Failure while rejecting the request, check request ID.");
        }
    }

    private static void redirectRequestToFM() {
        String requestID = JOptionPane.showInputDialog("Enter request ID to redirect:");
        boolean success = requestService.redirectToFinancialManager(requestID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Request " + requestID + " redirected to financial manager.");
        } else {
            JOptionPane.showMessageDialog(null, "Failure while redirecting request, check request ID.");
        }
    }
}