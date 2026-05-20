import java.util.Stack;

/**
 * BorrowStack — Individual Task 2: Borrowing History
 * author: Nadiyah Aqilah Putri 24211204
 * 
 * Uses a Stack<Book> to record every book a student borrows.
 * The most recently borrowed book always appears at the top (LIFO order).
 */
public class BorrowStack {

    // The internal stack is kept private only this class can touch it directly.
    private Stack<Book> history = new Stack<>();

    /**
     * push(book) called when a student borrows a book.
     * Pushes the Book object onto the top of the stack.
     */
    public void push(Book book) {
        history.push(book);
        System.out.println("\"" + book.title + "\" added to your borrowing history.");
    }

    /**
     * peek() — returns the most recently borrowed book WITHOUT removing it.
     * Use for showing the user what they last borrowed.
     */
    public Book peek() {
        if (history.isEmpty()) {
            System.out.println("No borrowing history yet.");
            return null;
        }
        return history.peek();
    }

    /**
     * pop() — removes and returns the most recently borrowed book.
     * Could be used for a "return last borrowed book" feature.
     */
    public Book pop() {
        if (history.isEmpty()) {
            System.out.println("No borrowing history to undo.");
            return null;
        }
        Book returned = history.pop();
        System.out.println("Removed \"" + returned.title + "\" from history.");
        return returned;
    }

    // isEmpty() — checks whether the history stack has any entries.
    public boolean isEmpty() {
        return history.isEmpty();
    }

    
    //show() — displays all borrowed books in LIFO order (most recent first).
    public void show() {
        if (history.isEmpty()) {
            System.out.println("Borrowing history is empty.");
            return;
        }

        System.out.println("\n--- Borrowing History (Most Recent First) ---");
        // Traverse from top of stack (last index) to bottom (index 0)
        for (int i = history.size() - 1; i >= 0; i--) {
            Book b = history.get(i);
            System.out.println("[" + (history.size() - i) + "] "
                    + "ISBN: " + b.isbn
                    + " | Title: " + b.title
                    + " | Author: " + b.author);
        }
        System.out.println("---------------------------------------------");
    }

    
     //size() — returns how many books are in the history.
     
    public int size() {
        return history.size();
    }
}
