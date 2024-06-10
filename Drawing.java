
public class Drawing extends Item {
    private String artist;
    
    public Drawing(String artist, String name, double price, int stock, String desciprtion, int id) {
        super(name, price, stock, desciprtion, id);
        this.artist = artist;
    } 
    public boolean useItem() {
        return false;
    }
    public String toString() {
        return super.toString() + "\nArtist " + artist;
    }
} 