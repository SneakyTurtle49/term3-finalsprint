package org.keyin.user;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    // Hash password
    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verify password
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    // Register user
    public void registerUser(String username, String password, String email, String phone, String address, String role) {
        String hashedPassword = hashPassword(password);
        User newUser = new User(username, hashedPassword, email, phone, address, role);
        try {
            userDao.registerUser(newUser);
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    // Login user
    public User login(String username, String password) {
        try {
            User user = userDao.getUserByUsername(username);
            if (user != null && verifyPassword(password, user.getPassword())) {
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }

    // Get all users
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());
            return null;
        }
    }

    // Delete user
    public void deleteUserById(int userId) {
        try {
            userDao.deleteUserById(userId);
            System.out.println("User deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}
