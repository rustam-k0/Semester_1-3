#include <stdio.h>

int main() {
    int a, b, temp;
    printf("Print numbers (1-6): ");
    scanf("%d %d", &a, &b);

    if (a > b) {
        temp = a;
        a = b;
        b = temp;
    }

    if (a == b) {
        printf("%der Pasch\n", a);
    }
    else if (a == 1 && b == 2) {
        printf("Maexchen\n");
    }
    else {
        printf("%d\n", b * 10 + a);
    }

    return 0;
}

// Aufgabe 2 – Maexchen

