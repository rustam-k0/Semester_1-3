#include <stdio.h>

int main() {
    int a, b, n;
    scanf("%d %d %d", &a, &b, &n);

    int big = n / 5;
    if (big > b) {
        big = b;
    }

    int small = n - big * 5;

    if (small <= a) {
        printf("%d\n", small);
    } else {
        printf("-1\n");
    }

    return 0;
}

// Aufgabe 5 – Schokoriegel
