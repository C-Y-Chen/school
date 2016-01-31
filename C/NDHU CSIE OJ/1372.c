#include <stdio.h>
int main() {
    int N,i,j,A,M,B,count,tmp,tmp2,a[101];
    while(scanf("%d %d",&N,&M)!=EOF){
        for(i=1;i<=N;i++){
            a[i-1] = 2*i;
        }
        count= 0;tmp = 0;
        tmp2 = N%M;
        for(i=0;i<N-tmp2;i++){
                tmp = tmp + a[i];
                count++;
            if(count == M){
                if(tmp2 == 0 && i==N-1)
                    printf("%d\n",tmp/M);
                else
                    printf("%d ",tmp/M);
                tmp = 0;count = 0;
            }
        }
        for(i=N-tmp2;i<N;i++){
                tmp = tmp + a[i];
                count++;
            if(count == tmp2){
                printf("%d\n",tmp/tmp2);
                tmp = 0;count = 0;
            }
        }
    }

    return 0;
}
//2 4 6 8 10 12
//2 4 6 8
