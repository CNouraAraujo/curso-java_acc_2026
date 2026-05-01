package controller;

import model.ContaCorrente;
import exception.ContaException;
import service.ContaService;

public class ContaController {

	private ContaService service = new ContaService();

	public void depositar(ContaCorrente conta, double valor) {
		try {
			service.depositar(conta, valor);
			System.out.println("Deposito realizado!");
		} catch (ContaException e) {
			System.out.println(e.getMessage());
		}
	}

	public void sacar(ContaCorrente conta, double valor) {
		try {
			service.sacar(conta, valor);
			System.out.println("Saque realizado!");
		} catch (ContaException e) {
			System.out.println(e.getMessage());
		}
	}

	public void transferir(ContaCorrente origem, ContaCorrente destino, double valor) {
		try {
			service.transferir(origem, destino, valor);
			System.out.println("Transferencia realizada com sucesso!");
		} catch (ContaException e) {
			System.out.println(e.getMessage());
		}
	}

	public void extrato(ContaCorrente conta) {
		service.exibirExtrato(conta);
	}

}
