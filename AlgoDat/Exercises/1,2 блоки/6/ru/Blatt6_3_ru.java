import java.util.ArrayList;

public class Blatt6_3_ru {

    public static void main(String[] args) {
        // 1. Создаем "Хеш-таблицу".
        // По лекции (слайд 237), Offenes Hashing — это массив списков.
        // Размер таблицы N = 8 (по заданию).
        // Мы используем ArrayList, так как это удобный динамический список в Java.
        ArrayList<String>[] hashTable = new ArrayList[8];

        // Инициализируем каждую ячейку массива пустым списком, 
        // иначе получим ошибку NullPointerException при попытке добавить что-то.
        for (int i = 0; i < 8; i++) {
            hashTable[i] = new ArrayList<>();
        }

        // 2. Наши входные данные (имена из задания)
        String[] names = {
            "Patrizia", "Sebastian", "Maike", "Lukas", 
            "Nele", "Sarah", "Matthias", "Manuel"
        };

        System.out.println("Лог вставки элементов:");
        System.out.println("----------------------");

        // 3. Проходим по каждому имени и вставляем в таблицу
        for (String name : names) {
            // Считаем хеш для текущего имени
            int hashValue = calculateHash(name);

            // Выводим инфо для проверки (как на бумаге)
            System.out.println("Имя: " + name + " -> Хеш: " + hashValue);

            // Вставляем имя в соответствующий список (корзину)
            // hashTable[индекс] — это список, метод .add() добавляет в конец списка.
            hashTable[hashValue].add(name);
        }

        // 4. Выводим итоговую таблицу на экран
        System.out.println("\nИтоговая Хеш-таблица (Offenes Hashing):");
        System.out.println("---------------------------------------");
        for (int i = 0; i < 8; i++) {
            // Выводим индекс и содержимое списка.
            // Если список пуст, выведется [].
            System.out.println("Index " + i + ": " + hashTable[i]);
        }
    }

    // Вспомогательный метод для вычисления хеш-функции
    // Формула из задания: (Гласные + Длина) % 8
    public static int calculateHash(String s) {
        int length = s.length();         // Длина строки
        int vowelCount = countVowels(s); // Количество гласных
        
        // Формула: (Гласные + Длина) mod 8
        int sum = vowelCount + length;
        int result = sum % 8; // Оператор % — это остаток от деления
        
        return result;
    }

    // Метод для подсчета гласных букв
    public static int countVowels(String s) {
        int count = 0;
        // Приводим к нижнему регистру, чтобы не проверять 'A' и 'a' отдельно
        String lowerCaseName = s.toLowerCase();

        // Проходим по каждому символу строки
        for (int i = 0; i < lowerCaseName.length(); i++) {
            char c = lowerCaseName.charAt(i);
            // Проверяем, является ли символ гласной (a, e, i, o, u)
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }
}