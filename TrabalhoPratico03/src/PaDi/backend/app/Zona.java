package PaDi.backend.app;

import java.io.Serializable;

/**
 *
 * @author menelik
 */

public class Zona implements Serializable {
    private String nome;
    
    public Zona(String nome) {
        this.nome = nome;
    }
    
    public Zona(Zona z) {
    	this.nome = z.nome;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public Zona clone() {
		return new Zona(this);
    	
    }
    
    
}