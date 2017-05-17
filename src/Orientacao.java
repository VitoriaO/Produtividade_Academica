
public class Orientacao extends Publicacao {
	private Aluno aluno;
	private Professor orientador;
	
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public Professor getOrientador() {
		return orientador;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}
}
