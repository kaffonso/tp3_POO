package PaDi.backend.app;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que implementa Documento de Identificação
 *
 *
 * @author Paulo Silva
 * @version 11-05-2020
 */
public class DocumentoID implements Serializable {
    private final String codigo;
    private final TipoDocumento tipo;

    public DocumentoID(String codigo, TipoDocumento tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }
    public DocumentoID(DocumentoID doc) {
        this.codigo = doc.codigo;
        this.tipo = doc.tipo;
    }
    public String getCodigo() {
        return codigo;
    }
    public TipoDocumento getTipo() {
        return tipo;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentoID other = (DocumentoID) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return this.tipo == other.tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        hash = 89 * hash + Objects.hashCode(this.tipo);
        return hash;
    }
    @Override
    public DocumentoID clone() {
        DocumentoID aux =  new DocumentoID(this);
        return aux;
    }

    @Override
    public String toString() {
        return "DocumentoID{" + "codigo=" + codigo + ", tipo=" + tipo + '}';
    }

}
