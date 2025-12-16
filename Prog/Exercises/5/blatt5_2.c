#include <stdio.h>

// Округляем 
int runde10(int n) {
    int rest = n % 10; // Последняя цифра
    if (rest >= 5) {
        // Вверх
        return n + (10 - rest);
    } else {
        // Вниз
        return n - rest;
    }
}

// Округляем 3 числа через runde10 и возвращает сумму.
int rundeSumme(int a, int b, int c) {
    return runde10(a) + runde10(b) + runde10(c);
}

int main() {
    // Пример 
    int a = 11;
    int b = 15;
    int c = 19;

    int summe = rundeSumme(a, b, c);
    // 11 -> 10
    // 15 g-> 20
    // 19 -> 20
    // Сумма: 10 + 20 + 20 = 50
    printf("Числа: %d, %d, %d\n", a, b, c);
    printf("Округленная сумма: %d\n", summe);

    return 0;
}