#include <stdio.h>

/*
* BMI: Вес / (Рост * Рост)
*/
float berechneBMI(float gewicht, float groesse) {
    if (groesse <= 0) return 0.0f; // Защита от 0
    return gewicht / (groesse * groesse);
}

/**
 * Категории BMI
 */
void zeigeKategorie(float bmi) {
    printf("Kategorie: ");
    if (bmi < 18.5) {
        printf("Untergewicht\n");
    } else if (bmi < 25.0) {
        printf("Normalgewicht\n");
    } else if (bmi < 30.0) {
        printf("Uebergewicht\n");
    } else {
        printf("Adipositas\n");
    }
}

int main() {
    float gewicht, groesse, bmi;
    char weiter;
    // do-while = хотя бы раз
    do {
        printf("\n--- BMI Rechner ---\n");
        
        printf("Gewicht eingeben (in kg): ");
        scanf("%f", &gewicht);
        
        printf("Groesse eingeben (in m): ");
        scanf("%f", &groesse);

        // Функция расчета
        bmi = berechneBMI(gewicht, groesse);

        // Результат
        printf("Ihr BMI betraegt: %.2f\n", bmi);

        // Классификация
        zeigeKategorie(bmi);

        // Продолжать?
        printf("\nMoechten Sie noch einen BMI berechnen? (j/n): ");
        // %c, чтобы пропустить символ новой строки в буфере
        scanf(" %c", &weiter);

    } while (weiter == 'j' || weiter == 'J');

    return 0;
}