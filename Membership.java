/*
Membership.java
Brian Chen
June 5, 2024
A.Y. Jackson S.S.
 
The abstract class contains the fields and methods
that apply on all memberships for the bookstore. 
*/

public abstract class Membership {
    private int issueDay;
    private int issueMonth;
    private int issueYear;
    private double discountPercent;
    private int perkNum;
    private Customer customer;
    private double renewalCost;

    /*
     * Membership(int issueDay, int issueMonth, int issueYear, double discountPercent, int perkNum, Customer customer, double renewalCost)
     * 
     * int issueDay - Day membership was issued.
     * int issueMonth - Month membership was issued.
     * int issueYear - Year membership was issued.
     * double discountPercent: Percent discount given to member. 
     * int perkNum: Number of items sent as part of perks per yearly term. 
     * Customer customer: The customer which this membership is associated with. 
     * double renewalCost: Cost to renew the membership. 
     * The constructor initialises all of the fields. 
     */
    public Membership(int issueDay, int issueMonth, int issueYear, double discountPercent, int perkNum, Customer customer, double renewalCost) {

    }

    // Accessors
    public int getIssueDay() {
        return issueDay;
    }
    
    public int getIssueMonth() {
        return issueMonth;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public int getPerkNum() {
        return perkNum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getRenewalCost

    // Mutators
}
