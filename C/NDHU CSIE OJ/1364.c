#include <stdio.h>
int main() {
    int i,N,ar[1000],a,j,s[100][100];

    double tmp,count;
    while(scanf("%d",&N)!=EOF){

            while(N != 1){
                if(N%2==0){
                    printf("%d/2=%d\n",N,N/2);
                    N = N/2;
                }
                else if(N%2==1){
                    printf("%d*3+1=%d\n",N,N*3+1);
                    N = N*3+1;
                }
            }
    }

    return 0;
}
