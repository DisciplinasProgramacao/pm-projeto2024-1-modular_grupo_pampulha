import java.util.Map;
import java.util.Scanner;

public class App {
    private static Restaurante restaurante;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        inicializarRestaurante(scan);

        boolean funcionando = true;
        while (funcionando) {
            exibirMenu();
            int opcao = scan.nextInt();
            scan.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    realizarReserva(scan);
                    break;
                case 2:
                    finalizarReserva(scan);
                    break;
                case 3:
                    listarMesasOcupadas();
                    break;
                case 4:
                    realizarPedido(scan);
                    break;
                case 5:
                    realizarMenuFechado(scan);
                    break;
                case 6:
                    listarPedidosMesa(scan);
                    break;
                case 7:
                    funcionando = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scan.close();
    }

    private static void inicializarRestaurante(Scanner scan) {
        String nomeRestaurante;

        while (true) {
            System.out.println("Insira o nome do restaurante (mínimo 2 caracteres):");
            nomeRestaurante = scan.nextLine();

            if (nomeRestaurante.length() >= 2) {
                break;
            } else {
                System.out.println("Nome inválido. O nome deve ter pelo menos 2 caracteres.");
            }
        }

        restaurante = new Restaurante(nomeRestaurante);
        System.out.println("Restaurante " + restaurante.getNomeRestaurante() + " criado com sucesso.");
    }
    private static void exibirMenu() {
        System.out.println("### Menu Principal ###");
        System.out.println("1. Realizar Reserva");
        System.out.println("2. Finalizar Reserva");
        System.out.println("3. Listar Mesas Ocupadas");
        System.out.println("4. Realizar Pedido");
        System.out.println("5. Realizar Menu Fechado");
        System.out.println("6. Listar Pedidos de uma Mesa");
        System.out.println("7. Sair");
    }
    private static void realizarReserva(Scanner scan) {
        int quantidade = 0;

        // Solicita a quantidade de pessoas até que seja inserido um valor válido
        while (true) {
            System.out.println("Insira a quantidade de pessoas (1-8):");
            if (scan.hasNextInt()) {
                quantidade = scan.nextInt();
                scan.nextLine(); // Limpar o buffer do scanner
                if (quantidade > 0 && quantidade < 9) {
                    break;
                } else {
                    System.out.println("Quantidade inválida. Deve ser um número entre 1 e 8.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scan.nextLine(); // Limpar o buffer do scanner
            }
        }
        String nomeCliente = "";

        // Solicita o nome do cliente até que seja inserido um nome válido (mínimo 2 caracteres)
        while (true) {
            System.out.println("Insira o nome do cliente (mínimo 2 caracteres):");
            nomeCliente = scan.nextLine();
            if (nomeCliente.length() >= 2) {
                break;
            } else {
                System.out.println("Nome inválido. Deve ter pelo menos 2 caracteres.");
            }
        }

        Cliente cliente = new Cliente(nomeCliente, quantidade);
        boolean sucesso = restaurante.requerirMesa(quantidade, cliente);
      
        if (sucesso) {
            System.out.println("Mesa reservada com sucesso para " + cliente.getNome() + ".");
        } else {
            System.out.println("Não foi possível encontrar uma mesa disponível para " + cliente.getNome() + ".");
        }
    }
    private static void finalizarReserva(Scanner scan) {
    	listarMesasOcupadas();
        int numeroMesa = 0;

        // Solicita o número da mesa até que seja inserido um valor válido (entre 1 e 10)
        while (true) {
            System.out.println("Insira o número da mesa que deseja finalizar a reserva (1-10):");
            if (scan.hasNextInt()) {
                numeroMesa = scan.nextInt();
                scan.nextLine(); // Limpar o buffer do scanner
                if (numeroMesa >= 1 && numeroMesa <= 10) {
                    break;
                } else {
                    System.out.println("Número da mesa inválido. Deve ser um número entre 1 e 10.");
                }
            } else {
            	System.out.println("Mesa não encontrada ou já está desocupada.");
                scan.nextLine(); // Limpar o buffer do scanner
            }
        }

        Mesa mesa = restaurante.getMesas().get(numeroMesa);
        if (mesa != null && mesa.isOcupada()) {
            System.out.println("Finalizando reserva da mesa " + numeroMesa + ".");
            
            // Exibir pedidos da mesa
            System.out.println("### Pedidos da Mesa ###");
            System.out.println(mesa.listarPedidos());
            
            // Calcular e exibir valor total dos pedidos
            double valorTotal = mesa.calcularValorTotal();
            System.out.println("Valor Total: R$ " + valorTotal);
            
            // Calcular e exibir valor total com taxa de serviço
            double valorTotalComTaxa = mesa.calcularValorComTaxa();
            System.out.println("Valor Total com Taxa de Serviço: R$ " + valorTotalComTaxa);
            

            double valorDividido = valorTotalComTaxa / mesa.getCliente().getQuantidadePessoas();
            System.out.println("Valor Total para Cada Pessoa: R$ " + valorDividido);
            

            restaurante.sairMesa(mesa);
        } else {
            System.out.println("Mesa não encontrada ou já está desocupada.");
        }
    }

    private static void listarMesasOcupadas() {
        System.out.println("### Mesas Ocupadas ###");
        Map<Integer, Mesa> mesas = restaurante.getMesas();
        for (Mesa mesa : mesas.values()) {
            if (mesa.isOcupada()) {
                System.out.println(mesa);
            }
        }
    }
    private static void realizarPedido(Scanner scan) {
    	listarMesasOcupadas();
        int numeroMesa = 0;

        // Solicita o número da mesa até que seja inserido um valor válido (entre 1 e 10)
        while (true) {
            System.out.println("Insira o número da mesa que deseja realizar o pedido (1-10):");
            if (scan.hasNextInt()) {
                numeroMesa = scan.nextInt();
                scan.nextLine(); // Limpar o buffer do scanner
                if (numeroMesa >= 1 && numeroMesa <= 10) {
                    break;
                } else {
                    System.out.println("Número da mesa inválido. Deve ser um número entre 1 e 10.");
                }
            } else {
            	System.out.println("Mesa não encontrada ou já está desocupada.");
                scan.nextLine(); // Limpar o buffer do scanner
            }
        }

        Mesa mesa = restaurante.getMesas().get(numeroMesa);
        if (mesa != null && mesa.isOcupada()) {
            exibirMesasComResponsaveis();
            exibirCardapio();
            System.out.println("Digite o código do item do cardápio:");
            int codigoItem = scan.nextInt();
            scan.nextLine(); // Limpar o buffer do scanner

            boolean pedidoRealizado = restaurante.realizarPedido(codigoItem, mesa);
            if (pedidoRealizado) {
                System.out.println("Pedido realizado com sucesso.");
            } else {
                System.out.println("Não foi possível realizar o pedido. Verifique o código do item e tente novamente.");
            }
        } else {
            System.out.println("Mesa não encontrada ou não está ocupada.");
        }
    }
    private static void realizarMenuFechado(Scanner scan) {
    	listarMesasOcupadas();
        System.out.println("Digite o número da mesa que deseja realizar o menu fechado:");
        int numeroMesa = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        Mesa mesa = restaurante.getMesas().get(numeroMesa);
        if (mesa != null && mesa.isOcupada()) {
            // Escolha da comida
            System.out.println("Escolha a comida (digite o número):");
            System.out.println("1. Falafel Assado");
            System.out.println("2. Caçarola de Legumes");
            int opcaoComida = 0;
            while (true) {
                if (scan.hasNextInt()) {
                    opcaoComida = scan.nextInt();
                    scan.nextLine(); // Limpar o buffer do scanner
                    if (opcaoComida == 1 || opcaoComida == 2) {
                        break;
                    } else {
                        System.out.println("Opção inválida para comida. Escolha 1 ou 2:");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    scan.nextLine(); // Limpar o buffer do scanner
                }
            }

            Item comida = null;
            switch (opcaoComida) {
                case 1:
                    comida = new Item("Falafel Assado", 20.0);
                    break;
                case 2:
                    comida = new Item("Caçarola de Legumes", 22.0);
                    break;
            }

            // Escolha das bebidas
            System.out.println("Escolha as bebidas (digite os números, separados por espaço):");
            System.out.println("1. Copo de suco");
            System.out.println("2. Refrigerante orgânico");
            System.out.println("3. Cerveja vegana");
            String[] opcoesBebidas = null;
            while (true) {
                String bebidasInput = scan.nextLine();
                opcoesBebidas = bebidasInput.split("\\s+");
                if (opcoesBebidas.length == 2 && 
                    (opcoesBebidas[0].equals("1") || opcoesBebidas[0].equals("2") || opcoesBebidas[0].equals("3")) &&
                    (opcoesBebidas[1].equals("1") || opcoesBebidas[1].equals("2") || opcoesBebidas[1].equals("3"))) {
                    break;
                } else {
                    System.out.println("Opções inválidas para bebidas. Escolha 2 números entre 1 e 3:");
                }
            }

            Item bebida1 = null;
            Item bebida2 = null;
            for (String opcaoBebida : opcoesBebidas) {
                switch (opcaoBebida) {
                    case "1":
                        if (bebida1 == null) {
                            bebida1 = new Item("Copo de suco", 7.0);
                        } else {
                            bebida2 = new Item("Copo de suco", 7.0);
                        }
                        break;
                    case "2":
                        if (bebida1 == null) {
                            bebida1 = new Item("Refrigerante orgânico", 7.0);
                        } else {
                            bebida2 = new Item("Refrigerante orgânico", 7.0);
                        }
                        break;
                    case "3":
                        if (bebida1 == null) {
                            bebida1 = new Item("Cerveja vegana", 9.0);
                        } else {
                            bebida2 = new Item("Cerveja vegana", 9.0);
                        }
                        break;
                    default:
                        System.out.println("Opção inválida para bebida: " + opcaoBebida);
                        return;
                }
            }

            boolean pedidoRealizado = restaurante.realizarMenuFechado(numeroMesa, comida, bebida1, bebida2);
            if (pedidoRealizado) {
                System.out.println("Menu fechado realizado com sucesso.");
            } else {
                System.out.println("Não foi possível realizar o menu fechado. Verifique as opções e tente novamente.");
            }
        } else {
            System.out.println("Mesa não encontrada ou não está ocupada.");
        }
    }
    private static void listarPedidosMesa(Scanner scan) {
    	listarMesasOcupadas();
        System.out.println("Insira o ID da mesa para listar os pedidos:");
        int idMesa = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

    private static void listarPedidosMesa(Scanner scan) {
    	listarMesasOcupadas();
        System.out.println("Insira o ID da mesa para listar os pedidos:");
        int idMesa = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        Mesa mesa = restaurante.getMesas().get(idMesa);
        if (mesa != null && mesa.isOcupada()) {
            System.out.println("### Pedidos da Mesa " + idMesa + " ###");
            System.out.println(mesa.listarPedidos());
            double total = mesa.calcularValorTotal();
            System.out.println("Valor total: R$ " + String.format("%.2f", total));
        } else {
            System.out.println("Mesa não está ocupada ou não existe.");
        }
    }
    private static void exibirMesasComResponsaveis() {
        System.out.println("### Mesas e Responsáveis ###");
        for (Mesa mesa : restaurante.getMesas().values()) {
            if (mesa.isOcupada()) {
                System.out.println("Mesa " + mesa.getIdMesa() + ": " + mesa.getCliente().getNome());
            }
        }
    }
    private static void exibirCardapio() {
        System.out.println("### Cardápio ###");
        System.out.println(restaurante.listarItensCardapio());
    }
}
