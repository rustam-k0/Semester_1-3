#include <stdio.h>

//  НОД - алгоритм Евклида (ИСПРАВЛЕНО: строго методом вычитания по заданию)
int ggT(int a, int b) {
    if (a <= 0 || b <= 0) {
        return -1;
    }

    // Используем вычитание, так как modulo запрещен в задаче 3
    while (a != b) {
        if (a > b) {
            a = a - b;
        } else {
            b = b - a;
        }
    }
    return a;
}

// НОК 
int kgV(int a, int b) {
    if (a <= 0 || b <= 0) {
        return -1;
    }

    // Сначала делим, потом умножаем, чтобы избежать переполнения типа int
    // Формула: (a * b) / ggT(a, b) превращается в (a / ggT(a, b)) * b
    return (a / ggT(a, b)) * b;
}

int main() {
    // Тестовый пример
    int a = 12;
    int b = 18;
    
    // ggT(12, 18) = 6
    // kgV(12, 18) = (12 * 18) / 6 = 36
    printf("Zahlen: %d, %d\n", a, b);
    printf("ggT: %d\n", ggT(a, b));
    printf("kgV: %d\n", kgV(a, b));

    return 0;
}