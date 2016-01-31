#include <stdio.h>
#include <string.h>
int main() {
    int N,i,j,A,M,B,count,tmp,tmp2,a[1001],tmp3,tmp4;
    char s[100];
    while(scanf("%d",&N)!=EOF){
        count = 0;
        while(count < N){
            scanf("%s",&s);
            tmp=0;tmp2=0;tmp3=0;tmp4=0;
            if(strlen(s) >= 8 && strlen(s) <= 16){
                for(i=0;i<strlen(s);i++){
                    if(s[i] >= 65 && s[i] <= 90 && tmp == 0)
                        tmp = 1;
                    else if(s[i] >= 97 && s[i] <= 122 && tmp2 == 0)
                        tmp2 = 1;
                    else if(s[i] >= 48 && s[i] <= 57 && tmp3 == 0)
                        tmp3 = 1;
                    else if(s[i]=='~' || s[i]=='!' || s[i]=='@' || s[i]=='#' || s[i]=='$' || s[i]=='%' || s[i]=='^' && tmp4 == 0)
                        tmp4 = 1;
                }
                if((tmp+tmp2+tmp3+tmp4)>=3)
                    printf("YES\n");
                else
                    printf("NO\n");
            }
            else{
                printf("NO\n");
            }
            count++;
        }
    }

    return 0;
}
//65 90
// 97 122
