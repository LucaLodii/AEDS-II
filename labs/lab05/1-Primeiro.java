package labs.lab05;

class QuicksortPrimeiro extends Geracao {

	/**
	 * Construtor.
	 */
   public QuicksortPrimeiro(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public QuicksortPrimeiro(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao Quicksort.
	 */
   @Override
   public void sort() {
      quicksort(0, n-1);
   }

	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
	 */
    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[esq];            // AQUI
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            // se i <= j, troca os elementos i e j
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)  quicksort(esq, j);
        if (i < dir)  quicksort(i, dir);
    }
}