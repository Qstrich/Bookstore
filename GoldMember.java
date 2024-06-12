/*
 * GoldMember.java
 * Brian Chen
 * June 8, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains the fields and methods for a gold membership. 
 */

public class GoldMember extends Membership {
    public static final double MAX_PERK_PRICE = 25;
    public static final double RENEWAL_COST = 100;
    public static final int PERK_NUM = 4;
    public static final double DISCOUNT = 10;
    public static final String DESCRIPTION = "Gold membership gives a 10% discount on all items ordered as well as 4 items equal to or under $25 that you have not ordered yet.";

    /*
     * GoldMember(int issueDay, int issueMonth, int issueYear, Customer customer)
     * 
     * int issueDay - Day membership was issued.
     * int issueMonth - Month membership was issued.
     * int issueYear - Year membership was issued.
     * Customer customer: The customer which this membership is associated with.
     * The constructor initialises all the fields and sets the customer connected
     * to this membership's membership to this object.
     */
    public GoldMember(int issueDay, int  issueMonth, int issueYear, Customer customer) {
        super(issueDay, issueMonth, issueYear, DISCOUNT, customer, RENEWAL_COST);
        customer.setMembership(this);
    }

    /*
     * int specialPerk(int currentOrderNum, Item[] items, Order[] orders)
     * Return int - The number of orders made as a special perk. 
     * 
     * int currentOrderNum - Current number of order for order id.
     * Item[] items - Array of all items of the bookstore from ItemManager. 
     * order[] orders - Array of all orders of the bookstore. 
     * The method does finds all the items that were previously bought by the customer. 
     * It then goes through the array of items to find any items that were not previously
     * ordered and equal to or under MAX_PERK_PRICE in price and makes
     * an order on it for PERK_NUM number of different items. It then adds the order to
     * the account order history and main order array and returns the number of orders 
     * that were made. 
     */
    public int specialPerk(int currentOrderNum, Item[] items, Order[] orders) {
        int orderCount = 0;
        int itemId;
        boolean previouslyOrdered = false;
        Item[] perkItems = new Item[PERK_NUM];
        Customer customer = super.getCustomer();
        Order perkOrder;
        int[] previouslyOrderedItemIds = new int[customer.getCurrentOrderHistory()];

        // Find all the items the customer previously ordered
        for (int i = 0; i < previouslyOrderedItemIds.length; i++) {
            previouslyOrderedItemIds[i] = customer.getOrderHistory(i).getProduct().getId();
        }

        // Loop through items until all PERK_NUM items are found or the end of the item array is reached
        for (int i = 0; i < items.length && items[i] != null && orderCount < PERK_NUM; i++) {
            itemId = items[i].getId();
            for (int j = 0; i < previouslyOrderedItemIds.length; j++) {
                // Find if the current item was previously purchased
                if (itemId == previouslyOrderedItemIds[j]) {
                    previouslyOrdered = true;
                }
            }

            // Find if the item has enough stock and MAX_PERK_PRICE and make an order. 
            if (!previouslyOrdered && items[i].enoughStock(1) && items[i].getPrice() <= MAX_PERK_PRICE) {
                perkOrder = new Order(customer, items[i], currentOrderNum, super.getIssueDay(), super.getIssueMonth(), super.getIssueYear(), 1, 0);
                items[i].setStock(items[i].getStock() - 1);
                
                // Add order to main order array of bookstore
                orders[currentOrderNum] = perkOrder;
                currentOrderNum++;

                // Add order to customer order history
                customer.addToHistory(perkOrder);
                orderCount++;
            }

            previouslyOrdered = false;
        }

        return orderCount;
    }

    /*
     * String toString()
     * Return String - Formatted string of the fields of this object. 
     * 
     * The method returns a formatted string containing all fields of this membership. 
     */
    public String toString() {
        return "Gold Membership\n" + super.toString();
    }
}