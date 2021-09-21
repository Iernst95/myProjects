/* Header .h file for CS 2050, 2020 SP, Tuesday labs. Should NOT be modified or submitted. -- Shizeng */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct tempstruct{
    float temperature;
    struct tempstruct *Ptr;
}Weather;

Weather* initListWithDummyNode(void);
int insertToHead(Weather*,float);
int insertToTail(Weather*,float);
void printList(Weather*);
void emptyList(Weather*);
void freeList(Weather*);

