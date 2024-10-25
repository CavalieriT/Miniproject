package miniprojectswingit3;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AMInterfaceSwing {
    private static RequestService requestService = new RequestService();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Administration Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JButton viewRequestsButton = new JButton("View Requests");
        JButton acceptRequestButton = new JButton("Accept a Request");
        JButton rejectRequestButton = new JButton("Reject a Request");
        JButton exitButton = new JButton("Exit");

        viewRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewRequests();
            }
        });

        acceptRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acceptRequest();
            }
        });

        rejectRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rejectRequest();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.add(viewRequestsButton);
        panel.add(acceptRequestButton);
        panel.add(rejectRequestButton);
        panel.add(exitButton);
        
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void viewRequests() {
        Map<String, Request> AMrequests = requestService.getAMrequests();
        StringBuilder requests = new StringBuilder("Requests for Administration Manager:\n");
        if (AMrequests.isEmpty()) {
            requests.append("No new requests.");
        } else {
            for (Request request : AMrequests.values()) {
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

    private static void acceptRequest() {
        String requestId = JOptionPane.showInputDialog("Enter Request ID to accept:");
        boolean success = requestService.acceptRequest(requestId);
        if (success) {
            JOptionPane.showMessageDialog(null, "Request " + requestId + " has been accepted.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to accept request, check the request ID.");
        }
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
}
