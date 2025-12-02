package Treino;
import java.util.Scanner;

public class Protocolo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] packets = new int[1000];
        
        while (sc.hasNext()) {
            String line = sc.nextLine();
            
            if (line.equals("1")) {
                for (int i = 0; i < 1000; i++) {
                    packets[i] = 0;
                }
            } else if (line.equals("0")) {
                for (int i = 0; i < 1000; i++) {
                    while (packets[i] > 0) {
                        System.out.printf("Package %03d\n", i);
                        packets[i]--;
                    }
                }
                System.out.println();
            } else if (line.startsWith("Package")) {
                int id = Integer.parseInt(line.substring(8).trim());
                packets[id]++;
            }
        }
        sc.close();
    }
}