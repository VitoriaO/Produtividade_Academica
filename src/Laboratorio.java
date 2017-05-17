import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Laboratorio {
	private ArrayList<Projeto> projetos;
	private ArrayList<Colaborador> colaboradores;
	private ArrayList<Publicacao> producao;
	
	public Laboratorio(){
		projetos = new ArrayList<Projeto>();
		colaboradores = new ArrayList<Colaborador>();
		producao = new ArrayList<Publicacao>();
	}
	
	//Getters
	public ArrayList<Projeto> getProjetos() {
		return projetos;
	}
	
	public ArrayList<Colaborador> getColaboradores() {
		return colaboradores;
	}
	
	public ArrayList<Publicacao> getProducao() {
		return producao;
	}
	
	//Setters
	public void addColaboradores(Colaborador colab) {
		colaboradores.add(colab);
	}
	
	public void addPublicacao(Publicacao prod) {
		producao.add(prod);
	}
	
	public void addproj(Scanner entrada, Scanner ent_string){
		System.out.println("Adicionar um novo projeto");
		Projeto proj = new Projeto();
		String str;
		int aux;
		
		proj.modificarTitulo(ent_string);
		proj.setStatus('e');
		
		System.out.println("Projeto \"" + proj.getTitulo() + "\" criado. Status atual: \"Em elabora��o\"");
		System.out.println("Este projeto s� poder� passar para o status \"Em andamento\""
				+ " quando todas as informa��es b�sicas estiverem preenchidas.");
		System.out.println("S�o elas:\n1. Data de in�cio;\n2. Data de t�rmino;\n3. Ag�ncia financiadora;\n"
				+ "4. Valor financiado;\n5. Objetivo;\n6. Descri��o.");
		System.out.println("OBS.: Projetos devem ter pelo menos um professor associado. "
				+ "O primeiro professor associado ser� tamb�m o gerente desse projeto.");
		
		System.out.println("Deseja adicionar alguma informa��o ao projeto \"" + proj.getTitulo() + "\"? (Sim/N�o)");
		str = ent_string.nextLine();
		if(str.equals("n�o") || str.equals("N�o")){
			projetos.add(proj);
			Collections.sort(projetos);
			return;
		}
		
		do{
			System.out.println("Qual informa��o deseja adicionar a este projeto?");
			System.out.println("1. Data de in�cio");
			System.out.println("2. Data de t�rmino");
			System.out.println("3. Ag�ncia financiadora");
			System.out.println("4. Valor financiado");
			System.out.println("5. Objetivo");
			System.out.println("6. Descri��o");
			System.out.println("7. Colaboradores");
			aux = entrada.nextInt();
			
			switch(aux){
			case 1:
				proj.modificarDInicio(entrada);
				break;
				
			case 2:
				proj.modificarDTermino(entrada);
				break;
				
			case 3:
				proj.modificarAgencia(ent_string);
				break;
				
			case 4:
				proj.modificarValorF(entrada);
				break;
				
			case 5:
				proj.modificarObjetivo(ent_string);
				break;
				
			case 6:
				proj.modificarDescricao(ent_string);
				break;
				
			case 7:
				System.out.println("Selecione a op��o desejada:");
				System.out.println("1. Adicionar um colaborador j� existente");
				System.out.println("2. Adicionar um novo colaborador e associ�-lo a esse projeto");
				aux = entrada.nextInt();
				switch(aux){
				case 1:
					System.out.println("Escolha um colaborador:");
					int i;
					for(i = 0; i < colaboradores.size(); i++){
						System.out.println((i+1) + ". " + colaboradores.get(i).getNome());
					}
					proj.addColaborador(colaboradores.get(i-1));
					break;
					
				case 2:
					proj.addColaborador(ent_string, this);
					break;
					
				default:
					System.err.println("Op��o Inv�lida!");
				}
				break;
				
			default:
				System.err.println("Op��o inv�lida. Tente novamente.");
			}
			System.out.println("Deseja adicionar outra informa��o ao projeto \"" + proj.getTitulo() + "\"? (Sim/N�o)");
			str = ent_string.nextLine();
			if(str.equals("n�o") || str.equals("N�o")){
				break;
			}
		}while(aux != 0);
		
		projetos.add(proj);
		Collections.sort(projetos);
	}
	
	public void editarProjeto(Scanner entrada, Scanner ent_string){
		if(projetos.isEmpty()){
			System.err.println("N�o existem projetos cadastrados");
			return;
		}
		System.out.println("Edi��o de projetos");
		int aux;
		Projeto proj;
		String str;
		
		System.out.println("Qual projeto deseja modificar?");
		for(int i = 0; i < projetos.size(); i++){
			System.out.println((i+1) + ". " + projetos.get(i).getTitulo());
		}
		aux = entrada.nextInt();
		
		proj = projetos.get(aux-1);
		
		do{
			System.out.println("Escolha a op��o desejada:");
			System.out.println("1. Alterar status");
			System.out.println("2. Aloca��o de colaboradores");
			System.out.println("3. Adicionar/Modificar informa��es b�sicas");
			aux = entrada.nextInt();
			
			switch(aux){
			case 1:
				System.out.println("Apenas o gerente pode modificar o status de um projeto");
				if(proj.getGerente() == null){
					System.err.println("Esse projeto n�o possui um gerente cadastrado\n"
							+ "Cadastre um novo professor e tente novamente");
					break;
				}
				System.out.println("Digite a senha para verifica��o");
				str = ent_string.nextLine();
				if(proj.getGerente().getSenha().equals(str)){
					if(proj.checkStatus() != 2){
						if(proj.getStatus() == 'e'){
							proj.setStatus('a');
							System.out.println("Status do projeto alterado de \"Em elabora��o\" para \"Em andamento\"");
						}
						else if(proj.getStatus() == 'a'){
							if(proj.getProducao().isEmpty()){
								System.err.println("N�o existem publica��es associadas a esse projeto.\n"
										+ "Projetos s� podem ser conclu�dos depois de possu�rem publica��es associadas");
								break;
							}
							proj.setStatus('c');
							System.out.println("Status do projeto alterado de \"Em andamento\" para \"Conclu�do\"");
						}
						else{
							System.err.println("Esse projeto j� foi conclu�do. Por favor, tente novamente com um projeto em aberto.");
						}
					}
					else{
						System.err.println("� necess�rio que todas as informa��es b�sicas "
								+ "estejam preenchidas para que o status do projeto possa ser alterado");
					}
				}
				else{
					System.err.println("Senha incorreta.");
				}
				break;
				
			case 2:
				proj.addColaborador(ent_string, this);
				break;
				
			case 3:
				do{
					System.out.println("Qual informa��o deseja adicionar/modificar neste projeto?");
					System.out.println("1. Data de in�cio");
					System.out.println("2. Data de t�rmino");
					System.out.println("3. Ag�ncia financiadora");
					System.out.println("4. Valor financiado");
					System.out.println("5. Objetivo");
					System.out.println("6. Descri��o");
					System.out.println("7. Colaboradores");
					aux = entrada.nextInt();
					
					switch(aux){
					case 1:
						proj.modificarDInicio(entrada);
						break;
						
					case 2:
						proj.modificarDTermino(entrada);
						break;
						
					case 3:
						proj.modificarAgencia(ent_string);
						break;
						
					case 4:
						proj.modificarValorF(entrada);
						break;
						
					case 5:
						proj.modificarObjetivo(ent_string);
						break;
						
					case 6:
						proj.modificarDescricao(ent_string);
						break;
						
					case 7:
						proj.addColaborador(ent_string, this);
						break;
						
					default:
						System.err.println("Op��o inv�lida. Tente novamente.");
					}
					System.out.println("Deseja adicionar outra informa��o ao projeto? (Sim/N�o)");
					str = ent_string.nextLine();
					if(str.equals("n�o") || str.equals("N�o")){
						break;
					}
				}while(aux != 0);
				break;
				
			default:
				System.err.println("Op��o Inv�lida. Por favor tente novamente.");
			}
			
			System.out.println("Deseja editar outra informa��o desse projeto? (Sim/N�o)");
			str = ent_string.nextLine();
			if(str.equals("n�o") || str.equals("N�o")){
				break;
			}
			
		}while(true);
	}
	
	public void relatorio(){
		System.out.println("Relat�rio da produ��o acad�mica");
		int count_e = 0, count_a = 0, count_c = 0;
		
		if(colaboradores.isEmpty()){
			System.err.println("N�o existem colaboradores cadastrados");
		}
		else{
			System.out.println("N�mero de colaboradores: " + colaboradores.size());
		}
		if(projetos.isEmpty()){
			System.err.println("N�o existem projetos cadastrados");
		}
		else{
			for(int i = 0; i < projetos.size(); i++){
				if(projetos.get(i).getStatus() == 'e'){
					count_e++;
				}
				else if(projetos.get(i).getStatus() == 'a'){
					count_a++;
				}
				else{
					count_c++;
				}
			}
			System.out.println("N�mero de projetos em elabora��o: " + count_e);
			System.out.println("N�mero de projetos em andamento: " + count_a);
			System.out.println("N�mero de projetos conclu�dos: " + count_c);
			System.out.println("N�mero total de projetos: " + projetos.size());
		}
		if(producao.isEmpty()){
			System.err.println("N�o foram feitas publica��es");
		}
		else{
			System.out.println("Produ��o acad�mica: ");
			for(int i = 0; i < producao.size(); i++){
				System.out.println(producao.get(i).getTitulo());
			}
		}
	}

	public void consultarColaborador(Scanner entrada){
		System.out.println("Consulta por colaborador: ");
		if(colaboradores.isEmpty()){
			System.err.println("N�o existem colaboradores cadastrados");
			return;
		}
		int aux;
		Colaborador col;
		
		System.out.println("As informa��es de qual colaborador voc� deseja acessar: ");
		for(int i = 0; i < colaboradores.size(); i++){
			System.out.println((i+1) + ". " + colaboradores.get(i).getNome());
		}
		aux = entrada.nextInt();
		col = colaboradores.get(aux-1);
		
		System.out.println("Nome: " + col.getNome());
		System.out.println("Email: " + col.getEmail());
		System.out.println("Projetos conclu�dos: ");
		col.printProjetos('c');
		System.out.println("Projetos em andamento: ");
		col.printProjetos('a');
		System.out.println("Produ��o acad�mica: ");
		col.printProducao();
	}

	public void consultarProjeto(Scanner entrada){
		System.out.println("Consulta por projeto:");
		if(projetos.isEmpty()){
			System.err.println("N�o existem projetos cadastrados");
			return;
		}
		int aux;
		Projeto proj;
		ArrayList<Colaborador> colab;
		ArrayList<Professor> prof;
		
		System.out.println("Qual projeto voc� deseja consultar: ");
		for(int i = 0; i < projetos.size(); i++){
			System.out.println((i+1) + ". " + projetos.get(i).getTitulo());
		}
		aux = entrada.nextInt();
		proj = projetos.get(aux-1);
		
		System.out.println("Titulo: " + proj.getTitulo());
		System.out.print("Data de in�cio: ");
		proj.getData_inicio();
		System.out.print("Data de t�rmino: ");
		proj.getData_termino();
		System.out.println("Ag�ncia financiadora: " + proj.getAgencia());
		System.out.println("Valor financiado: " + proj.getValor());
		System.out.println("Objetivo: " + proj.getObjetivo());
		System.out.println("Descri��o: " + proj.getDescricao());
		System.out.println("Participantes: ");
		colab = proj.getParticipantes();
		for(int i = 0; i < colab.size(); i++){
			System.out.println(colab.get(i).getNome());
		}
		System.out.println("Desses participantes os seguintes s�o professores:");
		prof = proj.getProfessores();
		for(int i = 0; i < prof.size(); i++){
			System.out.println(prof.get(i).getNome());
		}
		System.out.println("Com \"" + proj.getGerente().getNome() + "\" sendo o gerente do projeto");
		System.out.println("Producao Academica: ");
		proj.printProducao();
	}
	
	public void addProducao(Scanner entrada, Scanner ent_string){
		System.out.println("Adicionar informa��es sobre produ��o acad�mica");
		int aux;
		String str;
		
		System.out.println("1. Adicionar publica��o");
		System.out.println("2. Associar uma publica��o a um projeto");
		aux = entrada.nextInt();
		switch(aux){
		case 1:
			Publicacao pub = new Publicacao();
			int j;
			
			System.out.println("Digite o t�tulo da publica��o");
			str = ent_string.nextLine();
			pub.setTitulo(str);
			System.out.println("Quantos autores essa publica��o possui");
			aux = entrada.nextInt();
			System.out.println("Digite os nomes dos autores, separados por uma quebra de linha");
			for(int i = 0; i < aux; i++){
				str = ent_string.nextLine();
				for(j = 0; j < colaboradores.size(); i++){
					if(colaboradores.get(j).getNome().equals(str)){
						break;
					}
				}
				pub.addAutores(colaboradores.get(j));
			}
			System.out.println("Em que confer�ncia foi publicada?");
			str = ent_string.nextLine();
			pub.setConferencia(str);
			System.out.println("Qual o ano de publica��o");
			aux = entrada.nextInt();
			pub.setAno(aux);
			System.out.println("Deseja associar essa publica��o a um projeto? (Sim/N�o)");
			str = ent_string.nextLine();
			if(str.equals("N�o") || str.equals("n�o")){
				break;
			}
			else{
				if(projetos.isEmpty()){
					System.err.println("N�o existem projetos cadastrados");
					break;
				}
				System.out.println("Escolha o projeto ao qual essa publica��o ser� associada");
				for(int i = 0; i < projetos.size(); i++){
					System.out.println((i+1) + ". " + projetos.get(i).getTitulo());
				}
				int projet = entrada.nextInt();
				projetos.get(projet-1).addProducao(pub);
				pub.setProjeto_a(projetos.get(projet-1));
			}
			break;
			
		case 2:
			if(projetos.isEmpty()){
				System.err.println("N�o existem projetos cadastrados");
				break;
			}
			System.out.println("Escolha o projeto ao qual essa publica��o ser� associada");
			for(int i = 0; i < projetos.size(); i++){
				System.out.println((i+1) + ". " + projetos.get(i).getTitulo());
			}
			int projet = entrada.nextInt();
			System.out.println("Escolha a publica��o que voc� deseja associar a esse projeto");
			for(int i = 0; i < producao.size(); i++){
				System.out.println((i+1) + ". " + producao.get(i).getTitulo());
			}
			int produc = entrada.nextInt();
			projetos.get(projet-1).addProducao(producao.get(produc-1));
			producao.get(produc-1).setProjeto_a(projetos.get(projet-1));
			break;
			
		default:
			System.err.println("Op��o inv�lida!");
		}
		Collections.sort(producao);
	}
}
