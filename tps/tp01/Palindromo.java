import java.util.Scanner;

/**
 * Classe que verifica se uma string é um palíndromo
 * Um palíndromo é uma string que pode ser lida igualmente de trás para frente
 */
public class Palindromo {
  
  /**
   * Método principal que executa o programa
   * Lê strings da entrada padrão até encontrar "FIM" ou uma linha vazia
   * Para cada string, verifica se é um palíndromo e imprime "SIM" ou "NAO"
   * @param args argumentos da linha de comando (não utilizados)
   */
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    String line;
    while(scan.hasNextLine()){
      line = scan.nextLine();
      if(line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M') break;
      if(line.isEmpty()) break;
      boolean palindrome = isPalindrome(line);
      if(palindrome){
        System.out.println("SIM");
      } else{
        System.out.println("NAO");
      } 
    };
    scan.close();
  }

  /**
   * Método que verifica se uma string é um palíndromo
   * Compara caracteres das extremidades movendo para o centro
   * @param str string a ser verificada
   * @return true se a string for um palíndromo, false caso contrário
   */
  public static boolean isPalindrome(String str){
    for(int i = 0, j = str.length()-1; i < j; i++, j--){
      if(str.charAt(i) != str.charAt(j)){
        return false;
      }
    }
    return true;
  }

}
