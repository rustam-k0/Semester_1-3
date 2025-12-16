#include <stdio.h>
#include <stdbool.h>

int main() {
    int day, month, year;

    // Ask the user to enter the date (German text stays unchanged)
    printf("Tag, Monat, Jahr eingeben (Format: 24 12 2022)\n");
    scanf("%d %d %d", &day, &month, &year);

    // January 1, 1900 was a Monday → base reference
    int shift = year - 1900;

    // Add number of years passed + number of leap years + passed days in current month
    shift = shift + shift / 4 + (day - 1);

    // Add days of all full months that have already passed in the current year
    if (month > 1)  shift = shift + 31; // January
    if (month > 2)  shift = shift + 28; // February
    if (month > 3)  shift = shift + 31; // March
    if (month > 4)  shift = shift + 30; // April
    if (month > 5)  shift = shift + 31; // May
    if (month > 6)  shift = shift + 30; // June
    if (month > 7)  shift = shift + 31; // July
    if (month > 8)  shift = shift + 31; // August
    if (month > 9)  shift = shift + 30; // September
    if (month > 10) shift = shift + 31; // October
    if (month > 11) shift = shift + 30; // November

    /* If it is January or February in a leap year, subtract 1 day,
       because February 29 has not happened yet.
       Exception: year = 1900 (base year) */
    if ((month == 1 || month == 2) && (year % 4 == 0) && year != 1900) {
        shift = shift - 1;
    }

    // shift % 7 gives the weekday (0 = Monday, 6 = Sunday) → output stays in German
    switch (shift % 7) {
        case 0: printf("Montag\n"); break;
        case 1: printf("Dienstag\n"); break;
        case 2: printf("Mittwoch\n"); break;
        case 3: printf("Donnerstag\n"); break;
        case 4: printf("Freitag\n"); break;
        case 5: printf("Samstag\n"); break;
        case 6: printf("Sonntag\n"); break;
    }
}
