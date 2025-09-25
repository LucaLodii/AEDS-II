package labs.lab05;

/**
 * Algoritmo de ordenacao Quicksort
 * @author Max do Val Machado
 * @version 3 08/2020
 */
class QuicksortMediana extends Geracao {

	/**
	 * Construtor.
	 */
   public QuicksortMediana(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public QuicksortMediana(int tamanho){
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

        int val1 = array[(dir+esq)/2];
        int val2 = array[esq];
        int val3 = array[dir];
        int pivo;
        if(val1 >= val2 && val1 <= val3) pivo = val1;
        else if(val2 >= val1 && val2 <= val3) pivo = val2;
        else pivo = val3;

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
}