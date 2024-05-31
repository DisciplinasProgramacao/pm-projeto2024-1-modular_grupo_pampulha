
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
  * Classe requisicao: representa o atendimento de um cliente, em uma mesa, em uma data
  */
  public class Requisicao {

	private Cliente cliente;
	private Mesa mesa;
	private int quantPessoas;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private boolean encerrada;

	/**
	 * Cria uma requisição para quantPessoas em nome do cliente. A requisição é criada sem horário de entrada nem saída, sem mesa e com estado de não encerrada
	 * @param quantPessoas Quantidade de pessoas a serem atendidas na mesa. Deve ser >= 1
	 * @param cliente Cliente que faz o pedido. Não deve ser nulo.
	 */
	public Requisicao(int quantPessoas, Cliente cliente) {
		this.quantPessoas = 1;
		if(quantPessoas > 1 )
			this.quantPessoas = quantPessoas;
		this.cliente = cliente;
		entrada = saida = null;
		mesa = null;
		encerrada = false;
	}

	/**
	 * Encerra uma requisição, caso não tenha sido encerrada anteriormente, marcando o horário de finalização e desocupa sua mesa. Retorna a mesa desocupada
	 * @return A mesa desocupada, ou null caso a requisição não estivesse ativa.
	 */
	public Mesa encerrar() {
		Mesa liberada = null;
		if(!encerrada && mesa!=null ){
			saida = LocalDateTime.now();
			mesa.desocupar();
			liberada = mesa;
			encerrada = true;
		}
		return liberada;
	}

	/**
	 * Aloca uma mesa para a requisição, desde que esta mesa esteja disponível e a requisição não tenha sido encerrada. Ajusta a hora de atendimento, se a mesa for alocada.
	 * @param mesa Mesa a ser alocada para a requisição.
	 */
	public void alocarMesa(Mesa mesa) {
		if(!encerrada && mesa.estahLiberada(quantPessoas)){
			this.mesa = mesa;
			entrada = LocalDateTime.now();
			this.mesa.ocupar();
		}
	}

	/**
	 * Responde se a requisição já foi encerrada
	 * @return TRUE/FALSE conforme a requisição já foi encerrada ou não.
	 */
	public boolean estahEncerrada(){
		return encerrada;
	}

	/**
	 * Responde se esta requisição pertence à mesa em questão, passada por parâmetro
	 * @param idMesa Número da mesa
	 * @return TRUE/FALSE conforme a requisição pertença ou não àquela mesa.
	 */
	public boolean ehDaMesa(int idMesa){
		return idMesa == mesa.getIdMesa();
	}

	
	public int getQuantPessoas(){
		return quantPessoas;
	}

	public String toString(){
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		StringBuilder stringReq = new StringBuilder(cliente.toString());
		if(mesa!=null){
			stringReq.append("\n"+mesa.toString()+"\n");
			stringReq.append("Entrada em "+ formato.format(entrada)+"\n");
			if(saida!=null)
				stringReq.append("Saída em "+formato.format(saida)+"\n");
		}
		else{
			stringReq.append(" Em espera.");
		}
		return stringReq.toString();
	}


	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mesa getMesa() {
		return this.mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public void setQuantPessoas(int quantPessoas) {
		this.quantPessoas = quantPessoas;
	}

	public LocalDateTime getEntrada() {
		return this.entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return this.saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public boolean isEncerrada() {
		return this.encerrada;
	}

	public boolean getEncerrada() {
		return this.encerrada;
	}

	public void setEncerrada(boolean encerrada) {
		this.encerrada = encerrada;
	}

}