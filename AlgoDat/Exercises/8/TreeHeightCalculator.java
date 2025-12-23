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
    // Поле для хранения рассчитанной высоты (кратчайшего пути до листа)
    int calculatedHeight;

    public Node(int value) {
        this.value = value;
        this.calculatedHeight = -1; // Инициализация
    }
}

public class TreeHeightCalculator {

    /**
     * Вычисляет высоту (min-depth) для текущего узла и всех его потомков.
     * Алгоритм работает по принципу Post-Order Traversal (обратный обход):
     * сначала обрабатываем детей, потом текущий узел.
     *
     * @param node Текущий рассматриваемый узел
     * @return Высота текущего узла (кратчайшее расстояние до листа)
     */
    public static int calculateHeights(Node node) {
        // Базовый случай: если узла не существует, возвращаем -1
        // (чтобы при добавлении +1 родитель получил 0, если это лист, но тут логика сложнее)
        // Для корректной работы min-depth проще обрабатывать null внутри рекурсии.
        if (node == null) {
            return -1;
        }

        // 1. Рекурсивный спуск: сначала вычисляем высоты для левого и правого поддерева.
        // Мы обязаны спуститься вниз, чтобы соблюсти требование "для всех узлов v".
        int leftHeight = calculateHeights(node.left);
        int rightHeight = calculateHeights(node.right);

        // 2. Логика вычисления высоты (Кратчайший путь до листа):
        
        // Случай А: Узел является листом (нет детей).
        // Путь до самого себя (листа) равен 0.
        if (node.left == null && node.right == null) {
            node.calculatedHeight = 0;
        }
        // Случай Б: Есть только правый ребенок.
        // Мы не можем пойти влево (там null, но это не лист), значит путь идет вправо.
        else if (node.left == null) {
            node.calculatedHeight = 1 + rightHeight;
        }
        // Случай В: Есть только левый ребенок.
        // Аналогично, путь обязан идти влево.
        else if (node.right == null) {
            node.calculatedHeight = 1 + leftHeight;
        }
        // Случай Г: Есть оба ребенка.
        // Выбираем кратчайший из двух путей.
        else {
            node.calculatedHeight = 1 + Math.min(leftHeight, rightHeight);
        }

        return node.calculatedHeight;
    }

    // Вспомогательный метод для печати дерева (Level Order)
    public static void printTreeWidthHeights(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println("Узел: " + current.value + " | Высота (min-path): " + current.calculatedHeight);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public static void main(String[] args) {
        // Тестовое построение дерева:
        //       10
        //      /  \
        //     5    20
        //         /  \
        //        15   30
        //              \
        //               40
        
        Node root = new Node(10);
        root.left = new Node(5); // Лист, высота д.б. 0
        root.right = new Node(20);
        root.right.left = new Node(15); // Лист, высота д.б. 0
        root.right.right = new Node(30);
        root.right.right.right = new Node(40); // Лист, высота д.б. 0

        // Запуск алгоритма
        calculateHeights(root);

        // Вывод результатов
        // Ожидание для root (10):
        // Путь влево: 10 -> 5 (длина 1)
        // Путь вправо: 10 -> 20 -> 15 (длина 2) или 10 -> 20 -> 30 -> 40 (длина 3)
        // Min путь = 1.
        System.out.println("Результаты вычислений:");
        printTreeWidthHeights(root);
    }
}