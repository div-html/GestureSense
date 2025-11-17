#include<iostream>
using namespace std;
int main(){
    int r1;
    int c1;
    cout<<"enter the size of the row"<<endl;
    cin>>r1;
    cout<<"enter the size of the column"<<endl;
    cin>>c1;

    int arr[r1][c1];
    for(int i=0;i<r1;i++){
        for(int j=0;j<c1;j++){
            cout<<"enter the value for ["<<i<<"] ["<<j<<"]"<<endl;
            cin>>arr[i][j];
        }
    }
    for(int i=0;i<r1;i++){
        for(int j=0;j<c1;j++){
            cout<<arr[i][j]<<" ";
        }
        cout<<endl;
    }
}