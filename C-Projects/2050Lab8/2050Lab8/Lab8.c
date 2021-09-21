#include "Lab8.h"
Weather* createNode(float temp){
    Weather * p=(Weather *)malloc(sizeof(Weather));
    if(p){
        p->temperature=temp;
        p->Ptr=NULL;
    }
    return p;
}
Weather* initList(void){
    
    return NULL;
}
Weather* addToHead(Weather * head,float temp){
    Weather * p=createNode(temp);
    p->Ptr=head;
        head=p;
    
    return p;
}
Weather* addToTail(Weather * head,float temp){
    Weather *p=createNode(temp);
    Weather * count;
    if(head==NULL){
        head=p;
        return head;
    }
    else{
        count=head;
        while(count->Ptr!=NULL){
            count=count->Ptr;
        }
        count->Ptr=p;
    }
    return head;
}
void printList(Weather * head){
    
    while(head!=NULL){
        printf("%.2f\n", head->temperature);
        head=head->Ptr;
    }
}
int checkMinMaxTemp(Weather * head,float * tempMin,float * tempMax){
    Weather * count=head;
        if(head==NULL){
        return -1;
    }
    float a=head->temperature;
    while(count!=NULL){
        if(a>count->temperature){
            a=count->temperature;
        }
        count=count->Ptr;
    }
    *tempMin=a;
    float b=head->temperature;
    count=head;
    while(count!=NULL){
        if(count->temperature>b){
            b=count->temperature;
        }
        count=count->Ptr;
    }
    *tempMax=b;
    return 1;
}
Weather* freeList(Weather * head){
    Weather *p, *store;
       
       p=head;
       while(p!=NULL){
           store=p->Ptr;
           free(p);
           p=store;
       }
       head=NULL;
    return head;
}

