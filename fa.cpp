#include "stdio.h"                  /*����I/O�����õ�ĳЩ��ͱ���*/
#include "string.h"                 /*�����ַ����⺯��*/
#include "conio.h"                  /*�ṩ�й���Ļ���ڲ�������*/
#include "ctype.h"                  /*���ຯ��*/

char prog[80]= {'\0'},
               token[8];                     /*��Ź��ɵ��ʷ��ŵ��ַ���*/
char ch;
char ch2;
char ch3;
int syn,                           /*��ŵ����ַ����ֱ���*/
n,
sum, sum2,                          /*��������͵���*/
m,p;                           /*p�ǻ�����prog��ָ�룬m��token��ָ��*/
float  xiaoshu;
float   k;
int flag=1;
int flag2=1;
int j;
int t=10;
int warn=0;
double g;
//char *rwtab[6]={"begin","if","then","while","do","end"};
char *gjz[9]= {"main","int","float","double","char","if","else","do","while"};

void scaner()
{
    m=0;
    sum=0;
    xiaoshu=0;
    k=0.1;
    for(n=0; n<8; n++)
        token[n]='\0';
    ch=prog[p++];
    while(ch==' ')
        ch=prog[p++];
    while(ch=='\n')
        ch=prog[p++];

    if(isalpha(ch))    /*chΪ��ĸ�ַ�*/
    {
        while(isalpha(ch)||isdigit(ch))    /*ch Ϊ��ĸ�ַ����������ַ�*/
        {
            token[m++]=ch;
            ch=prog[p++];
        }
        token[m++]='\0';
        ch=prog[p--];
        syn=10;
        for(n=0; n<9; n++)
            if(strcmp(token,gjz[n])==0)    /*�ַ����ıȽ�*/
            {
                syn=n+1;
                break;
            }
    }
    else if(isdigit(ch))    /*ch�������ַ�*/
    {

        while(isdigit(ch))    /*ch�������ַ�*/
        {

            //sum=sum*10+ch-'0';
            xiaoshu=xiaoshu*10+ch-'0';
            ch=prog[p++];

        }
        if(ch=='.')
        {
            //xiaoshu=sum;
            ch=prog[p++];


            while(isdigit(ch))    /*ch�������ַ�*/
            {
                xiaoshu=xiaoshu+k*(ch-'0');

                k=k*k;
                ch=prog[p++];
            }


        }
        if(ch=='e')
        {
            g=xiaoshu;
            ch=prog[p++];



            if(ch=='+')
            {

                ch=prog[p++];
                while(isdigit(ch))
                {

                    sum2=sum2*10+ch-'0';
                    ch=prog[p++];

                }
                for(; sum2>0; sum2--)
                {
                    g=g*10;
                }







            }
            if(ch=='-')
            {
                k=0.1;
                ch=prog[p++];
                flag2=-1;
                while(isdigit(ch))
                {

                    sum2=sum2*10+ch-'0';
                    ch=prog[p++];

                }
                for(; sum2>0; sum2--)
                {
                    g=g*k;
                }
            }
            if(isdigit(ch))
            {
                while(isdigit(ch))
                {

                    sum2=sum2*10+ch-'0';
                    ch=prog[p++];

                }


                for(; sum2>0; sum2--)
                {
                    g=g*10;
                }

            }
        }


        ch=prog[p--];
        syn=11;


    }
    else
        switch(ch)
        {
        case'<':
            m=0;
            token[m++]=ch;
            ch=prog[p++];
            if(ch=='>')
            {
                syn=41;
                token[m++]=ch;
            }
            else if(ch=='=')
            {
                syn=35;
                token[m++]=ch;
            }
            else
            {
                syn=34;
                ch=prog[p--];
            }
            break;
        case'>':
            m=0;
            token[m++]=ch;
            ch=prog[p++];
            if(ch=='=')
            {
                syn=33;
                token[m++]=ch;
            }
            else
            {
                syn=32;
                ch=prog[p--];
            }
            break;
        case':':
            m=0;
            token[m++]=ch;
            ch=prog[p++];
            if(ch=='=')
            {
                syn=18;
                token[m++]=ch;
            }
            else
            {
                syn=17;
                ch=prog[p--];
            }
            break;
        case'!':
            m=0;
            token[m++]=ch;
            ch=prog[p++];
            if(ch=='=')
            {
                syn=37;
                token[m++]=ch;
            }
            else
            {
                ch=prog[p--];
            }
            break;
        case'=':
            m=0;
            token[m++]=ch;
            ch=prog[p++];
            if(ch=='=')
            {
                syn=36;
                token[m++]=ch;
            }
            else
            {
                syn=21;
                ch=prog[p--];
            }

            break;

        case'+':
            m=0;
            ch2=ch;
            j=p-2;
            ch3=prog[j];
            ch=prog[p++];
            if((isdigit(ch))&&(!isdigit(ch3)))
            {
                syn=99;
                ch=prog[p--];
            }

            else
            {
                token[m++]=ch2;
                syn=22;
                ch=prog[p--];


            }
            break;
        case'-':
            m=0;
            ch2=ch;
            j=p-2;
            ch3=prog[j];
            ch=prog[p++];
            if((isdigit(ch))&&(!isdigit(ch3)))
            {
                syn=99;
                ch=prog[p--];
                flag=-1;
            }

            else
            {
                token[m++]=ch2;
                syn=23;
                ch=prog[p--];


            }
            break;

        case'*':
            syn=24;
            token[0]=ch;
            break;
        case'/':
            syn=25;
            token[0]=ch;
            break;
        case',':
            syn=30;
            token[0]=ch;
            break;
        case';':
            syn=31;
            token[0]=ch;
            break;
        case'(':
            syn=26;
            token[0]=ch;
            break;
        case')':
            syn=27;
            token[0]=ch;
            break;
        case'}':
            syn=29;
            token[0]=ch;
            break;
        case'{':
            syn=28;
            token[0]=ch;
            break;
        case'#':
            syn=0;
            token[0]=ch;
            break;

        default:
            syn=-1;
        }
}
main()
{
    printf("\n\nThe significance of the figures:\n"
           "1.figures 1 to 9 said Keyword\n"
           "2.figures 10 and 11 said Other indicators\n"
           "3.figures 21 to 37 said Operators\n");

    p=0;
    printf("\nplease input string:\n");
    do
    {
        ch=getchar();
        prog[p++]=ch;
    }
    while(ch!='#');

    p=0;
    do
    {
        scaner();

        switch(syn)
        {
        case 11:
            if(g==0)
            {
                xiaoshu=flag*xiaoshu;
                flag=1;
                printf("(%d,%.3f)\n",syn,xiaoshu);
            }
            else
            {
                g=g*flag;
                flag=1;
                printf("(%d,%g)\n",syn,g);
                g=0;
            }


            break;
        case -1:
            printf("\n ERROR;\n");
            break;
        case  99:
            break;
        default:
            printf("(%d,%s)\n",syn,token);
        }
    }
    while(syn!=0);
    getch();

}
