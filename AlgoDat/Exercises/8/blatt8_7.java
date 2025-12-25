
private Node rotateRight(Node n) {
  // error was: Node t = n;
  Node t = n.left; // t as the LEFT child
  n.left = t.right; // t's right (LR) to n's left
  t.right = n; // n is t's right child
  t.color = n.color; // new root t inherits the color of the old root n
  n.color = RED; // n becomes red
  t.size = n.size; // Update sizes
  n.size = 1 + size(n.left) + size(n.right);
  return t; // Return the new root
//Левый всплыл и отдал правого ребенка. Новый — забрал цвет старого,а старый — покраснел
}
/*
Before
    N
   / \
   L  R
  / \
 LL  LR
 
The Bug
    N <----.
   / \    |
      R `----' (Self-Loop: n.right = n) - infinite loop
(L is lost!) garbage collector
 
Correct
    L (new root, takes n's color)
   / \
  LL  N (now red)
     / \
    LR  R