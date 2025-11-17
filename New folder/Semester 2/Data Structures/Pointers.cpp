#include<iostream>
using namespace std;
int main(){
    int var=10;
    int* ptr=&var; //declare a pointer to an integer 
    cout<<"value of var:"<<var<<endl;
    cout<<"Address of var:"<<&ptr<<endl;//address operator
    cout<<"Value of ptr:"<<ptr<<endl;
    cout<<"value pointed to by ptr: "<<*ptr<<endl; //it is called the dereference operator 
    cout<<sizeof(ptr)<<endl;
}
/*
Dereferencing process of accessing the value at that particular address
Size of the pointers
The size of a pointer in a system is equal for every pointer no matter whay type of data it is pointing to. It
does not depened on the type, but in the oprating system

Types of pointers

Null pointers: A null pointer is a pointer that does not point to any valid memory location but NULL. It is 
often used to initialize a pointer when yiu do not want it to point to ay object.

void Pointers:

Dangling pointer: A dangling piinter is a pointer that refers to memory which has already been freed
*/