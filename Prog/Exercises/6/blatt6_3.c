#include <stdio.h>
#include "chars.h"

int main(){
  char c1 = 'G';
  char c2 = 'm';
  char c3 = '5';

  printf("Char '%c': Upper= %d, Lower=%d, Digit = %d\n", c1, isUpper(c1), isLower(c1), isDigit(c1));
  printf("Char '%c': Upper= %d, Lower=%d, Digit = %d\n", c2, isUpper(c2), isLower(c2), isDigit(c2));
  printf("Char '%c': Upper= %d, Lower=%d, Digit = %d\n", c3, isUpper(c3), isLower(c3), isDigit(c3));


}