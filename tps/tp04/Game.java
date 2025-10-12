import java.io.File;
import java.util.Scanner;

public class GameNoLibs {
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

    public GameNoLibs() {
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

    public static int myStrlen(String str) {
        int len = 0;
        for (int i = 0; i < str.length(); i++) {
            len++;
        }
        return len;
    }

    public static String myTrim(String str) {
        if (str == null) return "";

        int start = 0;
        int end = str.length() - 1;

        while (start <= end && (str.charAt(start) == ' ' || str.charAt(start) == '\t' ||
               str.charAt(start) == '\n' || str.charAt(start) == '\r')) {
            start++;
        }

        while (end >= start && (str.charAt(end) == ' ' || str.charAt(end) == '\t' ||
               str.charAt(end) == '\n' || str.charAt(end) == '\r')) {
            end--;
        }

        if (start > end) return "";

        char[] result = new char[end - start + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = str.charAt(start + i);
        }
        return new String(result);
    }

    public static int myStrcmp(String s1, String s2) {
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
        if (str == null) return "";

        char[] temp = new char[str.length()];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '\'' && c != '"') {
                temp[j++] = c;
            }
        }
        return new String(temp, 0, j);
    }

    public static String removeColchetes(String str) {
        if (str == null) return "";

        char[] temp = new char[str.length()];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '[' && c != ']') {
                temp[j++] = c;
            }
        }
        return new String(temp, 0, j);
    }

    public static String removeVirgula(String str) {
        if (str == null) return "";

        char[] temp = new char[str.length()];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ',') {
                temp[j++] = c;
            }
        }
        return new String(temp, 0, j);
    }

    public static String removeAllNotNumbers(String str) {
        if (str == null) return "0";

        char[] temp = new char[str.length()];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                temp[j++] = c;
            }
        }

        if (j == 0) return "0";
        return new String(temp, 0, j);
    }

    public static boolean isMonth(String str) {
        return myStrcmp(str, "Jan") == 0 || myStrcmp(str, "Feb") == 0 ||
               myStrcmp(str, "Mar") == 0 || myStrcmp(str, "Apr") == 0 ||
               myStrcmp(str, "May") == 0 || myStrcmp(str, "Jun") == 0 ||
               myStrcmp(str, "Jul") == 0 || myStrcmp(str, "Aug") == 0 ||
               myStrcmp(str, "Sep") == 0 || myStrcmp(str, "Oct") == 0 ||
               myStrcmp(str, "Nov") == 0 || myStrcmp(str, "Dec") == 0;
    }

    public static String[] mySplit(String str, char delimiter) {
        if (str == null) return new String[0];

        // Count delimiters
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == delimiter) count++;
        }

        String[] result = new String[count];
        int start = 0;
        int index = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == delimiter) {
                char[] token = new char[i - start];
                for (int j = 0; j < token.length; j++) {
                    token[j] = str.charAt(start + j);
                }
                result[index++] = new String(token);
                start = i + 1;
            }
        }

        // Last token
        char[] token = new char[str.length() - start];
        for (int j = 0; j < token.length; j++) {
            token[j] = str.charAt(start + j);
        }
        result[index] = new String(token);

        return result;
    }

    public static String stringToDate(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        str = removeAspas(str);
        str = myTrim(str);

        if (str.length() == 0) {
            return "";
        }

        String[] date = mySplit(str, ' ');
        if (date.length == 0) {
            return "";
        }

        String month = "01";
        String day = "01";
        String year = "";

        if (myStrcmp(date[0], "Jan") == 0) month = "01";
        else if (myStrcmp(date[0], "Feb") == 0) month = "02";
        else if (myStrcmp(date[0], "Mar") == 0) month = "03";
        else if (myStrcmp(date[0], "Apr") == 0) month = "04";
        else if (myStrcmp(date[0], "May") == 0) month = "05";
        else if (myStrcmp(date[0], "Jun") == 0) month = "06";
        else if (myStrcmp(date[0], "Jul") == 0) month = "07";
        else if (myStrcmp(date[0], "Aug") == 0) month = "08";
        else if (myStrcmp(date[0], "Sep") == 0) month = "09";
        else if (myStrcmp(date[0], "Oct") == 0) month = "10";
        else if (myStrcmp(date[0], "Nov") == 0) month = "11";
        else if (myStrcmp(date[0], "Dec") == 0) month = "12";
        else {
            day = removeVirgula(date[0]);
            if (day.length() == 0) day = "01";
        }

        if (date.length >= 2) {
            if (myStrcmp(month, "01") != 0 || isMonth(date[0])) {
                if (isMonth(date[0])) {
                    day = removeVirgula(date[1]);
                    if (day.length() == 0) day = "01";
                }
            }
        }

        if (date.length >= 3) {
            year = removeVirgula(date[2]);
        } else if (date.length == 2 && myStrcmp(month, "01") != 0) {
            year = removeVirgula(date[1]);
        }

        if (year.length() == 0) year = "2000";

        if (stringToInt(day) < 10 && day.charAt(0) != '0') {
            char[] temp = new char[day.length() + 1];
            temp[0] = '0';
            for (int i = 0; i < day.length(); i++) {
                temp[i + 1] = day.charAt(i);
            }
            day = new String(temp);
        }

        return day + "/" + month + "/" + year;
    }

    public static float stringToFloat(String str) {
        if (str == null || str.length() == 0) {
            return 0.0f;
        }

        str = myTrim(str);

        if (myStrcmp(str, "Free to Play") == 0) {
            return 0.0f;
        }

        float result = 0.0f;
        int sign = 1;
        int i = 0;

        if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10.0f + (str.charAt(i) - '0');
            i++;
        }

        if (i < str.length() && str.charAt(i) == '.') {
            i++;
            float divisor = 10.0f;
            while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                result = result + (str.charAt(i) - '0') / divisor;
                divisor *= 10.0f;
                i++;
            }
        }

        result = (int)(result * 100 + 0.5f) / 100.0f;
        return result * sign;
    }

    public static int validScore(int score) {
        return score;
    }

    public static float validScoreFloat(String score) {
        if (score == null) return -1.0f;

        score = myTrim(score);

        if (score.length() == 0 || myStrcmp(score, "tdb") == 0)
            return -1.0f;

        return stringToFloat(score);
    }

    public static String[] parseCSVLine(String line) {
        String[] tempFields = new String[20];
        int fieldCount = 0;
        boolean inQuotes = false;
        char[] currentField = new char[2048];
        int charIndex = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tempFields[fieldCount++] = new String(currentField, 0, charIndex);
                charIndex = 0;
            } else {
                currentField[charIndex++] = c;
            }
        }
        tempFields[fieldCount++] = new String(currentField, 0, charIndex);

        String[] fields = new String[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            fields[i] = tempFields[i];
        }

        return fields;
    }

    public static void printArrayField(String field) {
        String[] tokens = mySplit(field, ',');

        for (int i = 0; i < tokens.length; i++) {
            String token = myTrim(tokens[i]);
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(token);
        }
    }

    public static void printGame(GameNoLibs game) {
        System.out.print("=> ");
        System.out.print(game.getId() + " ## ");
        System.out.print(game.getName() + " ## ");
        System.out.print(game.getReleaseDate() + " ## ");
        System.out.print(game.getEstimatedOwners() + " ## ");
        System.out.print(game.getPrice() + " ## ");
        System.out.print("[");
        printArrayField(game.getSupportedLanguage()[0]);
        System.out.print("] ## ");
        System.out.print(game.getMetacriticScore() + " ## ");
        System.out.print(game.getUserScore() + " ## ");
        System.out.print(game.getAchievements() + " ## ");
        System.out.print("[");
        printArrayField(game.getPublishers()[0]);
        System.out.print("] ## ");
        System.out.print("[");
        printArrayField(game.getDevelopers()[0]);
        System.out.print("] ## ");
        System.out.print("[");
        printArrayField(game.getCategories()[0]);
        System.out.print("] ## ");
        System.out.print("[");
        printArrayField(game.getGenres()[0]);
        System.out.print("] ## ");
        System.out.print("[");
        printArrayField(game.getTags()[0]);
        System.out.print("] ##");
        System.out.println();
    }

    public static void searchGame(GameNoLibs[] games, int index) {
        Scanner scan = new Scanner(System.in);
        String idSearch = "";
        int idSearchInt = 0;
        try {
            while (myStrcmp(idSearch, "FIM") != 0) {
                if (scan.hasNextLine()) {
                    idSearch = scan.nextLine();
                    if (myStrcmp(idSearch, "FIM") != 0) {
                        idSearchInt = stringToInt(idSearch);
                        for (int i = 0; i < index; i++) {
                            if (games[i].getId() == idSearchInt && idSearchInt != 0) {
                                printGame(games[i]);
                                break;
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        } finally {
            scan.close();
        }
    }

    public static void main(String[] args) {
        GameNoLibs[] games = new GameNoLibs[10000];
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
            } else {
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

                    GameNoLibs game = new GameNoLibs();
                    game.setId(stringToInt(fields[0]));
                    game.setName(fields[1]);
                    game.setReleaseDate(stringToDate(fields[2]));
                    game.setEstimatedOwners(stringToInt(removeAllNotNumbers(fields[3])));
                    game.setPrice(stringToFloat(fields[4]));

                    String[] lang = new String[1];
                    lang[0] = removeColchetes(removeAspas(fields[5]));
                    game.setSupportedLanguage(lang);

                    game.setMetacriticScore(validScore(stringToInt(fields[6])));
                    game.setUserScore(validScoreFloat(fields[7]));
                    game.setAchievements(stringToInt(fields[8]));

                    String[] pub = new String[1];
                    pub[0] = removeAspas(fields[9]);
                    game.setPublishers(pub);

                    String[] dev = new String[1];
                    dev[0] = removeAspas(fields[10]);
                    game.setDevelopers(dev);

                    String[] cat = new String[1];
                    cat[0] = removeAspas(fields[11]);
                    game.setCategories(cat);

                    String[] gen = new String[1];
                    gen[0] = removeAspas(fields[12]);
                    game.setGenres(gen);

                    String[] tag = new String[1];
                    tag[0] = removeAspas(fields[13]);
                    game.setTags(tag);

                    games[index] = game;
                    index++;
                }
                sc.close();
            }
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Error reading file: games.csv (No such file or directory)");
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        searchGame(games, index);
    }
}
