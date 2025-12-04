package Treino;
import java.util.Scanner;

class Carro {
    String placa, modelo, tipo, chassi;

    void ler(String linha) {
        String[] partes = linha.split(",");
        placa = partes[0];
        modelo = partes[1];
        tipo = partes[2];
        chassi = partes[3];
    }

    void imprimir() {
        System.out.println(placa + " " + modelo + " " + tipo + " " + chassi);
    }
}

class No {
    Carro carro;
    No proximo;

    public No(Carro c) {
        this.carro = c;
        this.proximo = null;
    }
}

public class HashComLista {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextLine()) {
            int N = Integer.parseInt(sc.nextLine());
            No[] tabela = new No[N];
            
            String linha = sc.nextLine();
            while (!linha.equals("FIM")) {
                Carro c = new Carro();
                c.ler(linha);
                
                int soma = 0;
                for (int i = 0; i < c.placa.length(); i++) {
                    soma += c.placa.charAt(i);
                }
                int pos = soma % N;
                
                No novo = new No(c);
                novo.proximo = tabela[pos];
                tabela[pos] = novo;
                
                linha = sc.nextLine();
            }
            
            if (sc.hasNextLine()) {
                linha = sc.nextLine();
                while (!linha.equals("FIM_CONSULTA")) {
                    int soma = 0;
                    for (int i = 0; i < linha.length(); i++) {
                        soma += linha.charAt(i);
                    }
                    int pos = soma % N;
                    
                    No atual = tabela[pos];
                    boolean encontrado = false;
                    
                    while (atual != null && !encontrado) {
                        if (atual.carro.placa.equals(linha)) {
                            atual.carro.imprimir();
                            encontrado = true;
                        }
                        atual = atual.proximo;
                    }
                    
                    if (!encontrado) {
                        System.out.println("VEICULO NAO CADASTRADO");
                    }
                    
                    if (sc.hasNextLine()) {
                        linha = sc.nextLine();
                    } else {
                        linha = "FIM_CONSULTA";
                    }
                }
            }
        }
        sc.close();
    }
}