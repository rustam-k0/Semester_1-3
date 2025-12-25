// Импорт стандартных библиотек.
// Queue - это интерфейс (контракт), ArrayDeque - реализация (инструмент).
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Blatt6_1_ru {

    public static void main(String[] args) {
        // Scanner читает ввод с клавиатуры
        Scanner scanner = new Scanner(System.in);
        System.out.print("N: ");
        int n = scanner.nextInt(); // Читаем число N
        solve(n); // Запускаем решение
        scanner.close(); // Хороший тон: закрываем поток ввода
    }

    public static void solve(int n) {
        // Создаем очередь.
        // <Long> значит, что внутри храним целые 64-битные числа.
        // ArrayDeque - это "Array Double Ended Queue", быстрая очередь на массиве.
        Queue<Long> queue = new ArrayDeque<>();
        
        // По условию число должно быть положительным (non-zero),
        // и состоять из 0 и 9. Самое маленькое такое число - 9.
        // Кладем его в очередь.
        queue.add(9L); // L в конце означает тип Long (литерал)

        // Цикл крутится, пока в очереди есть числа
        while (!queue.isEmpty()) {
            
            // queue.remove() достает "голову" очереди (самое старое число)
            // и удаляет его оттуда.
            long current = queue.remove(); 

            // Проверка: делится ли текущее число на N без остатка?
            if (current % n == 0) {
                System.out.println("Результат: " + current);
                return; // Нашли! Завершаем работу метода.
            }

            // Математическая магия генерации:
            // Чтобы приписать цифру справа, умножаем на 10 и прибавляем цифру.
            // Было 9 -> 9 * 10 = 90 (добавили 0)
            // Было 9 -> 9 * 10 + 9 = 99 (добавили 9)
            
            queue.add(current * 10);      // Добавляем ветку с нулем
            queue.add(current * 10 + 9);  // Добавляем ветку с девяткой
        }
    }
}