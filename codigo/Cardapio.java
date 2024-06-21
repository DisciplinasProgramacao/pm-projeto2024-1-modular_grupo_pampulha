import java.util.HashMap;
import java.util.Map;

public class Cardapio {
    private Map<Integer, ItemMenu> itens;

    public Cardapio() {
        itens = new HashMap<>();
        inicializarCardapio();
    }

    private void inicializarCardapio() {
        itens.put(1, new ItemMenu(1, "Moqueca de Palmito", 32.0));
        itens.put(2, new ItemMenu(2, "Falafel Assado", 20.0));
        itens.put(3, new ItemMenu(3, "Salada Primavera com Macarrão Konjac", 25.0));
        itens.put(4, new ItemMenu(4, "Escondidinho de Inhame", 18.0));
        itens.put(5, new ItemMenu(5, "Strogonoff de Cogumelos", 35.0));
        itens.put(6, new ItemMenu(6, "Caçarola de legumes", 22.0));
        itens.put(7, new ItemMenu(7, "Água", 3.0));
        itens.put(8, new ItemMenu(8, "Copo de suco", 7.0));
        itens.put(9, new ItemMenu(9, "Refrigerante orgânico", 7.0));
        itens.put(10, new ItemMenu(10, "Cerveja vegana", 9.0));
        itens.put(11, new ItemMenu(11, "Taça de vinho vegano", 18.0));
    }

    public ItemMenu getItem(int codigoItem) {
        return itens.get(codigoItem);
    }

    public String listarItens() {
        StringBuilder sb = new StringBuilder();
        for (ItemMenu item : itens.values()) {
            sb.append(item.getCodigo()).append(". ").append(item.getNome()).append(" - R$ ").append(item.getPreco()).append("\n");
        }
        return sb.toString();
    }
}
