#include <stdio.h>

// C cannot print arrays directly with %s
void print_array(int arr[], int n) {
    printf("{ ");
    for (int i = 0; i < n; i++) {
        printf("%d", arr[i]);
        if (i < n - 1) printf(", ");
    }
    printf(" }\n");
}

void reverse(int arr[], int n) {
    if (n <= 0) return;
    // Stop at n/2 (middle)

    for (int i = 0; i < n / 2; i++) {
        int temp = arr[i];
        arr[i] = arr[n - 1 - i]; // "end minus i", we can not use -i index
        arr[n - 1 - i] = temp;
    }
}

void swap(int arr1[], int arr2[], int len) {
    // element by element: Arrays cannot be assigned directly
    for (int i = 0; i < len; i++) {
        int temp = arr1[i];
        arr1[i] = arr2[i];
        arr2[i] = temp;
    }
}

int main() {
    int data1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int count1 = 10;

    int data2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int data3[] = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    int count2 = 9;

    int data4[] = {1, 2, 3, 4};
    int data5[] = {4, 3, 2, 1};
    int count4 = 4;

    // TEST 1: REVERSE
    printf("Original array 1: ");
    print_array(data1, count1);
    
    reverse(data1, count1); // Call function first
    
    printf("Reversed array 1: ");
    print_array(data1, count1); 
    printf("\n");

    // TEST 2: SWAP ODD
    printf("Before swap (data2, data3):\n");
    print_array(data2, count2);
    print_array(data3, count2);

    swap(data2, data3, count2);

    printf("After swap:\n");
    print_array(data2, count2);
    print_array(data3, count2);
    printf("\n");

    // TEST 3: SWAP EVEN
    printf("Before swap (data4, data5):\n");
    print_array(data4, count4);
    print_array(data5, count4);

    swap(data4, data5, count4);

    printf("After swap:\n");
    print_array(data4, count4);
    print_array(data5, count4);

    return 0;
}