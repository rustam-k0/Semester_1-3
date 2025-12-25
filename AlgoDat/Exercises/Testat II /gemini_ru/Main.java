import java.util.Random;

public class Main {
  public static void main(String[] args) {
    RBTree<IntComparable> tree = new RBTree<>();
    Random rand = new Random();

    System.out.println("Starting insertion of 15 random integers...");

    for (int i = 0; i < 15; i++) {
      int randomInt = rand.nextInt(100); // Случайные числа 0-99
      IntComparable key = new IntComparable(randomInt);

      System.out.println("Step " + (i + 1) + ": Insert " + randomInt);
      tree.insert(key);

      // Сохранение DOT после каждого шага [cite: 1316]
      // Имена файлов: tree_01.dot, tree_02.dot ...
      String filename = String.format("tree_%02d.dot", i + 1);
      tree.printDOT(filename);
    }

    System.out.println("Done. DOT files generated.");
  }
}