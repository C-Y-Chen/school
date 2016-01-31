#include <stdio.h>
#include <string.h>
int main(){

    int i,tmp,N,o,t,f,j,k;
    char s[100];
    while(scanf("%d",&N)!=EOF){
        tmp = 0;
         for(i=1;i<=N/5;i++)
                for(j=1;j<=N/2;j++)
                       for(k=1;k<=N/1;k++){
                           if((i*5+j*2+k*1) == N){
                                tmp++;
                           }
                       }
        printf("%d\n",tmp);
    }

    return 0;
}
//65 90 97 122
