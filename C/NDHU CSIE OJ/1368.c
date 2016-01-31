#include <stdio.h>
int main() {
    int N,i,j;
    long long int tmp;
    while(scanf("%d",&N)!=EOF){
        tmp = 1;
        for(i=1;i<=N;i++){
            tmp = tmp * i;
        }
        printf("%lld\n",tmp);
    }

    return 0;
}
