package PaDi.backend.app;

public enum TipoPasseTurista {
	
//	private int dias;
//	private double valor;
//	
//	public TipoPasseTurista(int d, double v) {
//		dias = d;
//		valor = v;
//	}
//
//	/**
//	 * @return the dias
//	 */
//	public int getDias() {
//		return dias;
//	}
//
//	/**
//	 * @param dias the dias to set
//	 */
//	public void setDias(int dias) {
//		this.dias = dias;
//	}
//
//	/**
//	 * @return the valor
//	 */
//	public double getValor() {
//		return valor;
//	}
//
//	/**
//	 * @param valor the valor to set
//	 */
//	public void setValor(double valor) {
//		this.valor = valor;
//	}
//	
	
	
    DIA(1,500),
    TRESDIAS(3,700),
    SEMANA(5,1500);
    
    private final double valor;
    private final int dias;
    
    TipoPasseTurista(int qdias, double v) {
        this.dias = qdias;
        this.valor = v;
    }
    
    public int getDias() {
        return dias;
    }
    
    public double getValor() {
        return valor;
    }
}
