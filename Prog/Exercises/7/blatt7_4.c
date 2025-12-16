#include <stdio.h>
#include <string.h> 

void removeVowels(char s[]) {
    int write = 0;
    int read = 0;
    const char *vowels = "AEIOUaeiou"; 

    while (s[read] != '\0') {
        if (strchr(vowels, s[read]) == NULL) {
            s[write] = s[read];
            write++;
        }
        read++;
    }
    s[write] = '\0';
}

int main() {
    char str[] = "Programmieren"; 

    printf("Before: %s\n", str);
    removeVowels(str);
    printf("After:  %s\n", str);

    return 0;
}