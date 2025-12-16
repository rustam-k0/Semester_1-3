#include <stdio.h>

// 2.a) Alle Zahlen von 100 bis 200
void print_100_to_200() {
    for (int i = 100; i <= 200; i++) {
        printf("%d ", i);
    }
    printf("\n");
}

// 2.b) Alle geraden Zahlen zwischen 111 und 222
void print_even_111_to_222() {
    for (int i = 112; i <= 222; i += 2) {
        printf("%d ", i);
    }
    printf("\n");
}

// 2.c) Alle durch 3 teilbaren Zahlen von 333 bis 222 absteigend
void print_div3_333_to_222() {
    for (int i = 333; i >= 222; i--) {
        if (i % 3 == 0) {
            printf("%d ", i);
        }
    }
    printf("\n");
}

// 2.d) Summe aller durch 3 teilbaren Zahlen von 1 bis 1000
void sum_div3_1_to_1000() {
    int sum = 0;
    for (int i = 1; i <= 1000; i++) {
        if (i % 3 == 0) {
            sum += i;
        }
    }
    printf("%d\n", sum);
}

// 2.e) Prüft, ob die Ziffer 7 vorkommt
void number_7_inside() {
    int a;
    scanf("%d", &a);
    if (a < 0) a = -a;
    while (a > 0) {
        if (a % 10 == 7) {
            printf("true\n");
            return;
        }
        a /= 10;
    }
    printf("false\n");
}

// 2.f) Zählt, wie oft die Ziffer 7 vorkommt
void count_number_7_inside() {
    int a;
    scanf("%d", &a);
    if (a < 0) a = -a;

    int b = 0;
    while (a > 0) {
        if (a % 10 == 7) {
            b++;
        }
        a /= 10;
    }
    printf("%d\n", b);
}


int main() {
    print_100_to_200();
    print_even_111_to_222();
    print_div3_333_to_222();
    sum_div3_1_to_1000();

    number_7_inside();
    count_number_7_inside();

    return 0;
}