package trocaid.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import trocaid.model.entities.Produto;

/**
 *
 * @author RafaelNunes
 */
public class ProdutoDao {

    private Connection connection;

    public ProdutoDao(Connection connection) {
        this.connection = connection;
    }

    public List<Produto> getListaDeProdutos(Integer iniciador) throws SQLException {
        return listProdutos(iniciador);
    }

    private List<Produto> listProdutos(Integer iniciador) throws SQLException {
        List<Produto> list = new ArrayList<Produto>();
        try (PreparedStatement pstm = connection.prepareStatement("SELECT id, codigo FROM produto;");
                ResultSet rs = pstm.executeQuery()) {

            int n = iniciador;
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodigo(rs.getString("codigo"));
                p.setIdAntiga(rs.getInt("id"));
                // Defino ID pela posição do produto na lista
                p.setIdNova(n);
                n++;
                list.add(p);
                System.out.println(p);
            }
        }
        return list;
    }
}
