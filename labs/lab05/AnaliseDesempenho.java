package labs.lab05;

// Análise de desempenho das estratégias de pivô
public class AnaliseDesempenho {
    
    public static void main(String[] args) {
        System.out.println("=== ANÁLISE DE DESEMPENHO - QUICKSORT ===\n");
        
        int[] tamanhos = {100, 1000, 10000};
        String[] tipos = {"aleatório", "crescente", "decrescente"};
        
        for (int tamanho : tamanhos) {
            System.out.println("TAMANHO: " + tamanho + " elementos");
            System.out.println("=" + "=".repeat(40));
            
            for (String tipo : tipos) {
                System.out.println("\nTipo: " + tipo.toUpperCase());
                System.out.println("-".repeat(20));
                
                testarEstrategia("Primeiro", new QuicksortPrimeiro(tamanho), tipo);
                testarEstrategia("Último", new QuicksortUltimo(tamanho), tipo);
                testarEstrategia("Aleatório", new QuicksortAleatorio3(tamanho), tipo);
                testarEstrategia("Mediana", new QuicksortMediana(tamanho), tipo);
            }
            System.out.println("\n" + "=".repeat(50) + "\n");
        }
    }
    
    private static void testarEstrategia(String nome, Geracao algoritmo, String tipo) {
        switch (tipo) {
            case "aleatório": algoritmo.aleatorio(); break;
            case "crescente": algoritmo.crescente(); break;
            case "decrescente": algoritmo.decrescente(); break;
        }
        
        long inicio = System.nanoTime();
        algoritmo.sort();
        long fim = System.nanoTime();
        
        double tempoMs = (fim - inicio) / 1_000_000.0;
        System.out.printf("%-12s: %6.2f ms%n", nome, tempoMs);
    }
}
