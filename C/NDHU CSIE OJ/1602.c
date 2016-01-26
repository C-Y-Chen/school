#include <stdio.h>
#include <string.h>
int main() {
    int i,N,M,count,j,x,y,x2,y2,a,b,c,d;
    char s[100][100];
    while(scanf("%d %d %d %d",&a,&b,&c,&d)!=EOF){
            memset(s,'-',sizeof(s[0][0])*100*100);
            x = a>c? c:a;
            x2 = a>c? a:c;
            y = b>d? d:b;
            y2 = b>d? b:d;
            N = x>x2? x:x2;
            M = y>y2? y:y2;
            for(i=0;i<=M;i++){
                for(j=0;j<=N;j++){
                    if(j >= x && i == y){
                        s[i][j] = 'a';
                    }
                    else if(j == x && i > y && i < y2){
                        s[i][j] = 'b';
                    }
                    else if(j == x2 && i > y && i < y2){
                        s[i][j] = 'b';
                    }
                    else if(j >= x && i == y2){
                        s[i][j] = 'a';
                    }
                    else{
                        s[i][j] = '-';
                    }
                }

            }
            for(i=0;i<=M;i++){
                for(j=0;j<=N;j++){
                    printf("%c",s[i][j]);
                }
                printf("\n");
            }

    }

    return 0;
}
