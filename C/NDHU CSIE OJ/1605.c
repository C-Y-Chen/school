#include <stdio.h>
#include <string.h>
int main(){

    int i,tmp;
    char s[100];
    while(scanf("%s",&s)!=EOF){
        tmp = 0;
        for(i=0;i<strlen(s);i++){
            if(s[i] >=65 && s[i] <= 90){
                tmp = tmp + s[i] - 64;
            }
            else if(s[i] >=97 && s[i] <= 122){
                tmp = tmp + s[i] - 96;
            }
        }

        printf("%d\n",tmp);
    }

    return 0;
}
//65 90 97 122
