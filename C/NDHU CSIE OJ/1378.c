#include <stdio.h>
int main() {
    int i,N,count,j,M,tmp,tmp2,a[1001],b[1001];
    char s[100];
    while(scanf("%d %d",&N,&M)!=EOF){
        tmp = 1 + ((N/3)+1)*3;
            count = 0;
            while(tmp <= M){
                if(tmp%5 == 3){
                    count = count + tmp;
                }
                tmp = tmp + 3;
            }
            printf("%d\n",count);
    }

    return 0;
}
