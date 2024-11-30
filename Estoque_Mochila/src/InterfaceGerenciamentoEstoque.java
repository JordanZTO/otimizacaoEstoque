import java.util.Scanner;
import java.util.List;

public class InterfaceGerenciamentoEstoque {
    private Estoque estoque;
    private AlgoritmoMochila algoritmo;
    private Scanner scanner;

    public InterfaceGerenciamentoEstoque() {
        this.scanner = new Scanner(System.in);
        configurarCapacidadeEstoque();
        this.algoritmo = new AlgoritmoMochila();
    }

    private void configurarCapacidadeEstoque() {
        while (true) {
            try {
                System.out.print("Digite a capacidade máxima de armazenamento (em kg): ");
                double capacidade = scanner.nextDouble();

                if (capacidade <= 0) {
                    System.out.println("A capacidade deve ser um número positivo.");
                    continue;
                }

                this.estoque = new Estoque(capacidade);
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                scanner.nextLine(); // Limpar buffer do scanner
            }
        }
    }

    public void iniciarMenu() {
        while (true) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    otimizarEstoque();
                    break;
                case 3:
                    exibirRelatorioOtimizacao();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n--- Sistema de Otimização de Estoque ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Otimizar Estoque");
        System.out.println("3. Exibir Relatório de Estoque");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpar buffer do scanner
            return -1;
        }
    }

    private void cadastrarProduto() {
        scanner.nextLine(); // Limpar buffer do scanner

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        double peso = lerDoublePositivo("Digite o peso do produto (em kg): ");
        double valor = lerDoublePositivo("Digite o valor do produto (em R$): ");
        int quantidade = lerInteiroPositivo("Digite a quantidade do produto: ");

        Produto novoProduto = new Produto(nome, peso, valor, quantidade);

        if (estoque.adicionarProduto(novoProduto)) {
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível adicionar o produto. Espaço insuficiente.");
        }
    }

    private double lerDoublePositivo(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = scanner.nextDouble();

                if (valor > 0) {
                    return valor;
                }

                System.out.println("O valor deve ser positivo.");
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                scanner.nextLine(); // Limpar buffer do scanner
            }
        }
    }

    private int lerInteiroPositivo(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();

                if (valor > 0) {
                    return valor;
                }

                System.out.println("O valor deve ser positivo.");
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                scanner.nextLine(); // Limpar buffer do scanner
            }
        }
    }

    public void otimizarEstoque() {
        List<Produto> produtosOtimizados = algoritmo.otimizarEstoque(estoque);

        if (produtosOtimizados.isEmpty()) {
            System.out.println("Não há produtos para otimizar.");
            return;
        }

        System.out.println("Produtos otimizados:");
        produtosOtimizados.forEach(System.out::println);
    }

    public void exibirRelatorioOtimizacao() {
        System.out.println("Relatório de Otimização de Estoque:");
        System.out.printf("Capacidade Máxima: %.2f kg%n", estoque.getCapacidadeMaxima());
        System.out.printf("Ocupação Atual: %.2f kg%n", estoque.calcularOcupacaoAtual());
        System.out.println("Produtos no Estoque:");

        List<Produto> produtos = estoque.getProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        produtos.forEach(System.out::println);
    }

    public static void main(String[] args) {
        InterfaceGerenciamentoEstoque gerenciador = new InterfaceGerenciamentoEstoque();
        gerenciador.iniciarMenu();
    }
}