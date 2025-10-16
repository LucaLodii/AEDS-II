import java.util.Scanner;

public class QuadroDeMedalhas {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        // Usa um array padrão em vez de ArrayList
        Pais[] paises = new Pais[n];

        // Loop para ler a entrada e popular o array de objetos
        for (int i = 0; i < n; i++) {
            String nome = scan.next();
            int ouro = scan.nextInt();
            int prata = scan.nextInt();
            int bronze = scan.nextInt();
            paises[i] = new Pais(nome, ouro, prata, bronze);
        }

        // Chama nosso método de ordenação manual
        ordenarPaises(paises);

        // Imprime a lista ordenada
        for (Pais p : paises) {
            System.out.println(p.nome + " " + p.ouro + " " + p.prata + " " + p.bronze);
        }
        
        scan.close();
    }

    public static void ordenarPaises(Pais[] paises) {
        int n = paises.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                // Se o país 'j' deve vir DEPOIS do país 'j+1', eles são trocados
                if (deveTrocar(paises[j], paises[j + 1])) {
                    // Troca os objetos de lugar no array
                    Pais temp = paises[j];
                    paises[j] = paises[j + 1];
                    paises[j + 1] = temp;
                }
            }
        }
    }

    public static boolean deveTrocar(Pais p1, Pais p2) {
        // Regra 1: Ouro (decrescente)
        if (p1.ouro < p2.ouro) return true;
        if (p1.ouro > p2.ouro) return false;

        // Regra 2: Prata (decrescente) - só executa se o ouro empatar
        if (p1.prata < p2.prata) return true;
        if (p1.prata > p2.prata) return false;

        // Regra 3: Bronze (decrescente) - só executa se ouro e prata empatarem
        if (p1.bronze < p2.bronze) return true;
        if (p1.bronze > p2.bronze) return false;

        // Regra 4: Nome (alfabética/crescente) - só executa se todas as medalhas empatarem
        // p1.nome.compareTo(p2.nome) > 0 significa que p1 vem depois de p2 na ordem alfabética
        if (p1.nome.compareTo(p2.nome) > 0) return true;

        // Se nenhuma das condições acima for atendida, eles já estão na ordem certa
        return false;
    }
}

// Classe para representar os dados de cada país
class Pais {
    String nome;
    int ouro;
    int prata;
    int bronze;

    public Pais(String nome, int ouro, int prata, int bronze) {
        this.nome = nome;
        this.ouro = ouro;
        this.prata = prata;
        this.bronze = bronze;
    }
}