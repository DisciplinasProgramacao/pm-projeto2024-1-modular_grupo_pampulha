
import java.io.*;
import java.util.*;

public class Restaurante {

    private String nomeRestaurante;
    private Map<Integer, Mesa> mesas;

    public Restaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
        this.mesas = new HashMap<>();
        gerarMesas();
    }

    private void gerarMesas() {
        // Adiciona 4 mesas de capacidade 4
        for (int i = 1; i <= 4; i++) {
            mesas.put(i, new Mesa(4));
        }
        // Adiciona 4 mesas de capacidade 6
        for (int i = 5; i <= 8; i++) {
            mesas.put(i, new Mesa(6));
        }
        // Adiciona 2 mesas de capacidade 8
        for (int i = 9; i <= 10; i++) {
            mesas.put(i, new Mesa(8));
        }
    }

    private boolean verificarMesaVazia(Mesa mesa) {
        return !mesa.mesaLotada();
    }

    public boolean requerirMesa(int quantidade, Mesa mesa, Cliente cliente) {
        if (verificarMesaVazia(mesa)) {
            mesa.registrarEntrada(cliente);
            System.out.println("Hora de Inicio:" + mesa.getInicio());
            return true;
        } else {
            return false;
        }
    }

    public boolean sairMesa(Mesa mesa) {
        if (!verificarMesaVazia(mesa)) {
            mesa.registrarSaida();
            System.out.println("Hora de Encerramento:" + mesa.getFim());
            return true;
        } else {
            return false;
        }
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public Map<Integer, Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(Map<Integer, Mesa> mesas) {
        this.mesas = mesas;
    }

	
}