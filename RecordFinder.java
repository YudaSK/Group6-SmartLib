/**
 * RecordFinder — Individual Task 3: Record Finder
 * author: Abdul Malik Rasyid
 *
 * Implements a recursive search function within the BST
 * to find books by ISBN number.
 *
 * How it works:
 * - Uses the BST property: left < root < right (by ISBN)
 * - Recursively traverses left or right subtree based on ISBN comparison
 * - Returns the Book object if found, or null if not found
 */
public class RecordFinder {

    // Reference to the root of the BST (shared from BookBST)
    private Book root;

    // Constructor — accepts the root node from BookBST
    public RecordFinder(Book root) {
        this.root = root;
    }

    /**
     * findByISBN(int isbn)
     * Public method called by the main system interface.
     * Starts the recursive search from the root and prints the result.
     */
    public Book findByISBN(int isbn) {
        System.out.println("\n--- Searching for ISBN: " + isbn + " ---");
        Book result = searchRecursive(root, isbn);

        if (result != null) {
            System.out.println("Book Found!");
            System.out.println("------------------------------");
            System.out.println("  ISBN   : " + result.isbn);
            System.out.println("  Title  : " + result.title);
            System.out.println("  Author : " + result.author);
            System.out.println("------------------------------");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found in the catalogue.");
        }

        return result;
    }

    /**
     * searchRecursive(Book currentRoot, int isbn)
     * Private recursive method — Information Hiding principle.
     * Hides the tree traversal logic from outside classes.
     *
     * Base cases:
     *   1. currentRoot == null — ISBN not found, return null
     *   2. isbn == currentRoot.isbn — match found, return this node
     *
     * Recursive cases:
     *   - isbn < currentRoot.isbn — search left subtree
     *   - isbn > currentRoot.isbn — search right subtree
     */
    private Book searchRecursive(Book currentRoot, int isbn) {
        // Base case 1: reached a null node, ISBN does not exist in the tree
        if (currentRoot == null) {
            return null;
        }

        // Base case 2: current node's ISBN matches the target
        if (isbn == currentRoot.isbn) {
            return currentRoot;
        }

        // Recursive case: target ISBN is smaller, search the left subtree
        if (isbn < currentRoot.isbn) {
            return searchRecursive(currentRoot.left, isbn);
        }
        // Recursive case: target ISBN is greater, search the right subtree
        else {
            return searchRecursive(currentRoot.right, isbn);
        }
    }
}
