package PaDi.backend.app;

import java.time.LocalDate;

/**
 * Classe passe jovem 
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public class EpasseJovem extends EpassStandard  {

	public EpasseJovem(DocumentoID docID, LocalDate ddn, String nome, int telemovel) throws IdadeInvalida {
		super(docID, ddn, nome, telemovel);
		if (ddn.minusYears(25).isBefore(LocalDate.now()))
			throw new IdadeInvalida(ddn,"Idade invalida para passe Jovem");
		
	}


	/**
	 * @param docID
	 * @param ddn
	 * @param nome
	 * @param email
	 */
	public EpasseJovem(DocumentoID docID, LocalDate ddn, String nome, String email) {
		super(docID, ddn, nome, email);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param docID
	 * @param nome
	 * @param ddn
	 * @param morada
	 * @param email
	 * @param telemovel
	 */
	public EpasseJovem(DocumentoID docID, String nome, LocalDate ddn, String morada, String email, int telemovel) {
		super(docID, nome, ddn, morada, email, telemovel);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param pass
	 */
	public EpasseJovem(EpasseJovem pass) {
		super(pass);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param codigoDoc
	 * @param tipoDoc
	 * @param ddn
	 * @param nome
	 * @param email
	 */
	public EpasseJovem(String codigoDoc, TipoDocumento tipoDoc, LocalDate ddn, String nome, String email) {
		super(codigoDoc, tipoDoc, ddn, nome, email);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void pagarViagem(double valor) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void pagarViagemComPontos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregarEpass(double valor) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void passarSaldo(EPass pass, double valor) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public boolean saldoValido() throws EpassInvalido {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validarEpass() throws EpassInvalido {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public EpasseJovem clone() {
		EpasseJovem aux = new EpasseJovem(this);
		return aux;
		
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getCodigo()+" - ").append(getTitular().getNome().toUpperCase());
		return sb.toString();
	}
}
