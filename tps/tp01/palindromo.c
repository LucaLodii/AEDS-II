#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include <locale.h>

/**
 * Função que verifica se uma string é um palíndromo
 * @param str string a ser verificada
 * @return true se a string for um palíndromo, false caso contrário
 */
bool isPalindrome(char* str);

/**
 * Função recursiva que verifica se uma string é um palíndromo
 * @param str string a ser verificada
 * @param i índice inicial para comparação
 * @param j índice final para comparação
 * @return true se a string for um palíndromo, false caso contrário
 */
bool isPalindromeRec(char* str, int i, int j);

/**
 * Função principal que executa o programa
 * Lê strings da entrada padrão até encontrar "FIM"
 * Para cada string, verifica se é um palíndromo e imprime "SIM" ou "NAO"
 * @return 0 se a execução for bem-sucedida
 */
int main(){
    char line[1000];
    setlocale(LC_ALL, "ISO-8859-1");
    while(fgets(line, sizeof(line), stdin) != NULL){
        line[strcspn(line, "\n")] = '\0';
        
        if(strcmp(line, "FIM") == 0) break;
        
        bool palindrome = isPalindrome(line);
        if(palindrome){
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }
    return 0;
}

/**
 * Função que verifica se uma string é um palíndromo
 * Chama a função recursiva para fazer a verificação
 * @param str string a ser verificada
 * @return true se a string for um palíndromo, false caso contrário
 */
bool isPalindrome(char* str){
    int len = strlen(str);
    if(len <= 1) return true;
    
    return isPalindromeRec(str, 0, len-1);
}

/**
 * Função recursiva que verifica se uma string é um palíndromo
 * Compara caracteres das extremidades movendo para o centro
 * @param str string a ser verificada
 * @param i índice inicial para comparação
 * @param j índice final para comparação
 * @return true se a string for um palíndromo, false caso contrário
 */
bool isPalindromeRec(char* str, int i, int j){
    bool is_palindrome = true;
    if(i >= j) is_palindrome = true;
    else if(str[i] != str[j]){
        is_palindrome = false;
    } else {
        is_palindrome = isPalindromeRec(str, i+1, j-1);
    }
    
    return is_palindrome;
}