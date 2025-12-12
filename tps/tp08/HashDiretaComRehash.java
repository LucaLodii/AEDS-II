import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

class ConstantesHashRehash {
    public static final int MAX_GAMES = 500;
    public static final int MAX_INNER_ARRAY = 50;
    public static final int MAX_IDS = 100;
}

class GameRehash {
    int id;
    String name;
    String releaseDate;
    int estimatedOwners;
    float price;
    String[] supportedLanguages;
    int supportedLanguagesCount;
    int metacriticScore;
    float userScore;
    int achievements;
    String[] publishers;
    int publishersCount;
    String[] developers;
    int developersCount;
    String[] categories;
    int categoriesCount;
    String[] genres;
    int genresCount;
    String[] tags;
    int tagsCount;

    GameRehash() {
        this.id = 0;
        this.name = "";
        this.releaseDate = "";
        this.estimatedOwners = 0;
        this.price = 0.0f;
        this.supportedLanguages = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
        this.supportedLanguagesCount = 0;
        this.metacriticScore = -1;
        this.userScore = -1.0f;
        this.achievements = 0;
        this.publishers = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
        this.publishersCount = 0;
        this.developers = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
        this.developersCount = 0;
        this.categories = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
        this.categoriesCount = 0;
        this.genres = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
        this.genresCount = 0;
        this.tags = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
        this.tagsCount = 0;
    }

    GameRehash(int id, String name, String releaseDate, int estimatedOwners, float price,
            String[] supportedLanguages, int supportedLanguagesCount, int metacriticScore, float userScore,
            int achievements,
            String[] publishers, int publishersCount, String[] developers, int developersCount,
            String[] categories, int categoriesCount, String[] genres, int genresCount, String[] tags, int tagsCount) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.estimatedOwners = estimatedOwners;
        this.price = price;

        this.supportedLanguages = supportedLanguages;
        this.supportedLanguagesCount = supportedLanguagesCount;
        this.publishers = publishers;
        this.publishersCount = publishersCount;
        this.developers = developers;
        this.developersCount = developersCount;
        this.categories = categories;
        this.categoriesCount = categoriesCount;
        this.genres = genres;
        this.genresCount = genresCount;
        this.tags = tags;
        this.tagsCount = tagsCount;

        this.metacriticScore = metacriticScore;
        this.userScore = userScore;
        this.achievements = achievements;
    }
}

class HashRehash {
    GameRehash tabela[];
    int tamanhoTabela;

    public HashRehash() {
        this(21);
    }

    // Construtor: cria tabela com tamanho 21
    public HashRehash(int tamanhoTabela) {
        this.tamanhoTabela = tamanhoTabela;
        this.tabela = new GameRehash[this.tamanhoTabela];
        for (int i = 0; i < tamanhoTabela; i++) {
            tabela[i] = null;
        }
    }

    // Hash: somaAscii mod 21
    public int hash(int somaAscii) {
        return somaAscii % tamanhoTabela;
    }

    // Rehash: (somaAscii + 1) mod 21
    public int rehash(int somaAscii) {
        return (somaAscii + 1) % tamanhoTabela;
    }

    // Inserir: coloca na posicao hash, se ocupada usa rehash
    public boolean inserir(GameRehash game) {
        boolean inserido = false;
        if (game != null) {
            int somaAscii = 0;
            for (int i = 0; i < game.name.length(); i++) {
                somaAscii += (int) game.name.charAt(i);
            }
            int posicao = hash(somaAscii);
            
            if (tabela[posicao] == null) {
                tabela[posicao] = game;
                inserido = true;
            } else {
                int posicaoRehash = rehash(somaAscii);
                int tentativas = 1;
                
                while (tabela[posicaoRehash] != null && tentativas < tamanhoTabela) {
                    posicaoRehash = (posicaoRehash + 1) % tamanhoTabela;
                    tentativas++;
                }
                
                if (tabela[posicaoRehash] == null) {
                    tabela[posicaoRehash] = game;
                    inserido = true;
                }
            }
        }
        return inserido;
    }

    // Pesquisar: busca na posicao hash, se nao achar usa rehash
    public boolean pesquisar(String name) {
        boolean encontrado = false;
        int somaAscii = 0;
        for (int i = 0; i < name.length(); i++) {
            somaAscii += (int) name.charAt(i);
        }
        
        int posicao = hash(somaAscii);
        
        if (tabela[posicao] != null && tabela[posicao].name.equals(name)) {
            encontrado = true;
        } else {
            int posicaoRehash = rehash(somaAscii);
            int tentativas = 1;
            
            while (tabela[posicaoRehash] != null && !encontrado && tentativas < tamanhoTabela) {
                if (tabela[posicaoRehash].name.equals(name)) {
                    posicao = posicaoRehash;
                    encontrado = true;
                } else {
                    posicaoRehash = (posicaoRehash + 1) % tamanhoTabela;
                    tentativas++;
                }
            }
        }
        
        if (encontrado) {
            System.out.println(name + ":  (Posicao: " + posicao + ") SIM");
            HashDiretaComRehash.logWriter.println(name + ":  (Posicao: " + posicao + ") SIM");
        } else {
            System.out.println(name + ":  (Posicao: " + posicao + ") NAO");
            HashDiretaComRehash.logWriter.println(name + ":  (Posicao: " + posicao + ") NAO");
        }
        return encontrado;
    }
}

public class HashDiretaComRehash {
    public static Scanner sc;
    public static PrintWriter logWriter;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        try {
            logWriter = new PrintWriter(new FileWriter("885732_hashRehash.txt"));
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de log: " + e.getMessage());
            return;
        }

        String entrada = sc.nextLine();
        String ids[] = new String[2000];
        int quantidadeIds = 0;
        for (; !entrada.equals("FIM"); quantidadeIds++) {
            ids[quantidadeIds] = entrada;
            entrada = sc.nextLine();
        }
        HashRehash hash = JogosDigitadosHashRehash.inicializacao(ids, quantidadeIds);

        entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            hash.pesquisar(entrada);
            entrada = sc.nextLine();
        }
        sc.close();
    }
}

class JogosDigitadosHashRehash {
    public static Scanner leitorArquivo;
    static int posicaoLeitura = 0;
    static String[] ids;
    static int idsTamanho;

    static HashRehash inicializacao(String[] idArray, int tamanho) {
        HashRehash hash = new HashRehash();

        ids = idArray;
        idsTamanho = tamanho;

        for (int j = 0; j < tamanho; j++) {
            int indiceEncontrado = -1;

            try {
                java.io.File arquivo = new java.io.File("/tmp/games.csv");
                if (!arquivo.exists()) {
                    System.out.println("Arquivo 'games.csv' não encontrado!");
                    return hash;
                }

                InputStream inputStream = new FileInputStream(arquivo);
                leitorArquivo = new Scanner(inputStream);

                if (leitorArquivo.hasNextLine())
                    leitorArquivo.nextLine();

                while (leitorArquivo.hasNextLine() && indiceEncontrado == -1) {
                    String linha = leitorArquivo.nextLine();
                    posicaoLeitura = 0;

                    int id = capturaId(linha);
                    indiceEncontrado = igualId(id);

                    if (indiceEncontrado != -1) {
                        String name = capturaName(linha);
                        String releaseDate = capturaReleaseDate(linha);
                        int estimatedOwners = capturaEstimatedOwners(linha);
                        float price = capturaPrice(linha);

                        String[] supportedLanguages = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
                        int supportedLanguagesCount = capturaSupportedLanguages(linha, supportedLanguages);
                        int metacriticScore = capturaMetacriticScore(linha);
                        float userScore = capturaUserScore(linha);
                        int achievements = capturaAchievements(linha);

                        String[] publishers = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
                        int publishersCount = capturaUltimosArrays(linha, publishers);
                        String[] developers = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
                        int developersCount = capturaUltimosArrays(linha, developers);
                        String[] categories = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
                        int categoriesCount = capturaUltimosArrays(linha, categories);
                        String[] genres = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
                        int genresCount = capturaUltimosArrays(linha, genres);
                        String[] tags = new String[ConstantesHashRehash.MAX_INNER_ARRAY];
                        int tagsCount = capturaUltimosArrays(linha, tags);

                        GameRehash jogo = new GameRehash(id, name, releaseDate, estimatedOwners, price,
                                supportedLanguages, supportedLanguagesCount, metacriticScore, userScore, achievements,
                                publishers, publishersCount, developers, developersCount, categories, categoriesCount,
                                genres, genresCount, tags, tagsCount);

                        removerId(indiceEncontrado);
                        hash.inserir(jogo);
                    }
                }

                leitorArquivo.close();
                inputStream.close();

            } catch (Exception e) {
                System.out.println("Erro ao abrir ou ler o arquivo: " + e.getMessage());
            }
        }

        return hash;
    }

    static int igualId(int id) {
        for (int i = 0; i < idsTamanho; i++) {
            if (idsTamanho > 0 && Integer.parseInt(ids[0]) == id)
                return 0;
        }
        return -1;
    }

    static void removerId(int indice) {
        if (indice == 0 && idsTamanho > 0) {
            for (int j = indice; j < idsTamanho - 1; j++) {
                ids[j] = ids[j + 1];
            }
            ids[idsTamanho - 1] = null;
            idsTamanho--;
        }
    }

    static int capturaId(String linhaCSV) {
        int id = 0;
        while (posicaoLeitura < linhaCSV.length() && Character.isDigit(linhaCSV.charAt(posicaoLeitura))) {
            id = id * 10 + (linhaCSV.charAt(posicaoLeitura) - '0');
            posicaoLeitura++;
        }
        return id;
    }

    static String capturaName(String linhaCSV) {
        String name = "";
        while (linhaCSV.charAt(posicaoLeitura) != ',' && posicaoLeitura < linhaCSV.length()) {
            posicaoLeitura++;
        }
        posicaoLeitura++;
        if (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) == '"') {
            posicaoLeitura++;
            while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != '"') {
                name += linhaCSV.charAt(posicaoLeitura);
                posicaoLeitura++;
            }
            if (posicaoLeitura < linhaCSV.length())
                posicaoLeitura++;
        } else {
            while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
                name += linhaCSV.charAt(posicaoLeitura);
                posicaoLeitura++;
            }
        }
        return name;
    }

    static String capturaReleaseDate(String linhaCSV) {
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != '"') {
            posicaoLeitura++;
        }
        if (posicaoLeitura < linhaCSV.length())
            posicaoLeitura++;

        String dia = "", mes = "", ano = "";
        for (int i = 0; posicaoLeitura < linhaCSV.length() && i < 3; i++) {
            mes += linhaCSV.charAt(posicaoLeitura);
            posicaoLeitura++;
        }
        mes = mes.trim();
        switch (mes) {
            case "Jan":
                mes = "01";
                break;
            case "Feb":
                mes = "02";
                break;
            case "Mar":
                mes = "03";
                break;
            case "Apr":
                mes = "04";
                break;
            case "May":
                mes = "05";
                break;
            case "Jun":
                mes = "06";
                break;
            case "Jul":
                mes = "07";
                break;
            case "Aug":
                mes = "08";
                break;
            case "Sep":
                mes = "09";
                break;
            case "Oct":
                mes = "10";
                break;
            case "Nov":
                mes = "11";
                break;
            case "Dec":
                mes = "12";
                break;
            default:
                break;
        }
        while (posicaoLeitura < linhaCSV.length() && !Character.isDigit(linhaCSV.charAt(posicaoLeitura)) && linhaCSV.charAt(posicaoLeitura) != ',') {
            posicaoLeitura++;
        }
        while (posicaoLeitura < linhaCSV.length() && Character.isDigit(linhaCSV.charAt(posicaoLeitura))) {
            dia += linhaCSV.charAt(posicaoLeitura);
            posicaoLeitura++;
        }
        while (posicaoLeitura < linhaCSV.length() && !Character.isDigit(linhaCSV.charAt(posicaoLeitura))) {
            posicaoLeitura++;
        }
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != '"') {
            ano += linhaCSV.charAt(posicaoLeitura);
            posicaoLeitura++;
        }
        if (dia.isEmpty())
            dia = "01";
        if (mes.isEmpty())
            mes = "01";
        if (ano.isEmpty())
            ano = "0000";
        return dia + "/" + mes + "/" + ano;
    }

    static int capturaEstimatedOwners(String linhaCSV) {
        int estimatedOwners = 0;
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
            posicaoLeitura++;
        }
        posicaoLeitura++;
        StringBuilder numStr = new StringBuilder();
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
            if (Character.isDigit(linhaCSV.charAt(posicaoLeitura))) {
                numStr.append(linhaCSV.charAt(posicaoLeitura));
            }
            posicaoLeitura++;
        }
        try {
            estimatedOwners = Integer.parseInt(numStr.toString());
        } catch (NumberFormatException e) {
            estimatedOwners = 0;
        }
        return estimatedOwners;
    }

    static float capturaPrice(String linhaCSV) {
        String price = "";
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',' && linhaCSV.charAt(posicaoLeitura) != 'F'
                && !Character.isDigit(linhaCSV.charAt(posicaoLeitura))) {
            posicaoLeitura++;
        }
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
            price += linhaCSV.charAt(posicaoLeitura);
            posicaoLeitura++;
        }
        price = price.trim();
        if (price.isEmpty() || price.toLowerCase().contains("free to play")) {
            return 0.0f;
        }
        price = price.replaceAll("[^0-9.]", "");
        try {
            return Float.parseFloat(price);
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    static int capturaSupportedLanguages(String linhaCSV, String[] supportedLanguages) {
        int quantidade = 0;
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ']' && quantidade < supportedLanguages.length) {
            String idioma = "";
            while (posicaoLeitura < linhaCSV.length() && !Character.isAlphabetic(linhaCSV.charAt(posicaoLeitura))) {
                posicaoLeitura++;
            }
            while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',' && linhaCSV.charAt(posicaoLeitura) != ']') {
                if (linhaCSV.charAt(posicaoLeitura) != '"') {
                    idioma += linhaCSV.charAt(posicaoLeitura);
                }
                posicaoLeitura++;
            }
            supportedLanguages[quantidade++] = idioma.trim();
        }
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
            posicaoLeitura++;
        }
        if (posicaoLeitura < linhaCSV.length())
            posicaoLeitura++;
        return quantidade;
    }

    static int capturaMetacriticScore(String linhaCSV) {
        String metacriticScore = "";
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
            posicaoLeitura++;
        }
        posicaoLeitura++;
        while (posicaoLeitura < linhaCSV.length() && Character.isDigit(linhaCSV.charAt(posicaoLeitura))) {
            metacriticScore += linhaCSV.charAt(posicaoLeitura);
            posicaoLeitura++;
        }
        if (metacriticScore.isEmpty())
            return -1;
        else
            return Integer.parseInt(metacriticScore);
    }

    static float capturaUserScore(String linhaCSV) {
        String userScore = "";
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
            posicaoLeitura++;
        }
        posicaoLeitura++;
        while (posicaoLeitura < linhaCSV.length() && (Character.isDigit(linhaCSV.charAt(posicaoLeitura)) || linhaCSV.charAt(posicaoLeitura) == '.')) {
            userScore += linhaCSV.charAt(posicaoLeitura);
            posicaoLeitura++;
        }
        if (userScore.isEmpty())
            return -1.0f;
        else
            return Float.parseFloat(userScore);
    }

    static int capturaAchievements(String linhaCSV) {
        String achievements = "";
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
            posicaoLeitura++;
        }
        posicaoLeitura++;
        while (posicaoLeitura < linhaCSV.length() && (Character.isDigit(linhaCSV.charAt(posicaoLeitura)) || linhaCSV.charAt(posicaoLeitura) == '.')) {
            achievements += linhaCSV.charAt(posicaoLeitura);
            posicaoLeitura++;
        }
        if (achievements.isEmpty())
            return 0;
        else
            return Integer.parseInt(achievements);
    }

    static int capturaUltimosArrays(String linhaCSV, String[] arrayDestino) {
        int quantidade = 0;
        while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != '"') {
            posicaoLeitura++;
        }

        if (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) == '"') {
            posicaoLeitura++;
            while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != '"' && quantidade < arrayDestino.length) {
                String elemento = "";
                while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',' && linhaCSV.charAt(posicaoLeitura) != '"') {
                    elemento += linhaCSV.charAt(posicaoLeitura);
                    posicaoLeitura++;
                }
                arrayDestino[quantidade++] = elemento.trim();
                if (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) == ',') {
                    posicaoLeitura++;
                }
            }
            if (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) == '"') {
                posicaoLeitura++;
            }
        } else {
            if (quantidade < arrayDestino.length) {
                String elemento = "";
                while (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) != ',') {
                    elemento += linhaCSV.charAt(posicaoLeitura);
                    posicaoLeitura++;
                }
                arrayDestino[quantidade++] = elemento;
            }
        }
        if (posicaoLeitura < linhaCSV.length() && linhaCSV.charAt(posicaoLeitura) == ',') {
            posicaoLeitura++;
        }
        return quantidade;
    }
}