package labs.lab05;

// Quicksort com pivô no último elemento
public class QuicksortUltimo extends Geracao {

   public QuicksortUltimo(){
      super();
   }

   public QuicksortUltimo(int tamanho){
      super(tamanho);
   }

   @Override
   public void sort() {
      quicksort(0, n-1);
   }

    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[dir]; // pivô = último elemento
        
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

    // Teste da implementação
    public static void main(String[] args) {
        System.out.println("=== Quicksort - Pivô Último ===");
        
        QuicksortUltimo qs = new QuicksortUltimo(10);
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