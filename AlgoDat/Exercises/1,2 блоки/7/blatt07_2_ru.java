/**
 * Файл: blatt07_2_ru.java
 * Решение для задачи 7.2
 */
public class blatt07_2_ru {

    // Простейший класс узла, как в лекции [cite: 189-202]
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Основной алгоритм для задачи 7.2
     * Находит наименьший ключ >= k.
     * * @param root Корневой узел дерева
     * @param k Искомое значение (порог)
     * @return Найденный узел или null, если подходящих нет
     */
    public static Integer findCeiling(Node root, int k) {
        // Если дерево пустое, искать нечего
        if (root == null) {
            return null;
        }

        // Переменная для хранения "лучшего кандидата" на данный момент
        Integer bestCandidate = null;
        
        Node current = root;

        while (current != null) {
            if (current.data == k) {
                // БИНГО! Мы нашли точное совпадение.
                // Меньше уже быть не может (так как мы ищем >= k).
                return current.data; 
            } 
            else if (current.data > k) {
                // Текущий узел ПОДХОДИТ под условие (он больше k).
                // Запоминаем его как кандидата.
                bestCandidate = current.data;
                
                // Но мы жадные: вдруг слева есть число, которое тоже >= k,
                // но меньше текущего (ближе к k)?
                // Пример: k=10, current=20. Запомнили 20, но идем влево искать 12.
                current = current.left;
            } 
            else {
                // Случай: current.data < k
                // Текущее число слишком маленькое. Оно бесполезно.
                // Нам нужно число побольше, поэтому идем направо.
                // (Кандидата не обновляем, так как это число провалило тест).
                current = current.right;
            }
        }

        // Возвращаем того, кого нашли. Если никого не нашли, вернется null.
        return bestCandidate;
    }

    // --- ТЕСТ (Main) ---
    // Препод просил "Implementieren und testen" [cite: 11]
    public static void main(String[] args) {
        // Строим дерево из задачи 7.1 для теста
        //       30
        //     /    \
        //   24      40
        //  /  \       \
        // 11  26      58
        //   \        /
        //   13      48
        
        Node root = new Node(30);
        insert(root, 40);
        insert(root, 24);
        insert(root, 58);
        insert(root, 48);
        insert(root, 26);
        insert(root, 11);
        insert(root, 13);

        System.out.println("Дерево построено.");

        // Тест 1: Ищем точное совпадение (24)
        // Ожидание: 24
        System.out.println("Ищем >= 24: " + findCeiling(root, 24));

        // Тест 2: Ищем число, которого нет, но есть большее (ищем 12)
        // Ближайшее большее к 12 в дереве — это 13.
        // Ожидание: 13
        System.out.println("Ищем >= 12: " + findCeiling(root, 12));

        // Тест 3: Ищем число (35).
        // Ближайшее большее к 35 — это 40.
        // Ожидание: 40
        System.out.println("Ищем >= 35: " + findCeiling(root, 35));

        // Тест 4: Ищем число больше всех в дереве (100)
        // Ожидание: null
        System.out.println("Ищем >= 100: " + findCeiling(root, 100));
    }

    // Вспомогательный метод для вставки (чтобы работал тест main)
    // Взят из логики лекции [cite: 435]
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