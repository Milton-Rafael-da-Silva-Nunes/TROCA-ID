package trocaid.model.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author RafaelNunes
 */
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String codigo;
    private Integer idAntiga;
    private Integer idNova;

    public Produto() {
    }

    public Produto(Integer id, String codigo, Integer idAntiga, Integer idNova) {
        super();
        this.id = id;
        this.idAntiga = idAntiga;
        this.idNova = idNova;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdAntiga() {
        return idAntiga;
    }

    public void setIdAntiga(Integer idAntiga) {
        this.idAntiga = idAntiga;
    }

    public Integer getIdNova() {
        return idNova;
    }

    public void setIdNova(Integer idNova) {
        this.idNova = idNova;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAntiga);
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
        Produto other = (Produto) obj;
        return Objects.equals(idAntiga, other.idAntiga);
    }

    @Override
    public String toString() {
        return "ID: " + id + " - CODIGO: " + codigo + " - ID_ANTIGA: " + idAntiga + " - ID_NOVA_CONTADOR: " + idNova;
    }
}
