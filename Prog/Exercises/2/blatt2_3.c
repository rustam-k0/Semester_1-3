#include <stdio.h>

int main() {
    int n, r;
    printf("Print a number: ");
    scanf("%d", &n);

    r = n % 57;
    printf("Remainder: %d\n", r);

    if (r == 0) {
        printf("Scherzkeks\n");
    }

    return 0;
}

// Aufgabe 3 – Rest der Division
