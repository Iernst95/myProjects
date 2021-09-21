#include "Lab9.h"
/* This function receives a value. It creates a weather pointer and mallocs memory for it. It sets the value eaqual to the value the function received. It sets the Ptr value to NULL*/
Weather* createNode(float temp){
    Weather * p=(Weather *)malloc(sizeof(Weather));
    if(p){
        p->temperature=temp;
        p->Ptr=NULL;
    }
    return p;
}
/* This function creates an empty list with a dummy node at the head and tail and returns the head of the list */
Weather* initListWithDummyNode(){
    Weather *dummyhead=(Weather *)malloc(sizeof(Weather));
    if(dummyhead==NULL){
        printf("Malloc has failed.\n");
        return NULL;
    }
    Weather *dummytail=(Weather *)malloc(sizeof(Weather));
       if(dummytail==NULL){
           printf("Malloc has failed.\n");
           return NULL;
       }
    dummytail->Ptr=NULL;
    dummytail->temperature= 0;
    dummyhead->Ptr=dummytail;
    dummyhead->temperature=0;
    
    
    return dummyhead;
}

/* This function receives the head of a linked list and a value and creates a node with the value inside of it and adds it to the head of the list */
int insertToHead(Weather* head,float temp){
    Weather *p = createNode(temp);
    p->Ptr=head->Ptr;
    head->Ptr=p;
    if(p==NULL){
        return 0;
    }
    return 1;
}
/* This function receives the head of a linked list and a value and creates a node with the value inside of it and loops through the list and adds the value to the tail of the list*/
int insertToTail(Weather* head,float temp){
    Weather *p = createNode(temp);
    Weather *count;
    count=head;
    while(count->Ptr->Ptr!=NULL){
        count=count->Ptr;
    }
    p->Ptr=count->Ptr;
       count->Ptr=p;
    if(p==NULL){
        return 0;
    }
    return 1;
}
/* This function receives the head of a list and prints out all of the values inside of the list*/
void printList(Weather* head){
    Weather *count;
    count=head->Ptr;
    while(count->Ptr!=NULL){
          printf("%.2f\n", count->temperature);
          count=count->Ptr;
      }
}
/* This function recieves the head of a list and frees all of the memory for the nodes EXCEPT for the dummy nodes */
void emptyList(Weather* head){
    Weather * temp, * count;
    if(head->Ptr==NULL){
        printf("There is nothing in the list\n");
        
    }
    count=head->Ptr;
    while(count->Ptr!=NULL){
        temp=count->Ptr;
        count->Ptr=temp->Ptr;
        free(temp);
    }
    
    
}
/* This function recieves the ehad of a list and frees all of the memory for each member, then sets the head to NULL */
void freeList(Weather* head){
    Weather *p, *store;
         
         p=head;
         while(p!=NULL){
             store=p->Ptr;
             free(p);
             p=store;
         }
         head=NULL;
}
