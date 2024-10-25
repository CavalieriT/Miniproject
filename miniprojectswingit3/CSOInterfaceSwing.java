package miniprojectswingit3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;

public class CSOInterfaceSwing {
    private static RequestService requestService = new RequestService();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Customer Service Officer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(0, 1));

            JButton createRequestButton = new JButton("Create a New Request");
            JButton sendRequestButton = new JButton("Send Request to Senior Customer Service Officer");
            JButton viewRequestsButton = new JButton("View All Requests");
            JButton exitButton = new JButton("Exit");

            createRequestButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createClientRequest();
                }
            });

            sendRequestButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendRequestToSCSO();
                }
            });

            viewRequestsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewCSORequests();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame.add(createRequestButton);
            frame.add(sendRequestButton);
            frame.add(viewRequestsButton);
            frame.add(exitButton);

            frame.setVisible(true);
        });
    }

    public static void createClientRequest() {
        try {
            int recordNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the client's record number:"));
            String clientID = JOptionPane.showInputDialog("Enter the client's name:");
            String eventType = JOptionPane.showInputDialog("Enter the event type:");
            Date eventFromDate = Date.valueOf(JOptionPane.showInputDialog("Enter the event's starting date (YYYY-MM-DD):"));
            Date eventToDate = Date.valueOf(JOptionPane.showInputDialog("Enter the event's ending date (YYYY-MM-DD):"));
            int expectedAttendees = Integer.parseInt(JOptionPane.showInputDialog("Enter the expected number of attendees:"));
            Preferences preferences = Preferences.valueOf(JOptionPane.showInputDialog("Enter the preferences (e.g., FOOD, DRINKS):").toUpperCase());
            int budget = Integer.parseInt(JOptionPane.showInputDialog("Enter the budget:"));

            UserRole role = UserRole.CUSTOMER_SERVICE;
            RequestStatus status = RequestStatus.IN_PROGRESS;

            requestService.createRequest(recordNumber, clientID, eventType, eventFromDate, eventToDate, expectedAttendees, preferences, budget, status, role);
            JOptionPane.showMessageDialog(null, "Request created with request ID: " + recordNumber);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating request: " + e.getMessage());
        }
    }

    public static void sendRequestToSCSO() {
        String requestID = JOptionPane.showInputDialog("Enter the request record number:");
        boolean success = requestService.sendToSCSO(requestID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Request " + requestID + " sent successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to send request.");
        }
    }

    private static void viewCSORequests() {
        HashMap<String, Request> CSOrequests = requestService.getCSOrequests();
        StringBuilder requests = new StringBuilder();
        if (CSOrequests.isEmpty()) {
            requests.append("No new requests.");
        } else {
            requests.append("Requests for Customer Service Officer:\n");
            for (Request request : CSOrequests.values()) {
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
}