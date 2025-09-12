import java.util.Scanner;

public class LAB04 {

	public static void main(String[] args) { 
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			if (!scan.hasNextInt()) break; // Verifica se há um próximo inteiro
			int n = scan.nextInt();
			int[] largada = new int[n];
			int[] chegada = new int[n];
			
			// Lê os valores de largada
			for (int i = 0; i < n; i++) {
				largada[i] = scan.nextInt();
			}
			
			// Lê os valores de chegada
			for (int i = 0; i < n; i++) {
				chegada[i] = scan.nextInt();
			}
			
			ultrapassagem(n, largada, chegada);
		}
		scan.close();
	}
	
    public static void swap(int[] array, int j) {
        if (j >= 0 && j + 1 < array.length) {
            int temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
        }
    }

    public static int bubble(int[] resposta) {
        int count = 0;
        int n = resposta.length;
        for (int i = (n - 1); i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (resposta[j] > resposta[j + 1]) {
                    swap(resposta, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void ultrapassagem(int n, int[] largada, int[] chegada) {
        int[] resposta = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (largada[i] == chegada[j]) {
                    resposta[i] = j;
                }
            }
        }
        int ultrapassagens = bubble(resposta);
        System.out.println(ultrapassagens);
    }

}