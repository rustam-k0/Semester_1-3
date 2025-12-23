import java.util.LinkedList;
import java.util.Queue;

// LIFO, Call Stack, O(n), Post-Order Traversal, 
// left-right-root, reverse DFS,Min-Depth, left to right, child gives its height&+1
// non existent return 1, only root 0, only 1 child 1 ets.
//linear O(n) because dies instantly (if its a leaf) or on a way back after the death of all children
class Node {
    int value;
    Node left;
    Node right;
    int calculatedHeight;

    public Node(int value) {
        this.value = value;
        this.calculatedHeight = -1; 
    }
}

public class blatt8_2 {

    public static int calculateHeights(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = calculateHeights(node.left);
        int rightHeight = calculateHeights(node.right);

        if (node.left == null && node.right == null) {
            node.calculatedHeight = 0;
        }
        else if (node.left == null) {
            node.calculatedHeight = 1 + rightHeight;
        }
        else if (node.right == null) {
            node.calculatedHeight = 1 + leftHeight;
        }
        else {
            node.calculatedHeight = 1 + Math.min(leftHeight, rightHeight);
        }

        return node.calculatedHeight;
    }

    public static void printTreeWidthHeights(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println("Node: " + current.value + " | Height (min-path): " + current.calculatedHeight);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public static void main(String[] args) {
        //       10
        //      /  \
        //     5    20
        //         /  \
        //        15   30
        //              \
        //               40
        
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(30);
        root.right.right.right = new Node(40);

        calculateHeights(root);

        System.out.println("Calculation results:");
        printTreeWidthHeights(root);
    }
}