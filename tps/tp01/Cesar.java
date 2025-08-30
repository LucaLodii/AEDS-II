/**
 * Classe que implementa a criptografia de César
 * Desloca cada caractere 3 posições para a direita no alfabeto
 */
public class Cesar {
    
    /**
     * Método principal que executa o programa
     * Lê strings da entrada padrão até encontrar "FIM"
     * Para cada string, aplica a criptografia de César e imprime o resultado
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args){
        String line;
        line = MyIO.readLine();
        while(!(line.charAt(0) == 'F' && line.charAt(1) == 'I' && line.charAt(2) == 'M')){
            MyIO.println(new String(CesarCripto(line)));
            line = MyIO.readLine();
        }
    }
    
    /**
     * Método que aplica a criptografia de César em uma string
     * Desloca cada caractere 3 posições para a direita no alfabeto
     * @param str string original a ser criptografada
     * @return array de caracteres contendo a string criptografada
     */
    public static char[] CesarCripto(String str){
        char[] newStr = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            newStr[i] = (char)(c+3);
        }
        return newStr;
    }
}