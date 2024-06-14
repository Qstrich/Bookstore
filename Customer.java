/*
 * Customer.java
 * Jimmy Tao
 * June 8, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains all methods and fields for the Customer class, which is extended from Account
 */

public class Customer extends Account {


    //Fields
    private Membership membership;

    /*
     * Customer(String name, String password)
     * 
     * String name - name of the Customer
     * String password - Password of the Customer
     * The constructor initialises all the fields, with membership being null since there is no membership yet connected with the Customer 
    */
    public Customer(String name, String password){
        super(name, password);
    }

    // Accessors
    public Membership getMembership() {
        return membership;
    }

    //Mutators
    public void setMembership(Membership membership) {
        this.membership = membership;
    }
        
    /*
     * int double getDiscount()
     * Return double - discount amount
     * 
     * This method returns the discount the Customer receives: 0% if no membership, otherwise the membership method is called to find the specific discount percent.
     */
    public double getDiscount(){
        if(membership == null) {
            return 0;
        }
        else {
            return membership.getDiscountPercent();
        }
    }

    /*
     * String toString()
     * Return String - Formatted string of the fields of this object. 
     * 
     * The method returns a formatted string containing all fields of this Customer. 
     */
    public String toString(){
        return super.toString() + "\nDiscount: "+ getDiscount();
    }


}
