#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_GAMES 10000
#define MAX_LINE 10000
#define MAX_FIELD 1000
#define MAX_ARRAY 100

typedef struct {
    int id;
    char name[500];
    char releaseDate[20];
    int estimatedOwners;
    float price;
    char supportedLanguage[MAX_ARRAY][200];
    int supportedLanguageCount;
    int metacriticScore;
    float userScore;
    int achievements;
    char publishers[MAX_ARRAY][200];
    int publishersCount;
    char developers[MAX_ARRAY][200];
    int developersCount;
    char categories[MAX_ARRAY][200];
    int categoriesCount;
    char genres[MAX_ARRAY][200];
    int genresCount;
    char tags[MAX_ARRAY][200];
    int tagsCount;
} Game;

// Utility functions
int stringToInt(const char* str) {
    if (str == NULL || strlen(str) == 0) return 0;
    int numero = 0;
    int i = 0;
    while (str[i] != '\0' && isdigit(str[i])) {
        numero = numero * 10 + (str[i] - '0');
        i++;
    }
    return numero;
}

void removeAspas(char* dest, const char* str) {
    if (str == NULL) {
        dest[0] = '\0';
        return;
    }
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] != '\'' && str[i] != '"') {
            dest[j++] = str[i];
        }
    }
    dest[j] = '\0';
}

void removeColchetes(char* dest, const char* str) {
    if (str == NULL) {
        dest[0] = '\0';
        return;
    }
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] != '[' && str[i] != ']') {
            dest[j++] = str[i];
        }
    }
    dest[j] = '\0';
}

void removeVirgula(char* dest, const char* str) {
    if (str == NULL) {
        dest[0] = '\0';
        return;
    }
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (str[i] != ',') {
            dest[j++] = str[i];
        }
    }
    dest[j] = '\0';
}

void removeAllNotNumbers(char* dest, const char* str) {
    if (str == NULL) {
        strcpy(dest, "0");
        return;
    }
    int j = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        if (isdigit(str[i])) {
            dest[j++] = str[i];
        }
    }
    dest[j] = '\0';
    if (j == 0) {
        strcpy(dest, "0");
    }
}

void trim(char* str) {
    if (str == NULL) return;
    
    // Trim leading spaces
    int start = 0;
    while (str[start] != '\0' && isspace(str[start])) {
        start++;
    }
    
    // Trim trailing spaces
    int end = strlen(str) - 1;
    while (end >= 0 && isspace(str[end])) {
        end--;
    }
    
    // Move string to beginning
    int j = 0;
    for (int i = start; i <= end; i++) {
        str[j++] = str[i];
    }
    str[j] = '\0';
}

void stringToDate(char* dest, const char* str) {
    if (str == NULL || strlen(str) == 0) {
        strcpy(dest, "");
        return;
    }
    
    char clean[500];
    removeAspas(clean, str);
    trim(clean);
    
    char date[3][50];
    int partCount = 0;
    char* token = strtok(clean, " ");
    while (token != NULL && partCount < 3) {
        strcpy(date[partCount++], token);
        token = strtok(NULL, " ");
    }
    
    if (partCount == 0) {
        strcpy(dest, "");
        return;
    }
    
    char month[3] = "01";
    char day[3] = "01";
    char year[5] = "2000";
    
    // Parse month
    if (strcmp(date[0], "Jan") == 0) strcpy(month, "01");
    else if (strcmp(date[0], "Feb") == 0) strcpy(month, "02");
    else if (strcmp(date[0], "Mar") == 0) strcpy(month, "03");
    else if (strcmp(date[0], "Apr") == 0) strcpy(month, "04");
    else if (strcmp(date[0], "May") == 0) strcpy(month, "05");
    else if (strcmp(date[0], "Jun") == 0) strcpy(month, "06");
    else if (strcmp(date[0], "Jul") == 0) strcpy(month, "07");
    else if (strcmp(date[0], "Aug") == 0) strcpy(month, "08");
    else if (strcmp(date[0], "Sep") == 0) strcpy(month, "09");
    else if (strcmp(date[0], "Oct") == 0) strcpy(month, "10");
    else if (strcmp(date[0], "Nov") == 0) strcpy(month, "11");
    else if (strcmp(date[0], "Dec") == 0) strcpy(month, "12");
    else {
        // If first part is not a month, treat as day
        char dayTemp[50];
        removeVirgula(dayTemp, date[0]);
        if (strlen(dayTemp) > 0) {
            int dayInt = stringToInt(dayTemp);
            sprintf(day, "%02d", dayInt);
        }
    }
    
    // Parse day and year
    if (partCount >= 2) {
        if (strcmp(month, "01") != 0) {
            char dayTemp[50];
            removeVirgula(dayTemp, date[1]);
            if (strlen(dayTemp) > 0) {
                int dayInt = stringToInt(dayTemp);
                sprintf(day, "%02d", dayInt);
            }
        }
    }
    
    if (partCount >= 3) {
        char yearTemp[50];
        removeVirgula(yearTemp, date[2]);
        if (strlen(yearTemp) > 0) {
            strcpy(year, yearTemp);
        }
    } else if (partCount == 2 && strcmp(month, "01") != 0) {
        char yearTemp[50];
        removeVirgula(yearTemp, date[1]);
        if (strlen(yearTemp) > 0) {
            strcpy(year, yearTemp);
        }
    }
    
    sprintf(dest, "%s/%s/%s", day, month, year);
}

float stringToFloat(const char* str) {
    if (str == NULL || strlen(str) == 0) {
        return 0.0f;
    }
    
    char temp[100];
    strcpy(temp, str);
    trim(temp);
    
    if (strcmp(temp, "Free to Play") == 0) {
        return 0.0f;
    }
    
    return atof(temp);
}

int validScore(int score) {
    return score;
}

float validScoreFloat(const char* score) {
    if (score == NULL || strlen(score) == 0 || strcmp(score, "tdb") == 0) {
        return -1.0f;
    }
    return stringToFloat(score);
}

int parseCSVLine(char* line, char fields[][MAX_FIELD]) {
    int fieldCount = 0;
    int inQuotes = 0;
    int fieldPos = 0;
    
    for (int i = 0; line[i] != '\0' && fieldCount < MAX_ARRAY; i++) {
        char c = line[i];
        
        if (c == '"') {
            inQuotes = !inQuotes;
        } else if (c == ',' && !inQuotes) {
            fields[fieldCount][fieldPos] = '\0';
            fieldCount++;
            fieldPos = 0;
        } else {
            fields[fieldCount][fieldPos++] = c;
        }
    }
    fields[fieldCount][fieldPos] = '\0';
    fieldCount++;
    
    return fieldCount;
}

void splitString(const char* str, char delimiter, char result[][200], int* count) {
    *count = 0;
    if (str == NULL || strlen(str) == 0) {
        return;
    }
    
    char temp[MAX_FIELD];
    strcpy(temp, str);
    
    char* token = strtok(temp, &delimiter);
    while (token != NULL && *count < MAX_ARRAY) {
        // Trim the token
        char trimmed[200];
        strcpy(trimmed, token);
        trim(trimmed);
        
        // Only add non-empty tokens
        if (strlen(trimmed) > 0) {
            strcpy(result[*count], trimmed);
            (*count)++;
        }
        token = strtok(NULL, &delimiter);
    }
}

void printGame(Game* game) {
    printf("=> ");
    printf("%d ## ", game->id);
    printf("%s ## ", game->name);
    printf("%s ## ", game->releaseDate);
    printf("%d ## ", game->estimatedOwners);
    printf("%.1f ## ", game->price);
    printf("[");
    for (int i = 0; i < game->supportedLanguageCount; i++) {
        char trimmed[200];
        strcpy(trimmed, game->supportedLanguage[i]);
        trim(trimmed);
        if (i == game->supportedLanguageCount - 1) {
            printf("%s", trimmed);
        } else {
            printf("%s, ", trimmed);
        }
    }
    printf("] ## ");
    printf("%d ## ", game->metacriticScore);
    printf("%.1f ## ", game->userScore);
    printf("%d ## ", game->achievements);
    printf("[");
    for (int i = 0; i < game->publishersCount; i++) {
        char trimmed[200];
        strcpy(trimmed, game->publishers[i]);
        trim(trimmed);
        if (i == game->publishersCount - 1) {
            printf("%s", trimmed);
        } else {
            printf("%s, ", trimmed);
        }
    }
    printf("] ## ");
    printf("[");
    for (int i = 0; i < game->developersCount; i++) {
        char trimmed[200];
        strcpy(trimmed, game->developers[i]);
        trim(trimmed);
        if (i == game->developersCount - 1) {
            printf("%s", trimmed);
        } else {
            printf("%s, ", trimmed);
        }
    }
    printf("] ## ");
    printf("[");
    for (int i = 0; i < game->categoriesCount; i++) {
        char trimmed[200];
        strcpy(trimmed, game->categories[i]);
        trim(trimmed);
        if (i == game->categoriesCount - 1) {
            printf("%s", trimmed);
        } else {
            printf("%s, ", trimmed);
        }
    }
    printf("] ## ");
    printf("[");
    for (int i = 0; i < game->genresCount; i++) {
        char trimmed[200];
        strcpy(trimmed, game->genres[i]);
        trim(trimmed);
        if (i == game->genresCount - 1) {
            printf("%s", trimmed);
        } else {
            printf("%s, ", trimmed);
        }
    }
    printf("] ## ");
    printf("[");
    for (int i = 0; i < game->tagsCount; i++) {
        char trimmed[200];
        strcpy(trimmed, game->tags[i]);
        trim(trimmed);
        if (i == game->tagsCount - 1) {
            printf("%s", trimmed);
        } else {
            printf("%s, ", trimmed);
        }
    }
    printf("] ##");
    printf("\n");
}

void searchGame(Game* games, int index) {
    char idSearch[100];
    
    while (1) {
        if (fgets(idSearch, sizeof(idSearch), stdin) == NULL) {
            break;
        }
        
        // Remove newline
        idSearch[strcspn(idSearch, "\n")] = '\0';
        
        if (strcmp(idSearch, "FIM") == 0) {
            break;
        }
        
        int idSearchInt = stringToInt(idSearch);
        for (int i = 0; i < index; i++) {
            if (games[i].id == idSearchInt && idSearchInt != 0) {
                printGame(&games[i]);
                break;
            }
        }
    }
}

int main() {
    Game* games = (Game*)malloc(MAX_GAMES * sizeof(Game));
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
    } else {
        char line[MAX_LINE];
        
        // Skip header line
        if (fgets(line, sizeof(line), arquivo) != NULL) {
            // Header skipped
        }
        
        while (fgets(line, sizeof(line), arquivo) != NULL && index < MAX_GAMES) {
            // Remove newline
            line[strcspn(line, "\n")] = '\0';
            
            char fields[MAX_ARRAY][MAX_FIELD];
            int fieldCount = parseCSVLine(line, fields);
            
            if (fieldCount < 14) {
                fprintf(stderr, "Skipping malformed line with %d fields\n", fieldCount);
                continue;
            }
            
            Game game;
            game.id = stringToInt(fields[0]);
            strcpy(game.name, fields[1]);
            stringToDate(game.releaseDate, fields[2]);
            
            char temp[MAX_FIELD];
            removeAllNotNumbers(temp, fields[3]);
            game.estimatedOwners = stringToInt(temp);
            
            game.price = stringToFloat(fields[4]);
            
            char langTemp[MAX_FIELD], langTemp2[MAX_FIELD];
            removeAspas(langTemp, fields[5]);
            removeColchetes(langTemp2, langTemp);
            splitString(langTemp2, ',', game.supportedLanguage, &game.supportedLanguageCount);
            
            game.metacriticScore = validScore(stringToInt(fields[6]));
            game.userScore = validScoreFloat(fields[7]);
            game.achievements = stringToInt(fields[8]);
            
            removeAspas(temp, fields[9]);
            splitString(temp, ',', game.publishers, &game.publishersCount);
            
            removeAspas(temp, fields[10]);
            splitString(temp, ',', game.developers, &game.developersCount);
            
            removeAspas(temp, fields[11]);
            splitString(temp, ',', game.categories, &game.categoriesCount);
            
            removeAspas(temp, fields[12]);
            splitString(temp, ',', game.genres, &game.genresCount);
            
            removeAspas(temp, fields[13]);
            splitString(temp, ',', game.tags, &game.tagsCount);
            
            games[index] = game;
            index++;
        }
        
        fclose(arquivo);
    }
    
    searchGame(games, index);
    
    free(games);
    return 0;
}

