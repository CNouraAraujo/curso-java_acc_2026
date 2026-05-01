package service;

import java.util.List;

import model.Aluno;

public class AlunoService {
	public double maiorMedia(List<Aluno> aluno) {
		double maior = Double.MIN_VALUE;
		for (Aluno a : aluno) {
			maior = Math.max(maior, a.getMedia());
		}
		return maior;
	}

	public double menorMedia(List<Aluno> aluno) {
		double menor = Double.MAX_VALUE;
		for (Aluno a : aluno) {
			menor = Math.min(menor, a.getMedia());
		}
		return menor;
	}
	
	 public double mediaGeral(List<Aluno> alunos) {
	        double soma = 0;
	        for (Aluno a : alunos) soma += a.getMedia();
	        return soma / alunos.size();
	    }
	
	public int[] distribuicao(List<Aluno> alunos) {
		int aprovados = 0;
		int recuperacao = 0;
		int reprovados = 0;
		
		for (Aluno a: alunos) {
			switch (a.getStatusAluno()) {
			case APROVADO:
				aprovados +=1;
				break;
				
			case RECUPERACAO:
				recuperacao+=1;
				break;
				
			case REPROVADO:
				reprovados+=1;
				break;
			}
		}
		return new int[]{aprovados, recuperacao, reprovados};
	}
}
