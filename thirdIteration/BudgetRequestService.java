package miniproject;
import java.util.HashMap;
import java.util.Date;

public class BudgetRequestService {
    private HashMap<String, BudgetRequest> budgetRequests_in = new HashMap<>();
    private HashMap<String, BudgetRequest> budgetRequests_out = new HashMap<>();

    public boolean acceptBudgetRequest(String requestID){
        BudgetRequest request = budgetRequests_in.get(requestID);
        if(request != null){
            budgetRequests_out.put(requestID, request);
            budgetRequests_in.remove(requestID);
            return true;
        }
        return false;
    }

    public boolean rejectBudgetRequest(String requestID){
        BudgetRequest request = budgetRequests_in.get(requestID);
        if(request != null){
            budgetRequests_in.remove(requestID, request);
            return true;
        }
    }

    public HashMap<String, BudgetRequest> getBudgetRequests_in(){
        return budgetRequests_in;
    }

    public HashMap<String, BudgetRequest> getBudgetRequests_out(){
        return budgetRequests_out;
    }  
    
}
