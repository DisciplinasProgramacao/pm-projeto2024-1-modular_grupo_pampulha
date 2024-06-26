import java.util.HashMap;
import java.util.Map;

public class Restaurante {
    private String nomeRestaurante;
    private Map<Integer, Mesa> mesas;
    private Cardapio cardapio;
    

    public Restaurante(String nomeRestaurante) {
        if (nomeRestaurante.length() < 2) {
            throw new IllegalArgumentException("O nome do restaurante deve ter pelo menos 2 caracteres.");
        }
        this.nomeRestaurante = nomeRestaurante;
        this.mesas = new HashMap<>();
        this.cardapio = new Cardapio();
        inicializarMesas();
    }

    private void inicializarMesas() {
        for (int i = 1; i <= 4; i++) {
            mesas.put(i, new MesaPequena());
        }
        for (int i = 5; i <= 8; i++) {
            mesas.put(i, new MesaMedia());
        }
        for (int i = 9; i <= 10; i++) {
            mesas.put(i, new MesaGrande());
        }
    }

    public boolean requerirMesa(int quantidade, Cliente cliente) {
        for (Mesa mesa : mesas.values()) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= quantidade) {
                mesa.ocupar(cliente);
                return true;
            }
        }
        return false;
    }

    public void sairMesa(Mesa mesa) {
        mesa.desocupar();
    }

    public String listarMesasOcupadas() {
        StringBuilder sb = new StringBuilder();
        for (Mesa mesa : mesas.values()) {
            if (mesa.isOcupada()) {
                sb.append(mesa).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean realizarPedido(int codigoItem, Mesa mesa) {
        ItemMenu item = cardapio.getItem(codigoItem);
        if (item != null) {
            Pedido pedido = new Pedido(item);
            mesa.adicionarPedido(pedido);
            return true;
        }
        return false;
    }

    public boolean realizarMenuFechado(int numeroMesa, Item comida, Item bebida1, Item bebida2) {
        Mesa mesa = mesas.get(numeroMesa);
        if (mesa != null && mesa.isOcupada()) {
            // Cria os pedidos para comida e bebidas
            Pedido comidaPedido = new Pedido(new ItemMenu(0, comida.getNome(), comida.getPreco()));
            Pedido bebidaPedido1 = new Pedido(new ItemMenu(0, bebida1.getNome(), bebida1.getPreco()));
            Pedido bebidaPedido2 = new Pedido(new ItemMenu(0, bebida2.getNome(), bebida2.getPreco()));

            // Adiciona os pedidos à mesa
            mesa.adicionarPedido(comidaPedido);
            mesa.adicionarPedido(bebidaPedido1);
            mesa.adicionarPedido(bebidaPedido2);

            // Calcula o valor total e aplica o desconto
            double valorTotal = comida.getPreco() + bebida1.getPreco() + bebida2.getPreco();
            double desconto = valorTotal - 32.0;

            // Cria um pedido de desconto para ajustar o valor total para 32
            Pedido descontoPedido = new Pedido(new ItemMenu(0, "Desconto Menu Fechado", -desconto));
            mesa.adicionarPedido(descontoPedido);

            return true;
        }
        return false;
    }


    public String listarItensCardapio() {
        return cardapio.listarItens();
    }

    public Map<Integer, Mesa> getMesas() {
        return mesas;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    // Método para listar mesas ocupadas com seus responsáveis
    public String listarMesasComResponsaveis() {
        StringBuilder sb = new StringBuilder();
        sb.append("### Mesas Ocupadas ###\n");
        for (Mesa mesa : mesas.values()) {
            if (mesa.isOcupada()) {
                sb.append("Mesa ").append(mesa.getIdMesa()).append(" - Responsável: ").append(mesa.getCliente().getNome()).append("\n");
            }
        }
        return sb.toString();
    }
}
