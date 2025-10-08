package labs.lab05;

// Quicksort com mediana de três
public class QuicksortMediana extends Geracao {

   public QuicksortMediana(){
      super();
   }

   public QuicksortMediana(int tamanho){
      super(tamanho);
   }

   @Override
   public void sort() {
      quicksort(0, n-1);
   }

    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        int meio = (esq + dir) / 2;
        int pivo = medianaDeTres(esq, meio, dir); // pivô = mediana de três
        
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)  quicksort(esq, j);
        if (i < dir)  quicksort(i, dir);
    }
    
    private int medianaDeTres(int esq, int meio, int dir) {
        int a = array[esq];
        int b = array[meio];
        int c = array[dir];
        
        if ((a <= b && b <= c) || (c <= b && b <= a)) {
            return b;
        } else if ((b <= a && a <= c) || (c <= a && a <= b)) {
            return a;
        } else {
            return c;
        }
    }

    // Teste da implementação
    public static void main(String[] args) {
        System.out.println("=== Quicksort - Mediana de Três ===");
        
        QuicksortMediana qs = new QuicksortMediana(10);
        qs.aleatorio();
        
        System.out.println("Array original:");
        qs.mostrar();
        
        long inicio = System.nanoTime();
        qs.sort();
        long fim = System.nanoTime();
        
        System.out.println("Array ordenado:");
        qs.mostrar();
        System.out.println("Tempo: " + (fim - inicio) + " ns");
    }
}