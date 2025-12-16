#include <stdio.h>

int main() {
    int num;
    int max = 0;

    for (int i = 0; i < 10; i++) {
        printf("Enter number %d: ", i + 1);
        scanf("%d", &num);

        if (num < 0) break;
        if (num > max) max = num;
    }

    printf("Maximum: %d\n", max);
    return 0;
}

///

#include <stdio.h>

int main() {
    int num;
    int max = 0;
    int count = 0;

    while (count < 10) {
        printf("Enter a positive number %d: ", count + 1);
        scanf("%d", &num);

        if (num < 0) continue;

        if (num > max) max = num;
        count++;
    }

    printf("Maximum: %d\n", max);
    return 0;
}
