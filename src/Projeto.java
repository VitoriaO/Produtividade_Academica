import java.util.ArrayList;
import java.util.Scanner;

public class Projeto implements Comparable<Projeto>{
	private String titulo;
	private String agencia;
	private String objetivo;
	private String descricao;
	private char status;
	private int dia_inicio;
	private int dia_termino;
	private int mes_inicio;
	private int mes_termino;
	private int ano_inicio;
	private int ano_termino;
	private float valor;
	private Professor gerente;
	private ArrayList<Colaborador> participantes;
	private ArrayList<Professor> professores;
	private ArrayList <Publicacao> producao;
	
	public Projeto(){
		titulo = "";
		agencia = "";
		objetivo = "";
		descricao = "";
		status = 0;
		dia_inicio = 0;
		dia_termino = 0;
		mes_inicio = 0;
		mes_termino = 0;
		ano_inicio = 0;
		ano_termino = 0;
		valor = 0;
		gerente = null;
		participantes = new ArrayList<Colaborador>();
		producao = new ArrayList<Publicacao>();
		professores = new ArrayList<Professor>();
	}
	
	public void setStatus(char st){
		status = st;
	}
	
	public void setGerente(Professor gerente) {
		this.gerente = gerente;
	}
	
	// Getters
	public String getTitulo() {
		return titulo;
	}
	
	public String getAgencia() {
		return agencia;
	}
	
	public String getObjetivo() {
		return objetivo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public char getStatus() {
		return status;
	}
	
	public void getData_inicio() {
		System.out.println(dia_inicio + "/" + mes_inicio + "/" + ano_inicio);
	}
	
	public void getData_termino() {
		System.out.println(dia_termino + "/" + mes_termino + "/" + ano_termino);
	}
	
	public float getValor() {
		return valor;
	}
	
	public Professor getGerente() {
		return gerente;
	}
	
	public ArrayList<Colaborador> getParticipantes() {
		return participantes;
	}
	
	public ArrayList <Publicacao> getProducao() {
		return producao;
	}
	
	public ArrayList<Professor> getProfessores(){
		return professores;
	}
	
	/** @return 1 se projeto estiver em andamento ou concluído. 2 se alguma informação básica não tiver sido preenchida. 3 se todas as informações básicas estão preenchidas, mas ainda está em elaboração */
	public int checkStatus(){
		if(status == 'a' || status == 'c'){
			return 1;
		}
		else if (titulo.isEmpty() || objetivo.isEmpty() || agencia.isEmpty() || descricao.isEmpty() || (dia_inicio == 0) || (dia_termino == 0) || (mes_inicio == 0) || (mes_termino == 0) || (ano_inicio == 0) || (ano_termino == 0) || (valor == 0) || (participantes.isEmpty()) || professores.isEmpty()){
			return 2;
		}
		else{
			return 3;
		}
	}
	
	public void modificarTitulo(Scanner ent_string){
		System.out.println("Digite o nome do projeto:");
		titulo = ent_string.nextLine();
	}
	
	public void modificarDInicio(Scanner entrada){
		System.out.println("Digite o dia de início do projeto:");
		dia_inicio = entrada.nextInt();
		System.out.println("Digite o mês de início do projeto:");
		mes_inicio = entrada.nextInt();
		System.out.println("Digite o ano de início do projeto:");
		ano_inicio = entrada.nextInt();
		if(mes_inicio > 12){
			System.out.println("Data inválida");
			dia_inicio = 0;
			mes_inicio = 0;
			ano_inicio = 0;
			return;
		}
		if(mes_inicio == 2){
			
		}
	}
	
	public void modificarDTermino(Scanner entrada){
		System.out.println("Digite o dia de término do projeto:");
		dia_termino = entrada.nextInt();
		System.out.println("Digite o mês de término do projeto:");
		mes_termino = entrada.nextInt();
		System.out.println("Digite o ano de término do projeto:");
		ano_termino = entrada.nextInt();
	}
	
	public void modificarAgencia(Scanner ent_string){
		System.out.println("Digite o nome da agência financiadora do projeto:");
		agencia = ent_string.nextLine();
	}
	
	public void modificarValorF(Scanner entrada){
		System.out.println("Digite o valor financiado a esse projeto:");
		valor = entrada.nextFloat();
	}
	
	public void modificarObjetivo(Scanner ent_string){
		System.out.println("Digite o objetivo desse projeto: (Digite 0 ao término)");
		String str = ent_string.nextLine();
		while(!ent_string.hasNextInt()){
			str = str + "\n" + ent_string.nextLine();
		}
		objetivo = str;
		ent_string.nextLine();
	}
	
	public void modificarDescricao(Scanner ent_string){
		System.out.println("Digite uma breve descrição desse projeto: (Digite 0 ao término)");
		String str = ent_string.nextLine();
		while(!ent_string.hasNextInt()){
			str = str + "\n" + ent_string.nextLine();
		}
		descricao = str;
		ent_string.nextLine();
	}
	
	public void addColaborador(Colaborador col){
		for(int i = 0; i < participantes.size(); i++){
			if(participantes.get(i).equals(col)){
				System.out.println("Esse colaborador já está associado a esse projeto");
				return;
			}
		}
		participantes.add(col);
	}
	
	public void addColaborador(Scanner ent_string, Laboratorio lab){
		Colaborador novo = null;
		System.out.println("Deseja adicionar um professor, um aluno ou um pesquisador?");
		String str = ent_string.nextLine();
		switch(str){
		case "professor":
			novo = new Professor();
			professores.add((Professor)novo);
			break;
		
		case "Professor":
			novo = new Professor();
			professores.add((Professor)novo);
			break;
			
		case "Aluno":
			novo = new Aluno();
			break;
			
		case "aluno":
			novo = new Aluno();
			break;
			
		case "pesquisador":
			novo = new Pesquisador();
			break;
		
		case "Pesquisador":
			novo = new Pesquisador();
			break;
			
		default:
			System.out.println("Opção inválida. Tente novamente.");
		}
		
		if(novo != null){
			System.out.println("Digite o nome:");
			novo.setNome(ent_string.nextLine());
			System.out.println("Digite o email:");
			novo.setEmail(ent_string.nextLine());
			novo.addProjeto(this);
			if(str.equals("Aluno") || str.equals("aluno")){
				System.out.println("Aluno de? (Graduação/Mestrado/Doutorado)");
				str = ent_string.nextLine();
				Aluno n = (Aluno)novo;
				
				if(str.equals("Graduação") || str.equals("graduação")){
					n.setTipo('g');
				}
				else if(str.equals("Mestrado") || str.equals("mestrado")){
					n.setTipo('m');
				}
				else{
					n.setTipo('d');
				}
			}
			else if((str.equals("Professor") || str.equals("professor")) && professores.size() == 1){
				Professor prof = (Professor)novo;
				prof.setGerente();
				System.out.println("Esse professor é agora o gerente desse projeto.\n"
						+ "Digite uma senha de verificação");
				str = ent_string.nextLine();
				prof.setSenha(str);
				setGerente(prof);
			}
		}
		this.participantes.add(novo);
		lab.addColaboradores(novo);
	}

	public void addProducao(Publicacao pub){
		producao.add(pub);
	}
	
	@Override
	public int compareTo(Projeto proj) {
		if(this.ano_termino > proj.ano_termino){
			return -1;
		}
		else if(this.ano_termino < proj.ano_termino){
			return 1;
		}
		else{
			if(this.mes_termino > proj.mes_termino){
				return -1;
			}
			else if(this.mes_termino < proj.mes_termino){
				return 1;
			}
			else{
				if(this.dia_termino > proj.dia_termino){
					return -1;
				}
				else if(this.dia_termino < proj.dia_termino){
					return 1;
				}
			}
		}
		return 0;
	}

	public void printProducao(){
		if(producao.isEmpty()){
			System.out.println("Não existem publicações associadas a esse projeto");
			return;
		}
		for(int i = 0; i < producao.size(); i++){
			System.out.println(producao.get(i).getTitulo());
		}
	}



}
