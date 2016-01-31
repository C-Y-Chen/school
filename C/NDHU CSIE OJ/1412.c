#include <stdio.h>
int main() {
    int N,i,j,A,M,B,count,tmp,tmp2,a[1001];
    while(scanf("%d",&N)!=EOF){
        tmp = N;
        while(1){
            scanf("%d",&M);
            if(M == 0)
                break;
            if(M > tmp)
                tmp = M;
        }
        printf("%d\n",tmp);
    }

    return 0;
}
