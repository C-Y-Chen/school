#include <stdio.h>
#include <string.h>

void swap(int *a,int *b){
    int tmp;
    tmp = *a;
    *a = *b;
    *b = tmp;

}

int main() {
    int i,N,M,count,a[100],b[100],j,x,y;
    char s[100][100];
    while(scanf("%d",&N)!=EOF){
            for(i=0;i<N;i++){
                scanf("%d",&a[i]);
                b[i] = a[i];
            }
            for(i=0;i<N;i++){
                for(j=0;j<N-1;j++){
                    if(a[j] > a[j+1])
                        swap(&a[j],&a[j+1]);
                    if(b[j] < b[j+1])
                        swap(&b[j],&b[j+1]);
                }
            }
            for(i=0;i<N;i++){
                if(i<N-1)
                    printf("%d ",a[i]);
                else
                    printf("%d\n",a[i]);
            }
            for(i=0;i<N;i++){
                if(i<N-1)
                    printf("%d ",b[i]);
                else
                    printf("%d\n",b[i]);
            }


    }

    return 0;
}
