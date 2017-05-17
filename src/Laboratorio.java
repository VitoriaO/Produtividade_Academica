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
		
		System.out.println("Projeto \"" + proj.getTitulo() + "\" criado. Status atual: \"Em elaboração\"");
		System.out.println("Este projeto só poderá passar para o status \"Em andamento\""
				+ " quando todas as informações básicas estiverem preenchidas.");
		System.out.println("São elas:\n1. Data de início;\n2. Data de término;\n3. Agência financiadora;\n"
				+ "4. Valor financiado;\n5. Objetivo;\n6. Descrição.");
		System.out.println("OBS.: Projetos devem ter pelo menos um professor associado. "
				+ "O primeiro professor associado será também o gerente desse projeto.");
		
		System.out.println("Deseja adicionar alguma informação ao projeto \"" + proj.getTitulo() + "\"? (Sim/Não)");
		str = ent_string.nextLine();
		if(str.equals("não") || str.equals("Não")){
			projetos.add(proj);
			Collections.sort(projetos);
			return;
		}
		
		do{
			System.out.println("Qual informação deseja adicionar a este projeto?");
			System.out.println("1. Data de início");
			System.out.println("2. Data de término");
			System.out.println("3. Agência financiadora");
			System.out.println("4. Valor financiado");
			System.out.println("5. Objetivo");
			System.out.println("6. Descrição");
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
				System.out.println("Selecione a opção desejada:");
				System.out.println("1. Adicionar um colaborador já existente");
				System.out.println("2. Adicionar um novo colaborador e associá-lo a esse projeto");
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
					System.err.println("Opção Inválida!");
				}
				break;
				
			default:
				System.err.println("Opção inválida. Tente novamente.");
			}
			System.out.println("Deseja adicionar outra informação ao projeto \"" + proj.getTitulo() + "\"? (Sim/Não)");
			str = ent_string.nextLine();
			if(str.equals("não") || str.equals("Não")){
				break;
			}
		}while(aux != 0);
		
		projetos.add(proj);
		Collections.sort(projetos);
	}
	
	public void editarProjeto(Scanner entrada, Scanner ent_string){
		if(projetos.isEmpty()){
			System.err.println("Não existem projetos cadastrados");
			return;
		}
		System.out.println("Edição de projetos");
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
			System.out.println("Escolha a opção desejada:");
			System.out.println("1. Alterar status");
			System.out.println("2. Alocação de colaboradores");
			System.out.println("3. Adicionar/Modificar informações básicas");
			aux = entrada.nextInt();
			
			switch(aux){
			case 1:
				System.out.println("Apenas o gerente pode modificar o status de um projeto");
				if(proj.getGerente() == null){
					System.err.println("Esse projeto não possui um gerente cadastrado\n"
							+ "Cadastre um novo professor e tente novamente");
					break;
				}
				System.out.println("Digite a senha para verificação");
				str = ent_string.nextLine();
				if(proj.getGerente().getSenha().equals(str)){
					if(proj.checkStatus() != 2){
						if(proj.getStatus() == 'e'){
							proj.setStatus('a');
							System.out.println("Status do projeto alterado de \"Em elaboração\" para \"Em andamento\"");
						}
						else if(proj.getStatus() == 'a'){
							if(proj.getProducao().isEmpty()){
								System.err.println("Não existem publicações associadas a esse projeto.\n"
										+ "Projetos só podem ser concluídos depois de possuírem publicações associadas");
								break;
							}
							proj.setStatus('c');
							System.out.println("Status do projeto alterado de \"Em andamento\" para \"Concluído\"");
						}
						else{
							System.err.println("Esse projeto já foi concluído. Por favor, tente novamente com um projeto em aberto.");
						}
					}
					else{
						System.err.println("É necessário que todas as informações básicas "
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
					System.out.println("Qual informação deseja adicionar/modificar neste projeto?");
					System.out.println("1. Data de início");
					System.out.println("2. Data de término");
					System.out.println("3. Agência financiadora");
					System.out.println("4. Valor financiado");
					System.out.println("5. Objetivo");
					System.out.println("6. Descrição");
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
						System.err.println("Opção inválida. Tente novamente.");
					}
					System.out.println("Deseja adicionar outra informação ao projeto? (Sim/Não)");
					str = ent_string.nextLine();
					if(str.equals("não") || str.equals("Não")){
						break;
					}
				}while(aux != 0);
				break;
				
			default:
				System.err.println("Opção Inválida. Por favor tente novamente.");
			}
			
			System.out.println("Deseja editar outra informação desse projeto? (Sim/Não)");
			str = ent_string.nextLine();
			if(str.equals("não") || str.equals("Não")){
				break;
			}
			
		}while(true);
	}
	
	public void relatorio(){
		System.out.println("Relatório da produção acadêmica");
		int count_e = 0, count_a = 0, count_c = 0;
		
		if(colaboradores.isEmpty()){
			System.err.println("Não existem colaboradores cadastrados");
		}
		else{
			System.out.println("Número de colaboradores: " + colaboradores.size());
		}
		if(projetos.isEmpty()){
			System.err.println("Não existem projetos cadastrados");
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
			System.out.println("Número de projetos em elaboração: " + count_e);
			System.out.println("Número de projetos em andamento: " + count_a);
			System.out.println("Número de projetos concluídos: " + count_c);
			System.out.println("Número total de projetos: " + projetos.size());
		}
		if(producao.isEmpty()){
			System.err.println("Não foram feitas publicações");
		}
		else{
			System.out.println("Produção acadêmica: ");
			for(int i = 0; i < producao.size(); i++){
				System.out.println(producao.get(i).getTitulo());
			}
		}
	}

	public void consultarColaborador(Scanner entrada){
		System.out.println("Consulta por colaborador: ");
		if(colaboradores.isEmpty()){
			System.err.println("Não existem colaboradores cadastrados");
			return;
		}
		int aux;
		Colaborador col;
		
		System.out.println("As informações de qual colaborador você deseja acessar: ");
		for(int i = 0; i < colaboradores.size(); i++){
			System.out.println((i+1) + ". " + colaboradores.get(i).getNome());
		}
		aux = entrada.nextInt();
		col = colaboradores.get(aux-1);
		
		System.out.println("Nome: " + col.getNome());
		System.out.println("Email: " + col.getEmail());
		System.out.println("Projetos concluídos: ");
		col.printProjetos('c');
		System.out.println("Projetos em andamento: ");
		col.printProjetos('a');
		System.out.println("Produção acadêmica: ");
		col.printProducao();
	}

	public void consultarProjeto(Scanner entrada){
		System.out.println("Consulta por projeto:");
		if(projetos.isEmpty()){
			System.err.println("Não existem projetos cadastrados");
			return;
		}
		int aux;
		Projeto proj;
		ArrayList<Colaborador> colab;
		ArrayList<Professor> prof;
		
		System.out.println("Qual projeto você deseja consultar: ");
		for(int i = 0; i < projetos.size(); i++){
			System.out.println((i+1) + ". " + projetos.get(i).getTitulo());
		}
		aux = entrada.nextInt();
		proj = projetos.get(aux-1);
		
		System.out.println("Titulo: " + proj.getTitulo());
		System.out.print("Data de início: ");
		proj.getData_inicio();
		System.out.print("Data de término: ");
		proj.getData_termino();
		System.out.println("Agência financiadora: " + proj.getAgencia());
		System.out.println("Valor financiado: " + proj.getValor());
		System.out.println("Objetivo: " + proj.getObjetivo());
		System.out.println("Descrição: " + proj.getDescricao());
		System.out.println("Participantes: ");
		colab = proj.getParticipantes();
		for(int i = 0; i < colab.size(); i++){
			System.out.println(colab.get(i).getNome());
		}
		System.out.println("Desses participantes os seguintes são professores:");
		prof = proj.getProfessores();
		for(int i = 0; i < prof.size(); i++){
			System.out.println(prof.get(i).getNome());
		}
		System.out.println("Com \"" + proj.getGerente().getNome() + "\" sendo o gerente do projeto");
		System.out.println("Producao Academica: ");
		proj.printProducao();
	}
	
	public void addProducao(Scanner entrada, Scanner ent_string){
		System.out.println("Adicionar informações sobre produção acadêmica");
		int aux;
		String str;
		
		System.out.println("1. Adicionar publicação");
		System.out.println("2. Associar uma publicação a um projeto");
		aux = entrada.nextInt();
		switch(aux){
		case 1:
			Publicacao pub = new Publicacao();
			int j;
			
			System.out.println("Digite o título da publicação");
			str = ent_string.nextLine();
			pub.setTitulo(str);
			System.out.println("Quantos autores essa publicação possui");
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
			System.out.println("Em que conferência foi publicada?");
			str = ent_string.nextLine();
			pub.setConferencia(str);
			System.out.println("Qual o ano de publicação");
			aux = entrada.nextInt();
			pub.setAno(aux);
			System.out.println("Deseja associar essa publicação a um projeto? (Sim/Não)");
			str = ent_string.nextLine();
			if(str.equals("Não") || str.equals("não")){
				break;
			}
			else{
				if(projetos.isEmpty()){
					System.err.println("Não existem projetos cadastrados");
					break;
				}
				System.out.println("Escolha o projeto ao qual essa publicação será associada");
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
				System.err.println("Não existem projetos cadastrados");
				break;
			}
			System.out.println("Escolha o projeto ao qual essa publicação será associada");
			for(int i = 0; i < projetos.size(); i++){
				System.out.println((i+1) + ". " + projetos.get(i).getTitulo());
			}
			int projet = entrada.nextInt();
			System.out.println("Escolha a publicação que você deseja associar a esse projeto");
			for(int i = 0; i < producao.size(); i++){
				System.out.println((i+1) + ". " + producao.get(i).getTitulo());
			}
			int produc = entrada.nextInt();
			projetos.get(projet-1).addProducao(producao.get(produc-1));
			producao.get(produc-1).setProjeto_a(projetos.get(projet-1));
			break;
			
		default:
			System.err.println("Opção inválida!");
		}
		Collections.sort(producao);
	}
}
