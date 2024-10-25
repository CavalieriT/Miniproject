package miniprojectswingit3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FMInterface3Swing {
    private static RequestService requestService = new RequestService();
    private static BudgetRequestService budgetRequestService = new BudgetRequestService();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Financial Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton viewRequestsButton = new JButton("View Requests");
        JButton writeFeedbackButton = new JButton("Provide Feedback");
        JButton redirectRequestButton = new JButton("Redirect Request");
        JButton viewBudgetRequestsButton = new JButton("View Budget Requests");
        JButton acceptBudgetRequestButton = new JButton("Accept Budget Request");
        JButton rejectBudgetRequestButton = new JButton("Reject Budget Request");
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

        viewBudgetRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewFinancialRequest();
            }
        });

        acceptBudgetRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acceptFinancialRequest();
            }
        });

        rejectBudgetRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rejectFinancialRequest();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.add(viewRequestsButton);
        panel.add(writeFeedbackButton);
        panel.add(redirectRequestButton);
        panel.add(viewBudgetRequestsButton);
        panel.add(acceptBudgetRequestButton);
        panel.add(rejectBudgetRequestButton);
        panel.add(exitButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void viewRequests() {
        Map<String, Request> FMrequests = requestService.getFMrequests();
        StringBuilder requests = new StringBuilder("Requests for Financial Manager:\n");
        if (FMrequests.isEmpty()) {
            requests.append("No new requests.");
        } else {
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

    private static void viewFinancialRequest() {
        HashMap<String, BudgetRequest> FMrequests = budgetRequestService.getBudgetRequests_in();
        StringBuilder requests = new StringBuilder("Budget Requests for Financial Manager:\n");
        if (FMrequests.isEmpty()) {
            requests.append("No new budget requests.");
        } else {
            for (BudgetRequest budgetRequest : FMrequests.values()) {
                requests.append("Department: ").append(budgetRequest.getDepartment()).append("\n");
                requests.append("Project ID: ").append(budgetRequest.getProjectID()).append("\n");
                requests.append("Required amount: ").append(budgetRequest.getRequiredAmount()).append("\n");
                requests.append("Reason: ").append(budgetRequest.getReason()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, requests.toString());
    }

    private static void acceptFinancialRequest() {
        String requestID = JOptionPane.showInputDialog("Enter Request ID to accept:");
        boolean success = budgetRequestService.acceptBudgetRequest(requestID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Request " + requestID + " has been accepted.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to accept request, check the request ID.");
        }
    }

    private static void rejectFinancialRequest() {
        String requestID = JOptionPane.showInputDialog("Enter Request ID to reject:");
        boolean success = budgetRequestService.rejectBudgetRequest(requestID);
        if (success) {
            JOptionPane.showMessageDialog(null, "Request " + requestID + " has been rejected.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to reject request, check the request ID.");
        }
    }
}