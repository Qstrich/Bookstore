public class Customer extends Account {

    private Membership membership;
    
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
    
    public double getDiscount(){
        return membership.getDiscountPercent();
    }

    public String toString(){
        return super.toString() + "\nDiscount: "+membership;
    }


}
