package controller;

import java.util.List;

import model.Aluno;
import service.AlunoService;

public class AlunoController {

    private AlunoService service = new AlunoService();

    public void relatorioIndividual(List<Aluno> alunos) {
        System.out.println("\n===== RELATORIO INDIVIDUAL =====");

        for (Aluno a : alunos) {
            System.out.printf(
                "%s | Notas: %.1f, %.1f, %.1f | Média: %.2f | %s\n",
                a.getNome(),
                a.getNotas()[0],
                a.getNotas()[1],
                a.getNotas()[2],
                a.getMedia(),
                a.getStatusAluno()
            );
        }
    }

    public void estatisticas(List<Aluno> alunos) {
        double maior = service.maiorMedia(alunos);
        double menor = service.menorMedia(alunos);
        double media = service.mediaGeral(alunos);

        System.out.println("\n===== ESTATISTICAS =====");
        System.out.printf("Maior média: %.2f\n", maior);
        System.out.printf("Menor media: %.2f\n", menor);
        System.out.printf("Média geral: %.2f\n", media);
    }

    public void distribuicao(List<Aluno> alunos) {
        int[] dist = service.distribuicao(alunos);

        System.out.println("\n===== DISTRIBUICAO =====");
        System.out.println("Aprovados: " + dist[0]);
        System.out.println("Recuperacao: " + dist[1]);
        System.out.println("Reprovados: " + dist[2]);
    }

    public void melhoresAlunos(List<Aluno> alunos) {
        double maior = service.maiorMedia(alunos);

        System.out.println("\n=== MELHORES ALUNOS ===");

        for (Aluno a : alunos) {
            if (a.getMedia() == maior) {
                System.out.println(a.getNome() + " - " + String.format("%.2f", a.getMedia()));
            }
        }
    }
}