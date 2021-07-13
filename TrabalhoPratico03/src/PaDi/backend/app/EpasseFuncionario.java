package PaDi.backend.app;

import java.time.LocalDate;

/**
 * Classe passe funcionario 
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public class EpasseFuncionario extends EpassStandard {

	public EpasseFuncionario(DocumentoID docID, LocalDate ddn, String nome, int telemovel) {
		super(docID, ddn, nome, telemovel);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param docID
	 * @param ddn
	 * @param nome
	 * @param email
	 */
	public EpasseFuncionario(DocumentoID docID, LocalDate ddn, String nome, String email) {
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
	public EpasseFuncionario(DocumentoID docID, String nome, LocalDate ddn, String morada, String email,
			int telemovel) {
		super(docID, nome, ddn, morada, email, telemovel);
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param pass
	 */
	public EpasseFuncionario(EpasseFuncionario pass) {
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
	public EpasseFuncionario(String codigoDoc, TipoDocumento tipoDoc, LocalDate ddn, String nome, String email) {
		super(codigoDoc, tipoDoc, ddn, nome, email);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void pagarViagem(double valor) throws PgmtInvalido {
		try {
			this.saldoValido();			
		} catch (EpassInvalido e) {
			throw new PgmtInvalido("Passe fora de Validade");
		}			
		
	}



	@Override
	public void carregarEpass(double valor) throws ParametroInvalido {
		if (valor <= 0 ) 
			throw new ParametroInvalido("valor a carregar deve ser maior que zero!");
		else {
			super.setSaldo(super.getSaldo() + valor);
			super.setCarregamento(LocalDate.now());
			super.setValidade(super.getCarregamento().plusDays(30));
		
		}		
		
		
	}

	

	@Override
	public void passarSaldo(EPass pass, double valor) throws PgmtInvalido,EpassInvalido {
		try {
			this.saldoValido();
			if ( (valor > 0) && (this.getSaldo() >= valor) ) {			
				pass.setSaldo(pass.getSaldo() + valor);
				this.setSaldo(this.getSaldo() - valor);
				this.setValidade(this.getValidade().minusDays(5));				
			} else 
				throw new  PgmtInvalido("Saldo insuficiente ou valor invalido");
			
		} catch (EpassInvalido e) {
			throw new EpassInvalido("Validade expirada ");
		}
		
			
	}

	@Override
	public boolean saldoValido() throws EpassInvalido {
		
		 boolean valido=false;
	   
	    
	     
	     this.validarEpass();
	     valido = true;
	     
		 return valido;
	      
	   
		
	}



	@Override
	public void pagarViagemComPontos() throws PgmtInvalido {
		if (this.getPontos() >= 40) {
			this.setPontos(this.getPontos() - 40);
		} else 
			throw new PgmtInvalido("Pontos insuficientes");
		
	}



	@Override
	public boolean validarEpass() throws EpassInvalido {
		boolean res=false;
		
    	Chave<EpassStandard> aux = new Chave<EpassStandard>(
									this.getTitular().getNome(), 
									this.getTitular().getDdn().atStartOfDay(),
									this.getCodigo() );    		
    	
		if (aux.equals(this.getChave()) )
				res = true;
		else 
				throw new EpassInvalido("Chave invalida!");
		
		if (this.getValidade().isBefore(LocalDate.now()))
				throw new EpassInvalido("Validade expirada");
			
		
		
		return res;
	}
	
	@Override
	public EpasseFuncionario clone() {
		EpasseFuncionario aux = new EpasseFuncionario(this);
		return aux;
		
	}

}
