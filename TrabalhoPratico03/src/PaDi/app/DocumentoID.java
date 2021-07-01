package PaDi.app;

public class DocumentoID {
    private final String codigoID;
    private final TipoDocumento tipo;

    //Contrutores
    public DocumentoID (String codigoID, TipoDocumento tipo){
        this.codigoID = codigoID;
        this.tipo = tipo;
    }

    //Setters e Getters
    public String getCodigoID() {
        return codigoID;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    //ToString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DocumentoID{");
        sb.append("codigoID='").append(codigoID).append('\'');
        sb.append(", tipo=").append(tipo);
        sb.append('}');
        return sb.toString();
    }
}
