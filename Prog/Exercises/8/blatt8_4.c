#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Usage: %s <Day> <Month (German)> <Year>\n", argv[0]);
        return 1;
    }

    int day = atoi(argv[1]);
    char *inputMonth = argv[2];
    int year = atoi(argv[3]);

   
    const char *monthNames[] = {
        "Januar", "Februar", "Maerz", "April", "Mai", "Juni",
        "Juli", "August", "September", "Oktober", "November", "Dezember"
    };
    const int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int monthIndex = -1;
    for (int i = 0; i < 12; i++) {
        if (strcmp(inputMonth, monthNames[i]) == 0) {
            monthIndex = i;
            break;
        }
    }

    if (monthIndex == -1) {
        printf("Error: Invalid month name '%s'. Check spelling.\n", inputMonth);
        return 1;
    }

    if (day < 1 || day > daysInMonth[monthIndex]) {
        printf("Error: Day %d is out of range for %s (max %d).\n", day, monthNames[monthIndex], daysInMonth[monthIndex]);
        return 1;
    }

    if (year > 2025) {
        printf("Error: Date is in the future (after 2025).\n");
        return 1;
    }

    printf("%04d-%02d-%02d\n", year, monthIndex + 1, day);

    return 0;
}