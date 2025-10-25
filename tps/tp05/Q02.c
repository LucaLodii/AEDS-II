#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

/**
 * TP-05 Questão 02 - Ordenação por Seleção em C
 * Implementa ordenação por seleção por nome de jogos
 * Cria arquivo de log com matrícula, comparações, movimentações e tempo
 */

typedef struct {
    int id;
    char name[256];
    char releaseDate[11];
    int estimatedOwners;
    float price;
    char supportedLanguage[1024];
    int metacriticScore;
    float userScore;
    int achievements;
    char publishers[512];
    char developers[512];
    char categories[512];
    char genres[512];
    char tags[1024];
} Game;

// Variáveis globais para contagem
int comparacoes = 0;
int movimentacoes = 0;

// ==================== FUNÇÕES AUXILIARES ====================

int myStrlen(const char* str) {
    int len = 0;
    while (str[len] != '\0') len++;
    return len;
}

void myStrcpy(char* dest, const char* src) {
    int i = 0;
    while (src[i] != '\0') {
        dest[i] = src[i];
        i++;
    }
    dest[i] = '\0';
}

int myStrcmp(const char* s1, const char* s2) {
    int i = 0;
    while (s1[i] != '\0' && s2[i] != '\0') {
        if (s1[i] != s2[i]) {
            return s1[i] - s2[i];
        }
        i++;
    }
    return s1[i] - s2[i];
}

int stringToInt(const char* str) {
    int numero = 0;
    int i = 0;
    while (str[i] != '\0') {
        if (str[i] >= '0' && str[i] <= '9') {
            numero = numero * 10 + (str[i] - '0');
        }
        i++;
    }
    return numero;
}

void trim(char* str) {
    int start = 0;
    int end = myStrlen(str) - 1;

    while (str[start] == ' ' || str[start] == '\t' || str[start] == '\n' || str[start] == '\r') {
        start++;
    }

    while (end >= start && (str[end] == ' ' || str[end] == '\t' || str[end] == '\n' || str[end] == '\r')) {
        end--;
    }

    int i;
    for (i = 0; i <= end - start; i++) {
        str[i] = str[start + i];
    }
    str[i] = '\0';
}

void removeAspas(char* str) {
    char temp[2048];
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] != '\'' && str[i] != '"') {
            temp[j++] = str[i];
        }
    }
    temp[j] = '\0';
    myStrcpy(str, temp);
}

void removeColchetes(char* str) {
    char temp[2048];
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] != '[' && str[i] != ']') {
            temp[j++] = str[i];
        }
    }
    temp[j] = '\0';
    myStrcpy(str, temp);
}

void removeVirgula(char* str) {
    char temp[256];
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] != ',') {
            temp[j++] = str[i];
        }
    }
    temp[j] = '\0';
    myStrcpy(str, temp);
}

void removeAllNotNumbers(char* str) {
    char temp[256];
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] >= '0' && str[i] <= '9') {
            temp[j++] = str[i];
        }
    }
    temp[j] = '\0';
    if (j == 0) {
        myStrcpy(str, "0");
    } else {
        myStrcpy(str, temp);
    }
}

int isMonth(const char* str) {
    return (myStrcmp(str, "Jan") == 0 || myStrcmp(str, "Feb") == 0 ||
            myStrcmp(str, "Mar") == 0 || myStrcmp(str, "Apr") == 0 ||
            myStrcmp(str, "May") == 0 || myStrcmp(str, "Jun") == 0 ||
            myStrcmp(str, "Jul") == 0 || myStrcmp(str, "Aug") == 0 ||
            myStrcmp(str, "Sep") == 0 || myStrcmp(str, "Oct") == 0 ||
            myStrcmp(str, "Nov") == 0 || myStrcmp(str, "Dec") == 0);
}

void stringToDate(const char* input, char* output) {
    if (input == NULL || input[0] == '\0') {
        myStrcpy(output, "");
        return;
    }

    char str[256];
    myStrcpy(str, input);
    removeAspas(str);
    trim(str);

    char* tokens[3] = {NULL, NULL, NULL};
    int tokenCount = 0;
    char* ptr = str;
    char* start = ptr;

    while (*ptr != '\0' && tokenCount < 3) {
        if (*ptr == ' ') {
            *ptr = '\0';
            tokens[tokenCount++] = start;
            start = ptr + 1;
        }
        ptr++;
    }
    if (tokenCount < 3 && start[0] != '\0') {
        tokens[tokenCount++] = start;
    }

    if (tokenCount == 0) {
        myStrcpy(output, "");
        return;
    }

    char month[3] = "01";
    char day[3] = "01";
    char year[5] = "2000";

    if (myStrcmp(tokens[0], "Jan") == 0) myStrcpy(month, "01");
    else if (myStrcmp(tokens[0], "Feb") == 0) myStrcpy(month, "02");
    else if (myStrcmp(tokens[0], "Mar") == 0) myStrcpy(month, "03");
    else if (myStrcmp(tokens[0], "Apr") == 0) myStrcpy(month, "04");
    else if (myStrcmp(tokens[0], "May") == 0) myStrcpy(month, "05");
    else if (myStrcmp(tokens[0], "Jun") == 0) myStrcpy(month, "06");
    else if (myStrcmp(tokens[0], "Jul") == 0) myStrcpy(month, "07");
    else if (myStrcmp(tokens[0], "Aug") == 0) myStrcpy(month, "08");
    else if (myStrcmp(tokens[0], "Sep") == 0) myStrcpy(month, "09");
    else if (myStrcmp(tokens[0], "Oct") == 0) myStrcpy(month, "10");
    else if (myStrcmp(tokens[0], "Nov") == 0) myStrcpy(month, "11");
    else if (myStrcmp(tokens[0], "Dec") == 0) myStrcpy(month, "12");
    else {
        myStrcpy(day, tokens[0]);
        removeVirgula(day);
        if (day[0] == '\0') myStrcpy(day, "01");
    }

    if (tokenCount >= 2) {
        if (myStrcmp(month, "01") != 0 || isMonth(tokens[0])) {
            if (isMonth(tokens[0])) {
                myStrcpy(day, tokens[1]);
                removeVirgula(day);
                if (day[0] == '\0') myStrcpy(day, "01");
            }
        }
    }

    if (tokenCount >= 3) {
        myStrcpy(year, tokens[2]);
        removeVirgula(year);
    } else if (tokenCount == 2 && myStrcmp(month, "01") != 0) {
        myStrcpy(year, tokens[1]);
        removeVirgula(year);
    }

    if (year[0] == '\0') myStrcpy(year, "2000");

    if (stringToInt(day) < 10 && day[0] != '0' && day[0] != '\0') {
        char temp[3] = "0";
        int len = myStrlen(day);
        for (int i = 0; i < len; i++) {
            temp[i + 1] = day[i];
        }
        temp[len + 1] = '\0';
        myStrcpy(day, temp);
    }

    int i = 0;
    for (int j = 0; day[j] != '\0'; j++) output[i++] = day[j];
    output[i++] = '/';
    for (int j = 0; month[j] != '\0'; j++) output[i++] = month[j];
    output[i++] = '/';
    for (int j = 0; year[j] != '\0'; j++) output[i++] = year[j];
    output[i] = '\0';
}

float stringToFloat(const char* str) {
    if (str == NULL || str[0] == '\0') {
        return 0.0f;
    }

    char temp[256];
    myStrcpy(temp, str);
    trim(temp);

    if (myStrcmp(temp, "Free to Play") == 0) {
        return 0.0f;
    }

    float result = 0.0f;
    int sign = 1;
    int i = 0;

    if (temp[i] == '-') {
        sign = -1;
        i++;
    }

    while (temp[i] >= '0' && temp[i] <= '9') {
        result = result * 10.0f + (temp[i] - '0');
        i++;
    }

    if (temp[i] == '.') {
        i++;
        float divisor = 10.0f;
        while (temp[i] >= '0' && temp[i] <= '9') {
            result = result + (temp[i] - '0') / divisor;
            divisor *= 10.0f;
            i++;
        }
    }

    return result * sign;
}

float validScoreFloat(const char* score) {
    if (score == NULL) return -1.0f;

    char temp[256];
    myStrcpy(temp, score);
    trim(temp);

    if (temp[0] == '\0' || myStrcmp(temp, "tdb") == 0) {
        return -1.0f;
    }

    return stringToFloat(temp);
}

void parseCSVLine(const char* line, char fields[][2048], int* fieldCount) {
    int inQuotes = 0;
    int fieldIndex = 0;
    int charIndex = 0;

    for (int i = 0; line[i] != '\0' && fieldIndex < 20; i++) {
        char c = line[i];

        if (c == '"') {
            inQuotes = !inQuotes;
        } else if (c == ',' && !inQuotes) {
            fields[fieldIndex][charIndex] = '\0';
            fieldIndex++;
            charIndex = 0;
        } else {
            fields[fieldIndex][charIndex++] = c;
        }
    }
    fields[fieldIndex][charIndex] = '\0';
    *fieldCount = fieldIndex + 1;
}

void printArrayField(const char* field) {
    char temp[2048];
    myStrcpy(temp, field);

    int start = 0;
    int first = 1;

    for (int i = 0; i <= myStrlen(temp); i++) {
        if (temp[i] == ',' || temp[i] == '\0') {
            char token[512];
            int idx = 0;
            for (int j = start; j < i; j++) {
                token[idx++] = temp[j];
            }
            token[idx] = '\0';
            trim(token);

            if (!first) {
                printf(", ");
            }
            printf("%s", token);
            first = 0;
            start = i + 1;
        }
    }
}

void printGame(Game* game) {
    printf("=> ");
    printf("%d ## ", game->id);
    printf("%s ## ", game->name);
    printf("%s ## ", game->releaseDate);
    printf("%d ## ", game->estimatedOwners);
    printf("%.2f ## ", game->price);
    printf("[");
    printArrayField(game->supportedLanguage);
    printf("] ## ");
    printf("%d ## ", game->metacriticScore);
    printf("%.1f ## ", game->userScore);
    printf("%d ## ", game->achievements);
    printf("[");
    printArrayField(game->publishers);
    printf("] ## ");
    printf("[");
    printArrayField(game->developers);
    printf("] ## ");
    printf("[");
    printArrayField(game->categories);
    printf("] ## ");
    printf("[");
    printArrayField(game->genres);
    printf("] ## ");
    printf("[");
    printArrayField(game->tags);
    printf("] ##\n");
}

// ==================== ORDENAÇÃO POR SELEÇÃO ====================

/**
 * Ordenação por seleção
 * Ordena por nome, em caso de empate ordena por id
 * Conta comparações e movimentações
 */
void selectionSort(Game* games, int n) {
    for (int i = 0; i < n - 1; i++) {
        int menor = i;

        for (int j = i + 1; j < n; j++) {
            comparacoes++;
            int cmp = myStrcmp(games[j].name, games[menor].name);

            if (cmp < 0 || (cmp == 0 && games[j].id < games[menor].id)) {
                menor = j;
            }
        }

        // Trocar elementos
        if (menor != i) {
            Game tmp = games[i];
            games[i] = games[menor];
            games[menor] = tmp;
            movimentacoes += 3; // 3 movimentações para uma troca
        }
    }
}

// ==================== LEITURA E PROCESSAMENTO ====================

int lerGames(Game* games) {
    int index = 0;
    FILE* arquivo = fopen("/tmp/games.csv", "r");
    if (arquivo == NULL) {
        arquivo = fopen("games.csv", "r");
    }
    if (arquivo == NULL) {
        arquivo = fopen("./games.csv", "r");
    }

    if (arquivo == NULL) {
        fprintf(stderr, "Error reading file: games.csv (No such file or directory)\n");
        return 0;
    }

    char line[8192];

    // Skip header
    if (fgets(line, sizeof(line), arquivo) != NULL) {
    }

    while (fgets(line, sizeof(line), arquivo) != NULL) {
        int len = myStrlen(line);
        if (len > 0 && line[len - 1] == '\n') {
            line[len - 1] = '\0';
        }

        char fields[20][2048];
        int fieldCount;
        parseCSVLine(line, fields, &fieldCount);

        if (fieldCount < 14) {
            continue;
        }

        Game game;
        game.id = stringToInt(fields[0]);
        myStrcpy(game.name, fields[1]);
        stringToDate(fields[2], game.releaseDate);

        char temp[256];
        myStrcpy(temp, fields[3]);
        removeAllNotNumbers(temp);
        game.estimatedOwners = stringToInt(temp);

        game.price = stringToFloat(fields[4]);

        myStrcpy(game.supportedLanguage, fields[5]);
        removeAspas(game.supportedLanguage);
        removeColchetes(game.supportedLanguage);

        game.metacriticScore = stringToInt(fields[6]);
        game.userScore = validScoreFloat(fields[7]);
        game.achievements = stringToInt(fields[8]);

        myStrcpy(game.publishers, fields[9]);
        removeAspas(game.publishers);

        myStrcpy(game.developers, fields[10]);
        removeAspas(game.developers);

        myStrcpy(game.categories, fields[11]);
        removeAspas(game.categories);

        myStrcpy(game.genres, fields[12]);
        removeAspas(game.genres);

        myStrcpy(game.tags, fields[13]);
        removeAspas(game.tags);

        games[index++] = game;
    }
    fclose(arquivo);
    return index;
}

int lerEntrada(Game* gamesSelecionados, Game* todosGames, int totalGames) {
    char entrada[256];
    int index = 0;

    while (1) {
        if (fgets(entrada, sizeof(entrada), stdin) == NULL) {
            break;
        }

        int len = myStrlen(entrada);
        if (len > 0 && entrada[len - 1] == '\n') {
            entrada[len - 1] = '\0';
        }

        if (myStrcmp(entrada, "FIM") == 0) {
            break;
        }

        int idBusca = stringToInt(entrada);
        if (idBusca != 0) {
            for (int i = 0; i < totalGames; i++) {
                if (todosGames[i].id == idBusca) {
                    gamesSelecionados[index++] = todosGames[i];
                    break;
                }
            }
        }
    }
    return index;
}

void escreverLog(const char* matricula, int comparacoes, int movimentacoes, double tempo) {
    char filename[100];
    sprintf(filename, "%s_selecao.txt", matricula);

    FILE* log = fopen(filename, "w");
    if (log != NULL) {
        fprintf(log, "%s\t%d\t%d\t%.2f", matricula, comparacoes, movimentacoes, tempo);
        fclose(log);
    }
}

int main() {
    const char* matricula = "SUAMATRICULA"; // Substituir pela sua matrícula

    // Alocar arrays
    Game* todosGames = (Game*)malloc(100000 * sizeof(Game));
    Game* gamesSelecionados = (Game*)malloc(100000 * sizeof(Game));

    // Ler todos os jogos do CSV
    int totalGames = lerGames(todosGames);

    // Ler entrada do usuário
    int numSelecionados = lerEntrada(gamesSelecionados, todosGames, totalGames);

    // Iniciar contagem de tempo
    clock_t inicio = clock();

    // Ordenar por seleção
    selectionSort(gamesSelecionados, numSelecionados);

    // Fim da contagem de tempo
    clock_t fim = clock();
    double tempoExecucao = ((double)(fim - inicio)) / CLOCKS_PER_SEC * 1000.0; // em milissegundos

    // Imprimir jogos ordenados
    for (int i = 0; i < numSelecionados; i++) {
        printGame(&gamesSelecionados[i]);
    }

    // Escrever arquivo de log
    escreverLog(matricula, comparacoes, movimentacoes, tempoExecucao);

    // Liberar memória
    free(todosGames);
    free(gamesSelecionados);

    return 0;
}
