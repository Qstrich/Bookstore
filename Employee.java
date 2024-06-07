
public class Employee extends Account {
    
 
    public static double DISCOUNT = 8;
    public static final String EMPLOYEE_KEY = "BusinessAYJ";

    public Employee(String name, String password){
        super(name, password);
    }

  

    public double getDiscount(){
        return DISCOUNT;
    }

    public String toString(){
        return super.toString() + "\nDiscount: "+DISCOUNT+"%\nEmployee Key: "+EMPLOYEE_KEY;
    }

 
}