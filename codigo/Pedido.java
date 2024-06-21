public class Pedido {
    private ItemMenu item;

    public Pedido(ItemMenu item) {
        this.item = item;
    }

    public double calcularValor() {
        return item.getPreco();
    }

    @Override
    public String toString() {
        return item.getNome() + " - R$ " + item.getPreco();
    }
}
