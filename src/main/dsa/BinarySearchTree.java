package dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Day 22 — Binary Search Tree
 *
 * BST property: for every node N,
 *   all values in N.left subtree < N.value
 *   all values in N.right subtree > N.value
 *
 * This property is maintained by every insert and delete operation.
 * Violating it at any node makes the entire tree unreliable.
 */
public class BinarySearchTree {
    // ── NODE ─────────────────────────────────────────────────────────
    // WHY static nested class: same reasoning as LinkedList's Node.
    // Node only makes sense in the context of BST.
    // Static because it doesn't need access to BST's instance fields.
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null; // leaf node by default
            this.right = null;
        }
    }

    private Node root; // entry point - null for empty tree

    // ── INSERT ───────────────────────────────────────────────────────
    // Time: O(h) where h = tree height
    //   Balanced tree: h = log n → O(log n)
    //   Completely unbalanced (sorted input): h = n → O(n)
    //
    // WHY the recursive approach:
    // "Insert into tree rooted at node" naturally decomposes into:
    //   - If empty: create new node here
    //   - If value < node: insert into LEFT subtree
    //   - If value > node: insert into RIGHT subtree
    // This is a pure recursive structure — no iteration needed.
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, int value) {
        // Base case; found the empty spot - create new node
        if (node == null) return new Node(value);

        if (value < node.value) {
            // Go left — insert into left subtree
            // The recursive call returns the (possibly new) left subtree root
            // We assign it back to ensure the tree stays connected
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        }
        // If value == node.value: BST typically ignores duplicates
        // (some implementations allow duplicates in right subtree)

        return node; // return current node (unchanged, just its children may have changed)
    }

    // ── SEARCH ───────────────────────────────────────────────────────
    // Time: O(h) — same analysis as insert
    // Space: O(h) — recursive call stack depth
    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node node, int value) {
        // Base case: reached null - value not in tree
        if (node == null) return false;
        // Found it
        if (value == node.value) return true;
        // BST property tells us which direction to go
        if (value < node.value) return containsRecursive(node.left, value);
        return containsRecursive(node.right, value);

        // WHY no need to check both subtrees:
        // The BST property GUARANTEES value can only be in one subtree.
        // This is what makes BST search O(log n) instead of O(n).
    }

    // ── DELETE ───────────────────────────────────────────────────────
    // Deletion is the hardest BST operation because removing a node
    // might break the tree structure. Three cases:
    //
    // Case 1: Node has NO children (leaf) → just remove it
    // Case 2: Node has ONE child → replace node with its child
    // Case 3: Node has TWO children → find inorder successor
    //         (smallest value in right subtree), replace value, delete successor
    //
    // WHY inorder successor for Case 3:
    // We need a replacement that maintains BST property.
    // The inorder successor is the smallest value in the right subtree.
    // It is: > everything in the left subtree (because it's in the right)
    //        < all other nodes in the right subtree (because it's the minimum)
    // So it can legally sit in the deleted node's position.
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node node, int value) {
        if (node == null) return null; // value not found

        if (value < node.value) {
            node.left = deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Found the node to delete

            // Case 1 & 2: Zero or one child
            if (node.left == null) return node.right; // replace with right child (or null)
            if (node.right == null) return node.left; // replace with left child

            // Case 3 : two children
            int successorValue = findMin(node.right);
            node.value = successorValue; // replace value
            node.right = deleteRecursive(node.right, successorValue); // delete successor
        }

        return node;
    }

    private int findMin(Node node) {
        // Minimum is always the leftmost node
        // WHY: BST property means smaller values are always to the left
        while (node.left != null) node = node.left;
        return node.value;
    }

    // ── DFS TRAVERSALS ───────────────────────────────────────────────

    // INORDER: left → node → right
    // WHY this produces sorted output for BST:
    // All left subtree values < current < all right subtree values.
    // Visiting left first, then current, then right = ascending order.
    // This is the most important BST traversal.
    public List<Integer> inorder() {
    List<Integer> result = new ArrayList<>();
    inorderRecursive(root, result);
    return result;
    }

    private void inorderRecursive(Node node, List<Integer> result) {
        if(node == null) return; // base case
        inorderRecursive(node.left, result); // 1. go left
        result.add(node.value); // 2. process current
        inorderRecursive(node.right, result); // 3. go right
    }

    // PREORDER: node → left → right
    // WHY useful: copies the tree structure (root before children).
    // Used to serialize a tree — you can reconstruct the same BST
    // by inserting in preorder sequence.
    public List<Integer> preorder() {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    private void preorderRecursive(Node node, List<Integer> result) {
        if(node == null) return;
        result.add(node.value); // 1. process current
        preorderRecursive(node.left, result); // 2. go left
        preorderRecursive(node.right, result); // 3. go right
    }

    // POSTORDER: left → right → node
    // WHY useful: process children before parent.
    // Used to: delete a tree (delete children first),
    // calculate subtree sizes, evaluate expression trees.
    public List<Integer> postorder() {
        List<Integer> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void postorderRecursive(Node node, List<Integer> result) {
        if(node == null) return;
        postorderRecursive(node.left, result); // 1. go left
        postorderRecursive(node.right, result); // 2. go right
        result.add(node.value);
    }

    // ── BFS (LEVEL ORDER) ─────────────────────────────────────────────
    // Time: O(n) — visits every node exactly once
    // Space: O(w) where w = maximum width of the tree
    //   For a balanced tree, the last level has n/2 nodes → O(n) worst case
    //
    // WHY Queue for BFS:
    // We process nodes level by level.
    // After processing a node, its children go to the back of the queue.
    // By the time we reach a child, all nodes at the parent's level are done.
    // FIFO = process in the order they were discovered = level order.
    public List<List<Integer>> levelOrder() {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root); // start with the root

        while(!queue.isEmpty()) {
            // Process all nodes at the current level
            int levelSize = queue.size(); // number of nodes at this level
            List<Integer> currentLevel = new ArrayList<>();

            for(int i = 0; i < levelSize; i++) {
                Node node = queue.poll(); // dequeue front
                currentLevel.add(node.value);

                // Enqueue children for the NEXT level
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }

            result.add(currentLevel);
            // WHY snapshot levelSize before the loop:
            // As we enqueue children, queue.size() increases.
            // We only want to process the nodes that were in the queue
            // at the START of this level — not the children we just added.
        }

        return result;
    }

    // ── UTILITY METHODS ───────────────────────────────────────────────

    // Height: longest path from root to any leaf
    // Time: O(n) — must visit all nodes to find the deepest
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node node) {
        if(node == null) return -1; // empty tree has height -1
        // Height = 1 (current node) + max of left and right subtree heights
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    // Max Depth (LeetCode #104) — same as height but counts differently
    // Returns 0 for empty tree, 1 for single node
    public int maxDepth() {
        return maxDepthRecursive(root);
    }

    private int maxDepthRecursive(Node node) {
        if(node == null) return 0;
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    // Invert Binary Tree (LeetCode #226)
    // Swap left and right children at every node
    // Time: O(n), Space: O(h)
    public void invertTree() {
        invertRecursive(root);
    }

    private void invertRecursive(Node node) {
        if(node == null) return;
        // Swap children
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
        // Recursively invert subtree
        invertRecursive(node.left);
        invertRecursive(node.right);
    }

    // ── VALIDATE BST (LeetCode #98) ─────────────────────────────────
    // Verify a tree is a valid BST
    //
    // WHY the naive approach fails:
    // Checking left.value < node.value < right.value at each node
    // is NOT sufficient. Consider:
    //       10
    //      /  \
    //     5    15
    //    / \
    //   1   12    ← 12 > 10 but is in the LEFT subtree! Invalid BST.
    //
    // At node 5, checking 1 < 5 < 12 passes. But 12 violates the global BST property.
    // The correct approach: track valid range [min, max] for each node.
    // Every node must be strictly within its valid range.
    public boolean isValidBST() {
        return validateRecursive(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validateRecursive(Node node, long min, long max) {
        if(node == null) return true; // empty subtree is valid
        // Node value must be strictly within (min, max)
        if(node.value <= min || node.value >= max) return false;
        // Left subtree: all values must be < node.value (update max)
        // Right subtree: all values must be > node.value (update min)
        return validateRecursive(node.left, min, node.value) && validateRecursive(node.right, node.value, max);
    }

    // ── MAIN ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Build the tree by inserting values
        // Insertions: 50, 30, 70, 20, 40, 60, 80, 10, 65
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 65};
        for (int v : values) bst.insert(v);

        /*
         * Tree structure after insertions:
         *
         *              50
         *            /    \
         *          30      70
         *         /  \    /  \
         *        20   40 60   80
         *       /        \
         *      10         65
         */

        // DFS traversals
        System.out.println("=== DFS Traversals ===");
        System.out.println("Inorder   (sorted): " + bst.inorder());
        // [10, 20, 30, 40, 50, 60, 65, 70, 80] ← sorted!
        System.out.println("Preorder  (root first): " + bst.preorder());
        // [50, 30, 20, 10, 40, 70, 60, 65, 80]
        System.out.println("Postorder (root last):  " + bst.postorder());
        // [10, 20, 40, 30, 65, 60, 80, 70, 50]

        // BFS level order
        System.out.println("\n=== BFS Level Order ===");
        List<List<Integer>> levels = bst.levelOrder();
        for (int i = 0; i < levels.size(); i++) {
            System.out.printf("Level %d: %s%n", i, levels.get(i));
        }
        // Level 0: [50]
        // Level 1: [30, 70]
        // Level 2: [20, 40, 60, 80]
        // Level 3: [10, 65]

        // Search
        System.out.println("\n=== Search ===");
        System.out.println("Contains 65: " + bst.contains(65)); // true
        System.out.println("Contains 99: " + bst.contains(99)); // false
        System.out.println("Contains 10: " + bst.contains(10)); // true

        // Height and depth
        System.out.println("\n=== Height / Depth ===");
        System.out.println("Height:    " + bst.height());    // 3
        System.out.println("Max depth: " + bst.maxDepth());  // 4

        // Delete
        System.out.println("\n=== Delete ===");
        System.out.println("Before delete 30: " + bst.inorder());
        bst.delete(30); // Node with 2 children — inorder successor replaces it
        System.out.println("After delete 30:  " + bst.inorder());
        // 40 becomes the replacement (minimum of right subtree of 30)

        bst.delete(10); // Leaf node
        System.out.println("After delete 10:  " + bst.inorder());

        bst.delete(70); // Node with 2 children
        System.out.println("After delete 70:  " + bst.inorder());

        // Validate BST
        System.out.println("\n=== Validate BST ===");
        System.out.println("Is valid BST: " + bst.isValidBST()); // true

        // Invert
        System.out.println("\n=== Invert Tree ===");
        BinarySearchTree bst2 = new BinarySearchTree();
        for (int v : new int[]{4, 2, 7, 1, 3, 6, 9}) bst2.insert(v);
        System.out.println("Before invert: " + bst2.inorder()); // [1,2,3,4,6,7,9]
        bst2.invertTree();
        System.out.println("After invert:  " + bst2.inorder());  // [9,7,6,4,3,2,1]

        // Performance: BST vs linear scan
        System.out.println("\n=== Performance: BST vs Linear Scan ===");
        BinarySearchTree perfTree = new BinarySearchTree();
        int N = 1_000_000;
        // Insert in random order (sorted would create a degenerate tree)
        java.util.Random rand = new java.util.Random(42);
        for (int i = 0; i < N; i++) perfTree.insert(rand.nextInt(N * 10));

        int target = rand.nextInt(N * 10);
        long start = System.nanoTime();
        perfTree.contains(target);
        long bstTime = System.nanoTime() - start;

        System.out.printf("BST search in %,d nodes: %,d ns%n", N, bstTime);
        System.out.printf("Expected comparisons: ~%.0f (log₂ %,d)%n",
                Math.log(N) / Math.log(2), N);
    }
}
