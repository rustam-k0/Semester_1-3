#include <stdio.h>
#include <stdbool.h>

bool mauern(int klein, int gross, int ziel) {
    // Макс возможных больших камней, чтобы не превысить цель
    if (ziel / 5 < gross) {
        gross = ziel / 5;
    }

    // Остаток длины: хватит ли маленьких
    return (ziel - gross * 5) <= klein;
}

int main() {

    // Тесты из задания
    printf("mauern(3,1,8)  -> expected: 1, got: %d\n",  mauern(3,1,8));
    printf("mauern(3,1,9)  -> expected: 0, got: %d\n",  mauern(3,1,9));
    printf("mauern(2,3,13) -> expected: 0, got: %d\n",  mauern(2,3,13));
    printf("mauern(3,2,10) -> expected: 1, got: %d\n",  mauern(3,2,10));

    return 0;
}