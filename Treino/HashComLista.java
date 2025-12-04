package Treino;
import java.util.Scanner;

public class HashComLista {

    private static int hash(String placa, int N){
        int posicao = 0;

        for(int i = 0; i < placa.length(); i++) {
            posicao += palaca.charAt(i);
        }
        posicao = posicao % N;
        return posicao;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        String placa = scan.nextLine();

        hash(placa, N);
    }
}
