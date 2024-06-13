
public class Drawing extends Item {
    //fields
    private String artist;
    /*
    * Drawing(String artist, String name, double price, int stock, String desciprtion, int id)
    * This method is the constructor for drawings that initializes all varaibles
    */
    public Drawing(String artist, String name, double price, int stock, String desciprtion, int id) {
        super(name, price, stock, desciprtion, id);
        this.artist = artist;
    } 
    //accesors
    public String getMaker() { return artist; }
    /*
    *   boolean useItem()
    *   returns -> boolean
    *   this method uses the functionality of drawings (currently just a description and further functionality was intended if we finsihed early)
    */
    public boolean useItem() {
        System.out.println("Description " + super.getDescription());

        return true;
    }
    /*
    *   String toString()
    *   This method returns the string equivialnt of the drawing object
    */
    public String toString() {
        return super.toString() + "\nArtist " + artist;
    }
} 