#include<iostream> //input output stream
using namespace std; //cout insertion operator
int main(){ //extraction operator cin
   
    int arr[3][3]={{1,2,3},
                    {4,5,6},
                {7,8,9}};
    int arr2[3][3]={{5,6,7},
                    {8,9,2},
                    {3,2,1}};
    int arr3[3][3];
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            arr3[i][j]=0;
        }
    }
    for(int i=0;i<3;i++)
    {
        
        for(int j=0;j<3;j++){
            for(int k=0;k<3;k++){
                 arr3[i][j]+=arr[i][k]*arr2[k][j];
            }
           
            
        }
        
        cout<<endl;
    }
    for(int a=0;a<3;a++){
        for(int b=0;b<3;b++){
            cout<<arr3[a][b]<<" ";

        }
            cout<<endl;
    }
}
