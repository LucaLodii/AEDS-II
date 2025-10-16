package labs.lab05;

// Classe base para algoritmos de ordenação
abstract class Geracao {
    protected int[] array;
    protected int n;

    public Geracao() {
        this(1000);
    }

    public Geracao(int tamanho) {
        array = new int[tamanho];
        n = tamanho;
    }

    protected void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public abstract void sort();

    public void aleatorio() {
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
    }

    public void crescente() {
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    public void decrescente() {
        for (int i = 0; i < n; i++) {
            array[i] = n - 1 - i;
        }
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }
}
