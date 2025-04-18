package org.keyin.memberships;

//*\
// This is class file that represents a membership
//
import java.time.LocalDate;

public class Membership {
    private int membershipId;
    private String membershipType;
    private double membershipPrice;
    private String membershipDescription;
    private LocalDate datePurchased;
    private int userId;

    // Constructors
    public Membership(int membershipId, String membershipType, double membershipPrice, String membershipDescription, LocalDate datePurchased, int userId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipPrice = membershipPrice;
        this.membershipDescription = membershipDescription;
        this.datePurchased = datePurchased;
        this.userId = userId;
    }

    public Membership(String membershipType, double membershipPrice, String membershipDescription, LocalDate datePurchased, int userId) {
        this.membershipType = membershipType;
        this.membershipPrice = membershipPrice;
        this.membershipDescription = membershipDescription;
        this.datePurchased = datePurchased;
        this.userId = userId;
    }

    // Getters and Setters
    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public double getMembershipPrice() {
        return membershipPrice;
    }

    public void setMembershipPrice(double membershipPrice) {
        this.membershipPrice = membershipPrice;
    }

    public String getMembershipDescription() {
        return membershipDescription;
    }

    public void setMembershipDescription(String membershipDescription) {
        this.membershipDescription = membershipDescription;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}