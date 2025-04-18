package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// DAOs are responsible for handling the interactions with the database
public class MembershipDAO {

    // This method adds a membership to the database using a prepared statement.
    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (membership_type, membership_price, membership_description, date_purchased, user_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, membership.getMembershipType());
            pstmt.setDouble(2, membership.getMembershipPrice());
            pstmt.setString(3, membership.getMembershipDescription());
            pstmt.setDate(4, Date.valueOf(membership.getDatePurchased())); // LocalDate to SQL Date
            pstmt.setInt(5, membership.getUserId());

            pstmt.executeUpdate();
        }
    }
}