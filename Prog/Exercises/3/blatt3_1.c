#include <stdio.h>

// 1) Letzte Ziffer gleich
void last_digit() {
    int a, b;
    scanf("%d %d", &a, &b);
    if (a % 10 == b % 10) {
        printf("Letzte Ziffer gleich");
    }
    printf("\n");
}

int main() {
    last_digit();
    return 0;
}