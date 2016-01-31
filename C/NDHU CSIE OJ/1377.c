#include <stdio.h>
#include <math.h>
int N,count;
int check(int *c,int a){
    int i,tmp;
    tmp = 0;
    for(i=0;i<count;i++){
        if(c[i] == a){
            tmp++;
        }
    }
    if(tmp >= 1)
        return 1;
    else
        return 0;
}

int main() {
    int i,j,A,M,B,tmp,tmp2,a[1001],b[1001],tmp3,tmp4;
    char s[100];
    while(scanf("%d",&N)!=EOF){
        for(i=0;i<N;i++){
            scanf("%d",&a[i]);
        }
        count = 0;
        for(i=0;i<N;i++){
            if(check(b,a[i]) == 0){
                b[count] = a[i];
                count++;
            }
        }
        M = count;
        for(i=0;i<M;i++){
            for(j=0;j<M-1;j++){
                if(b[j] > b[j+1]){
                    tmp = b[j];
                    b[j] = b[j+1];
                    b[j+1] = tmp;
                }
            }
        }
        printf("%d\n",M);
        for(i=0;i<M;i++){
            if(i<M-1)
                printf("%d ",b[i]);
            else
                printf("%d\n",b[i]);
        }

    }

    return 0;
}
//65 90
// 97 122
