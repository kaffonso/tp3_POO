package PaDi.app;

import java.time.LocalDate;

public class PasseStandard extends Epass{
    private Titular titular;
    private int pontos;
    private int numeroViagens;
    private String chaveValida;

    //Contrutores
    public PasseStandard(double saldo, LocalDate dataCarrega, String codigoDoc, TipoDocumento tipo, LocalDate dataNasc, String nome, String email){
        super(saldo,dataCarrega);
        this.titular = new Titular(new DocumentoID(codigoDoc,tipo),nome,dataNasc,email);

        String[] parts = nome.split(" ");
        String first = parts[0];
        String second = parts[1];

        StringBuilder Nome = new StringBuilder();
        if (first.length()>=5){
            Nome.append(first.substring(0,5));
            Nome.reverse();
        }else
        Nome.append(first.substring(0,first.length()));
        Nome.reverse();

        StringBuilder Apelido = new StringBuilder();
        Apelido.append(second.substring(second.length()-5));
        Apelido.reverse();

        this.chaveValida=(Nome.toString()+Apelido.toString()).toUpperCase()+getTitular().getDataNasc()+getCodigo();
    }

    public PasseStandard(double saldo, LocalDate dataCarrega,DocumentoID docID, LocalDate dataNasc, String nome, String email){
        super(saldo,dataCarrega);
        this.titular = new Titular(docID,nome,dataNasc,email);

        String[] parts = nome.split(" ");
        String first = parts[0];
        String second = parts[1];

        StringBuilder Nome = new StringBuilder();
        if (first.length()>=5){
            Nome.append(first.substring(0,5));
            Nome.reverse();
        }else
            Nome.append(first.substring(0,first.length()));
        Nome.reverse();

        StringBuilder Apelido = new StringBuilder();
        Apelido.append(second.substring(second.length()-5));
        Apelido.reverse();

        this.chaveValida=(Nome.toString()+Apelido.toString()).toUpperCase()+getTitular().getDataNasc()+getCodigo();
    }

    public PasseStandard(double saldo, LocalDate dataCarrega, DocumentoID docID, String nome, LocalDate dataNasc, String email, int telefone){
        super(saldo,dataCarrega);
        this.titular = new Titular(docID,nome,dataNasc,email,telefone);

        String[] parts = nome.split(" ");
        String first = parts[0];
        String second = parts[1];

        StringBuilder Nome = new StringBuilder();
        if (first.length()>=5){
            Nome.append(first.substring(0,5));
            Nome.reverse();
        }else
            Nome.append(first.substring(0,first.length()));
        Nome.reverse();

        StringBuilder Apelido = new StringBuilder();
        Apelido.append(second.substring(second.length()-5));
        Apelido.reverse();

        this.chaveValida=(Nome.toString()+Apelido.toString()).toUpperCase()+getTitular().getDataNasc()+getCodigo();
    }

    public PasseStandard(double saldo, LocalDate dataCarrega, PasseStandard Passe){
        super(saldo,dataCarrega);
        this.titular=Passe.titular;
        this.pontos=Passe.pontos;
        this.numeroViagens=Passe.numeroViagens;
        this.chaveValida=Passe.chaveValida;
    }

    public PasseStandard(DocumentoID docID, String nome, LocalDate dataNasc,String morada, String email, int telefone){
        super();
        this.titular = new Titular(docID,nome,dataNasc,morada,email,telefone);

        String[] parts = nome.split(" ");
        String first = parts[0];
        String second = parts[1];

        StringBuilder Nome = new StringBuilder();
        if (first.length()>=5){
            Nome.append(first.substring(0,5));
            Nome.reverse();
        }else
            Nome.append(first.substring(0,first.length()));
        Nome.reverse();

        StringBuilder Apelido = new StringBuilder();
        Apelido.append(second.substring(second.length()-5));
        Apelido.reverse();

        this.chaveValida=(Nome.toString()+Apelido.toString()).toUpperCase()+getTitular().getDataNasc()+getCodigo();
    }

   //Setters e Getters
    public Titular getTitular() {
        return titular;
    }

    public int getPontos() {
        return pontos;
    }

    public String getChaveValida() {
        return chaveValida;
    }

    public void setChaveValida(String chaveValida) {
        this.chaveValida = chaveValida;
    }

    public void setNumeroViagens(int numeroViagens){
        this.numeroViagens=numeroViagens;
    }

    public int getNumeroViagens() {
        return numeroViagens;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setNome(String nome){
        titular.setNome(nome);
    }

    //Métodos
    public void pagarViagensComPontos(int pontosViagem) {
        this.pontos-=pontosViagem;
    }

    //ToString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PasseStandard{");
        sb.append("titular=").append(titular);
        sb.append(", pontos=").append(pontos);
        sb.append(", numeroViagens=").append(numeroViagens);
        sb.append(", chaveValida='").append(chaveValida).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
