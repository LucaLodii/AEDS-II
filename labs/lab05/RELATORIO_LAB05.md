# Relatório - Lab05: Implementação do QuickSort com Variação na Escolha do Pivô

## Objetivo

Implementar o algoritmo QuickSort utilizando diferentes estratégias de escolha do pivô e analisar o desempenho de cada estratégia em diferentes cenários de dados.

## Estratégias Implementadas

### 1. Primeiro Elemento
- **Estratégia**: O pivô é sempre o primeiro elemento do subarray
- **Implementação**: `int pivo = array[esq];`

### 2. Último Elemento
- **Estratégia**: O pivô é sempre o último elemento do subarray
- **Implementação**: `int pivo = array[dir];`

### 3. Pivô Aleatório
- **Estratégia**: O pivô é selecionado aleatoriamente entre os elementos do subarray
- **Implementação**: `int indicePivo = esq + rand.nextInt(dir - esq + 1);`

### 4. Mediana de Três
- **Estratégia**: O pivô é a mediana entre o primeiro, meio e último elementos
- **Implementação**: Função que compara os três valores e retorna a mediana

## Análise de Desempenho

### Metodologia
- **Tamanhos testados**: 100, 1.000 e 10.000 elementos
- **Tipos de dados**: Arrays aleatórios, crescentes e decrescentes
- **Métrica**: Tempo de execução em milissegundos

### Resultados Obtidos

#### Arrays de 1.000 elementos:

| Estratégia | Aleatório | Crescente | Decrescente |
|------------|-----------|-----------|-------------|
| **Primeiro** | 0.24 ms | 1.44 ms | 1.33 ms |
| **Último** | 0.14 ms | 0.95 ms | 1.31 ms |
| **Aleatório** | 0.37 ms | 0.09 ms | 0.10 ms |
| **Mediana** | 0.33 ms | 0.05 ms | 0.03 ms |

#### Arrays de 10.000 elementos:

| Estratégia | Aleatório | Crescente | Decrescente |
|------------|-----------|-----------|-------------|
| **Primeiro** | 0.82 ms | 21.46 ms | 19.22 ms |
| **Último** | 0.83 ms | 21.12 ms | 21.41 ms |
| **Aleatório** | 0.88 ms | 0.37 ms | 0.28 ms |
| **Mediana** | 0.86 ms | 0.09 ms | 0.07 ms |

## Análise dos Resultados

### Arrays Aleatórios
- **Melhor**: Primeiro/Último elemento
- **Motivo**: Menor overhead, boa distribuição natural dos dados
- **Diferença**: ~2-3x mais rápidos que Aleatório/Mediana

### Arrays Ordenados (Crescentes/Decrescentes)
- **Melhor**: Mediana de três
- **Motivo**: Evita o pior caso O(n²) do QuickSort
- **Diferença**: ~200-400x mais rápida que Primeiro/Último

### Estratégia Mais Robusta
- **Mediana de três** apresenta o melhor desempenho geral
- Performance consistente em todos os cenários
- Evita tanto o pior caso quanto o overhead da aleatoriedade

## Conclusões

1. **Para dados aleatórios**: Estratégias simples (Primeiro/Último) são mais eficientes
2. **Para dados ordenados**: Estratégias inteligentes (Aleatório/Mediana) são essenciais
3. **Estratégia recomendada**: **Mediana de três** para uso geral
4. **Complexidade**: Todas as estratégias mantêm O(n log n) no caso médio, mas Primeiro/Último podem degradar para O(n²)

---
