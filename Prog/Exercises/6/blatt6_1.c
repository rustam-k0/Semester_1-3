#include <stdio.h>

// a
int sum(int n) {
    if (n <= 0) return 0;
    return n + sum(n - 1);
}

// b
int fakultaet(int n) {
    if (n <= 1) return 1;
    return n * fakultaet(n - 1);
}

// c
int fibonacci(int n) {
    if (n <= 0) return 0;
    if (n == 1) return 1;
    return fibonacci(n - 1) + fibonacci(n - 2);
}
// main
int main() {
    // n
    int target;
    printf("Enter a number: ");
    if (scanf("%d", &target) != 1) {
        printf("Error: Invalid input.\n");
        return 1;
    }
    
    int total_sum = sum(target);
    int total_fakultaet = fakultaet(target);
    int total_fibonacci = fibonacci(target);

    // result
    printf("The recursive sum of numbers from 1 to %d is %d.\n", target, total_sum);
    printf("The recursive factorial of %d is %d.\n", target, total_fakultaet);
    printf("The recursive fibonacci of %d is %d.\n", target, total_fibonacci);

    return 0;
}