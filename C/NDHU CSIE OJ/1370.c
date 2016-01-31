#include <stdio.h>
int main() {
    int N,i,j,A,B,count,tmp,tmp2;
    while(scanf("%d",&N)!=EOF){
        count = 0;
        while(count < N){
            scanf("%d %d",&A,&B);
            tmp = 0;tmp2 = 0;
            for(i=1;i< (A>B? A:B);i++){
                if(A%i==0 && i<A){
                    tmp = tmp + i;
                }
                if(B%i==0 && i<B){
                    tmp2 = tmp2 + i;
                }
            }
            if(tmp == B && tmp2 == A)
                printf("YES\n");
            else
                printf("NO\n");
            count++;
        }

    }

    return 0;
}
