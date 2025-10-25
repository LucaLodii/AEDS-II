import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * TP-05 Questão 01 - Pesquisa Binária em Java
 * Implementa pesquisa binária por nome de jogos após ordenação
 * Cria arquivo de log com matrícula, tempo de execução e número de comparações
 */
public class Q01 {
    // Classe Game com todos os atributos necessários
    static class Game {
        private int id;
        private String name;
        private String releaseDate;
        private int estimatedOwners;
        private float price;
        private String[] supportedLanguage;
        private int metacriticScore;
        private float userScore;
        private int achievements;
        private String[] publishers;
        private String[] developers;
        private String[] categories;
        private String[] genres;
        private String[] tags;

        public Game() {
        }

        public Game(int id, String name, String releaseDate, int estimatedOwners, float price,
                String[] supportedLanguage, int metacriticScore, float userScore, int achievements,
                String[] publishers, String[] developers, String[] categories, String[] genres, String[] tags) {
            setId(id);
            setName(name);
            setReleaseDate(releaseDate);
            setEstimatedOwners(estimatedOwners);
            setPrice(price);
            setSupportedLanguage(supportedLanguage);
            setMetacriticScore(metacriticScore);
            setUserScore(userScore);
            setAchievements(achievements);
            setPublishers(publishers);
            setDevelopers(developers);
            setCategories(categories);
            setGenres(genres);
            setTags(tags);
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public int getEstimatedOwners() {
            return estimatedOwners;
        }

        public float getPrice() {
            return price;
        }

        public String[] getSupportedLanguage() {
            return supportedLanguage;
        }

        public int getMetacriticScore() {
            return metacriticScore;
        }

        public float getUserScore() {
            return userScore;
        }

        public int getAchievements() {
            return achievements;
        }

        public String[] getPublishers() {
            return publishers;
        }

        public String[] getDevelopers() {
            return developers;
        }

        public String[] getCategories() {
            return categories;
        }

        public String[] getGenres() {
            return genres;
        }

        public String[] getTags() {
            return tags;
        }

        // Setters
        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setEstimatedOwners(int estimatedOwners) {
            this.estimatedOwners = estimatedOwners;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void setSupportedLanguage(String[] supportedLanguage) {
            this.supportedLanguage = supportedLanguage;
        }

        public void setMetacriticScore(int metacriticScore) {
            this.metacriticScore = metacriticScore;
        }

        public void setUserScore(float userScore) {
            this.userScore = userScore;
        }

        public void setAchievements(int achievements) {
            this.achievements = achievements;
        }

        public void setPublishers(String[] publishers) {
            this.publishers = publishers;
        }

        public void setDevelopers(String[] developers) {
            this.developers = developers;
        }

        public void setCategories(String[] categories) {
            this.categories = categories;
        }

        public void setGenres(String[] genres) {
            this.genres = genres;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }
    }

    // ==================== MÉTODOS AUXILIARES PERSONALIZADOS ====================

    /**
     * Compara duas strings manualmente (substitui compareTo)
     * Retorna: < 0 se s1 < s2, 0 se iguais, > 0 se s1 > s2
     */
    public static int compareStrings(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int minLen = len1 < len2 ? len1 : len2;

        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.charAt(i) - s2.charAt(i);
            }
        }
        return len1 - len2;
    }

    /**
     * Verifica se duas strings são iguais (substitui equals)
     */
    public static boolean stringEquals(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove espaços do início e fim da string (substitui trim)
     */
    public static String trimString(String str) {
        if (str == null || str.length() == 0) return str;

        int inicio = 0;
        int fim = str.length() - 1;

        while (inicio <= fim && (str.charAt(inicio) == ' ' || str.charAt(inicio) == '\t' ||
               str.charAt(inicio) == '\n' || str.charAt(inicio) == '\r')) {
            inicio++;
        }

        while (fim >= inicio && (str.charAt(fim) == ' ' || str.charAt(fim) == '\t' ||
               str.charAt(fim) == '\n' || str.charAt(fim) == '\r')) {
            fim--;
        }

        if (inicio > fim) return "";

        StringBuilder result = new StringBuilder();
        for (int i = inicio; i <= fim; i++) {
            result.append(str.charAt(i));
        }
        return result.toString();
    }

    /**
     * Verifica se string está vazia (substitui isEmpty)
     */
    public static boolean stringIsEmpty(String str) {
        return str == null || str.length() == 0;
    }

    // ==================== MÉTODOS DE CONVERSÃO E PARSING ====================

    public static int stringToInt(String str) {
        int numero = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int tmp = c - '0';
            numero = numero * 10 + tmp;
        }
        return numero;
    }

    public static String removeAspas(String str) {
        if (str == null)
            return "";
        return str.replaceAll("[\'\"]", "");
    }

    public static String removeColchetes(String str) {
        if (str == null)
            return "";
        return str.replaceAll("[\\[\\]]", "");
    }

    public static String removeVirgula(String str) {
        if (str == null)
            return "";
        return str.replaceAll(",", "");
    }

    public static String removeAllNotNumbers(String str) {
        if (str == null)
            return "0";
        return str.replaceAll("[^0-9]", "");
    }

    public static String stringToDate(String str) {
        if (str == null || stringIsEmpty(trimString(str))) {
            return "";
        }

        str = trimString(removeAspas(str));
        String[] date = str.split(" ");
        if (date.length == 0) {
            return "";
        }

        String month = "01";
        String day = "01";
        String year = "";

        // Parse month
        switch (date[0]) {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
            default:
                day = removeVirgula(date[0]);
                if (day.isEmpty())
                    day = "01";
                month = "01";
                break;
        }

        if (date.length >= 2) {
            if (stringEquals(month, "01")
                    && !date[0].matches("Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec")) {
            } else {
                day = removeVirgula(date[1]);
                if (stringIsEmpty(day))
                    day = "01";
            }
        }

        if (date.length >= 3) {
            year = removeVirgula(date[2]);
        } else if (date.length == 2 && !stringEquals(month, "01")) {
            year = removeVirgula(date[1]);
        }

        if (stringIsEmpty(year))
            year = "2000";

        if (stringToInt(day) < 10)
            day = "0" + day;

        return day + "/" + month + "/" + year;
    }

    public static float stringToFloat(String str) {
        if (str == null || stringIsEmpty(trimString(str))) {
            return 0.0f;
        }

        str = trimString(str);
        if (stringEquals(str, "Free to Play")) {
            return 0.0f;
        }

        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    public static int validScore(int score) {
        return score;
    }

    public static float validScoreFloat(String score) {
        if (score == null || stringIsEmpty(trimString(score)) || stringEquals(score, "tdb"))
            return -1.0f;
        float scoreFloat = stringToFloat(score);
        return scoreFloat;
    }

    public static String[] parseCSVLine(String line) {
        java.util.List<String> fields = new java.util.ArrayList<>();
        boolean inQuotes = false;
        StringBuilder currentField = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        fields.add(currentField.toString());

        return fields.toArray(new String[0]);
    }

    public static void printGame(Game game) {
        System.out.print("=> ");
        System.out.print(game.getId() + " ## ");
        System.out.print(game.getName() + " ## ");
        System.out.print(game.getReleaseDate() + " ## ");
        System.out.print(game.getEstimatedOwners() + " ## ");
        System.out.print(game.getPrice() + " ## ");
        System.out.print("[");
        for (int i = 0; i < game.getSupportedLanguage().length; i++) {
            if (i == game.getSupportedLanguage().length - 1) {
                System.out.print(game.getSupportedLanguage()[i].trim());
            } else {
                System.out.print(game.getSupportedLanguage()[i].trim() + ", ");
            }
        }
        System.out.print("] ## ");
        System.out.print(game.getMetacriticScore() + " ## ");
        System.out.print(game.getUserScore() + " ## ");
        System.out.print(game.getAchievements() + " ## ");
        System.out.print("[");
        for (int i = 0; i < game.getPublishers().length; i++) {
            if (i == game.getPublishers().length - 1) {
                System.out.print(game.getPublishers()[i].trim());
            } else {
                System.out.print(game.getPublishers()[i].trim() + ", ");
            }
        }
        System.out.print("] ## ");
        System.out.print("[");
        for (int i = 0; i < game.getDevelopers().length; i++) {
            if (i == game.getDevelopers().length - 1) {
                System.out.print(game.getDevelopers()[i].trim());
            } else {
                System.out.print(game.getDevelopers()[i].trim() + ", ");
            }
        }
        System.out.print("] ## ");
        System.out.print("[");
        for (int i = 0; i < game.getCategories().length; i++) {
            if (i == game.getCategories().length - 1) {
                System.out.print(game.getCategories()[i].trim());
            } else {
                System.out.print(game.getCategories()[i].trim() + ", ");
            }
        }
        System.out.print("] ## ");
        System.out.print("[");
        for (int i = 0; i < game.getGenres().length; i++) {
            if (i == game.getGenres().length - 1) {
                System.out.print(game.getGenres()[i].trim());
            } else {
                System.out.print(game.getGenres()[i].trim() + ", ");
            }
        }
        System.out.print("] ## ");
        System.out.print("[");
        for (int i = 0; i < game.getTags().length; i++) {
            if (i == game.getTags().length - 1) {
                System.out.print(game.getTags()[i].trim());
            } else {
                System.out.print(game.getTags()[i].trim() + ", ");
            }
        }
        System.out.print("] ##");
        System.out.println();
    }

    // Variável global para contagem de comparações
    static int comparacoes = 0;

    /**
     * Ordenação por inserção para ordenar os jogos por nome
     * Em caso de empate, ordena por AppID
     */
    public static void ordenarPorNome(Game[] games, int n) {
        for (int i = 1; i < n; i++) {
            Game tmp = games[i];
            int j = i - 1;

            while (j >= 0 && (compareStrings(games[j].getName(), tmp.getName()) > 0
                    || (stringEquals(games[j].getName(), tmp.getName()) && games[j].getId() > tmp.getId()))) {
                games[j + 1] = games[j];
                j--;
            }
            games[j + 1] = tmp;
        }
    }

    /**
     * Pesquisa binária por nome de jogo
     * Retorna true se encontrar, false caso contrário
     * Incrementa o contador de comparações
     */
    public static boolean pesquisaBinaria(Game[] games, int n, String nomeBusca) {
        int esq = 0;
        int dir = n - 1;

        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            comparacoes++; // Conta a comparação

            int cmp = compareStrings(games[meio].getName(), nomeBusca);

            if (cmp == 0) {
                return true; // Encontrou
            } else if (cmp > 0) {
                dir = meio - 1; // Busca na metade esquerda
            } else {
                esq = meio + 1; // Busca na metade direita
            }
        }

        return false; // Não encontrou
    }

    /**
     * Lê jogos do arquivo CSV e armazena no array
     */
    public static int lerGames(Game[] games) {
        int index = 0;
        try {
            File arquivo = new File("/tmp/games.csv");
            if (!arquivo.exists()) {
                arquivo = new File("games.csv");
            }
            if (!arquivo.exists()) {
                arquivo = new File("./games.csv");
            }

            if (!arquivo.exists()) {
                System.err.println("Error reading file: games.csv (No such file or directory)");
                return 0;
            }

            Scanner sc = new Scanner(arquivo);

            // Skip header line
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = parseCSVLine(line);

                if (fields.length < 14) {
                    continue;
                }

                Game game = new Game();
                game.setId(stringToInt(fields[0]));
                game.setName(fields[1]);
                game.setReleaseDate(stringToDate(fields[2]));
                game.setEstimatedOwners(stringToInt(removeAllNotNumbers(fields[3])));
                game.setPrice(stringToFloat(fields[4]));
                game.setSupportedLanguage(removeColchetes(removeAspas(fields[5])).split(","));
                game.setMetacriticScore(validScore(stringToInt(fields[6])));
                game.setUserScore(validScoreFloat(fields[7]));
                game.setAchievements(stringToInt(fields[8]));
                game.setPublishers(removeAspas(fields[9]).split(","));
                game.setDevelopers(removeAspas(fields[10]).split(","));
                game.setCategories(removeAspas(fields[11]).split(","));
                game.setGenres(removeAspas(fields[12]).split(","));
                game.setTags(removeAspas(fields[13]).split(","));

                games[index] = game;
                index++;
            }
            sc.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return index;
    }

    /**
     * Lê IDs da entrada padrão até encontrar "FIM" e adiciona os jogos ao array
     */
    public static int lerEntrada(Game[] games, Game[] todosGames, int totalGames, Scanner scan) {
        int index = 0;
        String entrada = "";

        while (!stringEquals(entrada, "FIM")) {
            if (scan.hasNextLine()) {
                entrada = scan.nextLine();
                if (!stringEquals(entrada, "FIM")) {
                    int idBusca = stringToInt(entrada);
                    // Busca o jogo no array completo
                    for (int i = 0; i < totalGames; i++) {
                        if (todosGames[i].getId() == idBusca) {
                            games[index] = todosGames[i];
                            index++;
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
        return index;
    }

    /**
     * Escreve o arquivo de log com matrícula, tempo de execução e número de comparações
     */
    public static void escreverLog(String matricula, long tempo, int comparacoes) {
        try {
            FileWriter writer = new FileWriter(matricula + "_binaria.txt");
            writer.write(matricula + "\t" + tempo + "\t" + comparacoes);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error writing log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String matricula = "SUAMATRICULA"; // Substituir pela sua matrícula

        // Array para todos os jogos do CSV
        Game[] todosGames = new Game[100000];
        int totalGames = lerGames(todosGames);

        // Scanner único para toda a entrada
        Scanner scan = new Scanner(System.in);

        // Array para os jogos selecionados pela entrada
        Game[] gamesSelecionados = new Game[100000];
        int numSelecionados = lerEntrada(gamesSelecionados, todosGames, totalGames, scan);

        // Ordena os jogos selecionados por nome
        ordenarPorNome(gamesSelecionados, numSelecionados);

        // Inicia contagem de tempo
        long inicio = System.currentTimeMillis();

        // Lê as buscas e realiza pesquisa binária
        String busca = "";

        while (!stringEquals(busca, "FIM")) {
            if (scan.hasNextLine()) {
                busca = scan.nextLine();
                if (!stringEquals(busca, "FIM")) {
                    boolean encontrou = pesquisaBinaria(gamesSelecionados, numSelecionados, busca);
                    System.out.println(encontrou ? " SIM" : " NAO");
                }
            } else {
                break;
            }
        }
        scan.close();

        // Fim da contagem de tempo
        long fim = System.currentTimeMillis();
        long tempoExecucao = fim - inicio;

        // Escreve o arquivo de log
        escreverLog(matricula, tempoExecucao, comparacoes);
    }
}
