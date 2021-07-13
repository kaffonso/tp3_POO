package PaDi.backend.app;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Paulo Silva
 * @version 11-05-2020
 */


public class Chave<T> implements  Serializable {
    T obPasse;
    private final String idChave;
    private final LocalDateTime data;
    private final long idPasse;
    private final String nome;

    public Chave(String nome, LocalDateTime data, long idPasse) {
        this.data = data;
        this.idPasse = idPasse;
        this.nome = nome;
        this.idChave = gerarChave();
    }

    public Chave(int numero, LocalDateTime data) {
        this.data = data;
        this.nome = "";
        this.idPasse = numero;
        this.idChave = gerarChave();
    }

    private String inverter(String s) {
        StringBuilder aux = new StringBuilder();
        for (int i=s.length()-1; i >= 0; i--)
            aux.append(s.charAt(i));

        return aux.toString();

    }
    private String gerarChave() {
        StringBuilder auxChave = new StringBuilder();
        String primNome, ultNome;

        String[] sNome = nome.split(" ");

        primNome = sNome[0];
        ultNome = sNome[1];


        auxChave.append(inverter(primNome).toUpperCase());
        auxChave.append(inverter(ultNome).toUpperCase());
        auxChave.append(data);
        auxChave.append(Long.toString(idPasse));

        return auxChave.toString();

    }

    /**
     * @return the idChave
     */
    public String getIdChave() {
        return idChave;
    }

    @Override
    public String toString() {
        return idChave;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idChave == null) ? 0 : idChave.hashCode());
        result = prime * result + (int) (idPasse ^ (idPasse >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Chave<?> other = (Chave<?>) obj;
        if (idChave == null) {
            if (other.idChave != null)
                return false;
        } else if (!idChave.equals(other.idChave))
            return false;
        if (idPasse != other.idPasse)
            return false;
        return true;
    }

}
