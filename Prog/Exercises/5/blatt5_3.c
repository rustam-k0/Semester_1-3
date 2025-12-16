#include <stdio.h>

int ggT(int a, int b) {
    if (a <= 0 || b <= 0) {
        return -1;  // Зашита от 0
    }

    while (a != b) {
        if (a > b) {
            a = a - b;
        } else {
            b = b - a;
        }
    }
    return a;
}

int main() {
    // Тестовый пример
    int a = 24;
    int b = 9;
    // Ожидаемый результат: 3
    printf("ggT(%d, %d) = %d\n", a, b, ggT(a, b));
    
    return 0;
}