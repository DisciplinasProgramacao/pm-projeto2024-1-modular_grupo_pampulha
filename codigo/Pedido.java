public class Pedido {
    private ItemMenu item;

    public Pedido(ItemMenu item2) {
        this.item = item2;
    }

    public double calcularValor() {
        return item.getPreco();
    }

    @Override
    public String toString() {
        return item.getNome() + " - R$ " + String.format("%.2f", item.getPreco());
    }
}
