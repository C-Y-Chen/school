#include <stdio.h>
int main() {
    int N,i,j;
    double tmp;
    while(scanf("%d",&N)!=EOF){
        tmp = 0;
        for(i=1;i<=N;i++){
            tmp = tmp + 1.0/i;
        }
        printf("%.6lf\n",tmp);
    }

    return 0;
}
