import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartLibrary implements LibraryADT {

    private final BookBST catalogue = new BookBST();
    private final BorrowStack history = new BorrowStack();
    
    // BUG FIX: Initialize RecordFinder ONLY ONCE, passing the catalogue reference
    private final RecordFinder finder = new RecordFinder(catalogue);

    // LibraryADT implementations

    @Override
    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        // BUG FIX: Removed the redundant 'finder = new RecordFinder(...)' instantiation
        System.out.println("  \"" + title + "\" by " + author + " added to catalogue.");
    }

    @Override
    public void searchBook(int isbn) {
        System.out.println("\n  --- Searching for ISBN: " + isbn + " ---");
        Book result = finder.findByISBN(isbn);

        // BUG FIX: UI logic moved here from RecordFinder
        if (result != null) {
            System.out.println("  Book Found!");
            System.out.println("  ------------------------------");
            System.out.println("    ISBN   : " + result.isbn);
            System.out.println("    Title  : " + result.title);
            System.out.println("    Author : " + result.author);
            System.out.println("  ------------------------------");
        } else {
            System.out.println("  Book with ISBN " + isbn + " not found in the catalogue.");
        }
    }

    @Override
    public void borrowBook(int isbn) {
        Book b = finder.findByISBN(isbn);
        if (b != null) {
            history.push(b);
            System.out.println("  \"" + b.title + "\" has been borrowed successfully.");
        } else {
            // Added error handling if the user tries to borrow a non-existent book
            System.out.println("  Borrow failed. Book with ISBN " + isbn + " not found.");
        }
    }

    @Override
    public void viewLatestHistory() {
        history.show();
    }

    public void viewCatalogue() {
        System.out.println("\n--- Full Catalogue (Sorted by ISBN) ---");
        catalogue.printAllBooks();
    }

    // Console Interface
    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        printBanner();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readMenuInt(sc, "  Enter choice: ", 1, 6);

            switch (choice) {
                case 1 -> handleAddBook(sc);
                case 2 -> handleSearchBook(sc);
                case 3 -> handleBorrowBook(sc);
                case 4 -> viewLatestHistory();
                case 5 -> viewCatalogue();
                case 6 -> running = false;
            }

            if (running) System.out.println("\n  -----------------------------------------");
        }

        System.out.println("\n  Thank you for using Smart Library. Goodbye!\n");
        sc.close();
    }

    // Menu Handlers
    private void handleAddBook(Scanner sc) {
        System.out.println("\n  -- ADD BOOK --");
        int isbn = readISBN(sc, "  Enter ISBN   : ");
        if (isbn == -1) return;
        String title  = readNonEmpty(sc, "  Enter Title  : ");
        String author = readNonEmpty(sc, "  Enter Author : ");
        if (title == null || author == null) {
            System.out.println("  Title/Author cannot be blank — cancelled.");
            return;
        }
        addBook(isbn, title, author);
    }

    private void handleSearchBook(Scanner sc) {
        System.out.println("\n  -- SEARCH BOOK --");
        int isbn = readISBN(sc, "  Enter ISBN to search: ");
        if (isbn == -1) return;
        searchBook(isbn);
    }

    private void handleBorrowBook(Scanner sc) {
        System.out.println("\n  -- BORROW BOOK --");
        int isbn = readISBN(sc, "  Enter ISBN to borrow: ");
        if (isbn == -1) return;
        borrowBook(isbn);
    }

    // UI Formatting Methods
    private void printBanner() {
        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("         SMART LIBRARY SYSTEM");
        System.out.println("  ==========================================");
        System.out.println();
    }

    private void printMenu() {
        System.out.println();
        System.out.println("  ---- SMART LIBRARY MENU ----");
        System.out.println("  1. Add/Return Book");
        System.out.println("  2. Search Book");
        System.out.println("  3. Borrow Book");
        System.out.println("  4. View History");
        System.out.println("  5. View Catalogue");
        System.out.println("  6. Exit");
        System.out.println("  ----------------------------");
    }

    private int readMenuInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (val >= min && val <= max) return val;
                System.out.println("  Please enter a number between " + min + " and " + max + ".");
            } catch (InputMismatchException e) {
                sc.nextLine(); // Clear the invalid input buffer
                System.out.println("  Invalid input — please enter a whole number.");
            }
        }
    }

    private int readISBN(Scanner sc, String prompt) {
        System.out.print(prompt);
        try {
            int val = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (val <= 0) {
                System.out.println("  ISBN must be a positive number.");
                return -1;
            }
            return val;
        } catch (InputMismatchException e) {
            sc.nextLine(); // Clear the invalid input buffer
            System.out.println("  Invalid ISBN — must be a whole number.");
            return -1;
        }
    }

    private String readNonEmpty(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine().trim();
        return s.isEmpty() ? null : s;
    }

    // MAIN RUN
    public static void main(String[] args) {
        new SmartLibrary().runMenu();
    }
}