package miniproject;
import java.util.Date;

public class BudgetRequest {
    private Department department;
    private String projectID;
    private double requiredAmount;
    private String reason;

    public BudgetRequest(Department department, String projectID, double requiredAmount, String reason) {
        this.department = department;
        this.projectID = projectID;
        this.requiredAmount = requiredAmount;
        this.reason = reason;
    }

    public Department getDepartment() {
        return department;
    }

    public String getProjectID() {
        return projectID;
    }

    public double getRequiredAmount() {
        return requiredAmount;
    }

    public String getReason() {
        return reason;
    }
}
