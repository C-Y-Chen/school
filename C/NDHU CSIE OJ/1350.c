#include <stdio.h>
int main() {
    int N,i,j,A,M,B,count,tmp,tmp2,a[101];
    while(scanf("%d",&N)!=EOF){
        tmp = N*(N-1)-1+2;
        printf("%d*%d*%d=%d=",N,N,N,N*N*N);
        count = 0;
        while(count < N){
            if(count < N-1)
                printf("%d+",tmp);
            else
                printf("%d\n",tmp);
            tmp = tmp +2;
            count++;
        }
    }

    return 0;
}
