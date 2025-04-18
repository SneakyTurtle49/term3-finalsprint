package org.keyin.user;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // Fetch a user by username (for login)
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_email"),
                            rs.getString("user_phone"),
                            rs.getString("user_address"),
                            rs.getString("user_role")
                    );
                }
            }
        }
        return null;
    }

    // Register a new user
    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_name, user_password, user_email, user_phone, user_address, user_role) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // hashed before passing
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());
            pstmt.executeUpdate();
        }
    }

    // Get all users (for Admin)
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_email"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_role")
                ));
            }
        }
        return userList;
    }

    // Delete user by ID (for Admin)
    public void deleteUserById(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }
}