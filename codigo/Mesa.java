
import java.io.*;
import java.util.*;

public class Mesa {

	private static int ultimoID;
	private int idMesa;
	private int capacidade;
	private boolean ocupada;

	static {
		ultimoID = 0;
	}

	/**
	 * Cria uma mesa com capacidade mínima de 2 pessoas e id auto-gerado.
	 * 
	 * @param capacidade Capacidade da mesa. Deve ser maior ou igual a 2.
	 */
	public Mesa(int capacidade) {
		this.capacidade = 2;
		if (capacidade > 2)
			this.capacidade = capacidade;
		idMesa = ++ultimoID;
		ocupada = false;
	}

	/**
	 * Sinaliza a mesa como ocupada
	 */
	public void ocupar() {
		ocupada = true;
	}

	/**
	 * Sinaliza a mesa como desocupada
	 */
	public void desocupar() {
		ocupada = false;
	}

	/**
	 * Verifica se a mesa pode atender um número determinado de pessoas, citado no
	 * parâmetro quantPessoas.
	 * 
	 * @param quantPessoas Quantidade de pessoas a serem atendidas
	 * @return TRUE/FALSE conforme a mesa pode atender ou não esta quantidade.
	 */
	public boolean estahLiberada(int quantPessoas) {
		return (quantPessoas <= capacidade && !ocupada);
	}

	public int getIdMesa() {
		return idMesa;
	}

	public String toString() {
		String descricao = String.format("Mesa %02d (%d pessoas), ", idMesa, capacidade);
		if (ocupada)
			descricao += "ocupada.";
		else
			descricao += "liberada.";

		return descricao;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isOcupada() {
		return this.ocupada;
	}

	public boolean getOcupada() {
		return this.ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}


}