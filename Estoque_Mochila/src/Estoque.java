import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private double capacidadeMaxima;
    private List<Produto> produtos;

    public Estoque(double capacidadeMaxima) {
        if (capacidadeMaxima <= 0) {
            throw new IllegalArgumentException("Capacidade máxima deve ser um número positivo");
        }
        this.capacidadeMaxima = capacidadeMaxima;
        this.produtos = new ArrayList<>();
    }

    public boolean adicionarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        // Verifica se há espaço suficiente considerando o peso total
        double pesoTotalApos = calcularOcupacaoAtual() + produto.getPeso();
        if (pesoTotalApos <= capacidadeMaxima) {
            produtos.add(produto);
            return true;
        }
        return false;
    }

    public boolean removerProduto(Produto produto) {
        return produtos.remove(produto);
    }

    public double calcularOcupacaoAtual() {
        return produtos.stream()
                .mapToDouble(Produto::getPeso)
                .sum();
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }

    public double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void limparEstoque() {
        produtos.clear();
    }

    public int getTotalProdutos() {
        return produtos.size();
    }
}