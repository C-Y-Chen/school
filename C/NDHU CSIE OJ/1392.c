#include <stdio.h>
#include <string.h>

int main(){

    int i,tmp,N,o,t,f,j,k,M,a[101];
    char s[100];
    while(scanf("%d",&N)!=EOF){
        a[1] = 0;
        a[2] = 1;
        a[3] = 2;

        for(i=4;i<=N;i++){
            a[i] = (i-1)*(a[i-1]+a[i-2]);
        }
        printf("%d\n",a[N]);

    }

    return 0;
}
//65 90 97 122
