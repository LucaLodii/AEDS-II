package tps.tp06;

import java.io.File;
import java.util.Scanner;

public class Q04 {
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
            return supportedLanguage == null ? new String[0] : supportedLanguage;
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
            return publishers == null ? new String[0] : publishers;
        }

        public String[] getDevelopers() {
            return developers == null ? new String[0] : developers;
        }

        public String[] getCategories() {
            return categories == null ? new String[0] : categories;
        }

        public String[] getGenres() {
            return genres == null ? new String[0] : genres;
        }

        public String[] getTags() {
            return tags == null ? new String[0] : tags;
        }

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
            this.supportedLanguage = cloneArray(supportedLanguage);
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
            this.publishers = cloneArray(publishers);
        }

        public void setDevelopers(String[] developers) {
            this.developers = cloneArray(developers);
        }

        public void setCategories(String[] categories) {
            this.categories = cloneArray(categories);
        }

        public void setGenres(String[] genres) {
            this.genres = cloneArray(genres);
        }

        public void setTags(String[] tags) {
            this.tags = cloneArray(tags);
        }

        private static String[] cloneArray(String[] array) {
            if (array == null) {
                return new String[0];
            }
            String[] clone = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                clone[i] = array[i];
            }
            return clone;
        }

        public Game clone() {
            return new Game(id, name, releaseDate, estimatedOwners, price,
                    cloneArray(supportedLanguage), metacriticScore, userScore, achievements,
                    cloneArray(publishers), cloneArray(developers), cloneArray(categories),
                    cloneArray(genres), cloneArray(tags));
        }
    }

    class Celula {
        public Game game;
        public Celula prox;

        public Celula() {
            this(null);
        }

        public Celula(Game x) {
            game = x;
            prox = null;
        }
    }

    class Fila {
        private Celula primeiro, ultimo;

        public Fila() {
            primeiro = new Celula();
            ultimo = primeiro;
        }

        public void enfileirar(Game game) throws Exception {
            if (game == null) {
                throw new Exception("Erro ao enfileirar (jogo nulo)!");
            }
            ultimo.prox = new Celula(game);
            ultimo = ultimo.prox;
        }

        public Game desenfileirar() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao desenfileirar (fila vazia)!");
            }

            Celula removida = primeiro.prox;
            Game elemento = removida.game;
            primeiro.prox = removida.prox;
            if (primeiro.prox == null) {
                ultimo = primeiro;
            }
            removida.prox = null;
            return elemento;
        }

        public void mostrar() {
            int j = 0;
            for (Celula i = primeiro.prox; i != null; i = i.prox) {
                Game jogo = i.game;
                String releaseDate = jogo.getReleaseDate();
                if (releaseDate != null && releaseDate.length() > 1 && releaseDate.charAt(1) == '/') {
                    releaseDate = "0" + releaseDate;
                }
                System.out.println("[" + j + "]" +
                        " => " + jogo.getId() + " ## " + jogo.getName() + " ## " + (releaseDate == null ? "" : releaseDate)
                        + " ## " + jogo.getEstimatedOwners() + " ## " + jogo.getPrice()
                        + " ## " + printArray(jogo.getSupportedLanguage())
                        + " ## " + jogo.getMetacriticScore() + " ## " + jogo.getUserScore()
                        + " ## " + jogo.getAchievements()
                        + " ## " + printArray(jogo.getPublishers())
                        + " ## " + printArray(jogo.getDevelopers())
                        + " ## " + printArray(jogo.getCategories())
                        + " ## " + printArray(jogo.getGenres())
                        + " ## "
                        + (jogo.getTags().length == 0 ? "" : printArray(jogo.getTags()))
                        + (jogo.getTags().length == 0 ? "" : " ##"));
                j++;
            }
        }
    }

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

    public static boolean stringIsEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static int stringToInt(String str) {
        int numero = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                int tmp = c - '0';
                numero = numero * 10 + tmp;
            }
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
                day = removeAllNotNumbers(date[0]);
                if (stringIsEmpty(day)) {
                    day = "01";
                }
                month = "01";
                break;
        }

        if (date.length >= 2) {
            if (stringEquals(month, "01")
                    && !date[0].matches("Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec")) {
            } else {
                day = removeAllNotNumbers(date[1]);
                if (stringIsEmpty(day)) {
                    day = "01";
                }
            }
        }

        if (date.length >= 3) {
            year = removeAllNotNumbers(date[2]);
        } else if (date.length == 2 && !stringEquals(month, "01")) {
            year = removeAllNotNumbers(date[1]);
        }

        if (stringIsEmpty(year)) {
            year = "2000";
        }

        if (stringToInt(day) < 10 && day.length() == 1) {
            day = "0" + day;
        }

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
        return stringToFloat(score);
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

    public static String[] parseArrayField(String field) {
        String cleaned = removeColchetes(removeAspas(field));
        cleaned = trimString(cleaned);
        if (stringIsEmpty(cleaned)) {
            return new String[0];
        }

        cleaned = cleaned.replace(';', ',');
        String[] partes = cleaned.split(",");
        java.util.List<String> valores = new java.util.ArrayList<>();
        for (int i = 0; i < partes.length; i++) {
            String valor = trimString(partes[i]);
            if (!stringIsEmpty(valor)) {
                valores.add(valor);
            }
        }
        return valores.toArray(new String[0]);
    }

    public static String printArray(String[] array) {
        if (array == null || array.length == 0) {
            return "[]";
        }

        StringBuilder resultado = new StringBuilder("[");
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            String valor = array[i] == null ? "" : array[i].trim();
            if (valor.length() == 0) {
                continue;
            }
            if (count > 0) {
                resultado.append(", ");
            }
            resultado.append(valor);
            count++;
        }
        resultado.append("]");
        return count == 0 ? "[]" : resultado.toString();
    }

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
                game.setSupportedLanguage(parseArrayField(fields[5]));
                game.setMetacriticScore(validScore(stringToInt(removeAllNotNumbers(fields[6]))));
                game.setUserScore(validScoreFloat(fields[7]));
                game.setAchievements(stringToInt(removeAllNotNumbers(fields[8])));
                game.setPublishers(parseArrayField(fields[9]));
                game.setDevelopers(parseArrayField(fields[10]));
                game.setCategories(parseArrayField(fields[11]));
                game.setGenres(parseArrayField(fields[12]));
                game.setTags(parseArrayField(fields[13]));

                games[index] = game;
                index++;
            }
            sc.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return index;
    }

    public static Game buscarGamePorId(Game[] games, int totalGames, int id) {
        for (int i = 0; i < totalGames; i++) {
            Game game = games[i];
            if (game != null && game.getId() == id) {
                return game;
            }
        }
        return null;
    }

    private static void imprimirRemocao(Game game) {
        if (game != null && game.getName() != null) {
            System.out.println("(R) " + game.getName());
        }
    }

    public static void main(String[] args) throws Exception {
        Game[] todosGames = new Game[100000];
        int totalGames = lerGames(todosGames);

        Q04 programa = new Q04();
        Fila fila = programa.new Fila();

        Scanner scan = new Scanner(System.in);

        // IDs iniciais
        while (scan.hasNextLine()) {
            String linha = trimString(scan.nextLine());
            if (stringEquals(linha, "FIM")) {
                break;
            }
            if (stringIsEmpty(linha)) {
                continue;
            }
            int id = stringToInt(removeAllNotNumbers(linha));
            Game game = buscarGamePorId(todosGames, totalGames, id);
            if (game != null) {
                fila.enfileirar(game.clone());
            }
        }

        int numComandos = 0;
        if (scan.hasNextLine()) {
            String quantidade = trimString(scan.nextLine());
            if (!stringIsEmpty(quantidade)) {
                numComandos = stringToInt(removeAllNotNumbers(quantidade));
            }
        }

        for (int i = 0; i < numComandos && scan.hasNextLine(); i++) {
            String comandoLinha = trimString(scan.nextLine());
            if (stringIsEmpty(comandoLinha)) {
                i--;
                continue;
            }

            String[] partes = comandoLinha.split("\\s+");
            String comando = partes[0];

            if (stringEquals(comando, "I") || stringEquals(comando, "II")) {
                if (partes.length >= 2) {
                    int id = stringToInt(removeAllNotNumbers(partes[1]));
                    Game game = buscarGamePorId(todosGames, totalGames, id);
                    if (game != null) {
                        fila.enfileirar(game.clone());
                    }
                }
            } else if (stringEquals(comando, "R")) {
                Game removido = fila.desenfileirar();
                imprimirRemocao(removido);
            }
        }

        fila.mostrar();
        scan.close();
    }
}
