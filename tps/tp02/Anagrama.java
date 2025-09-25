package tps.tp02;

import java.util.Scanner;

public class Anagrama {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Compara duas strings caractere por caractere para ver se são idênticas.
     */
    public static boolean areEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se duas strings são anagramas usando contagem de frequência.
     */
    public static boolean areAnagrams(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        if (s1.length() != s2.length()) {
            return false;
        }

        // Array para contar a frequência de cada caractere.
        int[] charCounts = new int[256];

        for (int i = 0; i < s1.length(); i++) {
            charCounts[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            charCounts[s2.charAt(i)]--;
            // Se uma contagem fica negativa, as strings não são anagramas.
            if (charCounts[s2.charAt(i)] < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String stopWord = "FIM";

        while (true) {
            String line = scanner.nextLine();

            // Verifica a condição de parada.
            if (areEqual(line, stopWord)) {
                break;
            }
            
            // Separa a linha em palavras usando espaço ou hífen como divisor.
            String[] words = line.split("[ -]+");

            if (words.length == 2) {
                boolean isAnagram = areAnagrams(words[0], words[1]);

                if (isAnagram) {
                    System.out.println("SIM");
                } else {

                    MyIO.println("NÃO");
                }
            }
        }
        scanner.close();
    }
}