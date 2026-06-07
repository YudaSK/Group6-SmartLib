/**
 * RecordFinder — Individual Task 3: Record Finder
 * author: Abdul Malik Rasyid
 *
 * Implements a recursive search function within the BST
 * to find books by ISBN number.
 */
public class RecordFinder {

    // Dependency on the catalogue tree itself
    private BookBST catalogue;

    // Constructor — accepts the BookBST object to always access the latest root
    public RecordFinder(BookBST catalogue) {
        this.catalogue = catalogue;
    }

    /**
     * findByISBN(int isbn)
     * Starts the recursive search from the dynamic root.
     */
    public Book findByISBN(int isbn) {
        // Dynamically fetches the root from the catalogue
        return searchRecursive(catalogue.getRoot(), isbn);
    }

    /**
     * searchRecursive(Book currentRoot, int isbn)
     * Private recursive method — Information Hiding principle.
     */
    private Book searchRecursive(Book currentRoot, int isbn) {
        // Base case 1: reached a null node, ISBN does not exist
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