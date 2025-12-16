#include <stdio.h>

int main(void) {
    const int N = 1000000;

    double sum = 0.0;
    double sign = 1.0;
    for (int i = 0; i < N; i++) {
        double term = sign / (2.0 * i + 1.0);
        sum += term;
        sign = -sign;
    }
    double pi_leibniz = 4.0 * sum;

    double product = 1.0;
    for (int n = 1; n <= N; n++) {
        double num1 = 2.0 * n;
        double num2 = 2.0 * n;
        double den1 = 2.0 * n - 1.0;
        double den2 = 2.0 * n + 1.0;
        product *= (num1 / den1) * (num2 / den2);
    }
    double pi_wallis = 2.0 * product;

    printf("Pi (Leibniz) = %.15f\n", pi_leibniz);
    printf("Pi (Wallis)  = %.15f\n", pi_wallis);

    return 0;
}


/*
Главное:

1) Задаю количество шагов N = 1 000 000 — и для ряда, и для произведения используется одно и то же число итераций.

2) Ряд Лейбница:
   π/4 = Σ_{i=0}^{N-1} (-1)^i / (2i+1)
   - sum хранит текущую сумму.
   - sign хранит текущий знак (+1 или -1), чтобы не считать (-1)^i через pow.
   - В цикле for от i = 0 до N-1:
       term = sign / (2*i + 1)
       sum += term
       sign каждый раз умножаю на -1, чтобы чередовать + и -.
   - В конце умножаю sum на 4, получаю pi_leibniz.

3) Произведение Уоллиса:
   π/2 = Π_{n=1}^{N} (2n/(2n-1)) * (2n/(2n+1))
   - product хранит текущее произведение, стартует с 1.0.
   - В цикле for от n = 1 до N:
       считаю числители num1 = 2n, num2 = 2n
       считaю знаменатели den1 = 2n-1, den2 = 2n+1
       перемножаю множители: product *= (num1/den1) * (num2/den2)
   - В конце умножаю product на 2, получаю pi_wallis.

4) Все вещественные значения — типа double, как требуют условия.
   В конце просто печатаю обе аппроксимации с точностью до 15 знаков после запятой.
*/
