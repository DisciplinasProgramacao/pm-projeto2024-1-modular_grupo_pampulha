

import java.io.*;
import java.util.*;


  /**
  * Classe cliente simples para o restaurante. Serve apenas para cadastro com nome e ID
  */
public class Cliente {

	private static int ultimoID;
	private String nomeCliente;
	private int idCliente;

	static{
		ultimoID = 0;
	}

	/**
	 * Cria um cliente com este nome. Nome deve ser maior que 2 caracteres, ou será criado como cliente anônimo.
	 * @param nome Nome do cliente (>2 caracteres)
	 */
	
	public Cliente(String nome) {
		if(nome.length() > 2)
			nomeCliente = nome;
		else
			nomeCliente = "Anonimo";

		idCliente = ++ultimoID;

	}

	@Override
	public String toString(){
		return String.format("Nome: %s - identificador: %d", nomeCliente, idCliente);
	}

	@Override
	public int hashCode(){
		return idCliente;
	}


	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	

}
