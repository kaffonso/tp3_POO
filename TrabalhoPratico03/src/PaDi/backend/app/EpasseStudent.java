package PaDi.backend.app;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe passe estudante
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public class EpasseStudent extends EpassStandard {

    private String escola;
    private int anoFrequenta;
    private int viagemFree;
    private ArrayList<Zona> zonas;


    public EpasseStudent(DocumentoID docID, LocalDate ddn, String nome, String email) {
        super(docID, ddn, nome, email);

    }

    /**
     * Método construtor que recebe todos os parâmetros

     * @param codigoDoc
     * @param tipoDoc
     * @param ddn
     * @param nome
     * @param telef
     * @param escola
     * @param ano
     */
    public EpasseStudent(String codigoDoc, TipoDocumento tipoDoc,
                         LocalDate ddn, String nome, int telef, String escola, int ano) {

        super(new DocumentoID(codigoDoc, tipoDoc), ddn, nome, telef);
        this.escola = escola;
        this.anoFrequenta = ano;
        this.viagemFree = 4;
        this.zonas = new ArrayList<>();

    }

    /**
     *

     * @param codigoDoc
     * @param tipoDoc
     * @param nome
     * @param ddn
     * @param morada
     * @param email
     * @param telef
     * @param escola
     * @param ano
     */
    public EpasseStudent(String codigoDoc, TipoDocumento tipoDoc,
                         String nome,LocalDate ddn, String morada, String email, int telef, String escola, int ano) {

        super(new DocumentoID(codigoDoc, tipoDoc), nome, ddn, morada, email, telef);
        this.escola = escola;
        this.anoFrequenta = ano;
        this.viagemFree = 4;
        this.zonas = new ArrayList<>();

    }

    /**
     *
     * @param doc
     * @param nome
     * @param ddn
     * @param morada
     * @param email
     * @param telef
     * @param escola
     * @param ano
     */
    public EpasseStudent(DocumentoID doc,String nome,
                         LocalDate ddn, String morada, String email, int telef, String escola, int ano) {


        super(doc, nome, ddn, morada, email, telef);
        this.escola = escola;
        this.anoFrequenta = ano;
        this.viagemFree = 4;

        this.zonas = new ArrayList<>();
    }

    /**
     *
     * @param student
     */
    public EpasseStudent(EpasseStudent student) {
        super((EpassStandard)student);
        this.escola = student.getEscola();
        this.anoFrequenta = student.getAnoFrequenta();
        this.viagemFree = student.getViagemFree();
        this.zonas = student.getZonas();

    }

    /**
     *
     * @param nomes
     */
    public void inserirZonas(String[] nomes) {
        for (String nome : nomes) {
            this.zonas.add(new Zona(nome));
        }

    }

    /**
     * @return the escola
     */
    public String getEscola() {
        return escola;
    }

    /**
     * @return the anoFrequenta
     */
    public int getAnoFrequenta() {
        return anoFrequenta;
    }

    /**
     * @return the viagemFree
     */
    public int getViagemFree() {
        return viagemFree;
    }

    /**
     * @return the zonas
     */
    public ArrayList<Zona> getZonas() {
        ArrayList<Zona> aux = new ArrayList<Zona>();
        for (Zona a: zonas)
            aux.add(a.clone());
        return aux;
    }

    /**
     * @param escola the escola to set
     */
    public void setEscola(String escola) {
        this.escola = escola;
    }

    /**
     * @param anoFrequenta the anoFrequenta to set
     */
    public void setAnoFrequenta(int anoFrequenta) {
        this.anoFrequenta = anoFrequenta;
    }

    /**
     * @param viagemFree the viagemFree to set
     */
    public void setViagemFree(int viagemFree) {
        this.viagemFree = viagemFree;
    }



    /**
     * @param zonas the zonas to set
     */
    public void setZonas(ArrayList<Zona> zonas) {
        this.zonas = zonas;
    }

    @Override
    public void passarSaldo(EPass pass, double valor) throws PgmtInvalido {

        if ( (valor > 0) && (this.getSaldo() >= valor) ) {

            pass.setSaldo(pass.getSaldo() + valor);
            this.setSaldo(this.getSaldo() - valor);

        } else
            throw new  PgmtInvalido("Saldo insuficiente ou valor invalido");

    }

    @Override
    public boolean saldoValido() throws EpassInvalido {
        boolean valido=true;
        LocalDate hoje = LocalDate.now();

        if ( (this.getValidade().isBefore(hoje)) || (super.getSaldo() < TituloTransporte.PRECOVIAGEMSTUDENT))
            throw new EpassInvalido("Chave invalida!");

        return valido;

    }

    @Override
    public void pagarViagem(double valor) throws PgmtInvalido {
        try {
            this.saldoValido();
            super.setSaldo(super.getSaldo() - valor);
        } catch (EpassInvalido e) {
            throw new PgmtInvalido("Saldo inválido ou insuficiente!");
        }
    }

    /**
     * Método para pagar viagem em que o estudante poderá viajar caso não tenha saldo mas local é sua zona
     * @param valor
     * @param local zona para onde vai viajar
     * @return 1 se tem saldo -1 se vai viajar em saldo
     */
    public int pagarViagem(double valor, String local) throws PgmtInvalido {
        int res=-1;
        try {
            this.saldoValido();
            res=1;
            super.setSaldo(super.getSaldo() - valor);

        } catch (EpassInvalido e) {

            if ((this.getZonas().contains(new Zona(local) ) ) && (this.viagemFree > 0) ) {
                res=1;
                this.viagemFree--;
            }
            else
                throw new PgmtInvalido("Saldo inválido ou insuficiente!");
        }

        return res;
    }
    @Override
    public void pagarViagemComPontos() throws PgmtInvalido {
        if (this.getPontos() >= 40) {
            this.setPontos(this.getPontos() - 40);
        } else
            throw new PgmtInvalido("Pontos insuficientes");


    }

    @Override
    public void carregarEpass(double valor)  throws ParametroInvalido {
        if (valor <= 0 )
            throw new ParametroInvalido("valor a carregar deve ser maior que zero!");
        else {
            super.setSaldo(super.getSaldo() + valor);
            super.setCarregamento(LocalDate.now());
            super.setValidade(super.getCarregamento().plusDays(30));
            this.viagemFree = 4;
        }

    }

    @Override
    public boolean validarEpass() throws EpassInvalido {
        boolean res=false;

        Chave<EpassStandard> aux = new Chave<EpassStandard>(
                this.getTitular().getNome(),
                this.getTitular().getDdn().atStartOfDay(),
                this.getCodigo() );

        if (aux.equals(this.getChave()) )
            res = true;
        else
            throw new EpassInvalido("Chave invalida!");

        if (this.getValidade().isBefore(LocalDate.now()))
            throw new EpassInvalido("Validade expirada");

        if (this.getTitular().getDdn().isBefore(LocalDate.now().minusYears(18)) )
            throw new EpassInvalido("Idade de titular inválida");



        return res;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getCodigo() + " - ").append(getTitular().getNome().toUpperCase());
        return sb.toString();
    }

    @Override
    public EpasseStudent clone() {
        EpasseStudent aux = new EpasseStudent(this);
        return aux;

    }

}
