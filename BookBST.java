public class BookBST {
    private Book root; // The root node of the Binary Search Tree

    // Public method to be called by the main system interface
    public void insert(int isbn, String title, String author) {
        root = insertRecursive(root, isbn, title, author);
    }

    // Private recursive method to hide the tree traversal logic (Information Hiding)
    private Book insertRecursive(Book currentRoot, int isbn, String title, String author) {
        // Base case: If the current tree/subtree is empty, insert the new book here
        if (currentRoot == null) {
            return new Book(isbn, title, author);
        }

        // If the new ISBN is smaller, traverse the left subtree
        if (isbn < currentRoot.isbn) {
            currentRoot.left = insertRecursive(currentRoot.left, isbn, title, author);
        } 
        // If the new ISBN is greater, traverse the right subtree
        else if (isbn > currentRoot.isbn) {
            currentRoot.right = insertRecursive(currentRoot.right, isbn, title, author);
        }

        // Return the unchanged node pointer to reconnect the tree structure
        return currentRoot;
    }
}