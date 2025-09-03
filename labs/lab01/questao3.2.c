#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define MAX_LINE 1000

bool isVogal(char line[]);
bool isConsoante(char line[]);
bool isInteiro(char line[]);
bool isReal(char line[]);

int main(void) {
    char line[MAX_LINE];
    bool continuar = true;

    while (continuar) {
        scanf(" %[^\n]", line);
        if (strcmp(line, "FIM") == 0) {
            continuar = false;
        } else {
            const char *x1 = isVogal(line) ? "SIM" : "NAO";
            const char *x2 = isConsoante(line) ? "SIM" : "NAO";
            const char *x3 = isInteiro(line) ? "SIM" : "NAO";
            const char *x4 = isReal(line) ? "SIM" : "NAO";

            printf("%s %s %s %s\n", x1, x2, x3, x4);
        }
    }

    return 0;
}

bool isVogal(char line[]) {
    int len = strlen(line);
    if (len == 0) {
        return false;
    }
    for (int i = 0; i < len; i++) {
        char c = tolower(line[i]);
        if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
            return false;
        }
    }
    return true;
}

bool isConsoante(char line[]) {
    int len = strlen(line);
    if (len == 0) {
        return false;
    }
    for (int i = 0; i < len; i++) {
        char c = tolower(line[i]);
        bool isLetra = (c >= 'a' && c <= 'z');
        bool isVogal = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
        if (!isLetra || isVogal) {
            return false;
        }
    }
    return true;
}

bool isInteiro(char line[]) {
    int len = strlen(line);
    if (len == 0) {
        return false;
    }
    for (int i = 0; i < len; i++) {
        if (!isdigit(line[i])) {
            return false;
        }
    }
    return true;
}

bool isReal(char line[]) {
    int len = strlen(line);
    if (len == 0) {
        return false;
    }

    int countSeparadores = 0;
    for (int i = 0; i < len; i++) {
        if (line[i] == '.' || line[i] == ',') {
            countSeparadores++;
        } else if (!isdigit(line[i])) {
            return false;
        }
    }
    return countSeparadores <= 1;
}