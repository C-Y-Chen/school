#include <stdio.h>
int main() {
    int N,i,j;
    double tmp;
    while(scanf("%d",&N)!=EOF){
        tmp = 1;
        for(i=2;i<=N;i++){
            tmp = tmp - 1.0/(i*i);
        }
        printf("%.6lf\n",tmp);
    }

    return 0;
}
