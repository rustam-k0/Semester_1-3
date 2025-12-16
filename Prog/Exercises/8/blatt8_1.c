#include <stdio.h>

int main() {
    int current, max;
    
    FILE *in = fopen("werte.txt", "r");
    if (in == NULL) {
        printf("Error: Could not open werte.txt\n");
        return 1;
    }

    FILE *out = fopen("ergebnis.txt", "w");
    if (out == NULL) {
        printf("Error: Could not create ergebnis.txt\n");
        fclose(in);
        return 1;
    }

    if (fscanf(in, "%d", &max) == 1) {
        while (fscanf(in, "%d", &current) == 1) {
            if (current > max) {
                max = current;
            }
        }
        fprintf(out, "%d", max);
        printf("Done. Maximum: %d\n", max);
    } else {
        printf("File is empty.\n");
    }

    fclose(in);
    fclose(out);

    return 0;
}