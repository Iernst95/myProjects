/* Header .h file for CS 2050, 2020 SP, Thursday labs. Should NOT be modified or submitted. -- Shizeng */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct tempstruct{
    float temperature;
    struct tempstruct *Ptr;
}Weather;

Weather* createNode(float);
Weather* initList(void);
Weather* addToHead(Weather*,float);
Weather* addToTail(Weather*,float);
void printList(Weather*);
int checkMinMaxTemp(Weather*,float*,float*);
Weather* freeList(Weather*);

