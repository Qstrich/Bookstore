/*
 * BronzeMember.java
 * Brian Chen
 * June 6, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains the information and methods for a bronze level
 * member in the online bookstore. 
 */

 public class BronzeMember extends Membership {
    public static final double RENEWAL_COST = 40;
    public static final int PERK_NUM = 0;
    public static final double DISCOUNT = 8;
    public static final String DESCRIPTION = "Bronze membership gives an 8% discount on all items ordered. ";

    /*
     * BronzeMember(int issueDay, int issueMonth, int issueYear, Customer customer)
     * 
     * int issueDay - Day membership was issued.
     * int issueMonth - Month membership was issued.
     * int issueYear - Year membership was issued.
     * Customer customer: The customer which this membership is associated with.
     * The constructor initialises all the fields and sets the customer connected
     * to this membership's membership to this object. 
     */
    public BronzeMember(int issueDay, int issueMonth, int issueYear, Customer customer) {
        super(issueDay, issueMonth, issueYear, DISCOUNT, customer, RENEWAL_COST);
        customer.setMembership(this);
    }

    /*
     * void specialPerk(int currentOrderNum, Item[] items, Order[] orders)
     * Return int - The number of orders made as a special perk. 
     * 
     * int currentOrderNum - Current number of order for order id.
     * Item[] items - Array of all items of the bookstore  from ItemManager. 
     * order[] orders - Array of all orders of the booksstore. 
     * The method does nothing for a BronzeMember. 
     */
    public int specialPerk(int currentOrderNum, Item[] items, Order[] orders) {
        return 0;
    }

    /*
     * String toString()
     * Return String - Formatted string of the fields of this object. 
     * 
     * The method returns a formatted string containing all fields of this membership. 
     */
    public String toString() {
        return "Bronze Membership\n" + super.toString();
    }
}
