
// Left-Leaning Red-Black Tree (LLRB)

private Node insert(Node n, Key key, Value val) {
    if (n == null) {
        Node newNode = new Node(key, val, RED);
        newNode.count = 1; 
        return newNode;
    }

    int cmp = key.compareTo(n.key);
    if (cmp < 0)      n.left  = put(n.left, key, val);
    else if (cmp > 0) n.right = put(n.right, key, val);
    else              n.val   = val;

    if (isRed(n.right) && !isRed(n.left)) 
        n = rotateLeft(n);     //  right child is red & left is black: rotate left
    if (isRed(n.left) && isRed(n.left.left)) 
        n = rotateRight(n);  //  left child & left grandchild are red: rotate right
    if (isRed(n.left) && isRed(n.right)) 
        flipColors(n);     //  2 children are red: flip colors to pass red to a parent
    n.count = 1 + size(n.left) + size(n.right);
    return n;
}

```