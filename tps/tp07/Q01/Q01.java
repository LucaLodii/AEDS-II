import java.util.Scanner;
import java.io.*;

class No {
    public Game elemento;
    public No esq;
    public No dir;

    public No() {
        elemento = new Game();
        esq = dir = null;
    }
    public No(Game game) {
        elemento = game;
        esq = dir = null;
    }
}

class Arvore {
    public No raiz;

    public Arvore() {
        raiz = null;
    }

    public void inserirNome(Game game) {
        raiz = inserirNome(raiz, game);
    }
    private No inserirNome(No i, Game game) {
        if(i == null) i = new No(game);
        else if(game.getName().compareTo(i.elemento.getName()) > 0) i.dir = inserirNome(i.dir, game);
        else if(game.getName().compareTo(i.elemento.getName()) < 0) i.esq = inserirNome(i.esq, game);
        else;
        return i;
    }
    public void inserirId(Game game) {
        raiz = inserirId(raiz, game);
    }
    private No inserirId(No i, Game game) {
        if(i == null) i = new No(game);
        else if(game.getId() > i.elemento.getId()) i.dir = inserirId(i.dir, game);
        else if(game.getId() < i.elemento.getId()) i.esq = inserirId(i.esq, game);
        else;
        return i;
    }

    public void pesquisar(String str) {
        System.out.print(str + ": =>raiz  ");
        if(pesquisar(raiz, str)) {
            System.out.println("SIM");
        }
        else {
            System.out.println("NAO");
        }
    }
    private boolean pesquisar(No i, String str) {
        boolean resp = false;
        while(i != null) {
            Q01.compara++;
            if(str.compareTo(i.elemento.getName()) > 0) {
                System.out.print("dir ");
                i = i.dir;
            }
            else if(str.compareTo(i.elemento.getName()) < 0) {
                System.out.print("esq ");
                i = i.esq;
            }
            else {
                resp = true;
                i = null;
            }
        }
        return resp;
    }

    public void caminharPre() {
        caminharPre(raiz);
    }
    private void caminharPre(No i) {
        if(i != null) {
            System.out.println(i.elemento.toString());
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }
    public void caminharCentral() {
        caminharCentral(raiz);
    }
    private void caminharCentral(No i) {
        if(i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento.toString());
            caminharCentral(i.dir);
        }
    }
}

class Game{
    private int id;
    private String name;
    private String date;
    private int jogadores;
    private float preco;
    private String [] linguas;
    private int notaEspecial;
    private float notaUsuario;
    private int conquistas;
    private String [] publishers;
    private String [] developers;
    private String [] categorias;
    private String [] generos;
    private String [] tags;

    public void setId(String id){
        this.id = Integer.parseInt(id);
    }
    public void setNome(String name) {
        this.name = name;
    }
    public void setData(String data) {
        String mes = "", dia = "", ano = "", resp = "";
        for(int i = 0; i < 3; i++) {
            mes += data.charAt(i);
        }
        if(data.charAt(5) == ',') {
            dia += "0";
            dia += data.charAt(4);
            for(int i = 7; i < data.length(); i++) {
                ano += data.charAt(i);
            }
        }
        else {
            dia += data.charAt(4);
            dia += data.charAt(5);
            for(int i = 8; i < data.length(); i++) {
                ano += data.charAt(i);
            }
        }
        switch(mes) {
            case "Jan": mes = "01"; break;
            case "Feb": mes = "02"; break;
            case "Mar": mes = "03"; break;
            case "Apr": mes = "04"; break;
            case "May": mes = "05"; break;
            case "Jun": mes = "06"; break;
            case "Jul": mes = "07"; break;
            case "Aug": mes = "08"; break;
            case "Sep": mes = "09"; break;
            case "Oct": mes = "10"; break;
            case "Nov": mes = "11"; break;
            case "Dec": mes = "12"; break;
            default: mes = "01"; break;     
        }
        resp = dia + "/" + mes + "/" + ano;
        this.date = resp;
    }
    public void setJogadores(String jogadores) {
        String aux = "";
        for(int i = 0; i < jogadores.length(); i++) {
            if(jogadores.charAt(i) >= '0' && jogadores.charAt(i) <= '9') {
                aux += jogadores.charAt(i);
            }
        }
        this.jogadores = Integer.parseInt(aux);
    }

    public void setPreco(String preco) {
        if(compare(preco,"Free to play")) {
            this.preco = 0.0f;
        }
        else {
            this.preco = Float.parseFloat(preco);
        } 
    }

    public void setLinguas(String linguas) {
        this.linguas = formatar(linguas,0);
    }

    public void setNotaEspecial(String notaEspecial) {
        if(notaEspecial == null) {
            this.notaEspecial = -1;
        }
        else {
            this.notaEspecial = Integer.parseInt(notaEspecial);
        }
    }

    public void setNotaUsuario(String notaUsuario) {
        if(notaUsuario == null || notaUsuario == "tbd") {
            this.notaUsuario = -1.0f;
        }
        else {
            this.notaUsuario = Float.parseFloat(notaUsuario);
        }
    }

    public void setConquistas(String conquistas) {
        if(conquistas == null) {
            this.conquistas = 0;
        }
        else {
            this.conquistas = Integer.parseInt(conquistas);
        } 
    }

    public void setPublishers(String publishers) {
        this.publishers = removerEspacosIniciais(formatar(publishers,0));
    }

    public void setDevelopers(String developers) {
        this.developers = removerEspacosIniciais(formatar(developers,0));
    }

    public void setCategorias(String categorias) {
        this.categorias = formatar(categorias,0);
    }

    public void setGeneros(String generos) {
        this.generos = formatar(generos,1);
    }

    public void setTags(String tags) {
        this.tags = formatar(tags,1);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public int getJogadores() {
        return this.jogadores;
    }

    public float getPreco() {
        return this.preco;
    }

    public String[] getLinguas() {
        return this.linguas;
    }

    public int getNotaEspecial() {
        return this.notaEspecial;
    }

    public float getNotaUsuario() {
        return this.notaUsuario;
    }

    public int getConquistas() {
        return this.conquistas;
    }

    public String[] getPublishers() {
        return this.publishers;
    }

    public String[] getDevelopers() {
        return this.developers;
    }

    public String[] getCategorias() {
        return this.categorias;
    }

    public String[] getGeneros() {
        return this.generos;
    }

    public String[] getTags() {
        return this.tags;
    }

    public static boolean compare(String str1, String str2) {
		boolean resposta = true;

		if (str1.length() != str2.length()) {
            		resposta = false;
        	}else{
			boolean diferente = false;
			int i = 0;
        		while (i < str1.length() && !diferente) {
            			if (str1.charAt(i) != str2.charAt(i)) {
                			diferente = true;
					resposta = false;
            			}
			i++;
        		}
		}
        return resposta;
    }

    public String[] removerEspacosIniciais(String[] array) {
        if (array == null) {
            return null;
        }
        
        String[] resultado = new String[array.length];
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                resultado[i] = array[i].replaceFirst("^\\s+", "");
            } else {
                resultado[i] = null;
            }
        }
        
        return resultado;
    }

    public String printElementosMultiplos(String [] array, int aux){
        String result = "[";
        for(int i = 0; i < array.length; i++) {
            result += array[i];
            if(i < array.length - 1 && aux == 0) {
                result += ",";
            }
            else if(i < array.length - 1 && aux == 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    public String[] formatar(String entrada, int tipo) {
        int virgulas = 0;
        for(int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if(c == ',') {
                virgulas++;
            }
        }
        String resp[] = new String[virgulas + 1];
        String aux = "";
        int contador = 0;
        if(tipo == 1){
            for(int i = 0; i < entrada.length(); i++) {
                char c = entrada.charAt(i); 
                if(c == ',') {
                    resp[contador] = aux;
                    contador++;
                    aux = "";
                }
                else {
                    if(!(c == '[' || c == ']')) {
                        aux += c;
                    } 
                }
            }
        }else{
            for(int i = 0; i < entrada.length(); i++) {
                char c = entrada.charAt(i); 
                if(c == ',') {
                    resp[contador] = aux;
                    contador++;
                    aux = "";
                }
                else {
                    if(!(c == '[' || c == ']' || c == '\'')) {
                        aux += c;
                    } 
                }
            }
        }
        resp[contador] = aux;
        return resp;
    }

    public String printResultado() {
        return ("=> " + id + " ## " + name + " ## " + date + " ## " + jogadores + " ## " + preco + " ## " + printElementosMultiplos(linguas,0) + " ## " + notaEspecial + " ## " + notaUsuario + " ## " + conquistas + " ## " + printElementosMultiplos(publishers,1) + " ## " + printElementosMultiplos(developers,1) + " ## " + printElementosMultiplos(categorias,1) + " ## " + printElementosMultiplos(generos,1) + " ## " + printElementosMultiplos(tags,1) + " ##");
    }
}

public class Q01{
    static int compara = 0;

    public static boolean compare(String str1, String str2) {
		boolean resposta = true;

		if (str1.length() != str2.length()) {
            		resposta = false;
        	}else{
			boolean diferente = false;
			int i = 0;
        		while (i < str1.length() && !diferente) {
            			if (str1.charAt(i) != str2.charAt(i)) {
                			diferente = true;
					resposta = false;
            			}
			i++;
        		}
		}
        return resposta;
    }

    public static void sets(Game game, String array[]){
        game.setId(array[0]);
        game.setNome(array[1]);
        game.setData(array[2]);
        game.setJogadores(array[3]);
        game.setPreco(array[4]);
        game.setLinguas(array[5]);
        game.setNotaEspecial(array[6]);
        game.setNotaUsuario(array[7]);
        game.setConquistas(array[8]);
        game.setPublishers(array[9]);
        game.setDevelopers(array[10]);
        game.setCategorias(array[11]);
        game.setGeneros(array[12]);
        game.setTags(array[13]);
    }

    public static long now() {
        return System.nanoTime();
    }


    public static void main(String []args) throws FileNotFoundException{
        Scanner scanner = new Scanner(System.in);
        File arq = new File("tps/tp07/Q01/games.csv");
        Scanner scannerArq = new Scanner(arq);

        Game games[] = new Game[2000];
        Arvore arvore = new Arvore();
        int jogos = 0;

        if (scannerArq.hasNextLine()) {
            scannerArq.nextLine();
        }

        while(scannerArq.hasNextLine()) {

            String entrada = scannerArq.nextLine();

            String array[] = new String[14]; 
            String aux = "";
            int contador = 0;
            boolean aspas = false;

            for(int i = 0; i<entrada.length(); i++){
                char c = entrada.charAt(i);

                if(c == '"'){
                    aspas = !aspas;
                
                }else if(c == ',' && !aspas){
                    array[contador] = aux;
                    contador++;
                    aux = "";
                }else{
                    aux = aux + c;
                }
            }

            array[contador] = aux;

            Game tmp = new Game();
            sets(tmp, array);
            arvore.inserirId(tmp);
        }

        String flag = "FIM";
		boolean continuar = true;

        Arvore pesquisa = new Arvore();

        double inicio = now();

	    while (continuar) {
	        String busca = scanner.nextLine();

	        if (compare(busca, flag)) {
            	continuar = false;
        	}else{
                int Busca = Integer.parseInt(busca);
                No i = arvore.raiz;

                while(i != null) {
                    if(Busca > i.elemento.getId()){
                        i = i.dir;
                    } 
                    else if(Busca < i.elemento.getId()){
                        i = i.esq;
                    } 
                    else {
                        pesquisa.inserirNome(i.elemento);
                        i = null;
                    }
                }
	        }

	    }

        String buscaNome = scanner.nextLine();
        while(!compare(buscaNome, "FIM")) {
            pesquisa.pesquisar(buscaNome);
            buscaNome = scanner.nextLine();
        }

        double fim = now();

        double tempoExecucao = (fim - inicio) / 1_000_000.0;

        try {
            PrintWriter log = new PrintWriter("885033_arvoreBinaria.txt"); 
            log.printf("885033\t%.2fms\t%dcomparacoes\n", tempoExecucao, compara);
            log.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }

        scanner.close();
        scannerArq.close();
    }
}