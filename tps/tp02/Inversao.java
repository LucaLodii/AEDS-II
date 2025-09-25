package tps.tp02;

import java.util.Scanner;

public class Inversao {

    /**
     * Método que verifica se uma string é igual a "FIM" sem usar .equals().
     * @param s string a ser verificada.
     * @return true se a string for "FIM", false caso contrário.
     */
    public static boolean isFim(String s) {
        // A verificação de tamanho primeiro evita erros com strings curtas.
        return s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M';
    }

    /**
     * Método que imprime uma string de trás para frente.
     * @param str string a ser invertida.
     */
    public static void reverse(String str) {
        // Itera do último caractere (índice tamanho-1) até o primeiro (índice 0).
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
        System.out.println(); // Pula para a próxima linha após imprimir a string invertida.
    }

    /**
     * Método principal que executa o programa.
     * @param args argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String entrada;

        // Lê a primeira linha de entrada.
        entrada = scan.nextLine();

        // Continua lendo e processando novas linhas até que a entrada seja "FIM".
        while (!isFim(entrada)) {
            reverse(entrada);
            entrada = scan.nextLine();
        }

        scan.close();
    }
}