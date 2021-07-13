package PaDi.backend.app;

import java.time.LocalDate;

/**
 * Classe abstract reprensta tipos passe standard
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public abstract class EpassStandard extends EPass {
    private Titular titular;
    private int pontos;
    private int numeroViagens;
    private final Chave<EpassStandard> chave;
   //private final String chave;

    public EpassStandard(String codigoDoc, TipoDocumento tipoDoc, LocalDate ddn, String nome, String email)
    {
        super();
        this.titular = new Titular(new DocumentoID(codigoDoc,tipoDoc), nome, ddn, email);
        this.chave = new Chave<EpassStandard>(nome,ddn.atStartOfDay(),super.getCodigo() );
    }

    public EpassStandard(DocumentoID docID, LocalDate ddn, String nome, String email)
    {
        super();
        this.titular = new Titular(docID, nome, ddn, email);
        this.chave = new Chave<EpassStandard>(nome,ddn.atStartOfDay(),super.getCodigo() );
    }

    public EpassStandard(DocumentoID docID, LocalDate ddn, String nome, int telemovel)
    {
        super();
        this.titular = new Titular(docID, nome, ddn,telemovel);
        this.chave = new Chave<EpassStandard>(nome,ddn.atStartOfDay(),super.getCodigo() );
    }

    public EpassStandard(DocumentoID docID, String nome, LocalDate ddn, String morada, String email, int telemovel)
    {
        super();
        this.titular = new Titular(docID, nome, ddn, morada, email, telemovel);
        this.chave = new Chave<EpassStandard>(nome,ddn.atStartOfDay(),super.getCodigo() );
    }

    public EpassStandard(EpassStandard pass) {
        super(pass);
        this.titular = pass.getTitular();
        this.pontos = pass.getPontos();
        this.numeroViagens = pass.getNumeroViagens();
        this.chave = pass.getChave();

    }

    /**
     * @return the titular
     */
    public Titular getTitular() {
        return titular;
    }
    /**
     * @param titular the titular to set
     */
    public void setTitular(Titular titular) {
        this.titular = titular;
    }
    /**
     * @return the pontos
     */
    public int getPontos() {
        return pontos;
    }
    /**
     * @param pontos the pontos to set
     */
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    /**
     * @return the numeroViagens
     */
    public int getNumeroViagens() {
        return numeroViagens;
    }
    /**
     * @param numeroViagens the numeroViagens to set
     */
    public void setNumeroViagens(int numeroViagens) {
        this.numeroViagens = numeroViagens;
    }
    /**
     * @return the chave
     */
    public Chave<EpassStandard> getChave() {
        return chave;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getCodigo()+" - ").append(getTitular().getNome().toUpperCase());
        return sb.toString();
    }
}
