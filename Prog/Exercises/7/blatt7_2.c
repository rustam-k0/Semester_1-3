#include <stdio.h>

int countOdd (int f[], int n){
  if (n <= 0) return 0;
  int count = 0;
  for (int i = 0; i < n; i++) {
    if (f[i] % 2 != 0) {
      count++;
    }
  }
  return count; 
}

int countEven (int f[], int n){
  if (n <= 0) return 0;
  int count = 0;
  for (int i = 0; i < n; i++) {
    if (f[i] % 2 == 0) {
      count++;
    }
  }
  return count; 
}

int main(){
  int f1[] = {1,2,3,4,5,6,7,8,9};
  int n1 = 9;

  int f2[] = {10,20,30};
  int n2 = 3;

  int *f3 = {}; 
  int n3 = 0;

  printf("Test 1 (1-9): Odd: %d, Even: %d\n", countOdd(f1,n1), countEven(f1,n1));
  printf("Test 2 (Evens): Odd: %d, Even: %d\n", countOdd(f2,n2), countEven(f2,n2));
  printf("Test 3 (Empty): Odd: %d, Even: %d\n", countOdd(f3,n3), countEven(f3,n3));
  
  return 0; 
}