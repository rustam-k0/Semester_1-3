#include <stdio.h>
#include <ctype.h> 

int main(){
  int ch;
  int totalChars = 0;
  int aCount = 0, eCount = 0, iCount = 0, oCount = 0, uCount = 0;

  FILE *in = fopen("test.txt", "r");
  if (in == NULL) {
    printf("Error: Could not open test.txt\n");
    return 1;
  }

  while ((ch = fgetc(in)) != EOF) {
    totalChars++;
    ch = tolower(ch);
    switch (ch) {
      case 'a':
        aCount++;
        break;
      case 'e':
        eCount++;
        break;
      case 'i':
        iCount++;
        break;
      case 'o':
        oCount++;
        break;
      case 'u':
        uCount++;
        break;
      default:
        break;
    }
  }
  fclose(in);
  printf("Total charackers: %d\n", totalChars); 
  printf("a: %d\n", aCount);
  printf("e: %d\n", eCount);
  printf("i: %d\n", iCount);
  printf("o: %d\n", oCount);
  printf("u: %d\n", uCount);
}