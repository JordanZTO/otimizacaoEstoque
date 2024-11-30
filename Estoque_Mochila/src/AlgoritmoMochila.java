import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlgoritmoMochila {
    public List<Produto> otimizarEstoque(Estoque estoque) {
        if (estoque == null) {
            throw new IllegalArgumentException("Estoque não pode ser nulo");
        }

        List<Produto> todosProdutos = estoque.getProdutos();

        if (todosProdutos.isEmpty()) {
            return new ArrayList<>();
        }

        double capacidade = estoque.getCapacidadeMaxima();
        return calcularMelhorCombinacao(todosProdutos, capacidade);
    }

    private List<Produto> calcularMelhorCombinacao(List<Produto> produtos, double capacidade) {
        // Ordena produtos por valor/peso (densidade de valor)
        produtos.sort(Comparator.comparingDouble(p -> -p.getValor() / p.getPeso()));

        List<Produto> produtosSelecionados = new ArrayList<>();
        double pesoAtual = 0;

        for (Produto produto : produtos) {
            if (pesoAtual + produto.getPeso() <= capacidade) {
                produtosSelecionados.add(produto);
                pesoAtual += produto.getPeso();
            }
        }

        return produtosSelecionados;
    }

    // Método adicional para calcular o valor total dos produtos selecionados
    public double calcularValorTotal(List<Produto> produtos) {
        return produtos.stream()
                .mapToDouble(Produto::getValor)
                .sum();
    }

    // Método adicional para calcular o peso total dos produtos selecionados
    public double calcularPesoTotal(List<Produto> produtos) {
        return produtos.stream()
                .mapToDouble(Produto::getPeso)
                .sum();
    }
}