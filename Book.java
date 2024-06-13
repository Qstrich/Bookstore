import java.io.*;

public class Book extends Item {
    //fields
    private String author;
    /*
    *  Book(String author, String name, double price, int stock, String description, int id)
    *  
    *  This method is a constructor for Book, taking in all nessecary methods
    */
    public Book(String author, String name, double price, int stock, String description, int id) {
        super(name, price, stock, description, id);
        this.author = author;
    }
    // Accesors and mutators
    public String getMaker() { return author; }
    /*
    *   boolean useItem()
    *   returns -> boolean
    *   This method returns true if the item was used successfully.
    */ 
    public boolean useItem() {
        System.out.println("\n\nOpening Book of " + super.getName() + " by " + author);
        System.out.println("Description: " + super.getDescription() + "\n");
        try {
            BufferedReader br = new BufferedReader(new FileReader(super.getId() + ".txt"));
            String input = br.readLine();
            while (input != null) {
                System.out.println(input);
                input = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    /*
    *   String toString()
    *   returns -> String
    *   this method returns the strign equivilant of the object
    */
    public String toString() {
        return super.toString() + "\nAuthor " + author;
    }
}