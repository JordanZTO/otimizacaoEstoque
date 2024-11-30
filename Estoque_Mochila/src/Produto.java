public class Produto {
    private String nome;
    private double peso;
    private double valor;
    private int quantidade;

    public Produto(String nome, double peso, double valor, int quantidade) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso do produto deve ser positivo");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do produto deve ser positivo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade do produto deve ser positiva");
        }

        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso do produto deve ser positivo");
        }
        this.peso = peso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do produto deve ser positivo");
        }
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade do produto deve ser positiva");
        }
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format("Produto{nome='%s', peso=%.2f kg, valor=R$%.2f, quantidade=%d}",
                nome, peso, valor, quantidade);
    }
}