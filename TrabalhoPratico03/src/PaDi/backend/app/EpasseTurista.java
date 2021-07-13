package PaDi.backend.app;

import java.time.LocalDate;

/**
 * Classe passe turista 
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public class EpasseTurista extends EpasseSazonal{
	private final TipoPasseTurista tipo;
	
	
	/**
	 * 
	 */
	public EpasseTurista(int qDias) {
		super();
		double valor = (qDias == 1 ? 500 : (qDias == 3 ? 700 : 1500));
		String t = (qDias == 1 ? "DIA" : (qDias == 3 ? "TRESDIAS" : "SEMANA"));
        this.tipo = TipoPasseTurista.valueOf(t);
        super.setSaldo(valor);
        super.setCarregamento(LocalDate.now());
        super.setValidade(LocalDate.now().plusDays(qDias));
		
	}
	

	/**
	 * 
	 */
	public EpasseTurista(int qDias, double saldo, LocalDate data) {
		super(saldo,data);
	//	double valor = (qDias == 1 ? 500 : (qDias == 3 ? 700 : 1500));
		String t = (qDias == 1 ? "DIA" : (qDias == 3 ? "TRESDIAS" : "SEMANA"));
        this.tipo = TipoPasseTurista.valueOf(t);    
        super.setValidade(LocalDate.now().plusDays(qDias));
		
	}



	/**
	 * @param pass
	 */
	public EpasseTurista(EpasseTurista pass) {
		super(pass);
		this.tipo = pass.tipo;
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void pagarViagem(double valor) throws PgmtInvalido {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pagarViagemComPontos() throws PgmtInvalido {
		throw new  PgmtInvalido("Epass não pode pagar com pontos");
		
	}

	@Override
	public void carregarEpass(double valor) throws ParametroInvalido {
		throw new  ParametroInvalido ("Epass não pode ser carregado");
		
	}

	@Override
	public boolean validarEpass() throws EpassInvalido {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void passarSaldo(EPass pass, double valor) throws PgmtInvalido, EpassInvalido {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean saldoValido() throws EpassInvalido {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public EpasseTurista clone() {
		EpasseTurista aux = new EpasseTurista(this);
		return aux;
		
	}
}
