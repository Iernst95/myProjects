#include <stdio.h>
#include <stdlib.h>

void CountingFunc(int array[], int n, int num, int *maxval, int *minval);//prototype

int main(int argc, const char * argv[]){//main function
    int a=0;
    int x=21;
    int *minval;
    int z=0;
    minval=&z;
    int *maxval;
    int f=0;
    maxval=&f;
    int array[]={1,2,-3,4,5,-6,7,8,9,0,0,0,0,0,-12,123,234,-565,765,23,6543};
    CountingFunc(array, x, a, maxval, minval);
    printf("%d numbers were greater than %d\n ", *maxval, a);
    printf("%d numbers were smaller than %d\n", *minval, a);
    return 0;
}
void CountingFunc(int array[], int n, int num, int *maxval, int *minval){
    *maxval=0;
    *minval=0;
    for(int i=0;i<n;i++){//for loop that works through the array
        if(array[i]>num){
           *maxval=*maxval+1;
        }
        if(array[i]<num){
            *minval=*minval+1;
        }
    }
}
