import java.util.Scanner;

public class InversaoRec {

    public static void reverse(String str, int index) {
        // Caso base: se o índice for menor que 0, não há mais o que imprimir.
        if (index >= 0) {
            // 1. Imprime o caractere da posição atual.
            System.out.print(str.charAt(index));
            
            // 2. Chama a si mesma para a posição anterior (a mágica da recursão).
            reverse(str, index - 1);
        }
    }

    /**
     * Verifica de forma segura se a string é igual a "FIM".
     */
    public static boolean isFim(String s) {
        return s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M';
    }

    /**
     * Método principal para ler a entrada e iniciar o processo.
     */
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String entrada;

        // Loop para ler várias linhas até encontrar "FIM".
        while (true) {
            entrada = scan.nextLine();
            if (isFim(entrada)) {
                break;
            }
            
            // A primeira chamada da recursão começa no último caractere.
            reverse(entrada, entrada.length() - 1);
            
            // Pula uma linha para a próxima saída.
            System.out.println();
        }
        
        scan.close();
    }
}