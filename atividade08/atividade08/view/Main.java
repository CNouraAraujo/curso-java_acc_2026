package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import controller.ContaController;
import model.ContaCorrente;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		ContaController controller = new ContaController();

		List<ContaCorrente> contas = new ArrayList<>();
		ContaCorrente joao = new ContaCorrente(1, "João", new Date());
		ContaCorrente maria = new ContaCorrente(2, "Maria", new Date());

		contas.add(joao);
		contas.add(maria);

		int op;

		do {
			System.out.println("\n===== MENU =====");
			System.out.println("1 - Depositar");
			System.out.println("2 - Sacar");
			System.out.println("3 - Transferir");
			System.out.println("4 - Ver Extrato");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			op = lerInt(sc);

			switch (op) {
			case 1:
				System.out.print("Valor do depósito: ");
				controller.depositar(joao, lerDouble(sc));
				break;

			case 2:
				System.out.print("Valor do saque: ");
				controller.sacar(joao, lerDouble(sc));
				break;

			case 3:
				System.out.println("\nContas disponíveis:");
				for (ContaCorrente c : contas) {
					System.out.println("Conta: " + c.getNumero() + " - " + c.getNome());
				}

				System.out.print("Digite o número da conta destino: ");
				int numeroDestino = lerInt(sc);

				ContaCorrente destino = buscarConta(contas, numeroDestino);

				if (destino == null) {
					System.out.println("Conta não encontrada.");
					break;
				}

				System.out.print("Valor da transferência: ");
				double valor = lerDouble(sc);

				controller.transferir(joao, destino, valor);
				break;

			case 4:
				controller.extrato(joao);
				break;

			case 0:
				System.out.println("Encerrando sistema...");
				break;

			default:
				System.out.println("Opção inválida.");
			}

		} while (op != 0);

		sc.close();
	}

	private static ContaCorrente buscarConta(List<ContaCorrente> contas, int numero) {
		for (ContaCorrente c : contas) {
			if (c.getNumero() == numero) {
				return c;
			}
		}
		return null;
	}

	private static int lerInt(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Entrada inválida. Digite um número inteiro.");
			sc.next();
		}
		return sc.nextInt();
	}

	private static double lerDouble(Scanner sc) {
		while (!sc.hasNextDouble()) {
			System.out.println("Entrada inválida. Digite um número válido.");
			sc.next();
		}
		return sc.nextDouble();
	}
}