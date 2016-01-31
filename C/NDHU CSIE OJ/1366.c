#include <stdio.h>
int main() {
    int N,i,j,tmp;
    while(scanf("%d",&N)!=EOF){
        tmp = 0;
        for(i=1;i<=N;i++){
            if(i%2==1){
                tmp = tmp + i;
            }
        }
        printf("%d\n",tmp);
    }

    return 0;
}
