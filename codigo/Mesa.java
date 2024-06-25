import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private static int ultimoID = 0;
    private int idMesa;
    private int capacidade;
    private boolean ocupada;
    private Cliente cliente;
    private List<Pedido> pedidos;

    public Mesa(int capacidade) {
        this.idMesa = ++ultimoID;
        this.capacidade = Math.max(2, capacidade);
        this.ocupada = false;
        this.pedidos = new ArrayList<>();
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void ocupar(Cliente cliente) {
        this.ocupada = true;
        this.cliente = cliente;
    }

    public void desocupar() {
        this.ocupada = false;
        this.cliente = null;
        this.pedidos.clear();
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public String listarPedidos() {
        StringBuilder sb = new StringBuilder();
        for (Pedido pedido : pedidos) {
            sb.append(pedido).append("\n");
        }
        return sb.toString();
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Pedido pedido : pedidos) {
            total += pedido.calcularValor();
        }
        return total;
    }

    public double calcularValorComTaxa() {
        return calcularValorTotal() * 1.10;
    }

    // Método específico para adicionar pedidos de menu fechado
    public void adicionarPedidosMenuFechado(Item comida, Item bebida1, Item bebida2) {
        Pedido pedidoComida = new Pedido(new ItemMenu(0, comida.getNome(), comida.getPreco())); // ItemMenu com código 0 para identificar no pedido
        Pedido pedidoBebida1 = new Pedido(new ItemMenu(0, bebida1.getNome(), bebida1.getPreco()));
        Pedido pedidoBebida2 = new Pedido(new ItemMenu(0, bebida2.getNome(), bebida2.getPreco()));

        pedidos.add(pedidoComida);
        pedidos.add(pedidoBebida1);
        pedidos.add(pedidoBebida2);
    }

    @Override
    public String toString() {
        return "Mesa ID: " + idMesa + ", Capacidade: " + capacidade + ", Ocupada: " + ocupada +
               (ocupada ? ", Responsável: " + cliente.getNome() : "");
    }
}
