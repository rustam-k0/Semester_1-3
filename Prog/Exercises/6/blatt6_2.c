#include <stdio.h>

int count_digits_iterative(int number) {
  if (number == 0) return 1;
  int count = 0;
  while (number != 0) {
    number = number / 10;
    count++;
  }
  return count;
}

int count_digits_recursive(int number) {
  if (number < 10) return 1;
  return 1 + count_digits_recursive(number / 10);
}

int main() {
  int number;

  printf("Print any number: ");
  scanf("%d", &number);
  
  printf("Number: %d. Iterative: %d. Recursive: %d\n", 
    number, 
    count_digits_iterative(number), 
    count_digits_recursive(number));
    
  return 0;
}