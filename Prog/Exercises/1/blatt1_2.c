#include <stdio.h>
#include <stdbool.h>
int main() {
    int i =23,j;
    int zahl = 42;

    j = i + zahl;
    j = j + 5; //comment
    printf("j hat Wert %d\n", j);

    zahl = 20 * (17+47) - 70/3;
    printf("zahl hat Wert %d\n", zahl);

    return 0;
}