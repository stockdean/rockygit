#include "stdio.h"                  /*����I/O�����õ�ĳЩ��ͱ���*/
#include "string.h"                 /*�����ַ����⺯��*/
#include "conio.h"                  /*�ṩ�й���Ļ���ڲ�������*/
#include "ctype.h"                  /*���ຯ��*/
char prog[80]={'\0'},
        token[8];                     /*��Ź��ɵ��ʷ��ŵ��ַ���*/
char ch;
int syn,                           /*��ŵ����ַ����ֱ���*/
      n,
    sum,                           /*��������͵���*/
    m,p;                           /*p�ǻ�����prog��ָ�룬m��token��ָ��*/
char *rwtab[6]={"begin","if","then","while","do","end"};

void scaner(){
    m=0;
    sum=0;
    for(n=0;n<8;n++)
        token[n]='\0';
    ch=prog[p++];
    while(ch==' ')
        ch=prog[p++];
    if(isalpha(ch))    /*chΪ��ĸ�ַ�*/{
        while(isalpha(ch)||isdigit(ch))    /*ch Ϊ��ĸ�ַ����������ַ�*/{
           token[m++]=ch;
           ch=prog[p++];}
        token[m++]='\0';
        ch=prog[p--];
        syn=10;
        for(n=0;n<6;n++)
            if(strcmp(token,rwtab[n])==0)    /*�ַ����ıȽ�*/{
                syn=n+1;
                break;}}
    else
        if(isdigit(ch))    /*ch�������ַ�*/{
            while(isdigit(ch))    /*ch�������ַ�*/{
                sum=sum*10+ch-'0';
                ch=prog[p++];}
            ch=prog[p--];
            syn=11;}
        else
            switch(ch){
                case'<':m=0;token[m++]=ch;ch=prog[p++];
                        if(ch=='>'){
                            syn=41;
                            token[m++]=ch;}
                        else if(ch=='='){
                            syn=35;
                            token[m++]=ch;}
                            else{
                                 syn=34;
                                 ch=prog[p--];}
                        break;
                case'>':m=0;token[m++]=ch;ch=prog[p++];
                        if(ch=='='){
                            syn=33;
                            token[m++]=ch;}
                        else{
                            syn=32;
                            ch=prog[p--];}
                        break;
             case':':m=0;token[m++]=ch;ch=prog[p++];
                     if(ch=='='){
                         syn=18;
                         token[m++]=ch;}
                     else{
                         syn=17;
                         ch=prog[p--];}
                     break;
             case'!':m=0;token[m++]=ch;ch=prog[p++];
                      if(ch=='='){
                      syn=37;
                      token[m++]=ch;
                      }
                      else{
                      ch=prog[p--];}
                      break;
             case'=':m=0;token[m++]=ch;ch=prog[p++];
                      if(ch=='='){
                          syn=36;
                          token[m++]=ch;}
                          else{
                          syn=21;
                          ch=prog[p--];
                          }

                        break;
             case'+':syn=22;token[0]=ch;break;
             case'-':syn=23;token[0]=ch;break;
             case'*':syn=24;token[0]=ch;break;
             case'/':syn=25;token[0]=ch;break;

             case',':syn=30;token[0]=ch;break;
             case';':syn=31;token[0]=ch;break;
             case'(':syn=26;token[0]=ch;break;
             case')':syn=27;token[0]=ch;break;
             case'}':syn=29;token[0]=ch;break;
             case'{':syn=28;token[0]=ch;break;
             case'#':syn=0;token[0]=ch;break;
             default:syn=-1;}}
main()
{
    printf("\n\nThe significance of the figures:\n"
           "1.figures 1 to 6 said Keyword\n"
           "2.figures 10 and 11 said Other indicators\n"
           "3.figures 13 to 28 said Operators\n");

p=0;
    printf("\nplease input string:\n");
    do {
           ch=getchar();
           prog[p++]=ch;
       }while(ch!='#');

p=0;
    do{
        scaner();
        switch(syn){
            case 11: printf("(%d,%d)\n",syn,sum);break;
            case -1: printf("\n ERROR;\n");break;
            default: printf("(%d,%s)\n",syn,token);
}
     }while(syn!=0);
    getch();
}
