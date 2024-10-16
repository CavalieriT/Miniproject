package miniproject;

import java.util.HashMap;

public class AuthService {
    private HashMap<String, User> users = new HashMap<>();

    public AuthService() {
        // Create users (for demo purposes, manually adding users)
        users.put("john", new User("john", "password", UserRole.CUSTOMER_SERVICE));
        users.put("mary", new User("mary", "password", UserRole.SENIOR_CUSTOMER_SERVICE));
        users.put("paul", new User("paul", "password", UserRole.FINANCIAL_MANAGER));
    }

    // Authenticate user based on username and password
    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // Successful login
        }
        return null;  // Failed login
    }
}
