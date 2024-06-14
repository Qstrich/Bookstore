/*
 * Employee.java
 * Jimmy Tao
 * June 8, 2024
 * A.Y. Jackson S.S.
 * 
 * The class contains all methods, fields and constants for the Employee class, which is extended from Account
 */
public class Employee extends Account {

    //Constants
    public static double DISCOUNT = 8;  //Set discount as part of employee benefit
    public static final String EMPLOYEE_KEY = "BusinessAYJ"; //Employee Key that was set by the company, this is required in order to create an employee account

    /*
     * Employee(String name, String password)
     * 
     * String name - name of the Employee
     * String password - Password of the Employee
     * The constructor initialises all the fields
    */
    public Employee(String name, String password){
        super(name, password);
    }
    /*
     * int double getDiscount()
     * Return double - discount amount
     * 
     * This method returns discount percentage.
     */
    public double getDiscount(){
        return DISCOUNT;
    }
    /*
     * String toString()
     * Return String - Formatted string of the fields of this object. 
     * 
     * The method returns a formatted string containing all fields of this Employee. 
     */
    public String toString(){
        return super.toString() + "\nDiscount: "+DISCOUNT+"%\nEmployee Key: "+EMPLOYEE_KEY;
    }

 
}
