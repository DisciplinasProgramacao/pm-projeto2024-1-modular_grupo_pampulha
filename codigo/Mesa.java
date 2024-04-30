import java.util.Date;

public class Mesa {

    private int capacidade;
    private Cliente cliente;
    private Date inicio;
    private Date fim;

    public Mesa(int capacidade) {
        this.capacidade = capacidade;
        this.cliente = null;
        this.inicio = null;
        this.fim = null;
    }

    protected boolean mesaLotada() {
        return cliente != null;
    }

    public String registrarEntrada(Cliente cliente) {
        this.cliente = cliente;
        inicio = new Date();
        return "Entrada registrada com sucesso.";
    }

    public String registrarSaida() {
        fim = new Date();
        cliente = null;
        return "Saída registrada com sucesso.";
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }
}
