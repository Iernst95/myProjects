#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int empID, age;
    float salary;
}Record;

Record* readRecordFile(FILE * fp);
int getSize(Record* array);
Record* getEmpByID(Record* array, int ID);
int findSalary(Record* array,int num1,int num2);
void freeRecordArray(Record* array);

int main(void) {
    int num1=10000;
    int num2=20000;
    int employeeNum=2345;
    FILE *fp = fopen("employee.csv","r");
    if(fp==NULL){
        printf("File did not open\n");
        return 0;
    }
    Record * Info = readRecordFile(fp);
    getEmpByID(Info, employeeNum);
    findSalary(Info, num1, num2);
    
    fclose(fp);
    printf("Success!\n");
    return 0;
}
//This function receives a file pointer. It then scans this file pointer and puts the amount of lines in the file into an int. It then creates an array of type record and allocates memory for it. Then, it fills the array with the information from the file. It takes the integer that countains how many lines are in the file and hides it before the array, increments the array, and returns it.
Record* readRecordFile(FILE * fp){
    int size;
    fscanf(fp, "%d\n", &size);
    Record * array = malloc(sizeof(Record)*(size)+sizeof(int));
    if(((void*)array)== NULL){
        printf("Malloc Failed\n");
        return NULL;
    }
    *((int*)array)=size;
    array = (Record*)((int*)array + 1);
    for(int i=0; i<size; i++){
        fscanf(fp, "%d,%f,%d\n", &array[i].empID, &array[i].salary, &array[i].age);
    }
    return array;
}
//This function receives an array pointer and finds the size hidden before it and returns it.
int getSize(Record* array){
    int size= *((int*)array-1);
    return size;
}
//this function takes an array pointer and an int. It then creates another array pointer. it uses a loop to find an employee based on the integer it receives; if it finds one it sets the pointer equal to that pointer and returns it, if not, it returns NULL.
Record* getEmpByID(Record* array, int ID){
    int size=getSize(array);
    Record * Employee= NULL;
    for(int i=0; i<size; i++){
        if(ID ==array[i].empID){
            Employee=&array[i];
            break;
        }
    }
    if(Employee== NULL){
        return NULL;
    }
    return Employee;
}
//This function takes an array pointer and two ints. It then loops through that array pointer finding what salaries are between the two integers. For every salary that is, it incremements the counter. It then returns the counter.
int findSalary(Record* array, int num1 ,int num2){
    int size=getSize(array);
    int counter=0;
    for(int i=0; i<size; i++){
        if(array[i].salary>(float)num1 && array[i].salary<(float)num2){
            counter++;
        }
    }
    return counter;
}
//This function frees the array and sets the array pointer equal to NULL
void freeRecordArray(Record* array){
    free((int*)array-1);
    array=NULL;
}

