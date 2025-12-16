#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>

void to_lower_str(char str[]) {
    for (int i = 0; str[i] != '\0'; i++) {
        str[i] = (char)tolower((unsigned char)str[i]);
    }
}

bool palindrom(char str[]) {
    to_lower_str(str);

    int left = 0;
    int right = 0;

    while (str[right] != '\0') {
        right++;
    }
    right--;

    while (left < right) {
        if (str[left] != str[right]) {
            return false;
        }
        left++;
        right--;
    }

    return true;
}

int main(void) {
    char word1[] = "word";
    char word2[] = "level";
    char word3[] = "anna";
    char word4[] = "hello";

    printf("Is this word a palindrom?\n");

    printf("This word: %s - %s a palindrom.\n",
    word1, palindrom(word1) ? "is" : "is not");
    
    printf("This word: %s - %s a palindrom.\n",
    word2, palindrom(word2) ? "is" : "is not");
    
    printf("This word: %s - %s a palindrom.\n",
    word3, palindrom(word3) ? "is" : "is not");

    printf("This word: %s - %s a palindrom.\n",
    word4, palindrom(word4) ? "is" : "is not");

    return 0;
}
