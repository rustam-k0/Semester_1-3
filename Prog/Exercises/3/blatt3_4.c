#include <stdio.h>

// 4) Teiler
void Teiler(){
    int x;
    scanf("%d", &x);
    int sum = 0;
    for (int i = 1; i <= x; i++){
        if (x % i == 0){
            sum += i;
        }
    }
    printf("%d", sum);
}

int main() {
    Teiler();
    return 0;
}