import java.util.Random;

/**
 * Classe que implementa um algoritmo de alteração de caracteres em strings
 * Substitui caracteres aleatórios por outros caracteres aleatórios
 */
public class Alteracao {
    
    /**
     * Método principal que executa o programa
     * Lê strings da entrada padrão até encontrar "FIM"
     * Para cada string, gera dois caracteres aleatórios e substitui um pelo outro
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args){
        Random random = new Random();
        random.setSeed(4);
        String line = MyIO.readLine();
        while(!(line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M')){
            char randomChar = (char) ('a' + (Math.abs(random.nextInt()) % 26));
            char randomCharSubstitute = (char) ('a' + (Math.abs(random.nextInt()) % 26));
            MyIO.println(new String(Alterar(line, randomChar, randomCharSubstitute)));
            line = MyIO.readLine();
        }
    }
    
    /**
     * Método que altera uma string substituindo um caractere por outro
     * @param str string original a ser alterada
     * @param randomChar caractere que será substituído
     * @param randomCharSubstitute caractere que substituirá o randomChar
     * @return array de caracteres contendo a string com as alterações
     */
    public static char[] Alterar(String str, char randomChar, char randomCharSubstitute){
        char[] newStr = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == randomChar){
                c = randomCharSubstitute;
            }
            newStr[i] = c;
        }
        return newStr;
    }
}
