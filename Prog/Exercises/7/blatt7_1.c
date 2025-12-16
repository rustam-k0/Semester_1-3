#include <stdio.h>

void findMinMax(const int values[], int numValues, int *minOut, int *maxOut) {
  if (numValues <=0) return;

  *minOut = values[0];
  *maxOut = values[0];

  for (int i =1; i < numValues; i++) {
    if (values[i] > *maxOut) {
      *maxOut = values[i];
    }
     if (values[i] < *minOut) {
      *minOut = values[i];
    }
  }
}


int main(){
  int data[] = {1,2,3,4,5,6,7,8,9};
  int count = 9;
  int biggestNum;
  int smallestNum;
  findMinMax(data, count, &smallestNum, &biggestNum);
  printf("The biggest number of the list is %d\n", biggestNum);
  printf("The smallest number of the list is %d\n", smallestNum);
}
