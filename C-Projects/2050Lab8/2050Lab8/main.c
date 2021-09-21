#include "Lab8.h"

int main(void){
    Weather * head;
    float a=1.6f;
    float b=15.7f;
    float valueMin = 0;
    float valueMax = 0;
    int i=0;
   head=initList();
    head=addToHead(head, b);
    for(int x=0;x<20;x++){
        head=addToTail(head, (float)x+5);
    }
    head=addToTail(head, a);
    printList(head);
    i=checkMinMaxTemp(head, &valueMin, &valueMax);
    if(i==1){
        printf("Values found:\n Min Value: %f\n Max Value: %f\n", valueMin, valueMax);
        printf("%f, %f \n", valueMin, valueMax);
    }
    if(i==-1){
        printf("The current list is empty\n");
    }
    head=freeList(head);
    if(head==NULL)
        printf("success\n");
}
