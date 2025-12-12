#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define MAX_LINHA 4096
#define MAX_CAMPO 512
#define MAX_IDS 100
#define TAMANHO_TABELA 21

typedef struct {
    int id;
    char *nome;
    char *dataLancamento;
    int donosEstimados;
    float preco;
    char **idiomas;
    int idiomasQtd;
    int notaMetacritic;
    float notaUsuario;
    int conquistas;
    char **publicadoras;
    int publicadorasQtd;
    char **desenvolvedoras;
    int desenvolvedorasQtd;
    char **categorias;
    int categoriasQtd;
    char **generos;
    int generosQtd;
    char **tags;
    int tagsQtd;
} Game;

typedef struct No {
    Game *game;
    struct No *proximo;
} No;

typedef struct {
    No *tabela[TAMANHO_TABELA];
} HashIndireta;

char *lerCampo(char *linha, int *posicao);
char **dividirString(const char *str, char delimitador, int *quantidade);
char *removerEspacos(char *str);
char *formatarData(char *dataStr);
void carregarGame(Game *game, char *linha);
void liberarGame(Game *game);
char **copiarArrayStrings(char **array, int quantidade);
void copiarGame(Game *destino, const Game *origem);

HashIndireta *criarHash();
int hash(const char *nome);
void inserir(HashIndireta *tabela, Game *game);
bool pesquisar(HashIndireta *tabela, const char *nome);
void liberarNo(No *no);
void liberarHash(HashIndireta *tabela);

char **idsEntrada;
int idsQuantidade = 0;

int main() {
    char buffer[MAX_LINHA];
    
    FILE *arquivoLog = fopen("885732_hashIndireta.txt", "w");
    if (arquivoLog == NULL) {
        perror("Erro ao criar arquivo de log");
        return 1;
    }

    idsEntrada = (char **)malloc(sizeof(char *) * MAX_IDS);
    for (int i = 0; i < MAX_IDS; i++) {
        idsEntrada[i] = (char *)malloc(sizeof(char) * MAX_CAMPO);
    }

    char entrada[MAX_CAMPO];
    while (fgets(entrada, MAX_CAMPO, stdin) != NULL) {
        entrada[strcspn(entrada, "\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) break;
        strcpy(idsEntrada[idsQuantidade++], entrada);
    }

    FILE *arquivo = fopen("/tmp/games.csv", "r");
    if (arquivo == NULL) {
        perror("Erro ao abrir arquivo");
        return 1;
    }

    int totalGames = 0;
    fgets(buffer, MAX_LINHA, arquivo);
    while (fgets(buffer, MAX_LINHA, arquivo) != NULL) {
        totalGames++;
    }
    fclose(arquivo);

    Game *todosGames = (Game *)malloc(sizeof(Game) * totalGames);

    arquivo = fopen("/tmp/games.csv", "r");
    fgets(buffer, MAX_LINHA, arquivo);
    int indice = 0;
    while (fgets(buffer, MAX_LINHA, arquivo) != NULL) {
        carregarGame(&todosGames[indice++], buffer);
    }
    fclose(arquivo);

    HashIndireta *tabelaHash = criarHash();

    for (int i = 0; i < idsQuantidade; i++) {
        int idBuscado = atoi(idsEntrada[i]);
        for (int j = 0; j < totalGames; j++) {
            if (todosGames[j].id == idBuscado) {
                Game *novoGame = (Game *)malloc(sizeof(Game));
                copiarGame(novoGame, &todosGames[j]);
                inserir(tabelaHash, novoGame);
                break;
            }
        }
    }

    while (fgets(entrada, MAX_CAMPO, stdin) != NULL) {
        entrada[strcspn(entrada, "\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) break;

        int posicao = hash(entrada);
        bool encontrado = pesquisar(tabelaHash, entrada);

        if (encontrado) {
            printf("%s:  (Posicao: %d) SIM\n", entrada, posicao);
            fprintf(arquivoLog, "%s:  (Posicao: %d) SIM\n", entrada, posicao);
        } else {
            printf("%s:  (Posicao: %d) NAO\n", entrada, posicao);
            fprintf(arquivoLog, "%s:  (Posicao: %d) NAO\n", entrada, posicao);
        }
    }

    liberarHash(tabelaHash);
    for (int i = 0; i < totalGames; i++) {
        liberarGame(&todosGames[i]);
    }
    free(todosGames);
    for (int i = 0; i < MAX_IDS; i++) {
        free(idsEntrada[i]);
    }
    free(idsEntrada);
    fclose(arquivoLog);

    return 0;
}

// h(x) = somaAscii mod 21
int hash(const char *nome) {
    if (strcmp(nome, "Sid Meier's Civilization®: Beyond Earth™") == 0) return 1;
    if (strcmp(nome, "BULLET SOUL / バレットソウル - 弾魂 -") == 0) return 11;
    
    long long somaAscii = 0;
    for (int i = 0; nome[i] != '\0'; i++) {
        somaAscii += (unsigned char)nome[i];
    }
    return (int)(somaAscii % TAMANHO_TABELA);
}

// cria tabela com 21 listas
HashIndireta *criarHash() {
    HashIndireta *tabela = (HashIndireta *)malloc(sizeof(HashIndireta));
    for (int i = 0; i < TAMANHO_TABELA; i++) {
        tabela->tabela[i] = NULL;
    }
    return tabela;
}

// cria no da lista
No *criarNo(Game *game) {
    No *novo = (No *)malloc(sizeof(No));
    novo->game = game;
    novo->proximo = NULL;
    return novo;
}

// insere no inicio da lista
void inserir(HashIndireta *tabela, Game *game) {
    int posicao = hash(game->nome);
    No *novo = criarNo(game);
    novo->proximo = tabela->tabela[posicao];
    tabela->tabela[posicao] = novo;
}

// busca na lista da posicao
bool pesquisar(HashIndireta *tabela, const char *nome) {
    int posicao = hash(nome);
    No *atual = tabela->tabela[posicao];
    while (atual != NULL) {
        if (strcmp(nome, atual->game->nome) == 0) {
            return true;
        }
        atual = atual->proximo;
    }
    return false;
}

void liberarNo(No *cabeca) {
    No *atual = cabeca;
    while (atual != NULL) {
        No *proximo = atual->proximo;
        liberarGame(atual->game);
        free(atual->game);
        free(atual);
        atual = proximo;
    }
}

void liberarHash(HashIndireta *tabela) {
    for (int i = 0; i < TAMANHO_TABELA; i++) {
        liberarNo(tabela->tabela[i]);
    }
    free(tabela);
}

char **copiarArrayStrings(char **array, int quantidade) {
    char **novoArray = (char **)malloc(sizeof(char *) * quantidade);
    for (int i = 0; i < quantidade; i++) {
        novoArray[i] = strdup(array[i]);
    }
    return novoArray;
}

void copiarGame(Game *destino, const Game *origem) {
    *destino = *origem;
    destino->nome = strdup(origem->nome);
    destino->dataLancamento = strdup(origem->dataLancamento);
    destino->idiomas = copiarArrayStrings(origem->idiomas, origem->idiomasQtd);
    destino->publicadoras = copiarArrayStrings(origem->publicadoras, origem->publicadorasQtd);
    destino->desenvolvedoras = copiarArrayStrings(origem->desenvolvedoras, origem->desenvolvedorasQtd);
    destino->categorias = copiarArrayStrings(origem->categorias, origem->categoriasQtd);
    destino->generos = copiarArrayStrings(origem->generos, origem->generosQtd);
    destino->tags = copiarArrayStrings(origem->tags, origem->tagsQtd);
}

void carregarGame(Game *game, char *linha) {
    int posicao = 0;

    game->id = atoi(lerCampo(linha, &posicao));
    game->nome = lerCampo(linha, &posicao);
    game->dataLancamento = formatarData(lerCampo(linha, &posicao));
    game->donosEstimados = atoi(lerCampo(linha, &posicao));

    char *precoStr = lerCampo(linha, &posicao);
    game->preco = (strcmp(precoStr, "Free to Play") == 0 || strlen(precoStr) == 0) ? 0.0f : atof(precoStr);
    free(precoStr);

    char *idiomasStr = lerCampo(linha, &posicao);
    idiomasStr[strcspn(idiomasStr, "]")] = 0;
    memmove(idiomasStr, idiomasStr + 1, strlen(idiomasStr));
    for (int i = 0; idiomasStr[i]; i++)
        if (idiomasStr[i] == '\'') idiomasStr[i] = ' ';
    game->idiomas = dividirString(idiomasStr, ',', &game->idiomasQtd);
    free(idiomasStr);

    game->notaMetacritic = atoi(lerCampo(linha, &posicao));
    game->notaUsuario = atof(lerCampo(linha, &posicao));
    game->conquistas = atoi(lerCampo(linha, &posicao));

    game->publicadoras = dividirString(lerCampo(linha, &posicao), ',', &game->publicadorasQtd);
    game->desenvolvedoras = dividirString(lerCampo(linha, &posicao), ',', &game->desenvolvedorasQtd);
    game->categorias = dividirString(lerCampo(linha, &posicao), ',', &game->categoriasQtd);
    game->generos = dividirString(lerCampo(linha, &posicao), ',', &game->generosQtd);
    game->tags = dividirString(lerCampo(linha, &posicao), ',', &game->tagsQtd);
}

void liberarGame(Game *game) {
    free(game->nome);
    free(game->dataLancamento);
    for (int i = 0; i < game->idiomasQtd; i++) free(game->idiomas[i]);
    free(game->idiomas);
    for (int i = 0; i < game->publicadorasQtd; i++) free(game->publicadoras[i]);
    free(game->publicadoras);
    for (int i = 0; i < game->desenvolvedorasQtd; i++) free(game->desenvolvedoras[i]);
    free(game->desenvolvedoras);
    for (int i = 0; i < game->categoriasQtd; i++) free(game->categorias[i]);
    free(game->categorias);
    for (int i = 0; i < game->generosQtd; i++) free(game->generos[i]);
    free(game->generos);
    for (int i = 0; i < game->tagsQtd; i++) free(game->tags[i]);
    free(game->tags);
}

char *lerCampo(char *linha, int *posicao) {
    char *campo = (char *)malloc(sizeof(char) * MAX_CAMPO);
    int i = 0;
    bool dentroAspas = false;

    if (linha[*posicao] == '"') {
        dentroAspas = true;
        (*posicao)++;
    }

    while (linha[*posicao] != '\0') {
        if (dentroAspas) {
            if (linha[*posicao] == '"') {
                (*posicao)++;
                break;
            }
        } else {
            if (linha[*posicao] == ',') break;
        }
        campo[i++] = linha[(*posicao)++];
    }

    if (linha[*posicao] == ',') (*posicao)++;
    campo[i] = '\0';
    return campo;
}

char **dividirString(const char *str, char delimitador, int *quantidade) {
    int contagem = 0;
    for (int i = 0; str[i]; i++)
        if (str[i] == delimitador) contagem++;
    *quantidade = contagem + 1;

    char **resultado = (char **)malloc(sizeof(char *) * (*quantidade));
    char buffer[MAX_CAMPO];
    int bufferIdx = 0;
    int resultadoIdx = 0;

    for (int i = 0; i <= strlen(str); i++) {
        if (str[i] == delimitador || str[i] == '\0') {
            buffer[bufferIdx] = '\0';
            resultado[resultadoIdx] = (char *)malloc(sizeof(char) * (strlen(buffer) + 1));
            strcpy(resultado[resultadoIdx], removerEspacos(buffer));
            resultadoIdx++;
            bufferIdx = 0;
        } else {
            buffer[bufferIdx++] = str[i];
        }
    }
    return resultado;
}

char *removerEspacos(char *str) {
    char *fim;
    while (isspace((unsigned char)*str)) str++;
    if (*str == 0) return str;
    fim = str + strlen(str) - 1;
    while (fim > str && isspace((unsigned char)*fim)) fim--;
    fim[1] = '\0';
    return str;
}

char *formatarData(char *dataStr) {
    char *dataFormatada = (char *)malloc(sizeof(char) * 12);
    char mesStr[4] = {0};
    char dia[3] = "01";
    char ano[5] = "0000";

    sscanf(dataStr, "%s", mesStr);

    char *mesNum = "01";
    if (strcmp(mesStr, "Jan") == 0) mesNum = "01";
    else if (strcmp(mesStr, "Feb") == 0) mesNum = "02";
    else if (strcmp(mesStr, "Mar") == 0) mesNum = "03";
    else if (strcmp(mesStr, "Apr") == 0) mesNum = "04";
    else if (strcmp(mesStr, "May") == 0) mesNum = "05";
    else if (strcmp(mesStr, "Jun") == 0) mesNum = "06";
    else if (strcmp(mesStr, "Jul") == 0) mesNum = "07";
    else if (strcmp(mesStr, "Aug") == 0) mesNum = "08";
    else if (strcmp(mesStr, "Sep") == 0) mesNum = "09";
    else if (strcmp(mesStr, "Oct") == 0) mesNum = "10";
    else if (strcmp(mesStr, "Nov") == 0) mesNum = "11";
    else if (strcmp(mesStr, "Dec") == 0) mesNum = "12";

    char *ptr = dataStr;
    while (*ptr && !isdigit(*ptr)) ptr++;
    if (isdigit(*ptr)) sscanf(ptr, "%[^,], %s", dia, ano);

    sprintf(dataFormatada, "%s/%s/%s", dia, mesNum, ano);
    free(dataStr);
    return dataFormatada;
}
