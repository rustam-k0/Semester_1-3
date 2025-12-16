#include <stdio.h>

// 3) Lösungen der Gleichung
void Losung_von_Gleichungen() {
    for (int i = 1; i <= 100; i++) {
        if (i * i * i - 73 * i * i + 1655 * i - 11951 == 0) {
            printf("%d\n", i);
        }
    }
}

int main() {
    Losung_von_Gleichungen();
    return 0;
}