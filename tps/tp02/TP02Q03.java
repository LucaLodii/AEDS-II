package tps.tp02;

import java.util.Scanner;

public class TP02Q03 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        str = scan.nextLine();
        reverse((str));

        scan.close();
    }

    public static void reverse(String str){
        int size = str.length();
        while(size >= 0)
        {
            System.out.print(str.charAt(size-1));
            size--;
        }
      }
}
