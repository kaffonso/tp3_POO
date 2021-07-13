package PaDi.backend.app;

import java.time.LocalDate;

/**
 * Classe representa bilhete de um evento do passe festival 
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public class Ticket {
	  private final String codigo;
	    private final String nomeEvento;
	    private final double valor;
	    private final LocalDate dataIni;
	    private final LocalDate dataFim;
	    private static int numero;
	    
	    public Ticket(String nome, double valor) {
	        this.codigo = criarCodigo();
	        this.nomeEvento = nome;
	        this.valor = valor;
	        this.dataIni = LocalDate.now();
	        this.dataFim = LocalDate.now();
	        
	    }

	    public Ticket(String nome, double valor, LocalDate dataini, LocalDate datafim) {
	        this.codigo = criarCodigo();
	        this.nomeEvento = nome;
	        this.valor = valor;
	        this.dataIni = dataini;
	        this.dataFim = datafim;
	    }
	    
	    public Ticket(Ticket t) {
	        this.codigo = t.getCodigo();
	        this.nomeEvento = t.getNomeEvento();
	        this.valor = t.getValor();
	        this.dataIni = t.getIni();
	        this.dataFim = t.getFim();
	    }

	    public String getCodigo() {
	        return codigo;
	    }

	    public String getNomeEvento() {
	        return nomeEvento;
	    }

	    public double getValor() {
	        return valor;
	    }

	    public LocalDate getIni() {
	        return dataIni;
	    }
	    
	    public LocalDate getFim() {
	        return dataFim;
	    }
	    
	    @Override
	    public String toString() {
	        return "Ticket{" + "codigo=" + codigo + ", nomeEvento=" + nomeEvento + ", valor=" + valor + ", dataIni=" + dataIni + '}';
	    }

	    @Override
	    protected Ticket clone() throws CloneNotSupportedException  {
	        Ticket aux = new Ticket(this);
	        return aux;
	    }

	    private String criarCodigo() {
	        StringBuilder auxCod = new StringBuilder();
	        LocalDate ldata = LocalDate.now();
	        
	        auxCod.append(ldata.toString());
	        
	        auxCod.append(Integer.toString(numero));
	       
	        
	        Ticket.numero++;
	        
	        return auxCod.toString();
	    }
	    
	    
}
