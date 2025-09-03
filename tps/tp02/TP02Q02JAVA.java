package tps.tp02;

/* * X1 = VOGAL
 * X2 = CONSOANTE
 * X3 = INTEIRO
 * X4 = REAL
 */
import java.util.Scanner;
import java.lang.String;

public class TP02Q02JAVA {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;

        while (true) {
            line = scan.nextLine();
            if (line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M')
                break;
            if (line.isEmpty())
                break;

            String x1 = vogal(line) ? "SIM" : "NÃO";
            String x2 = consoante(line) ? "SIM" : "NÃO";
            String x3 = inteiro(line) ? "SIM" : "NÃO";
            String x4 = real(line) ? "SIM" : "NÃO";
            System.out.println(x1 + " " + x2 + " " + x3 + " " + x4);
        }

        scan.close();
    }

    public static boolean vogal(String line) {
        line = line.toLowerCase();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                return line.length() > 0;
            }
        }
        return true;
    }

    public static boolean consoante(String line) {
        line = line.toLowerCase();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!(c >= 'a' && c <= 'z') || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return false;
            }
        }
        return true;
    }

    public static boolean inteiro(String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean real(String line) {
        if (line.isEmpty()) {
            return false;
        }
        int countSeparadores = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '.' || c == ',') {
                countSeparadores++;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return countSeparadores <= 1;
    }
}