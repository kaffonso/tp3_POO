package PaDi.app;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Epass {
    private final long codigo;
    private double saldo;
    private double precoViagem;
    private LocalDate carregamento;
    private LocalDate validade;
    private final LocalDateTime dataEmissao;
    private static long ultCodigo;

    static {
        if (Long.valueOf(ultCodigo) == null){
            ultCodigo=0;
        }
    }

    //Construtores
    public Epass (double saldo, double precoViagem){
        this.codigo = ultCodigo+1;
        this.dataEmissao=LocalDateTime.now();
        this.saldo = saldo;
        this.precoViagem = precoViagem;
        ultCodigo++;
    }

    public Epass(double precoViagem){
        this.codigo = ultCodigo+1;
        this.dataEmissao = LocalDateTime.now();
        this.precoViagem = precoViagem;
        ultCodigo++;
    }

    public Epass(double saldo,double precoViagem, LocalDate dataCarrega, LocalDate dataValida){
        this.codigo = ultCodigo+1;
        this.dataEmissao = LocalDateTime.now();
        this.saldo = saldo;
        this.precoViagem = precoViagem;
        this.carregamento = dataCarrega;
        this.validade = dataValida;
        ultCodigo++;
    }

    public Epass(double saldo, LocalDate dataCarrega){
        this.codigo = ultCodigo+1;
        this.dataEmissao = LocalDateTime.now();
        this.saldo = saldo;
        this.carregamento = dataCarrega;
        this.validade = carregamento.plusDays(30);
        ultCodigo++;
    }

    public Epass(Epass Epass){
        this.codigo = ultCodigo+1;
        this.saldo = Epass.saldo;
        this.precoViagem = Epass.precoViagem;
        this.carregamento = Epass.carregamento;
        this.validade = Epass.validade;
        this.dataEmissao = LocalDateTime.now();
        ultCodigo++;
    }

    public Epass(){
        this.codigo=ultCodigo+1;
        ultCodigo++;
        this.dataEmissao=LocalDateTime.now();

    }

    //Setters e Getters
    public long getCodigo() {
        return codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getPrecoViagem() {
        return precoViagem;
    }

    public void setPrecoViagem(double precoViagem) {
        this.precoViagem = precoViagem;
    }

    public LocalDate getCarregamento() {
        return carregamento;
    }

    public void setCarregamento(LocalDate carregamento) {
        this.carregamento = carregamento;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public long getUltCod() {
        return ultCodigo;
    }

    //ToString
    public String toString() {
        final StringBuilder sb = new StringBuilder("Epass{");
        sb.append("codigo=").append(codigo);
        sb.append(", saldo=").append(saldo);
        sb.append(", precoViagem=").append(precoViagem);
        sb.append(", dataCarrega=").append(carregamento);
        sb.append(", dataValida=").append(validade);
        sb.append(", dataEmissao=").append(dataEmissao);
        sb.append(", ultCod=").append(ultCodigo);
        sb.append('}');
        return sb.toString();
    }

    //Metodos
    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (!(outro instanceof Epass)) return false;
        Epass epass = (Epass) outro;
        return getCodigo() == epass.getCodigo() && Double.compare(epass.getSaldo(), getSaldo()) == 0 && Double.compare(epass.getPrecoViagem(), getPrecoViagem()) == 0 && getUltCod() == epass.getUltCod() && getCarregamento().equals(epass.getCarregamento()) && getValidade().equals(epass.getValidade());
    }

}
