package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AlunoController;
import model.Aluno;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlunoController controller = new AlunoController();

        List<Aluno> alunos = new ArrayList<>();

        System.out.print("Quantos alunos? ");
        int qtd = lerInt(sc);

        for (int i = 0; i < qtd; i++) {
            System.out.println("\nAluno " + (i + 1));

            String nome;
            do {
                System.out.print("Nome: ");
                nome = sc.next();
            } while (nome.length() < 3);

            double[] notas = new double[3];

            for (int j = 0; j < 3; j++) {
                notas[j] = lerNota(sc, j + 1);
            }

            alunos.add(new Aluno(nome, notas));
        }

        controller.relatorioIndividual(alunos);
        controller.estatisticas(alunos);
        controller.distribuicao(alunos);
        controller.melhoresAlunos(alunos);

        sc.close();
    }

    private static int lerInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida.");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double lerNota(Scanner sc, int n) {
        double nota;
        while (true) {
            System.out.print("Nota " + n + ": ");
            if (sc.hasNextDouble()) {
                nota = sc.nextDouble();
                if (nota >= 0 && nota <= 100) return nota;
            } else {
                sc.next();
            }
            System.out.println("Nota inválida.");
        }
    }
}