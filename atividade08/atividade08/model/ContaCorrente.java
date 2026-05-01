package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContaCorrente {
	private int numero;
	private String nome;
	private double saldo;
	private Date dataCriacao;
	private List<String> historico;

	public ContaCorrente(int numero, String nome, double saldo, Date data) {
		this.numero = numero;
		this.nome = nome;
		this.saldo = saldo;
		this.dataCriacao = data;
		this.historico = new ArrayList<>();
	}

	public ContaCorrente(int numero, String nome, Date data) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.dataCriacao = data;
		this.saldo = 0; // Instanciar com saldo 0.0
		this.historico = new ArrayList<>();
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}


	public double getSaldo() {
		return saldo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public List<String> getHistorico(){
		return historico;
	}


	public void creditar(double valor) {
		saldo+=valor;
		registrarOperacao("Deposito de R$ " + valor);
	}
	
	public void debitar(double valor) {
		saldo-=valor;
		registrarOperacao("Saque de R$ " + valor);
	}
	
	public void registrarTransferencia(double valor, ContaCorrente destino) {
		registrarOperacao("Transferência de R$ " + valor + " para conta " + destino.getNumero());
	}
	
	public void registrarOperacao(String descricao) {
		Date agora = new Date();
		historico.add(agora + " - " + descricao);
	}

}
