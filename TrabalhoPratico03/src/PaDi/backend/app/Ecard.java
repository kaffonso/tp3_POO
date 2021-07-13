package PaDi.backend.app;

/**
 * Interface java que declara métodos abstractos para cartão pagamento
 * 
 *
 * @author Paulo Silva
 * @version 11-05-2020
 */
public interface Ecard {
	  void pagar(double valor) throws FalhaPagamento;
	  double consultarSaldo();
	  double transferirSaldo() throws FalhaPagamento;
	  double creditar(double valor);
	  double debitar(double valor);
}
