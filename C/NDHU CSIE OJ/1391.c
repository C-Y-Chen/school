#include <stdio.h>
#include <string.h>
int main(){

    int i,tmp,N,o,t,f,j,k,M;
    char s[100];
    while(scanf("%d",&N)!=EOF){
        for(i=0;i<N;i++){
            scanf("%d",&M);
            tmp = 3;
            for(j=0;j<M;j++){
                tmp = (tmp - 1)*2;
            }
            printf("%d\n",tmp);
        }


    }

    return 0;
}
//65 90 97 122
