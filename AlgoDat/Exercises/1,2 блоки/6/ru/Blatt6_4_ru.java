import java.util.Arrays;

public class Blatt6_4_ru {

    public static void main(String[] args) {
        // 1. Исходные данные
        // Массив ключей из задания
        int[] keys = {1001, 1542, 429, 1320, 17, 900, 417, 2302, 1920};
        int N = 10; // Размер таблицы

        // Создаем две пустые таблицы (массивы) для двух методов
        // -1 будет означать, что ячейка пуста (так как ключи положительные)
        int[] tableLinear = new int[N];
        int[] tableQuadratic = new int[N];

        // Заполняем массивы -1 (пустота), чтобы не путать с числом 0
        Arrays.fill(tableLinear, -1);
        Arrays.fill(tableQuadratic, -1);

        System.out.println("--- СТАРТ ---");

        // --- ЧАСТЬ 1: Линейное зондирование (Linear Probing) ---
        System.out.println("\n1. Линейное зондирование:");
        
        // Проходим по каждому ключу из нашего списка
        for (int key : keys) {
            // ШАГ A: Вычисляем хеш (базовый индекс)
            // Формула: (x / 100) % 10. В Java деление int обрезает дробную часть (floor)
            int hashIndex = (key / 100) % N;
            
            // ШАГ B: Ищем свободное место
            int attempt = 0; // Счётчик попыток
            boolean inserted = false; // Флаг: вставили или нет

            while (attempt < N) {
                // Линейная формула: (Hash + 0), (Hash + 1), (Hash + 2)...
                // % N нужно, чтобы если индекс стал 10, он вернулся в 0 (закольцевать)
                int currentIndex = (hashIndex + attempt) % N;

                // Проверка: если ячейка равна -1, она свободна
                if (tableLinear[currentIndex] == -1) {
                    tableLinear[currentIndex] = key; // Записываем число
                    inserted = true;
                    break; // Прерываем while, число записано
                }
                
                // Если занято, увеличиваем попытку и идем на следующий круг цикла
                attempt++;
            }

            if (!inserted) {
                System.out.println("Не удалось вставить число " + key + " (таблица полна)");
            }
        }
        // Вывод результата метода Arrays.toString() превращает массив в красивую строку
        System.out.println("Итог Linear: " + Arrays.toString(tableLinear));


        // --- ЧАСТЬ 2: Квадратичное зондирование (Quadratic Probing) ---
        System.out.println("\n2. Квадратичное зондирование:");
        
        for (int key : keys) {
            int hashIndex = (key / 100) % N;
            int attempt = 0;
            boolean inserted = false;

            // Ограничим попытки (например, 20), так как квадратичное может зациклиться бесконечно
            while (attempt < 20) {
                // Квадратичная формула: Hash + 0^2, Hash + 1^2, Hash + 2^2...
                // attempt * attempt — это возведение в квадрат
                int offset = attempt * attempt;
                int currentIndex = (hashIndex + offset) % N;

                if (tableQuadratic[currentIndex] == -1) {
                    tableQuadratic[currentIndex] = key;
                    inserted = true;
                    break;
                }
                
                // Коллизия (место занято) -> пробуем следующий квадрат
                attempt++;
            }

            if (!inserted) {
                System.out.println("ВНИМАНИЕ: Не удалось вставить число " + key + " (цикл или таблица полна)");
            }
        }
        System.out.println("Итог Quadratic: " + Arrays.toString(tableQuadratic));
    }
}

//Запустив этот код, вы увидите следующее:
//Линейное зондирование: Все числа будут вставлены, но образуются "кластеры" (группы занятых ячеек подряд). Последние числа придется двигать далеко от их родного индекса.
//Квадратичное зондирование: Одно из чисел (скорее всего 1920 или 2302) не удастся вставить, даже если в таблице есть свободные места. Это происходит потому, что квадратичный шаг (+1,+4,+9...) "перепрыгивает" одни и те же ячейки и может не попасть в свободную "дырку". Это известная проблема метода, если N (размер таблицы) не является простым числом (а 10 — не простое число).
