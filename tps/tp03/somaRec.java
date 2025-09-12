import java.util.Scanner;

public class somaRec {
    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int numero = scanner.nextInt();
            // Chama a função recursiva e guarda o resultado.
            int resultado = somaDigitos(numero);
            System.out.println(resultado);
        }
        scanner.close();
    }

    /**
     * Calcula a soma dos dígitos de um número de forma recursiva.
     */
    public static int somaDigitos(int n) {
        // Garante que o número seja positivo para o cálculo.
        if (n < 0) {
            n = -n;
        }

        // Caso base: se n tem só um dígito, a soma é o próprio n.
        if (n < 10) {
            return n;
        }
        // Passo recursivo: soma o último dígito (n % 10) com a
        // soma dos dígitos do resto do número (n / 10).
        else {
            return (n % 10) + somaDigitos(n / 10);
        }
    }
}