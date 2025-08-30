#include <stdbool.h>
#include <stdio.h>
#include <string.h>

int contaM(char string[]);

int main() {
  char string[100];
  bool continuar = true;

  do {
    scanf(" %[^\n]", string);
    if (strcmp(string, "FIM") == 0) {
      continuar = false;
    } else {
      contaM(string);
    }
  } while (continuar);

  return 0;
}

int contaM(char string[]) {
  int count = 0;
  for (int i = 0; i < strlen(string); i++) {
    if (string[i] >= 'A' && string[i] <= 'Z') {
      count++;
    }
  }
  printf("%i\n", count);
  return count;
}
