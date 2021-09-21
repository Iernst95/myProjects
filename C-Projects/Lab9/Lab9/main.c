#include <stdio.h>
#include "Lab9.h"

int main(void){
    Weather *head =initListWithDummyNode();
    for(int i=0;i<10;i++){
    int x= insertToTail(head, i+5);
    
    if(x==1){
        printf("succeeded\n");
    }
    if(x==0){
        printf("failed\n");
    }
    }
    for(int i=0;i<10;i++){
    int y= insertToHead(head, i);
       if(y==1){
           printf("succeeded\n");
       }
       if(y==0){
           printf("failed\n");
       }
    }
    printList(head);
    emptyList(head);
    printList(head);
    for(head=head;head!=NULL;head=head->Ptr){
        printf("%f", head->temperature);
    }
    freeList(head);
    if(head==NULL){
        printf("Yes;");
    }
}
