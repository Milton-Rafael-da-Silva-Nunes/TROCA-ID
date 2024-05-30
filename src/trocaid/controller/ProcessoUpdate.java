package trocaid.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
import trocaid.model.database.DatabaseMysql;
import trocaid.model.entities.Produto;

/**
 *
 * @author RafaelNunes
 */
public class ProcessoUpdate {

    private Connection connection;
    private TextArea textAreaLog;

    public ProcessoUpdate(Connection connection, TextArea textAreaLog) {
        if (connection == null) {
            System.exit(1);
        }
        this.textAreaLog = textAreaLog;
        this.connection = connection;
    }

    public void executar(List<Produto> produtos, Boolean podeIdParaCodigo) throws SQLException {
        updateTabelaProduto(produtos, podeIdParaCodigo);
        updateTabelasBanco(getListaTabelasDoBanco(), produtos);
    }

    private void updateTabelaProduto(List<Produto> produtos, Boolean podeIdParaCodigo) throws SQLException {
        String sql = "UPDATE produto SET id = ?, codigo = ? WHERE id = ?";

        for (Produto produto : produtos) {
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, produto.getIdNova());
                if (podeIdParaCodigo) {
                    pstm.setInt(2, produto.getIdAntiga());
                } else {
                    pstm.setString(2, produto.getCodigo());
                }
                pstm.setInt(3, produto.getIdAntiga());
                pstm.executeUpdate();
            }
        }
    }

    private void updateTabelasBanco(List<String> tabelas, List<Produto> produtos) throws SQLException {
        for (String tabela : tabelas) {
            System.out.println(tabela);
            String sql = "UPDATE " + tabela + " SET id_produto = ? WHERE id_produto = ?;";
            for (Produto produto : produtos) {
                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.setInt(1, produto.getIdNova());
                    pstm.setInt(2, produto.getIdAntiga());
                    pstm.executeUpdate();
                }
            }
        }
        textAreaLog.appendText("\n**** Tabelas atualziadas com sucesso ****\nTotal: " + (tabelas.size()));
    }

    private List<String> getListaTabelasDoBanco() throws SQLException {
        List<String> tabelas = new ArrayList<>();

        String sql = "SELECT table_name FROM information_schema.columns WHERE column_name = ? AND table_schema = ?;";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, "id_produto");
            pstm.setString(2, DatabaseMysql.bancoDados);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    tabelas.add(rs.getString("table_name"));
                }
            }
        }
        return tabelas;
    }
}
