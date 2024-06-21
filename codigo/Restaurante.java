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
            mesas.put(i, new Mesa(4));
        }
        for (int i = 5; i <= 8; i++) {
            mesas.put(i, new Mesa(6));
        }
        for (int i = 9; i <= 10; i++) {
            mesas.put(i, new Mesa(8));
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

    public String listarItensCardapio() {
        return cardapio.listarItens();
    }

    public Map<Integer, Mesa> getMesas() {
        return mesas;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }
}
