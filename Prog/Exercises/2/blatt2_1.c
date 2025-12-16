#include <stdio.h>

int main() {
    float a, b;
    printf("Enter width and height: ");
    scanf("%f %f", &a, &b);

    float area = a * b;
    float perimeter = 2 * (a + b);

    printf("Area = %.2f\n", area);
    printf("Perimeter = %.2f\n", perimeter);
    return 0;
}

// Aufgabe 1 – Geometrie