package PaDi.backend.app;

import java.time.LocalDate;

/**
 * Classe passe festivais 
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */
public class EpasseFest extends EpasseSazonal implements Ecard {
	
	private double saldoCartao;
	private String nomeEvento;
	private Ticket ticket;
	
	

	/**
	 * @return the saldoCartao
	 */
	public double getSaldoCartao() {
		return saldoCartao;
	}

	/**
	 * @param saldoCartao the saldoCartao to set
	 */
	public void setSaldoCartao(double saldoCartao) {
		this.saldoCartao = saldoCartao;
	}

	/**
	 * @return the nomeEvento
	 */
	public String getNomeEvento() {
		return nomeEvento;
	}

	/**
	 * @param nomeEvento the nomeEvento to set
	 */
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	/**
	 * @return the ticket
	 */
	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	/**
	 * 
	 */
	public EpasseFest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param saldo
	 * @param precoViagem
	 * @param carregamento
	 * @param validade
	 */
	public EpasseFest(double saldo, double precoViagem, LocalDate carregamento, LocalDate validade) {
		super(saldo, precoViagem, carregamento, validade);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param saldo
	 * @param precoViagem
	 */
	public EpasseFest(double saldo, double precoViagem) {
		super(saldo, precoViagem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param saldo
	 * @param carregamento
	 */
	public EpasseFest(double saldo, LocalDate carregamento) {
		super(saldo, carregamento);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param precoViagem
	 */
	public EpasseFest(double precoViagem) {
		super(precoViagem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pass
	 */
	public EpasseFest(EpasseFest pass) {
		super(pass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pagarViagem(double valor) throws PgmtInvalido {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pagarViagemComPontos() throws PgmtInvalido {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregarEpass(double valor) throws ParametroInvalido {
		// TODO Auto-generated method stub
		
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
	public void pagar(double valor) throws FalhaPagamento {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double consultarSaldo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double transferirSaldo() throws FalhaPagamento {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double creditar(double valor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double debitar(double valor) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public EpasseFest clone() {
		EpasseFest aux = new EpasseFest(this);
		return aux;
		
	}
	

}
