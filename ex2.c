#include <stdio.h>
#include <stdbool.h> // Para usar o tipo 'bool' e os valores true/false

/**
 * Análise de Complexidade:
 * A função utiliza um único laço 'while' que percorre a sequência principal SA.
 * A operação relevante dentro do laço é a comparação (SA[i_SA] == SB[i_SB]),
 * que tem custo constante O(1).
 * O ponteiro i_SA avança a cada iteração, percorrendo a sequência SA no máximo
 * uma vez. Portanto, a complexidade de tempo do algoritmo é linear em relação
 * ao tamanho da sequência principal, sendo O(A), onde A é o tamanho de SA.
 */
bool isSubsequence(int SA[], int sizeA, int SB[], int sizeB) {
    int i_SA = 0; // Ponteiro para a sequência principal SA
    int i_SB = 0; // Ponteiro para a subsequência SB

    // Percorre SA enquanto ainda há elementos em SB para serem encontrados
    while (i_SA < sizeA && i_SB < sizeB) {
        // Se o elemento atual de SA corresponde ao elemento que estamos procurando em SB...
        if (SA[i_SA] == SB[i_SB]) {
            // ...avançamos o ponteiro de SB para procurar o próximo.
            i_SB++;
        }
        // Sempre avançamos o ponteiro de SA para o próximo elemento.
        i_SA++;
    }

    // Se o ponteiro i_SB alcançou o final de SB (sizeB), significa que
    // todos os elementos foram encontrados na ordem correta.
    return (i_SB == sizeB);
}

int main() {
    int A, B;

    // Loop para ler múltiplos casos de teste até o fim da entrada (EOF)
    while (scanf("%d %d", &A, &B) != EOF) {
        int SA[A];
        int SB[B];

        // Lê os elementos da sequência SA
        for (int i = 0; i < A; i++) {
            scanf("%d", &SA[i]);
        }

        // Lê os elementos da sequência SB
        for (int i = 0; i < B; i++) {
            scanf("%d", &SB[i]);
        }

        // Chama a função e imprime 'S' ou 'N'
        if (isSubsequence(SA, A, SB, B)) {
            printf("S\n");
        } else {
            printf("N\n");
        }
    }

    return 0;
}