package PaDi.app;

public interface TituloTransporte {
    public void carregar(double saldo);
    public void pagarViagem();
    public boolean checkValidade();
    public void passarSaldo(PasseStandard outro, double saldo);
}
