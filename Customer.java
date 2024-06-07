public class Customer extends Account {

    Membership membership;
    public Customer(String name, String password){
        super(name, password);
    }

    public double getDiscount(){
        return membership.getDiscountPercent();
    }

    public String toString(){
        return super.toString() + "\nDiscount: "+membership;
    }


}
