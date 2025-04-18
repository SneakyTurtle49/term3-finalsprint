package org.keyin.memberships;


import java.sql.SQLException;
import java.time.LocalDate;
// Service class for membership handle all the business logic
// and only uses the DAO to interact with the database it does not have methods to do so
// you can inject in your dao to use in your service. An example will be in the code
public class MembershipService {
    private final MembershipDAO membershipDAO = new MembershipDAO();

    public void createMembership(String type, double price, String description, int userId) {
        LocalDate today = LocalDate.now();
        Membership membership = new Membership(type, price, description, today, userId);
        try {
            membershipDAO.addMembership(membership);
            System.out.println("✅ Membership successfully created.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to add membership: " + e.getMessage());
        }
    }
}

    // When you inject in the DAO you have access to all methods in it
    //MembershipDAO dao = new MembershipDAO();
