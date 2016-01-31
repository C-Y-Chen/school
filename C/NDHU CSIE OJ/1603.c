#include <stdio.h>
int main() {
    int i,N,count,j,k,l,M,tmp,tmp2,a[201][201],b[1001],edge;
    char s[100];
    while(scanf("%d %d",&N,&M)!=EOF){
            for(i=0;i<N;i++)
                for(j=0;j<M;j++)
                    scanf("%d",&a[i][j]);
            tmp = 0;
            for(i=0;i<N;i++){

                for(j=0;j<M;j++){

                    if(a[i][j]){
                        edge = 0;
                        for(k=0;k<N-i;k++){

                            for(l=0;l<M-j-edge;l++){ //M-j-edge = width  M-j中將0的減掉維為 Rect. width
                                if(!a[i+k][j+l]){
                                    edge = M-j-l; // edge 說明M-j中有幾個0
                                    break;
                                }
                                tmp = tmp > (k+1)*(l+1) ? tmp : (k+1)*(l+1);
                            }
                        }
                    }

                }

            }

            printf("%d\n",tmp);

//            for(i=0;i<N;i++){
//                for(j=0;j<M;j++)
//                    printf("%d",a[i][j]);
//                printf("\n");
//            }

    }

    return 0;
}
