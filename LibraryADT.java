/**
 * LibraryADT — Individual Task 4: ADT Designer
 * * This interface defines the contract for the Smart Library System.
 * It enforces "Information Hiding" by exposing only essential operations 
 * to the main system interface, keeping the internal data structures 
 * (BookBST, BorrowStack, RecordFinder) completely hidden.
 */
public interface LibraryADT {

    /**
     * Adds a new book to the library catalogue.
     * * @param isbn   The unique 9-13 digit identifier for the book.
     * @param title  The title of the book.
     * @param author The author of the book.
     */
    void addBook(int isbn, String title, String author);

    /**
     * Searches the catalogue for a book by its ISBN.
     * Must operate with O(log n) efficiency.
     * * @param isbn The ISBN of the book to search for.
     */
    void searchBook(int isbn);

    /**
     * Processes borrowing a book. Moves the record to the borrowing history.
     * * @param isbn The ISBN of the book being borrowed.
     */
    void borrowBook(int isbn);

    /**
     * Displays all previously borrowed books in LIFO (Last-In-First-Out) order.
     */
    void viewLatestHistory();
}