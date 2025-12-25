import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RBTree<T extends Comparable<T>> {

  private static final boolean RED = true;
  private static final boolean BLACK = false;

  // Внутренний класс узла [cite: 1298]
  private class Node {
    T data;
    Node left, right, parent;
    boolean color;

    Node(T data) {
      this.data = data;
      this.color = RED; // Новые узлы всегда красные (Slide 305)
    }
  }

  private Node root;
  private final Node nil; // Sentinel node (Лист NIL)

  public RBTree() {
    nil = new Node(null);
    nil.color = BLACK; // NIL всегда черный
    nil.left = nil;
    nil.right = nil;
    root = nil;
  }

  // Метод вставки, ориентированный на слайд 305 [cite: 1300]
  public void insert(T data) {
    Node z = new Node(data);
    Node y = nil;
    Node x = root;

    // Спуск по дереву (Slide 305)
    while (x != nil) {
      y = x;
      if (z.data.compareTo(x.data) < 0) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    z.parent = y;
    if (y == nil) {
      root = z;
    } else if (z.data.compareTo(y.data) < 0) {
      y.left = z;
    } else {
      y.right = z;
    }

    // Инициализация связей нового узла
    z.left = nil;
    z.right = nil;
    z.color = RED; // [cite: 1549]

    // Восстановление свойств RBT [cite: 1302]
    fixInsert(z);
  }

  // Балансировка после вставки. Реализует случаи 1-5 из лекции.
  // См. слайды 307-320.
  private void fixInsert(Node z) {
    // Пока отец красный (нарушение правила: красный не может иметь красного сына)
    while (z.parent.color == RED) {
      if (z.parent == z.parent.parent.left) {
        Node y = z.parent.parent.right; // Дядя (Uncle)

        // Случай 3: Дядя красный [cite: 1594]
        if (y.color == RED) {
          // System.out.println("Fixing Case 3: Uncle is RED"); // Документация в коде
          z.parent.color = BLACK; // Отец -> BLACK
          y.color = BLACK; // Дядя -> BLACK
          z.parent.parent.color = RED; // Дед -> RED [cite: 1626]
          z = z.parent.parent; // Поднимаемся к деду
        } else {
          // Случай 4: Дядя черный, Z - внутренний внук (Triangle shape) [cite: 1633]
          if (z == z.parent.right) {
            // System.out.println("Fixing Case 4: Inner grandchild (Left-Right)");
            z = z.parent;
            rotateLeft(z); // Превращаем в случай 5 [cite: 1759]
          }

          // Случай 5: Дядя черный, Z - внешний внук (Line shape) [cite: 1697]
          // System.out.println("Fixing Case 5: Outer grandchild (Left-Left)");
          z.parent.color = BLACK; // Отец -> BLACK
          z.parent.parent.color = RED; // Дед -> RED
          rotateRight(z.parent.parent); // Вращение деда вправо [cite: 1720]
        }
      } else {
        // Симметричная ситуация (Отец - правый ребенок)
        Node y = z.parent.parent.left; // Дядя

        // Случай 3 (симметричный)
        if (y.color == RED) {
          // System.out.println("Fixing Case 3 (Symmetric)");
          z.parent.color = BLACK;
          y.color = BLACK;
          z.parent.parent.color = RED;
          z = z.parent.parent;
        } else {
          // Случай 4 (симметричный)
          if (z == z.parent.left) {
            // System.out.println("Fixing Case 4 (Symmetric)");
            z = z.parent;
            rotateRight(z);
          }
          // Случай 5 (симметричный)
          // System.out.println("Fixing Case 5 (Symmetric)");
          z.parent.color = BLACK;
          z.parent.parent.color = RED;
          rotateLeft(z.parent.parent);
        }
      }
    }
    // Случай 1/2: Корень всегда черный [cite: 1565]
    root.color = BLACK;
  }

  // Левый поворот (Slide 303/Pseudo-Java for Right Rotation mirrored)
  private void rotateLeft(Node x) {
    Node y = x.right;
    x.right = y.left;
    if (y.left != nil) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == nil) {
      root = y;
    } else if (x == x.parent.left) {
      x.parent.left = y;
    } else {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  // Правый поворот (Slide 302) [cite: 1496]
  private void rotateRight(Node x) {
    Node y = x.left;
    x.left = y.right;
    if (y.right != nil) {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == nil) {
      root = y;
    } else if (x == x.parent.right) {
      x.parent.right = y;
    } else {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  // Вывод в DOT формат [cite: 1309]
  public void printDOT(String filename) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
      writer.println("digraph G {");
      writer.println("\tnode [style=filled, fontcolor=white, fontsize=12];"); // Стиль узлов

      // Рекурсивный обход для построения графа
      if (root != nil) {
        printDOTRecursive(root, writer);
      }

      // Отрисовка NIL узлов (чтобы структура была видна, как в примере)
      printNilNodes(root, writer);

      writer.println("}");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void printDOTRecursive(Node n, PrintWriter writer) {
    // Определение цвета узла
    String fillcolor = (n.color == RED) ? "red" : "black";
    writer.printf("\t%s [label=\"%s\", fillcolor=%s];%n", n.data.toString(), n.data.toString(), fillcolor);

    if (n.left != nil) {
      writer.printf("\t%s -> %s;%n", n.data.toString(), n.left.data.toString());
      printDOTRecursive(n.left, writer);
    }
    if (n.right != nil) {
      writer.printf("\t%s -> %s;%n", n.data.toString(), n.right.data.toString());
      printDOTRecursive(n.right, writer);
    }
  }

  // Вспомогательный метод для отображения NIL (квадратиков), как в
  // TestatÜ8BeispielDatei.txt
  private int nilCounter = 0;

  private void printNilNodes(Node n, PrintWriter writer) {
    if (n == nil)
      return;

    if (n.left == nil) {
      writer.printf("\tnil%d [label=\"NIL\", shape=record, width=.4, height=.25, fontsize=10, fillcolor=black];%n",
          nilCounter);
      writer.printf("\t%s -> nil%d;%n", n.data.toString(), nilCounter);
      nilCounter++;
    } else {
      printNilNodes(n.left, writer);
    }

    if (n.right == nil) {
      writer.printf("\tnil%d [label=\"NIL\", shape=record, width=.4, height=.25, fontsize=10, fillcolor=black];%n",
          nilCounter);
      writer.printf("\t%s -> nil%d;%n", n.data.toString(), nilCounter);
      nilCounter++;
    } else {
      printNilNodes(n.right, writer);
    }
  }
}