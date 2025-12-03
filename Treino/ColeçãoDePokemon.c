#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    int N;
    scanf("%d", &N);

    char mochila[1001][1001]; 
    char pokemon[1001];
    
    int capturados_unicos = 0;

    for (int i = 0; i < N; i++) {
        scanf("%s", pokemon);

        int ja_tenho = 0; // Flag: 0 = falso, 1 = verdadeiro

        for (int j = 0; j < capturados_unicos; j++) {
            if (strcmp(mochila[j], pokemon) == 0) {
                ja_tenho = 1;
                break;
            }
        }

        if (ja_tenho == 0) {
            strcpy(mochila[capturados_unicos], pokemon);
            capturados_unicos++;
        }
    }

    int faltam = 151 - capturados_unicos;

    printf("Falta(m) %d pomekon(s).\n", faltam);

    return 0;
}