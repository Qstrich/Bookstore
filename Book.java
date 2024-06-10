
public class Book extends Item {
    private String author;
    public Book(String author, String name, double price, int stock, String description, int id) {
        super(name, price, stock, description, id);
        this.author = author;
    }
    public boolean useItem() {
        return false;
    }
    public String toString() {
        return super.toString() + "\nAuthor " + author;
    }
}