package model;

import enums.AlunoEnums;

public class Aluno {

	private String nome;
	private double notas[] = new double[3];

	public Aluno(String nome, double[] notas) {
		this.nome = nome;
		this.notas = notas;
	}

	public String getNome() {
		return nome;
	}

	public double[] getNotas() {
		return notas;
	}

	public double getMedia() {
		double soma = 0;
		for (double nota : notas)
			soma += nota;
		return soma / notas.length;
	}

	public AlunoEnums getStatusAluno() {

		int faixa = (int) (getMedia() / 10);

		switch (faixa) {
			case 10:
			case 9:
			case 8:
			case 7:
				return AlunoEnums.APROVADO;

			case 6:
			case 5:
				return AlunoEnums.RECUPERACAO;

			default:
				return AlunoEnums.REPROVADO;
		}
	}
}
