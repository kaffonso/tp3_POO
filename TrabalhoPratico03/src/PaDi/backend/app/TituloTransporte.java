package PaDi.backend.app;

/**
 *
 * @author menelik
 */
public interface TituloTransporte {

    final static double PRECOVIAGEM = 38;
    final static double PRECOVIAGEMSTUDENT = 20;
    final static double PRECOVIAGENSTURISTA = 50;

    public void pagarViagem(double valor) throws PgmtInvalido;
    //public int pagarViagem(double valor, String local) throws PgmtInvalido;
    public void pagarViagemComPontos() throws PgmtInvalido;
    public void carregarEpass(double valor) throws ParametroInvalido;
    public boolean validarEpass() throws EpassInvalido;
    public void passarSaldo(EPass pass, double valor) throws PgmtInvalido,EpassInvalido;
    public boolean saldoValido() throws EpassInvalido;

//    public default <T extends EpassStandard> boolean validarEpass(T passe) throws EpassInvalido {
//    	boolean res=false;
//
//    	if (passe.getClass().getName().equalsIgnoreCase("EpassStandard") ) {
//    			Chave<T> aux = new Chave<T>(
//					passe.getTitular().getNome(),
//					passe.getTitular().getDdn().atStartOfDay(),
//					passe.getCodigo() );
//    	//} else if (passe.getClass().getName().equalsIgnoreCase("EpasseSazonal") ) {
//
//		if (aux.equals(passe.getChave()) )
//				res = true;
//		else
//				throw new EpassInvalido("Chave invalida!");
//
//		}
//		return res;
//    }

}
