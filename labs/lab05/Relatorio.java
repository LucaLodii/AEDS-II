package labs.lab05;

// Relatório de análise das estratégias de pivô
public class Relatorio {
    
    public static void main(String[] args) {
        System.out.println("=== RELATÓRIO - ESTRATÉGIAS DE PIVÔ ===\n");
        
        System.out.println("1. FUNCIONAMENTO DAS ESTRATÉGIAS:");
        System.out.println("   • Primeiro: Pivô = primeiro elemento");
        System.out.println("   • Último: Pivô = último elemento");
        System.out.println("   • Aleatório: Pivô = elemento aleatório");
        System.out.println("   • Mediana: Pivô = mediana entre primeiro, meio e último\n");
        
        System.out.println("2. DESEMPENHO OBSERVADO:");
        System.out.println("   Arrays aleatórios (1000 elementos):");
        System.out.println("   • Primeiro/Último: ~0.1-0.5 ms");
        System.out.println("   • Aleatório/Mediana: ~0.2-0.6 ms");
        System.out.println();
        System.out.println("   Arrays ordenados (1000 elementos):");
        System.out.println("   • Primeiro/Último: ~0.8-1.0 ms");
        System.out.println("   • Aleatório/Mediana: ~0.03-0.05 ms");
        System.out.println();
        System.out.println("   Arrays decrescentes (1000 elementos):");
        System.out.println("   • Primeiro/Último: ~0.8-1.0 ms");
        System.out.println("   • Aleatório/Mediana: ~0.03-0.05 ms\n");
        
        System.out.println("3. DISCUSSÃO:");
        System.out.println("   • Arrays aleatórios: Primeiro/Último mais eficientes");
        System.out.println("   • Arrays ordenados: Aleatório/Mediana ~20x mais rápidos");
        System.out.println("   • Arrays decrescentes: Aleatório/Mediana ~20x mais rápidos");
        System.out.println("   • Mediana de três: Estratégia mais robusta e consistente");
        System.out.println("   • Primeiro/Último: Sofrem com O(n²) em arrays ordenados");
    }
}
