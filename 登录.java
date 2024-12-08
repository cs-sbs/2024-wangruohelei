import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

enum UserRole {
    USER, ADMIN
}

class User {
    private final String username;
    private final String password;
    private final UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }
}

class UserManager {
    private final Map<String, User> users = new HashMap<>();

    public void registerUser(String username, String password, UserRole role) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
        } else {
            users.put(username, new User(username, password, role));
            System.out.println("Registration successful!");
        }
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid username or password!");
            return null;
        }
    }
}
