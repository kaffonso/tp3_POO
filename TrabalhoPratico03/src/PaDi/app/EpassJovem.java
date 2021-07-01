package PaDi.app;

import PaDi.app.DocumentoID;
import PaDi.app.PasseStandard;
import PaDi.app.TituloTransporte;

import java.time.LocalDate;

public class EpassJovem extends PasseStandard implements TituloTransporte {
    public EpassJovem(double saldo, LocalDate dataCarrega,String codDoc, TipoDocumento tipo, LocalDate dataNasc, String nome, String email) {
        super(saldo, dataCarrega, new DocumentoID(codDoc,tipo), dataNasc, nome, email);
        this.setPrecoViagem(40);
    }

    public EpassJovem(double saldo, LocalDate dataCarrega, DocumentoID docID, LocalDate dataNasc, String nome, String email) {
        super(saldo, dataCarrega, docID, dataNasc, nome, email);
        this.setPrecoViagem(40);
    }

    public EpassJovem(DocumentoID documentoID, String nome, LocalDate datanasc, String morada, String email, int telef) {
        super(documentoID,nome,datanasc,morada,email,telef);
    }

    @Override
    public void carregar(double saldo) {
        setCarregamento(LocalDate.now());
        setValidade(getCarregamento().plusDays(30));
        setSaldo(getSaldo()+saldo);
        setSaldo(getSaldo()+saldo);
    }

    @Override
    public void pagarViagem() {
        try {
            if(checkValidade()){
            }else
                throw new Exception();
        }catch (Exception error){
            System.out.println("Passe Inválido");
        }
    }

    public void passarSaldo(PasseStandard outro, double saldo){
        if (getSaldo()<saldo){
            System.out.println("Saldo insuficiente");
        }else
            setSaldo(getSaldo()-saldo);
        outro.setSaldo(getSaldo()+saldo);
    };


    @Override
    public boolean checkValidade() {
        if(this.getCarregamento().plusDays(30).isAfter(LocalDate.now())){
            return  true;
        }
        return false;
    }


}
