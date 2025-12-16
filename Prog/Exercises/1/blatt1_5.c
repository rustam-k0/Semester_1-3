#include <stdio.h>

int main() {
    int a, b = 5, c, d;

    a = b / 2;                    // a=2
    printf("a=%d\n", a);

    c = b % 2;                    // c=1
    printf("c=%d\n", c);

    d = 1 - (b - 1);              // d=-3
    printf("d=%d\n", d);

    b = b * -3;                   // b=-15
    printf("b=%d\n", b);

    d = d % 3;                    // d=0
    printf("d=%d\n", d);

    c = c + b * d + 4;            // c=5
    printf("c=%d\n", c);

    a = b + d;                    // a=-15
    printf("a=%d\n", a);

    a = 0; b = 2; c = 3; d = 4;

    a = (b + 2) * 2 * c + 1;      // a=25
    printf("a=%d\n", a);

    a = b * d * c * (-1);         // a=-24
    printf("a=%d\n", a);

    return 0;
}