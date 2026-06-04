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

       public Book getRoot(){
        return root;
    }

    // Added to print all books sorted by ISBN
    public void printAllBooks() {
        if (root == null) {
            System.out.println("  The catalogue is empty.");
            return;
        }
        System.out.println("  No. | ISBN          | Title                          | Author");
        System.out.println("  ----|---------------|--------------------------------|------------------");
        printInOrder(root, new int[]{1});
    }
    private void printInOrder(Book node, int[] counter) {
        if (node == null) return;
        printInOrder(node.left, counter);
        System.out.printf("  %-3d | %-13d | %-30s | %s%n",
                counter[0]++, node.isbn, node.title, node.author);
        printInOrder(node.right, counter);
    }
}
