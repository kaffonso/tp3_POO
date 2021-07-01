package PaDi.app;

import java.time.LocalDate;

public class Titular {
    private DocumentoID docID;
    private String nome;
    private LocalDate dataNasc;
    private String morada;
    private String email;
    private int telefone;

    //Contrutores
    public Titular(String codigoDoc, TipoDocumento tipo, String nome, LocalDate dataNasc, String email){
        this.docID = new DocumentoID (codigoDoc, tipo);
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
    }

    public Titular(DocumentoID docID, String nome, LocalDate dataNasc, String email){
        this.docID = docID;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
    }

    public Titular(DocumentoID docID, String nome, LocalDate dataNasc, String email, int telefone){
        this.docID = docID;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.telefone = telefone;
    }

    public Titular(DocumentoID docID, String nome, LocalDate dataNasc,String morada, String email, int telefone){
        this.docID = docID;
        this.nome = nome;
        this.morada=morada;
        this.dataNasc = dataNasc;
        this.email = email;
        this.telefone = telefone;
    }

    //Setters e Getters
    public DocumentoID getDocID() {
        return docID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    //ToString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Titular{");
        sb.append("docID=").append(docID);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", dataNasc=").append(dataNasc);
        sb.append(", morada='").append(morada).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telefone=").append(telefone);
        sb.append('}');
        return sb.toString();
    }
}
