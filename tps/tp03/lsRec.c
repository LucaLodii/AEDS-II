#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define MAX_LINE 1000

int main(void);
bool isVogal(char line[], int i, int size);
bool isConsoante(char line[], int i, int size);
bool isInteiro(char line[], int i, int size);
bool isReal(char line[], int i, int size);

int main(void)
{
    char line[MAX_LINE];

    while (fgets(line, MAX_LINE, stdin) != NULL)
    {
        line[strcspn(line, "\n")] = '\0';

        if (strcmp(line, "FIM") == 0)
        {
            break;
        }

        int i = 0;
        int size = strlen(line) - 1;

        const char *x1 = isVogal(line, 0, size) ? "SIM" : "NAO";
        const char *x2 = isConsoante(line, 0, size) ? "SIM" : "NAO";
        const char *x3 = isInteiro(line, 0, size) ? "SIM" : "NAO";
        const char *x4 = isReal(line, 0, size) ? "SIM" : "NAO";

        printf("%s %s %s %s\n", x1, x2, x3, x4);
    }

    return 0;
}

bool isVogal(char line[], int i, int size)
{
    if (i <= size)
    {
        if (tolower(line[i]) != 'a' && tolower(line[i]) != 'e' && tolower(line[i]) != 'i' && tolower(line[i]) != 'o' && tolower(line[i]) != 'u')
            return false;
        else
        {
            return isVogal(line, i + 1, size);
        }
    }
    return true;
}

bool isConsoante(char line[], int i, int size)
{
    if (i <= size)
    {
        if (tolower(line[i]) == 'a' || tolower(line[i]) == 'e' || tolower(line[i]) == 'i' || tolower(line[i]) == 'o' || tolower(line[i]) == 'u')
            return false;
        else if (!isalpha(line[i]))
            return false;
        else
        {
            return isConsoante(line, i + 1, size);
        }
    }
    return true;
}

bool isInteiro(char line[], int i, int size)
{
    if(i <= size)
    {
        if (isdigit(line[i]))
        {
            return isInteiro(line, i + 1, size);
        }
        else return false;
    }
    return true;
}

bool isReal(char line[], int i, int size)
{
    static int countSeparadores = 0;
    
    if (i == 0) countSeparadores = 0; // Reset counter for new string
    
    if (i <= size)
    {
        if (line[i] == '.' || line[i] == ',') {
            countSeparadores++;
            if (countSeparadores > 1) return false;
            return isReal(line, i + 1, size);
        }
        else if (isdigit(line[i]))
        {
            return isReal(line, i + 1, size);
        }
        else
        {
            return false;
        }
    }
    return countSeparadores <= 1;
}