/*
 * Membership.java
 * Brian Chen
 * June 5, 2024
 * A.Y. Jackson S.S.
 * 
 * The abstract class contains the fields and methods
 * that apply on all memberships for the bookstore. 
 */

 public abstract class Membership {
    private int issueDay;
    private int issueMonth;
    private int issueYear;
    private double discountPercent;
    private Customer customer;
    private double renewalCost;

    /*
     * Membership(int issueDay, int issueMonth, int issueYear, double discountPercent, Customer customer, double renewalCost)
     * 
     * int issueDay - Day membership was issued.
     * int issueMonth - Month membership was issued.
     * int issueYear - Year membership was issued.
     * double discountPercent: Percent discount given to member.  
     * Customer customer: The customer which this membership is associated with. 
     * double renewalCost: Cost to renew the membership. 
     * The constructor initialises all of the fields. 
     */
    public Membership(int issueDay, int issueMonth, int issueYear, double discountPercent, Customer customer, double renewalCost) {
        this.issueDay = issueDay;
        this.issueMonth = issueMonth;
        this.issueYear = issueYear;
        this.discountPercent = discountPercent;
        this.customer = customer;
        this.renewalCost = renewalCost;
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

    public Customer getCustomer() {
        return customer;
    }

    public double getRenewalCost() {
        return renewalCost;
    }

    // Mutators
    public void setIssueDay(int issueDay) {
        this.issueDay = issueDay;
    }

    public void setIssueMonth(int issueMonth) {
        this.issueMonth= issueMonth;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRenewalCost(double renewalCost) {
        this.renewalCost = renewalCost;
    }

    /*
     * boolean isExpired(int currentDay, int currentMonth, int currentYear)
     * Return boolean - If the membership is expired or not.
     * 
     * int currentDay - The current day. 
     * int currentMonth - The current month.
     * int currentYear - The current year.
     * The method finds if the membership is expired or not and if it is,
     * it sets the Customer object's membership to null and also dissociates
     * the customer from this membership. 
     */
    public boolean isExpired(int currentDay, int currentMonth, int currentYear) {
        boolean expired = false;

        // Find if the membership is expired
        if (currentYear > issueYear + 1) {
            expired = true;
        }
        else if (currentYear == issueYear + 1) {
            if (currentMonth > issueMonth) {
                expired = true;
            }
            else if (currentMonth == issueMonth && currentDay >= issueDay) {
                expired = true;
            }
        }

        // Remove membership from the customer and the customer from membership if expired
        if (expired) {
            customer.setMembership(null);
            customer = null;
        }

        return expired;
    }

    /*
     * abstract void specialPerk(int currentOrderNum, Item[] items, Order[] orders)
     * Return int - The number of orders made as a special perk. 
     * 
     * int currentOrderNum - Current number of order for order id.
     * Item[] items - Array of all items of the bookstore  from ItemManager. 
     * order[] orders - Array of all orders of the booksstore. 
     * The abstract method makes special orders which depend on the membership level. 
     */
    public abstract int specialPerk(int currentOrderNum, Item[] items, Order[] orders);

    /*
     * String toString()
     * Return String - Formatted string of the fields of the membership. 
     * 
     * The method returns a formatted string containing all fields of this membership. 
     */
    public String toString() {
        return "Issue date (dd/mm/yyyy): " + issueDay + "/" + issueMonth + "/" + issueYear
        + "\nDiscount percent: " + discountPercent + "%\nCustomer name: " + customer.getName() + "\nRenewal cost: $" + renewalCost;
    }
}
