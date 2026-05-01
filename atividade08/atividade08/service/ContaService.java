package service;

import model.ContaCorrente;
import exception.ContaException;

public class ContaService {

	public void depositar(ContaCorrente conta, double valor) throws ContaException {
		if (conta == null) {
			throw new ContaException("Conta Inválida");
		}
		
		if (valor <= 0) {
			throw new ContaException("Valor de deposito invalido");
		}

		conta.creditar(valor);
	}

	public void sacar(ContaCorrente conta, double valor) throws ContaException {
		if (conta == null) {
			throw new ContaException("Conta inválida");
		}
		
		if (conta.getSaldo() < valor) {
			throw new ContaException("Saldo Isuficiente");
		}

		if (valor <= 0) {
			throw new ContaException("Valor de saque inválido");
		}

		conta.debitar(valor);
	}

	public void transferir(ContaCorrente origem, ContaCorrente destino, double valor) throws ContaException {
		if (origem == null || destino == null) {
			throw new ContaException("Conta origem ou destino invalida");
		}
		
		if (origem ==  destino) {
			throw new ContaException("Nao eh possivel transferir para a mesma conta!");
		}
		
		if (valor <= 0) {
			throw new ContaException("Valor de transferencia invalido!");
		}
		
		if (origem.getSaldo() < valor) {
			throw new ContaException("Valor insuficiente para transferencia");
		}
		
		origem.debitar(valor);
		destino.creditar(valor);
		
		origem.registrarTransferencia(valor, destino);
		destino.registrarTransferencia(valor, origem);
	}

	public void exibirExtrato(ContaCorrente conta) {
		// Passar a data como parametro (data da consulta)
		// Exibir datas das operacoes (deposito, transferencia, saque)
		System.out.println("===== EXTRATO =====");
		System.out.println("Titular: " + conta.getNome());
		System.out.println("Conta: " + conta.getNumero());
		System.out.println("Data criação: " + conta.getDataCriacao());
		
		System.out.println("\n--- Histórico ---");
		for (String op : conta.getHistorico()) {
			System.out.println(op);
		}
		
		System.out.printf("\nSaldo atual: R$ %.2f\n", conta.getSaldo());
		System.out.println("=================");
	}
}
