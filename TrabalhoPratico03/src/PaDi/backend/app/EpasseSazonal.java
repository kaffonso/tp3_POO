package PaDi.backend.app;

import java.time.LocalDate;

/**
 * Classe abstracta que representa um ePass sazonal
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public abstract class EpasseSazonal extends EPass {
	private final int numeroSeq;
	private static int ultNumeroSeq;
	private final Chave<EpasseSazonal> chave;

	static {
		if (Integer.valueOf(ultNumeroSeq) == null )
			ultNumeroSeq = 0;

	}

	/**
	 * @param saldo
	 * @param precoViagem
	 * @param carregamento
	 * @param validade
	 */
	public EpasseSazonal(double saldo, double precoViagem, LocalDate carregamento, LocalDate validade) {
		super(saldo, precoViagem, carregamento, validade);
		this.numeroSeq = ultNumeroSeq + 1;
		this.chave = new Chave<EpasseSazonal>(numeroSeq, super.getEmissao() );
	}

	/**
	 * @param saldo
	 * @param precoViagem
	 */
	public EpasseSazonal(double saldo, double precoViagem) {
		super(saldo, precoViagem);
		this.numeroSeq = ultNumeroSeq + 1;
		this.chave = new Chave<EpasseSazonal>(numeroSeq, super.getEmissao() );
	}

	/**
	 * @param saldo
	 * @param carregamento
	 */
	public EpasseSazonal(double saldo, LocalDate carregamento) {
		super(saldo, carregamento);
		this.numeroSeq = ultNumeroSeq + 1;
		this.chave = new Chave<EpasseSazonal>(numeroSeq, super.getEmissao() );
	}

	/**
	 * @param precoViagem
	 */
	public EpasseSazonal(double precoViagem) {
		super(precoViagem);
		this.numeroSeq = ultNumeroSeq + 1;
		this.chave = new Chave<EpasseSazonal>(numeroSeq, super.getEmissao() );

	}

	/**
	 * @param pass
	 */
	public EpasseSazonal(EpasseSazonal pass) {
		super(pass);
		this.numeroSeq = ultNumeroSeq + 1;
		this.chave = new Chave<EpasseSazonal>(numeroSeq, super.getEmissao() );
		// TODO Auto-generated constructor stub
	}

	public EpasseSazonal()
	{
		super();
		this.numeroSeq = ultNumeroSeq + 1;
		this.chave = new Chave<EpasseSazonal>(numeroSeq, super.getEmissao() );
	}

	/**
	 * @return the numeroSeq
	 */
	public int getNumeroSeq() {
		return numeroSeq;
	}

	/**
	 * @return the ultNumeroSeq
	 */
	public static int getUltNumeroSeq() {
		return ultNumeroSeq;
	}

	/**
	 * @return the chave
	 */
	public Chave<EpasseSazonal> getChave() {
		return chave;
	}

}
