package miniprojectswingit3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class HRInterfaceSwing {
    private static HRRequestService hrRequestService = new HRRequestService();

    public static void main(String[] args) {
        JFrame frame = new JFrame("HR Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton checkRequestsButton = new JButton("Check HR Requests");
        JButton acceptRequestButton = new JButton("Accept HR Request");
        JButton exitButton = new JButton("Exit");

        checkRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkHRRequests();
            }
        });

        acceptRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acceptHRRequest();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.add(checkRequestsButton);
        panel.add(acceptRequestButton);
        panel.add(exitButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void checkHRRequests() {
        Map<String, HRRequest> hrRequests = hrRequestService.getHRRequests_hr();
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

    private static void acceptHRRequest() {
        String hrRequestID = JOptionPane.showInputDialog("Enter the HR request ID to accept:");
        if (hrRequestID != null && hrRequestService.acceptHRRequest(hrRequestID)) {
            JOptionPane.showMessageDialog(null, "HR request accepted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to accept HR request. Check the request ID.");
        }
    }
}