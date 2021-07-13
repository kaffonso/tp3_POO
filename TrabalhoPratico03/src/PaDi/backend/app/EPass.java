package PaDi.backend.app;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe abstracta que representa um ePass
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public abstract class EPass implements Serializable, TituloTransporte  {

    private final long codigo;
    private double saldo;
    private double precoViagem;
    private final LocalDateTime emissao;
    private LocalDate carregamento;
    private LocalDate validade;
    private static long ultCodigo;

    static {
        if (Long.valueOf(ultCodigo) == null )
            ultCodigo = 0;
    }

    public EPass()
    {
        this.codigo = ultCodigo + 1;
        this.emissao = LocalDateTime.now();
        ultCodigo++;
    }

    /**
     * Construtor para ser usado para criar um EPass com valor do saldo e precoviagem
     *
     * @param saldo
     * @param precoViagem
     *
     */
    public EPass(double saldo, double precoViagem)
    {
        this.codigo = ultCodigo + 1;
        ultCodigo++;
        this.saldo = saldo;
        this.precoViagem = precoViagem;
        this.emissao = LocalDateTime.now();

    }


    /**
     * @param precoViagem
     */
    public EPass(double precoViagem) {

        this.codigo = ultCodigo + 1;
        ultCodigo++;
        this.precoViagem = precoViagem;
        this.emissao = LocalDateTime.now();
    }

    /**
     * @param saldo
     * @param precoViagem
     * @param carregamento
     * @param validade
     */
    public EPass(double saldo, double precoViagem, LocalDate carregamento, LocalDate validade) {
        this.codigo = ultCodigo + 1;
        ultCodigo++;
        this.saldo = saldo;
        this.precoViagem = precoViagem;
        this.carregamento = carregamento;
        this.validade = validade;
        this.emissao = LocalDateTime.now();
    }

    /**
     * @param saldo
     * @param carregamento
     */
    public EPass(double saldo, LocalDate carregamento) {
        this.codigo = ultCodigo + 1;
        ultCodigo++;
        this.saldo = saldo;
        this.carregamento = carregamento;
        this.emissao = LocalDateTime.now();
    }


    /**
     * Construtor para criar uma cópia
     * @param pass objecto que queremos criar uma cópia
     */
    public EPass(EPass pass) {
        this.codigo = pass.getCodigo();
        this.saldo = pass.getSaldo();
        this.precoViagem = pass.precoViagem;
        this.carregamento = pass.carregamento;
        this.validade = pass.validade;
        this.emissao = pass.emissao;

    }

    /**
     * @return the ultCodigo
     */
    public static long getUltCodigo() {
        return ultCodigo;
    }


    /**
     * @return the codigo
     */
    public long getCodigo() {
        return codigo;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the precoViagem
     */
    public double getPrecoViagem() {
        return precoViagem;
    }


    /**
     * @param precoViagem the precoViagem to set
     */
    public void setPrecoViagem(double precoViagem) {
        this.precoViagem = precoViagem;
    }


    /**
     * @return the carregamento
     */
    public LocalDate getCarregamento() {
        return carregamento;
    }


    /**
     * @param carregamento the carregamento to set
     */
    public void setCarregamento(LocalDate carregamento) {
        this.carregamento = carregamento;
    }


    /**
     * @return the emissao
     */
    public LocalDateTime getEmissao() {
        return emissao;
    }

    /**
     * @return the validade
     */
    public LocalDate getValidade() {
        return validade;
    }


    /**
     * @param validade the validade to set
     */
    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }


    /**
     *
     * @return objecto representado na forma de string
     **/
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("------ePass N. : "); s.append(codigo);
        s.append("\n-Saldo actual: "); s.append(saldo);
        s.append("\n-Preço viagem: "); s.append(precoViagem);
        s.append("\n-Carregado em: "); s.append(carregamento);
        s.append("\n-Válido até: "); s.append(validade);
        s.append("\n-------------------------------------");
        return s.toString();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EPass other = (EPass) obj;
        if (carregamento == null) {
            if (other.carregamento != null)
                return false;
        } else if (!carregamento.equals(other.carregamento))
            return false;
        if (codigo != other.codigo)
            return false;
        if (Double.doubleToLongBits(precoViagem) != Double.doubleToLongBits(other.precoViagem))
            return false;
        if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
            return false;
        if (validade == null) {
            if (other.validade != null)
                return false;
        } else if (!validade.equals(other.validade))
            return false;
        return true;
    }

}