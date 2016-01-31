#include <stdio.h>
#include <math.h>
int main() {
    int N,i,j,A,M,B,count,tmp,tmp2,a[1001],b[1001],tmp3,tmp4;
    char s[100];
    while(scanf("%d %d",&N,&M)!=EOF){
        for(i=0;i<N;i++){
            scanf("%d",&a[i]);
        }
        for(j=0;j<M;j++){
            scanf("%d",&b[j]);
        }
        tmp = 10;
        for(i=0;i<N;i++){
            for(j=0;j<M;j++){
                tmp2 = abs(a[i]-b[j]);
                if(tmp2 < tmp){
                    tmp = tmp2;
                }
            }
        }
        printf("%d\n",tmp);

    }

    return 0;
}
//65 90
// 97 122
