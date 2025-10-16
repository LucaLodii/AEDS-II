package labs.lab05;

import java.util.Random;

// Quicksort com pivô aleatório
public class QuicksortAleatorio3 extends Geracao {
    private Random rand = new Random();

   public QuicksortAleatorio3(){
      super();
   }

   public QuicksortAleatorio3(int tamanho){
      super(tamanho);
   }

   @Override
   public void sort() {
      quicksort(0, n-1);
   }

    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        int indicePivo = esq + rand.nextInt(dir - esq + 1); // pivô aleatório
        int pivo = array[indicePivo];
        
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
        System.out.println("=== Quicksort - Pivô Aleatório ===");
        
        QuicksortAleatorio3 qs = new QuicksortAleatorio3(10);
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
