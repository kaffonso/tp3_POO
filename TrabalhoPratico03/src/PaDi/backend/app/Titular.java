package PaDi.backend.app;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa o titular de um ePass
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */
public class Titular implements Serializable {
    private final DocumentoID docID;
    private String nome;
    private LocalDate ddn;
    private String morada;
    private String email;
    private int telef;


    /**
     * @param codigoDoc
     * @param tipoDoc
     * @param nome
     * @param ddn
     * @param telef
     */
    public Titular(String codigoDoc, TipoDocumento tipoDoc, String nome, LocalDate ddn, int telef) {
        this.docID = new DocumentoID(codigoDoc,tipoDoc);
        this.nome = nome;
        this.ddn = ddn;
        this.telef = telef;
    }


    /**
     * @param docID
     * @param ddn
     * @param nome
     * @param morada
     * @param email
     * @param telef
     */
    public Titular(DocumentoID docID, String nome, LocalDate ddn, String morada, String email, int telef) {
        this.docID = docID;
        this.nome = nome;
        this.ddn = ddn;
        this.morada = morada;
        this.email = email;
        this.telef = telef;
    }

    /**
     * @param docID
     * @param ddn
     * @param nome
     * @param telef
     */
    public Titular(DocumentoID docID, String nome, LocalDate ddn, int telef) {
        this.docID = docID;
        this.nome = nome;
        this.ddn = ddn;
        this.morada = "";
        this.email = "";
        this.telef = telef;
    }

    /**
     * @param docID
     * @param ddn
     * @param nome
     * @param email
     */
    public Titular(DocumentoID docID, String nome, LocalDate ddn, String email) {
        this.docID = docID;
        this.nome = nome;
        this.ddn = ddn;
        this.morada = "";
        this.email = email;
    }

    /**
     * @return the bi
     */
    public DocumentoID getdocID() {
        return docID.clone();
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * @return the morada
     */
    public String getMorada() {
        return morada;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return the telef
     */
    public int getTelef() {
        return telef;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @param morada the morada to set
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param telef the telef to set.
     */
    public void setTelef(int telef) {
        this.telef = telef;
    }

    /**
     * @param titu
     * @return
     */
    public boolean equals(Titular titu) {
        boolean igual=false;

        if ( (this.docID.equals(titu.getdocID()) &&
                ( this.nome.equalsIgnoreCase(titu.getNome() ) ) &&
                ( this.email.equalsIgnoreCase(this.getEmail() ) )&&
                ( this.morada.equalsIgnoreCase(titu.getMorada() ) ) &&
                ( this.telef == titu.getTelef() )
        ) )
            igual=true;

        return igual;

    }

    /**
     * @return the ddn
     */
    public LocalDate getDdn() {
        return ddn;
    }

    /**
     * @param ddn the ddn to set
     */
    public void setDdn(LocalDate ddn) {
        this.ddn = ddn;
    }


    @Override
    public String toString() {
        return "Titular: [nome=" + nome + ", ddn=" + ddn + ", morada=" + morada + "]";
    }


}
