public class Book {
    int isbn;
    String title, author;
    Book left, right;

    // Constructor to initialize a new book node
    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }
}