package PaDi.backend.ui;

public class PaDiCLI {
	String cmd;
    private Comandos comandos;
    
    public PaDiCLI() {
    	comandos = new Comandos();
    	initComandos();
    }
    
    private void initComandos() {
    	Comando cm = new Comando("novo", "\t--criar um novo epass",
				"tipo","tipo do epass",true);
    	this.comandos.adicionar(cm);
    	
    	cm = new Comando("alterar", " --alterar os dados de um epass existente");
    	this.comandos.adicionar(cm);
    	
    	cm = new Comando("carregar", "--creditar novo valor que será o novo saldo do epass","codigo", "código do epass", true);
    	this.comandos.adicionar(cm);
    			
    	cm = new Comando("mostrar", " --mostrar detalhes de um epass", "codigo", "codigo do epass", true);
    	this.comandos.adicionar(cm);
    	   	
    	cm = new Comando("pagar","   --pagar uma viagem com um epass", "codigo", "codigo do epass", true);
    	this.comandos.adicionar(cm);
    	
    	Option[] aux = new Option[2];
    	aux[0] = new Option("origem", "código epass origem");
    	aux[1] = new Option("destino", "código epass destino");
    	cm = new Comando("passarSaldo", "--passar saldo de um epass para outro",2);
    	cm.setOption(aux);
    	this.comandos.adicionar(cm);
    	
    	cm = new Comando("info", "\t--mostrar detalhes de um epass");
    	this.comandos.adicionar(cm);
    	
    	cm = new Comando("guardar", "--exportar dados do programa para um ficheiro");
    	this.comandos.adicionar(cm);
    	
    	cm = new Comando("importar", "--importar lista de epass de um ficheiro");
    	this.comandos.adicionar(cm);
    	
    	cm = new Comando("terminar", "--terminar o programa");
    	this.comandos.adicionar(cm);

    	cm = new Comando("help", "--mostrar ajuda",
				"comando","nome do comando",false);
    	this.comandos.adicionar(cm);
    }

    public Comandos getComandos() {
    	return comandos;
    }
    /**
     * Método para apresentar uma mensgagem no ecra
     * @param ms String que será apresentada
     */
    public void  showMsg(String ms) {
		
    	System.out.println(ms);
    	Le.umChar();
    }
         
    /**
     * Método para ler uma string que representa um comando para uma funcionalidade 
     * @return String com o comando do utilizador
     */
    public String[] lerComando() {
    	String op;
    	String[] aux = new String[2];
    	String comandoIni=null;
        cmd = Le.umaString();

        int posE = cmd.indexOf(" ");
        if (posE != -1)
        	comandoIni= cmd.substring(0, posE);
        else {
        	comandoIni = cmd;
        	aux[0] = comandoIni;
        	aux[1] = "NE";
        	return aux;
        }

        int posO = cmd.indexOf("-");
        int posE2 = cmd.indexOf(' ', posO);
       
        if (posE2 != -1)
        	op = cmd.substring(posO+1, posE2);
        else 
        	op = cmd.substring(posO+1);
        
        aux[0] = comandoIni;
        aux[1] = op;
           
        return aux;
    }
    
    /**
     * Valida se uma string digitada é um comando válido do programa      
     * @param lcmd string dada pelo utilizador
     * @return array com comando e parâmetros se houver
     */
    public int validarComando(String lcmd) {
            int existe = procurar(lcmd);            
            return existe;
    }

	private int procurar(String c) {
		boolean found=false;
		int res = 0;
		for (Comando aux: comandos.getLista() )  {
			if (aux.getNome().equalsIgnoreCase(c) ) {
				found=true;
				break;
			}
			else
				res = res + 1;
		}
		return found ? res : -1;
	}

	public void showHelp() {
		showMsg(comandos.showHelp());
	}
	public void showHelp(String[] comandName) {
		showMsg(comandos.showHelp(comandName));
	}
	public void limparEcra() {
		for (int y = 0; y < 25; y++)
			System.out.println("\n");
	}
	public void showPrompt() {
		limparEcra();
		System.out.print(" PaDi. Digite seu comando  >>> ");
	}




	public boolean validarOpsComando(int pos, String lcmd) {
		boolean res=false;
		Option[] op = this.getComandos().getLista().get(pos).getOption();
	/*	for (int c=0; c < op.length; c++) {
			System.out.println("a = " + op[c].toString() );
			System.out.println("a = " + op[0].getTitle() );

		} */

		if (op != null) {
//    		System.out.println("Parametro foi "+lcmd);

			int posE = lcmd.indexOf('=');

			if ((posE == -1) && (!op[0].isObrigatorio() )) {
				return true;
			}

			if ((posE != -1) && (op[0].getTitle().equalsIgnoreCase(lcmd.substring(0, posE))) )
				res=true;
		} else if (lcmd.equals("NE") ){
			res=true;
		}

		return res;

	}

}













///**
//* Método para mostrar ajuda
//*/
//public void showhelp() {			
//       
//	System.out.println("Comandos válidos são :");
//	System.out.println("novo <Tipo de Epass> - para fazer novo ePass ");
//	System.out.println("alterar -alterar dados de um ePass");
//	System.out.println("carregar -realizar um carregamento num ePass");
//	System.out.println("mostrar -para mostrar dados de um ePass");
//	System.out.println("pagar - pagar uma viagem com ePass");
//	System.out.println("passar saldo -transferir saldo de um ePass para outro");
//	System.out.println("info -apresentar info: quantos ePass foram criados, total de saldo");
//	System.out.println("terminar -para terminar programa");
//	System.out.println("guardar - ");
//	System.out.println("importar - ");
//	System.out.println("\n");
//	System.out.println("prima ENTER tecla para continuar");
//	Le.umChar();
//
//}

//if (tamanho > 0) {
//    
//    switch (cmd) {
//        case ("novo") :  {                    
//            if (tamanho == 1) {
//            	resultado=false;
//                break;
//            }
//            	
//            else if ((tamanho == 2) && 
//                  (lcmd[1].equalsIgnoreCase("student") || lcmd[1].equalsIgnoreCase("turista") 
//                         || lcmd[1].equalsIgnoreCase("festival")) ) {
//			} else
//                resultado = false;
//            break;
//        }
//        case ("alterar") : {
//            if (tamanho == 1)
//                break;
//            else if (tamanho > 3) {resultado = false; break;}
//            
//            else if ((tamanho == 3) && 
//                  (lcmd[1].equalsIgnoreCase("nome") || lcmd[1].equalsIgnoreCase("email") 
//                         || lcmd[1].equalsIgnoreCase("telefone") 
//                         || lcmd[1].equalsIgnoreCase("morada")) ) {
//			}
//            
//            break;		
//        }
//						
//        case ("carregar") : {
//            if (tamanho == 1)
//                break;                                                
//            else if (tamanho > 3) 
//                {resultado = false; break;}
//            
//            else if  (tamanho == 3) {
//			}                                                             
//            break;		
//        }
//        
//        case ("guardar") : {
//            if (tamanho == 1)
//                break;
//        }
//        
//        case ("importar") : {
//            if (tamanho == 1)
//                break;
//        }
//    
//        case("mostrar") : {
//            if (tamanho == 1)
//                break;
//            
//            else if  (tamanho > 2) 
//                {resultado = false; break;}
//            
//            else if  (tamanho == 2) {
//			}                                                             
//            break;
//        }
//  
//        case ("passar saldo") : {
//            if (tamanho == 2)
//                break;
//                                    
//            else if ((tamanho == 1) || (tamanho > 5)) 
//                {resultado = false; break;}
//            
//            else if  (tamanho == 5) {
//			}                                                             
//            break;
//        }                
//        case ("info") : {
//            if (tamanho == 1)
//                break;
//            else if (tamanho > 1) { 
//                resultado = false; break;
//            }                                                                
//        } case ("terminar") : {
//            if (tamanho > 1) resultado = false; break;
//        } default : lcmd[0] = "NOCMD"; // quer dizer que não foi inserido nenhum comando
//    } 
//    
//}    
//
//if (!resultado && tamanho > 1) 
//    lcmd[1] = cmd.concat("PARINV"); // quer dizer que número parametros inválidos
//
//else if (!resultado && tamanho == 1) 
//    lcmd[0] = cmd.concat("NOPARAM"); // não foi passado parâmetros
//
//
//return  lcmd;
//


