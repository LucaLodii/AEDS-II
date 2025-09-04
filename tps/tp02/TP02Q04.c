#include <stdio.h>

/**
 * Função recursiva que soma os dígitos de um número.
 */
int somaDigitos(int n) {
    // Caso base: se o número tem um só dígito, a soma é ele mesmo.
    if (n < 10) {
        return n;
    } 
    // Passo recursivo: soma o último dígito com a soma do resto do número.
    else {
        return (n % 10) + somaDigitos(n / 10);
    }
}

int main() {
    int numero;

    // Lê números da entrada até o fim do arquivo (EOF).
    while (scanf("%d", &numero) != EOF) {
        int resultado = somaDigitos(numero);
        printf("%d\n", resultado);
    }

    return 0;
}