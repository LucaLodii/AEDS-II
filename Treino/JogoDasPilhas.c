#include <stdio.h>

int p1[110], p2[110], p3[110];

int N;

int resolve(int a, int b, int c)
{
    int soma = 0;
    for (int i = 0; i < N; i++) // p1 
    {
        soma += p1[i];
    }
    for (int i = 0; i < N; i++) // p2
    {
        soma += p2[i];        
    }
    for (int i = 0; i < N; i++) // p3
    {
        soma += p3[i];        
    }

    if(soma % 3 == 1) return 1; // ganhou

    return 0; // perdeu
}

int main()
{
    while (scanf("%d", &N) && N != 0)
    {

        for (int i = 0; i < N; i++)
        {
            scanf("%d %d %d", &p1[i], &p2[i], &p3[i]);
        }

        printf("%d\n", resolve(0, 0, 0));
    }
}