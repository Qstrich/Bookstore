import java.io.*;

public class Book extends Item {
    private String author;
    public Book(String author, String name, double price, int stock, String description, int id) {
        super(name, price, stock, description, id);
        this.author = author;
    }
    public String getMaker() { return author; } 
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
    public String toString() {
        return super.toString() + "\nAuthor " + author;
    }
}