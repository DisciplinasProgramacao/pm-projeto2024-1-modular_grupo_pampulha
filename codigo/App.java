import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Olá, seja bem vindo! Este é um programa feito em java para a organização de um restaurante.");
        System.out.println("Qual o nome do seu restaurante?");
        String nome = scan.nextLine();
        Restaurante restaurante = new Restaurante(nome);
        System.out.println("Olá, seja bem vindo ao " + restaurante.getNomeRestaurante() + "!");
        boolean funcionando = true;
        while (funcionando) {
            System.out.println("Menu " + restaurante.getNomeRestaurante());
            System.out.println("1-Realizar Uma Reserva");
            System.out.println("2-Finalizar Uma Reserva");
            System.out.println("3-Encerrar Programa");
            int key = scan.nextInt();
            switch (key) {
                case 1: {
                    scan.nextLine(); // Limpar o buffer do scanner
                    System.out.println("Insira o nome do responsável pela reserva?");
                    String nomeReserva = scan.nextLine();
                    System.out.println("Insira a quantidade de pessoas para reserva?");
                    int quantReserva = scan.nextInt();
                    scan.nextLine(); // Limpar o buffer do scanner
                    Cliente cliente = new Cliente(nomeReserva, quantReserva);
                    System.out.println("Cliente: " + cliente.getNome() + ", Quantidade de pessoas: " + cliente.getQuantPessoas());
                    // Para requerir uma mesa, você precisa especificar a mesa corretamente
                    // Aqui estou apenas passando a primeira mesa do restaurante como exemplo
                    boolean mesaRequerida = restaurante.requerirMesa(quantReserva, restaurante.getMesas().get(1), cliente);
                    if (mesaRequerida) {
                        System.out.println("Mesa reservada com sucesso!");
                    } else {
                        System.out.println("Desculpe, a mesa está lotada.");
                    }
                    break;
                }
                case 2: {
                    // Percorre todas as mesas do restaurante
                    for (Map.Entry<Integer, Mesa> entry : restaurante.getMesas().entrySet()) {
                        int numeroMesa = entry.getKey();
                        Mesa mesa = entry.getValue();

                        // Verifica se a mesa está ocupada
                        if (mesa.getCliente()!= null) {
                            Cliente cliente = mesa.getCliente();
                            System.out.println("Número da Mesa: " + numeroMesa);
                            System.out.println("Nome do Cliente: " + cliente.getNome());
                            System.out.println("Número de Pessoas: " + cliente.getQuantPessoas());
                            System.out.println();
                        } else {
                            System.out.println("Número da Mesa: " + numeroMesa);
                            System.out.println("Status: Mesa vazia");
                            System.out.println();
                        }
                    }

                    System.out.println("Digite o número da mesa que deseja finalizar a reserva:");
                    int numeroMesa = scan.nextInt();
                    scan.nextLine(); // Limpar o buffer do scanner

                    // Verifica se o número da mesa é válido
                    if (restaurante.getMesas().containsKey(numeroMesa)) {
                        Mesa mesa = restaurante.getMesas().get(numeroMesa);
                        
                        // Verifica se a mesa está ocupada
                        if (mesa.getCliente()!= null) {
                            boolean mesaLiberada = restaurante.sairMesa(mesa);
                            if (mesaLiberada) {
                                System.out.println("Reserva finalizada com sucesso!");
                                System.out.println("Hora de Encerramento: " + mesa.getFim());
                            } else {
                                System.out.println("Erro ao finalizar a reserva. Por favor, tente novamente.");
                            }
                        } else {
                            System.out.println("A mesa já está desocupada.");
                        }
                    } else {
                        System.out.println("Mesa não encontrada. Por favor, verifique o número da mesa e tente novamente.");
                    }
                    break;
                }

                case 3: {
                    funcionando = false; // Encerrar o programa
                    break;
                }
                default: {
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                    break;
                }
            }
        }
        scan.close(); // Fechar o Scanner
    }
}
