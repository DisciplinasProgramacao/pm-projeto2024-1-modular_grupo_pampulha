import java.util.List;
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
                    listarPedidosMesa(scan);
                    break;
                case 6:
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
        System.out.println("Insira o nome do restaurante (mínimo 2 caracteres):");
        String nomeRestaurante = scan.nextLine();

        restaurante = new Restaurante(nomeRestaurante);
        System.out.println("Restaurante " + restaurante.getNomeRestaurante() + " criado com sucesso.");
    }

    private static void exibirMenu() {
        System.out.println("### Menu Principal ###");
        System.out.println("1. Realizar Reserva");
        System.out.println("2. Finalizar Reserva");
        System.out.println("3. Listar Mesas Ocupadas");
        System.out.println("4. Realizar Pedido");
        System.out.println("5. Listar Pedidos de uma Mesa");
        System.out.println("6. Sair");
    }

    private static void realizarReserva(Scanner scan) {
        System.out.println("Insira o nome do responsável pela reserva:");
        String nomeCliente = scan.nextLine();
        if (nomeCliente.length() < 2) {
            System.out.println("O nome do responsável deve ter pelo menos 2 caracteres.");
            return;
        }
        System.out.println("Insira a quantidade de pessoas para a reserva:");
        int quantReserva = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        Cliente cliente = new Cliente(nomeCliente, quantReserva);
        boolean mesaRequerida = restaurante.requerirMesa(quantReserva, cliente);

        if (mesaRequerida) {
            System.out.println("Reserva realizada com sucesso para " + cliente.getNome() + ".");
        } else {
            System.out.println("Não foi possível realizar a reserva. Verifique a disponibilidade e tente novamente.");
        }
    }

    private static void finalizarReserva(Scanner scan) {
    	listarMesasComResponsaveis();
        System.out.println("Insira o número da mesa que deseja finalizar a reserva:");
        int numeroMesa = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        Mesa mesa = restaurante.getMesas().get(numeroMesa);
        if (mesa != null && mesa.isOcupada()) {
            System.out.println("Finalizando reserva da mesa " + numeroMesa + ".");
            System.out.println("### Pedidos da Mesa ###");
            System.out.println(mesa.listarPedidos());
            System.out.println("Valor Total: R$ " + mesa.calcularValorTotal());
            System.out.println("Valor Total com Taxa de Serviço: R$ " + mesa.calcularValorComTaxa());
            restaurante.sairMesa(mesa);
        } else {
            System.out.println("Mesa não encontrada ou já está desocupada.");
        }
    }

    private static void listarMesasOcupadas() {
        System.out.println("### Mesas Ocupadas ###");
        String mesasOcupadas = restaurante.listarMesasOcupadas();
        System.out.println(mesasOcupadas);
    }

    private static void realizarPedido(Scanner scan) {
        System.out.println("### Realizar Pedido ###");
        listarMesasComResponsaveis();
        System.out.println("Digite o número da mesa que deseja realizar o pedido:");
        int numeroMesa = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        if (restaurante.getMesas().containsKey(numeroMesa)) {
            Mesa mesa = restaurante.getMesas().get(numeroMesa);
            if (mesa.isOcupada()) {
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
                System.out.println("Mesa não está ocupada. Realize uma reserva antes de fazer um pedido.");
            }
        } else {
            System.out.println("Mesa não encontrada. Por favor, verifique o número da mesa e tente novamente.");
        }
    }

    private static void listarPedidosMesa(Scanner scan) {
        System.out.println("### Listar Pedidos de uma Mesa ###");
        listarMesasComResponsaveis();
        System.out.println("Digite o número da mesa para listar os pedidos:");
        int numeroMesa = scan.nextInt();
        scan.nextLine(); // Limpar o buffer do scanner

        if (restaurante.getMesas().containsKey(numeroMesa)) {
            Mesa mesa = restaurante.getMesas().get(numeroMesa);
            List<Pedido> pedidos = mesa.getPedidos();
            if (pedidos.isEmpty()) {
                System.out.println("Esta mesa não possui pedidos.");
            } else {
                System.out.println("### Pedidos da Mesa " + mesa.getIdMesa() + " ###");
                for (Pedido pedido : pedidos) {
                    System.out.println(pedido);
                }
            }
        } else {
            System.out.println("Mesa não encontrada. Por favor, verifique o número da mesa e tente novamente.");
        }
    }

    private static void exibirCardapio() {
        System.out.println("### Cardápio ###");
        String cardapio = restaurante.listarItensCardapio();
        System.out.println(cardapio);
    }

    private static void listarMesasComResponsaveis() {
        System.out.println("### Mesas e Responsáveis ###");
        for (Map.Entry<Integer, Mesa> entry : restaurante.getMesas().entrySet()) {
            Mesa mesa = entry.getValue();
            if (mesa.isOcupada()) {
                System.out.println("Mesa ID: " + mesa.getIdMesa() + ", Responsável: " + mesa.getCliente().getNome());
            }
        }
    }
}
