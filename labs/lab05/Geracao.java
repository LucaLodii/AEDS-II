package labs.lab05;

abstract class Geracao {
    protected int[] array;
    protected int n;

    /**
     * Construtor.
     */
    public Geracao() {
        this(1000);
    }

    /**
     * Construtor.
     * @param int tamanho do array de numeros inteiros.
     */
    public Geracao(int tamanho) {
        array = new int[tamanho];
        n = tamanho;
    }

    /**
     * Metodo para trocar dois elementos do array
     * @param i posicao do primeiro elemento
     * @param j posicao do segundo elemento
     */
    protected void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Metodo abstrato para ordenacao
     */
    public abstract void sort();

    /**
     * Metodo para gerar numeros aleatorios
     */
    public void aleatorio() {
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
    }

    /**
     * Metodo para gerar numeros em ordem crescente
     */
    public void crescente() {
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    /**
     * Metodo para gerar numeros em ordem decrescente
     */
    public void decrescente() {
        for (int i = 0; i < n; i++) {
            array[i] = n - 1 - i;
        }
    }

    /**
     * Metodo para mostrar o array
     */
    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }
}
