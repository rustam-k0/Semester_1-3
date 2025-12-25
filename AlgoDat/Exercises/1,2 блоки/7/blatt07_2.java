public class blatt07_2 {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Integer findCeiling(Node root, int k) {
        if (root == null) {
            return null;
        }

        Integer bestCandidate = null;
        Node current = root;

        while (current != null) {
            if (current.data == k) {
                return current.data;
            } 
            else if (current.data > k) {
                bestCandidate = current.data;
                current = current.left;
            } 
            else {
                current = current.right;
            }
        }

        return bestCandidate;
    }

    public static void main(String[] args) {
        // Tree construction based on Task 7.1
        Node root = new Node(30);
        insert(root, 40);
        insert(root, 24);
        insert(root, 58);
        insert(root, 48);
        insert(root, 26);
        insert(root, 11);
        insert(root, 13);

        System.out.println("Tree constructed.");

        // Test 1: Exact match (Search >= 24) -> Expected: 24
        System.out.println("Search >= 24: " + findCeiling(root, 24));

        // Test 2: No exact match, find larger (Search >= 12) -> Expected: 13
        System.out.println("Search >= 12: " + findCeiling(root, 12));

        // Test 3: No exact match, find larger (Search >= 35) -> Expected: 40
        System.out.println("Search >= 35: " + findCeiling(root, 35));

        // Test 4: Target larger than max (Search >= 100) -> Expected: null
        System.out.println("Search >= 100: " + findCeiling(root, 100));
    }

    public static void insert(Node node, int value) {
        if (value < node.data) {
            if (node.left == null) node.left = new Node(value);
            else insert(node.left, value);
        } else if (value > node.data) {
            if (node.right == null) node.right = new Node(value);
            else insert(node.right, value);
        }
    }
}