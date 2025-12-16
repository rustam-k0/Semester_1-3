#include <stdio.h>

// 5) Quersumme
void Quersumme() {
    int x;
    int sum = 0;
    scanf("%d", &x);

    while (x > 0) {
        sum += x % 10;
        x /= 10;
    }
    printf("%d\n", sum);
}

// 6) Iterierte Quersumme
void iterierte_quersumme() {
    int x;
    scanf("%d", &x);

    while (x >= 10) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        x = sum;
    }
    printf("%d\n", x);
}

int main() {
    Quersumme();
    iterierte_quersumme();
    return 0;
}