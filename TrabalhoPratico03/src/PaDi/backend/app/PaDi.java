package PaDi.backend.app;

import PaDi.backend.ui.Le;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.*;

import static java.lang.System.out;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * Classe que implementa trabalho prático 3 da disciplina POO
 * 
 *
 * @author Paulo Silva
 * @version 11-05-2020
 */

public class PaDi implements FuncioPaDi{
	
	private ArrayList<EPass> lPasses;
	
	public PaDi() {
		lPasses = new ArrayList<>();
		
	}
	
	public PaDi(String nomeF) throws IOException {
		lPasses = new ArrayList<>();
		
	}
	
	public PaDi(ArrayList<EPass> lp) {
		
	}
	
	public ArrayList<EPass> getlPasses() {
		return lPasses;
		
	}
	
	public void setlPassses(ArrayList<EPass> lp ) {
		ArrayList<EPass> aux = new ArrayList<EPass>();
		lPasses = null;
		for (EPass p: lp) 
			aux.add(p);
		lPasses = aux;
	}
	
	public ArrayList<EPass> executarCondicional() {
		return lPasses;
		
	}
	
	public EPass obterCondicional() {
		return null;
		
	}
	
	/**
	 * Método para importar objectos Epass para um ficheiro e criar o arraylist de passes
	 * 
	 * @param oin
	 * @param nomeF
	 * @return 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<EPass> importDados_ePass(ObjectInputStream oin, String nomeF)
			throws ClassNotFoundException, CloneNotSupportedException, FileNotFoundException {

		ArrayList<EPass> lista=new ArrayList<>();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("/home/menelik/traboi/2021/OOP/TPs/tp2/src/tp2/bd/"));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());

			try {
				oin = new ObjectInputStream(new FileInputStream(selectedFile.getAbsolutePath()));

				ArrayList<EPass> dadosEp = null;
				try {
					dadosEp = (ArrayList) oin.readObject();
				} catch (IOException e) {
					e.printStackTrace();
				}

				for (EPass p : dadosEp) {
					if (p instanceof EpasseStudent) {
						lista.add(((EpasseStudent) p).clone());
					} else if (p instanceof EpasseFuncionario) {
						lista.add(((EpasseFuncionario) p).clone());
					}
					if (p instanceof EpasseJovem) {
						lista.add(((EpasseJovem) p).clone());
					}
					if (p instanceof EpasseTI) {
						lista.add(((EpasseTI) p).clone());
					}
					if (p instanceof EpasseTurista) {
						lista.add(((EpasseTurista) p).clone());
					}
					if (p instanceof EpasseFest) {
						lista.add(((EpasseFest) p).clone());
					}
				}
				try {
					oin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}


			} catch (IOException e) {
				out.println("teste");
			}
		}
		return lista;
	}

	  /**
	  * Método para exportar objectos Epass para um ficheiro
	  * @param oout
	  * @param nome
	  * @throws IOException
	  */
	@Override
	public void exportDados_ePass(ObjectOutputStream oout, String nome) throws IOException {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("/home/menelik/traboi/2021/OOP/TPs/tp2/src/tp2/bd/"));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			oout = new ObjectOutputStream(new FileOutputStream(selectedFile));
			//oout = new ObjectOutputStream(new FileOutputStream("./bd/Epass.dat"));
			oout.writeObject(lPasses);
			out.println("Dados guardados em :"+selectedFile);
			oout.close();
		}

	}
	
	/**
	 * mostrar informação resumida sobre ePass. Quantos foram criados, total de saldo 
	 * e número de viagens realizadas
	 * 
	 */
	@Override
	public String info_ePass() {
		
		double totalSaldo=0; 
		int numeViagens=0;
                
        StringBuilder mss = new StringBuilder();
		
		if (lPasses.size() > 0) {			
			for (int i=0; i < lPasses.size(); i++) {			
				totalSaldo = totalSaldo + lPasses.get(i).getSaldo();
				//numeViagens = numeViagens + lPasses.get(i).get   getNumMovs();	
			}
			
			mss.append("------Info de ePass----------\n");
			mss.append("Total de ePass : " + (lPasses.size()) + "\n" );
			mss.append("Número de viagens realizadas : "+ numeViagens+ "\n");
			mss.append("Total de saldo nos ePass neste momento : "+ totalSaldo+ "\n");
            mss.append("---------------------------------"+ "\n");
			return mss.toString();
						
		} else 
			return ("Não existem ePasses criados.");
		
	}

	@Override
	public void pagarViagem(EPass passe) {
		System.out.println("Pagar viagem com o epasse");
		
	}

	@Override
	public int passarSaldo(EPass origem, EPass destino) {
		System.out.println("Passar saldo ");
		return 0;
	}

	@Override
	public <T extends EPass> void mostrarDados_ePass(T passe) {
		System.out.println(passe.toString());
		
			
	}

	@Override
	public void carregar(EPass passe, double valor) {
		
			try {
				passe.carregarEpass(valor);
			} catch (ParametroInvalido e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("ePass "+ passe.getCodigo() + " carregado com " + valor + "escudos");
			Le.umChar();
		
		
	}

	@Override
	public void alterarDados(EPass passe) {
		System.out.println("alterar dados do epass");
		
	}

	@Override
	public void novo(String tipoE) {
		EPass aux=null;
		switch (tipoE) {
		
		case "estudante": {
			aux = this.lerDadosEpass(new LeituraPorTipo(){

				@Override
				public EPass criarDadosEpass() {
					return lerStandard("estudante");
				}		
				
			});
			
			
			break;
			
		}
		case "funcionario": {
			aux = this.lerDadosEpass(new LeituraPorTipo() {

				@Override
				public EPass criarDadosEpass() {
					return lerStandard("funcionario");
				}		
				
			});
			
			break;
		}
		case "jovem" : {
			aux = this.lerDadosEpass(new LeituraPorTipo() {

				@Override
				public EPass criarDadosEpass() {
					return lerStandard("jovem");
				}		
				
			});
			break;
			
		}
		case "terceira idade" : {
			aux = this.lerDadosEpass(new LeituraPorTipo() {

				@Override
				public EPass criarDadosEpass() {
					return lerStandard("terceiraidade");
				}		
				
			});
			break;
			
		}
		case "turista": {
			aux = this.lerDadosEpass(new LeituraPorTipo() {

				@Override
				public EPass criarDadosEpass() {
					return lerSazonal("turista");
				}		
				
			});
			break;
			
		}
		case "festival" : {
			aux = this.lerDadosEpass(new LeituraPorTipo() {

				@Override
				public EPass criarDadosEpass() {
					return lerSazonal("festival");
				}		
				
			});
			break;
			
		}
		
	}
		if (aux != null ) {
			lPasses.add(aux);
			System.out.println(" Novo ePass com o código "+ aux.getCodigo() + " registado com sucesso! ");
			Le.umChar();
			
			
		}
		
	}
	
	/**
	    * Método para ler um número inteiro e validar se representa ou não um código
	    * de um ePass. 
	    * @return a posição no array de ePass onde foi encontrado o código ou -1 se não for encontrado
	    */
	@Override
	public int leCodigoEpass() {
			int cod;
			
			out.print("Digite código de ePass: ");
			cod = Le.umInt();
			
			return (find_ePass(cod) ); 
					
	 }
	
	/**
	 * Método que percorre o array de ePass e procura de um dado código de um ePass existe
	 * @param codigo número que estamos a procurar
	 * @return inteiro que é -1 se não encontrar e se encontrar a posição do array onde está o ePass
	 */
	public  int find_ePass(int codigo) {
		int posicao=-1;
		
		for (int i=0; i < lPasses.size(); i++) {
			if ( lPasses.get(i).getCodigo() == codigo ) {
				posicao = i;
				break;
			}										
		}
		
		return posicao;
	}
	
	
	@Override
	public EPass findEPass(int codigo) {
		EPass aux = null;
		
	//	Collections.sort(lPasses, c);
		
		for (EPass e: lPasses)  {
			if (e.getCodigo() == codigo) {
				aux = e;
				break;
			}				 			
		}
		return aux;
	}

	@Override
	public int find(int codigo) {
		// TODO Auto-generated method stub
		System.out.println("Find epass");
		return 0;
	}


	@Override
	public EPass lerDadosEpass(LeituraPorTipo tipoE) {
		
		EPass aux = tipoE.criarDadosEpass();

		return aux;
	}
	
	private EPass lerSazonal(String tipoE) {
		
//      } else if (tipoE.equalsIgnoreCase("turista")) {
//      EPassTurista turi = lerDadosTurista(new DocumentoID(codigoID, 
//                                              tipoDocumento),nome, 
//                                              datanasc, morada, email, telef);
//      
//      aux = (Epass)turi;
//     
//  } else if (tipoE.equalsIgnoreCase("festa")) {
//      
//      EPassFesta efest = lerDadosFest(new DocumentoID(codigoID, 
//                                              tipoDocumento),nome, 
//                                              datanasc, morada, email, telef);
//      aux =  (Epass)efest;
		
		
		return null;
	}

	@FXML
	private TextField nomeJovem, moradaJovem, codDoc, emailJovem,telefone;

	@FXML
	private DatePicker dataNasc;

    private EPass lerStandard(String tipoE) {

    	String nome  = nomeJovem.getText().trim();
    	String morada = moradaJovem.getText().trim();
    	String ddn = dataNasc.getValue().toString();
    	String email = emailJovem.toString().trim();
    	String codigoID = codDoc.getText().trim();

		int telef ;
    	//String nome, morada, email;

        
    	//out.println("Digite os dados do Titular do Epass: ");
				
    	//out.print("Nome : ");
    	//nome = Le.umaString();
              
    	//out.print("Data de nascimento : ");
    	//String ddn = Le.umaString();
      
    	LocalDate datanasc=null;
      
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuu"); 
    	try {
         datanasc  = LocalDate.parse(ddn, formatter);
    	}      
    	catch (DateTimeParseException e) {
          out.println("Deve inserir data no formato dia-mês-ano");
    	}
      
    	int resTipo=0;
    	do {
              out.print("Documento de Identificação : ");
              out.print("[ "+"1 -"+TipoDocumento.valueOf("BI")+ ", "+"2 -"+TipoDocumento.valueOf("PASSAPORTE")+" ]");
              resTipo = Le.umInt();
              if ( ( resTipo == 1) || ( resTipo  == 2) ) break;  
    	} while (true);
              
    	TipoDocumento tipoDocumento = (resTipo == 1) ? TipoDocumento.BI : TipoDocumento.PASSAPORTE;
                                                                
    	//out.print("Código do documento : ");
    	//String codigoID = Le.umaString();
		
    	//out.print("Morada : ");
    	//morada = Le.umaString();
		
    	//out.print("email : ");
    	//email = Le.umaString();
		
    	out.print("Telef : ");
    	telef = Le.umInt();
      
      
    	EPass aux=null;
    	
      
    	if (tipoE.equalsIgnoreCase("estudante")) {
    		EpasseStudent student = lerDadosStudent(new DocumentoID(codigoID, 
                                                  tipoDocumento),nome, 
                                                  datanasc, morada, email, telef);
          
    		System.out.println("Quer registar Zonas de viagens free? [s | n ] ");
    		String resposta = Le.umaString();
          
    		if (resposta.equalsIgnoreCase("s") ) {
    			System.out.println("Digite todas as Zonas de viagens free separados por virgula" );                                
    			resposta = Le.umaString();
    			String listaZonas[] = resposta.split(", ");
    			student.inserirZonas(listaZonas);
    			out.print("NOme das zonas:");
    			for(String s: listaZonas) 
                  out.println(s);
              
    		}
    		
    		aux = student;
    		
    	} else 	if  (tipoE.equalsIgnoreCase("funcionario")) {
    		EpasseFuncionario funcionario = new EpasseFuncionario(new DocumentoID(codigoID, 
                    tipoDocumento),nome, datanasc, morada, email, telef);
    		aux = funcionario;
    		
    	}  else  if  (tipoE.equalsIgnoreCase("jovem")) {
    		EpasseJovem jovem = new EpasseJovem(new DocumentoID(codigoID, 
                    tipoDocumento),nome, datanasc, morada, email, telef);
    		aux = jovem;
    		
    	}  else  if  (tipoE.equalsIgnoreCase("terceiraidade")) {
    		EpasseTI terceiraIdade = new EpasseTI(new DocumentoID(codigoID, 
                    tipoDocumento),nome, datanasc, morada, email, telef);
    		aux = terceiraIdade;
    		
    	}
          
   return aux;
 }


   private EpasseStudent lerDadosStudent(DocumentoID documentoID, String nome,
               LocalDate datanasc, String morada, String email, int telef) {
           
           String escola; 
           int ano;
           
                    
           out.println("Nome da escola: ");
   				
           escola = Le.umaString();
           
           out.println("Ano que frequenta: ");
           
           ano  = Le.umInt();
           
           EpasseStudent student = new EpasseStudent(documentoID, nome, 
                               datanasc, morada, email, telef, escola, ano);
           
           return student;
                   
       }
}