package PaDi.backend;
import static java.lang.System.out;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import PaDi.backend.app.EPass;
import PaDi.backend.app.OperacaoSobreEpass;
import PaDi.backend.app.PaDi;
import PaDi.backend.app.TipoPasse;
import PaDi.backend.ui.Le;
import PaDi.backend.ui.PaDiCLI;
import javafx.fxml.FXML;


/**
 * Classe principal que implementa trabalho prático 3 da disciplina POO
 *
 * @author Paulo Silva
 * @version 11-05-2020
 */
public class Appasse {

    private static PaDi padi;

    private static PaDiCLI interC;
    private static ObjectOutputStream oout;
    private static ObjectInputStream oin;
    private static ArrayList<String> nomePassesStandard;
    private static ArrayList<String> nomePassesSazonal;
    private static boolean terminarPrograma=false;

    public static void main (String args[]) {

        _initPaDi();

        do {
            interC.limparEcra();
            interC.showPrompt();
            String cmdLido[] = interC.lerComando();
            int existe = interC.validarComando(cmdLido[0]);
            
           /* for (int c=0; c < cmdLido.length; c++)
                out.println("cmdLido[c] = " + cmdLido[c]); */
            StringBuilder cmdEscrito = new StringBuilder();
            for (int c=0; c < cmdLido.length; c++)
                cmdEscrito.append(cmdLido[c]+" ");

            if (existe != -1) {
                if  ( (interC.validarOpsComando(existe, cmdLido[1]) ) )
                    executarComandoOp(cmdLido);
                else
                    interC.showMsg("Opções do comando inválidos "+cmdEscrito);
            } else {

                interC.showMsg("Comando inválido.Utilize o comando Help");
            }

        } while (!terminarPrograma);
    }

    private static void executarComandoOp(String[] comando) {
        String nomeComando = comando[0].trim().toLowerCase();
        switch (nomeComando) {
            case "novo": {
                String[] opCms = comando[1].split("=");
                __novo(opCms);
                break;
            }
            case "alterar": {
                __alterar();
                break;
            }
            case "carregar": {
                String[] opCms = comando[1].split("=");
                __carregar(Integer.valueOf(opCms[1]));
                break;
            }
            case "mostrar": {
                //__mostrar();
                String[] opCms = comando[1].split("=");
                __mostrar(Integer.valueOf(opCms[1]));
                break;
            }
            case "pagar": {
                System.out.println("Pagar");
                break;
            }
            case "passarSaldo": {
                System.out.println("Passar Saldo");
                break;
            }
            case "info": {
                interC.showMsg(padi.info_ePass());
                break;
            }
            case "guardar": {
                try {
                    padi.exportDados_ePass(oout, "nome");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            case "importar": {
                try {
                    String ficheiroImport = "/home/menelik/traboi/2021/OOP/TPs/tp2/src/tp2/bd/Epass.dat";
                    padi.setlPassses(padi.importDados_ePass(oin, ficheiroImport));
                    interC.showMsg("Dados de epass importados com sucesso!");
                } catch ( ClassNotFoundException |  CloneNotSupportedException  e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    interC.showMsg("Ficheiro de dados não encontrado");
                }
                break;
            }
            case "terminar": {

                terminarPrograma=true;
                break;
            }
            case "help": {
                if (comando[1].equalsIgnoreCase("NE")) {
                    interC.showHelp();
                } else {
                    interC.showHelp(comando);
                }

                break;
            }


        }
    }

    private static void __mostrar(int codigo) {
        //int codigo = padi.leCodigoEpass();
        EPass aux = padi.findEPass(codigo);
        if (aux != null) {
            //EPass aux = padi.getlPasses().get(codigo);
            padi.mostrarDados_ePass(aux);
            Le.umChar();
        } else
            interC.showMsg("Código de epass inválido ou não existe!");

    }

    private static void __carregar(int codigo) {
        double valor;

        out.print("Digite valor de carregamento : ");
        valor = Le.umDouble();
        if (valor > 0) {
        //    codigo = padi.leCodigoEpass();
            padi.carregar(padi.findEPass(codigo),valor);
        }

    }

    private static void __alterar() {
        System.out.println("Alterar");

    }

    @FXML
    private static void __novo(String[] opCms) {

        if (opCms[1].equalsIgnoreCase("estudante")) {
            __addNovoEpass(padi,new TipoPasse() {
                @Override
                public void novo() {
                    padi.novo("estudante");
                }

            });
        } else if ( (opCms[1].equalsIgnoreCase("funcionario")) ||
                (opCms[1].equalsIgnoreCase("jovem")) ||
                (opCms[1].equalsIgnoreCase("ti")) ) {
            __addNovoEpass(padi,new TipoPasse() {
                @Override
                public void novo() {
                    padi.novo(opCms[1]);
                }

            });


        } else if (opCms[1].equalsIgnoreCase("turista")) {
            __addNovoEpass(padi,new TipoPasse() {
                @Override
                public void novo() {
                    padi.novo("turista");
                }

            });
        } else if (opCms[1].equalsIgnoreCase("fest")) {
            __addNovoEpass(padi,new TipoPasse() {
                @Override
                public void novo() {
                    padi.novo("fest");
                }

            });
        } else
            out.println("Parâmetro inválido");
            Le.umChar();
    }

    private static void __addNovoEpass(PaDi padi2, TipoPasse tipoPasse) {
        tipoPasse.novo();
    }

    private static void _initPaDi() {
        interC=new PaDiCLI();
        padi = new PaDi();
        nomePassesStandard = new ArrayList<>();
        nomePassesSazonal = new ArrayList<>();

        nomePassesStandard.add("estudante");
        nomePassesStandard.add("jovem");
        nomePassesStandard.add("funcionario");
        nomePassesStandard.add("terceira idade");

        nomePassesSazonal.add("fest");
        nomePassesStandard.add("turista");

    }

    private static <T extends EPass> boolean operarEpass(OperacaoSobreEpass<T> opEpass, EPass ep) {

        return false;
    }

}
















//	/**
//	 * Método para pedir os dados ao utilizador e criar um novo ePass
//	 *
//     * @param tipoE
//	 */
//	public static void novo_ePass(String tipoE) {
//
//		EPass novopasse;
//
//		interC.limparEcra();
//
//		out.println(" aplPasses | novo "+ tipoE +" >>> ");
//
//		try {
//			novopasse = lerDadosEpass(tipoE);
//			passes.add(novopasse);
//			interC.showMsg(" Novo ePass criado ok ");
//		} catch (IdadeInvalida e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * Ler dados necessários criar um Epass e retornar objecto criado
//	 * @param tipoE tipo de objecto que se vai criar pode ser student, funcionario, jovem, etc ...
//	 * @return objecto criado
//	 * @throws IdadeInvalida
//	 */
//	private static EPass lerDadosEpass(String tipoE) throws IdadeInvalida {
//		EPass novoEpass=null;
//        String nome, morada, email;
//        int telef;
//
//        out.println("Digite os dados que a seguir são solicitados: ");
//
//        out.print("Nome : ");
//        nome = Le.umaString();
//
//        out.print("Data de nascimento : ");
//        String ddn = Le.umaString();
//
//        LocalDate datanasc=null;
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuu");
//        try {
//           datanasc  = LocalDate.parse(ddn, formatter);
//        }
//
//        catch (DateTimeParseException e) {
//            out.println("Deve inserir data no formato dia-mês-ano");
//        }
//
//        int resTipo=0;
//        do {
//                out.print("Documento de Identificação : ");
//                out.print("[ "+"1 -"+TipoDocumento.valueOf("BI")+ ", "+"2 -"+TipoDocumento.valueOf("PASSAPORTE")+" ]");
//                resTipo = Le.umInt();
//                if ( ( resTipo == 1) || ( resTipo  == 2) ) break;
//        } while (true);
//
//        TipoDocumento tipoDocumento = (resTipo == 1) ? TipoDocumento.BI : TipoDocumento.PASSAPORTE;
//
//        out.print("Código do documento : ");
//        String codigoID = Le.umaString();
//
//        DocumentoID docID = new DocumentoID(codigoID, tipoDocumento);
//
//
//        out.print("Morada : ");
//        morada = Le.umaString();
//
//        out.print("email : ");
//        email = Le.umaString();
//
//        out.print("Telef : ");
//        telef = Le.umInt();
//        Titular donoEpass=null;
//        if ( (!morada.isEmpty() ) && (!email.isEmpty() ) ) {
//        	donoEpass = new Titular(docID, nome, datanasc, morada, email, telef);
//        }
//
//
//        if ( nomePassesStandard.contains(tipoE.toLowerCase()) )
//        	novoEpass = _criarEpassStandard(donoEpass, tipoE);
//
//		else if ( nomePassesSazonal.contains(tipoE.toLowerCase()) )
//        	novoEpass = _criarEpassSazonal(tipoE);
//
//        return novoEpass;
//
//
//	}
//
//	/**
//	 * Método para pedir dados e criar epass do tipo sazonal.
//	 * @param tipoE String que indica qual o epasse que se quer criar
//	 * @return Objecto que representar o epass criado
//	 */
//	private static EPass _criarEpassSazonal(String tipoE) {
//
//		return null;
//	}
//
//	/**
//	 * Método para pedir dados e criar epass do tipo standard
//	 * @exception Pode lançar a excepção IdadeInvalida quando a idade do titular do epass é inválida
//	 * para o tipo de epass que se quer criar.
//	 * @param tipoE String que indica qual o epasse que se quer criar
//	 * @return Objecto que representar o epass criado
//	 */
//	private static EPass _criarEpassStandard(Titular donoEpass, String tipoE) throws IdadeInvalida {
//		Epass aux = null;
//		if (tipoE.equals("student") ){
//			  out.print("Escola : ");
//		      String nomeEscola = Le.umaString();
//
//		      out.print("Ano : ");
//		      int anoEscola = Le.umInt();
//
//		      if (LocalDate.now().getYear() - donoEpass.getDdn().getYear() <= 18) {
//		    	aux  = new EpasseStudent(PRECOVIAGEM_ESTUDANTE, donoEpass, anoEscola, nomeEscola);
//		      } else
//		    	  throw new IdadeInvalida();
//
//		} else if (tipoE.equals("jovem") ) {
//			// Completar código para Sprint 3
//
//		} else if (tipoE.equals("funcionario") ) {
//			// Completar código para Sprint 4
//
//		}  else if (tipoE.equals("terceira idade") ) {
//			// Completar código para Sprint 3
//		}
//
//		return aux;
//	}







//
//
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import static java.lang.System.out;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author menelik
// */
//public class AppPaDI {
//    private static ObjectOutputStream oout;
//    private static ObjectInputStream oin;
//
//    private static PaDiCLI inter;
//
//    public static void main (String args[]) {
//
//    		int local1, local2, cod;
//    		boolean querSair=false;
//
//
//           inter = new PaDiCLI();
//
//           do {
//        	   PaDiCLI.limparEcra();
//        	   PaDiCLI.showPrompt();
//               String[] cmdEscrito = PaDiCLI.lerComando();
//
//               String cmd[] = PaDiCLI.validarComando(cmdEscrito);
//
//               if ( ( (cmd.length == 1) && (cmd[0].equals("NOCMD") ) ) ||
//                                ( (cmd.length > 1) && (cmd[0].contains("NOPARAM") ) ) ||
//                                ( (cmd.length > 1) && (cmd[1].contains("INV") ) ) ) {
//            	   	PaDiCLI.showMsg("Comando inválido");
//                            for (String s: cmd)
//                                out.print(s+" ");
//                            Le.umChar();
//
//                            continue;
//                        }
//
//			if  ( cmd[0].equalsIgnoreCase("novo") ) {
//                            if (cmd.length == 1)
//				novo_ePass("ePass");
//                            else if (cmd.length == 2)
//                                novo_ePass(cmd[1]);
//
//			} else if (cmd[0].equalsIgnoreCase("alterar")) {
//				if (passes.size() > 0) {
//					local1=leCodigoEpass();
//					if ( local1 == -1  )
//						inter.showMsg("Código de ePass não existe! ");
//					else
//						alterarDados_ePass(local1);
//				} else
//					inter.showMsg("Funcionalidade indisponível. Não há ePass criados neste momento");
//
//			} else if (cmd[0].equalsIgnoreCase("carregar")) {
//				if (passes.size() > 0) {
//					local1 = leCodigoEpass();
//					if ( local1 == -1  )
//						inter.showMsg("Código de ePass não existe! ");
//					else
//						carregar_ePass(local1);
//				} else
//					inter.showMsg("Funcionalidade indisponível. Não há ePass criados neste momento");
//
//			} else if (cmd[0].equalsIgnoreCase("mostrar")) {
//				if (passes.size() > 0) {
//					local1 = leCodigoEpass();
//					if ( local1 == -1  )
//						inter.showMsg("Código de ePass não existe! ");
//					else
//						mostrarDados_ePass(local1);
//				} else
//					inter.showMsg("Funcionalidade indisponível. Não há ePass criados neste momento");
//
//			} else if (cmd[0].equalsIgnoreCase("guardar")) {
//
//                            try {
//                                exportDados_ePass();
//                            } catch (IOException ex) {
//                                Logger.getLogger(AplPasse.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//
//                        } else if (cmd[0].equalsIgnoreCase("importar")) {
//
//                            try {
//                                importDados_ePass();
//                            } catch (IOException ex) {
//                                Logger.getLogger(AplPasse.class.getName()).log(Level.SEVERE, null, ex);
//                            } catch (ClassNotFoundException ex) {
//                                Logger.getLogger(AplPasse.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//
//                        } else if (cmd[0].equalsIgnoreCase("pagar")) {
//				if (passes.size() > 0) {
//					local1 = leCodigoEpass();
//					if ( local1 == -1  )
//						inter.showMsg("Código de ePass não existe! ");
//					else
//						pagarViagem_ePass(local1);
//				} else
//					inter.showMsg("Funcionalidade indisponível. Não há ePass criados neste momento");
//
//			} else if (cmd[0].equalsIgnoreCase("passar saldo")) {
//				if (passes.size() > 0) {
//					local1 = leCodigoEpass();
//					if ( local1 == -1  )
//						inter.showMsg("Código de ePass não existe! ");
//					else {
//						out.print("Digite código de ePass para qual quer passar saldo: ");
//						cod = Le.umInt();
//						local2 = find_ePass(cod);
//						if (local2 != -1 )
//							if( passarSaldo_ePass(local1, local2) == 1)
//								inter.showMsg("Saldo transferido ok");
//							else
//								inter.showMsg("Saldo insuficiente para transferir o valor pretendido");
//						else
//							inter.showMsg("Código de ePass para qual quer passar saldo não existe");
//					}
//
//				} else
//					inter.showMsg("Funcionalidade indisponível. Não há ePass criados neste momento");
//
//
//			} else if (cmd[0].equalsIgnoreCase("info")) {
//				info_ePass();
//
//			} else if (cmd[0].equalsIgnoreCase("terminar")) {
//				break;
//
//
//			}	else
//				inter.showhelp();
//
//		} while (!querSair);
//
//	}
//
//
//
//}
//
