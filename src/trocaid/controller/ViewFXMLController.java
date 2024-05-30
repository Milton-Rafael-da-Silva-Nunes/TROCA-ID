package trocaid.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import trocaid.model.dao.ProdutoDao;
import trocaid.model.database.Database;
import trocaid.model.database.DatabaseFactory;
import trocaid.model.entities.Produto;
import trocaid.model.util.Alerts;
import trocaid.model.util.Constraints;

/**
 *
 * @author RafaelNunes
 */
public class ViewFXMLController implements Initializable {

    @FXML
    private TextField textFieldFormatoID;
    @FXML
    private Button buttonExecutar;
    @FXML
    private RadioButton buttonRadioIdCodigo;
    @FXML
    private TextArea textAreaLog;

    private final Database database = DatabaseFactory.getDatabase();
    private final Connection connection = database.conectar();

    private ProcessoUpdate pu;
    private ProdutoDao pdao;
    List<Produto> listProdutos;

    @FXML
    public void actionButtonExecutar() {
        String inicador = textFieldFormatoID.getText();
        boolean podeIdParaCodigo = buttonRadioIdCodigo.isSelected();

        if (inicador.length() >= 1) {
            try {
                listProdutos = pdao.getListaDeProdutos(Integer.parseInt(inicador));
                pu.executar(listProdutos, podeIdParaCodigo);
            } catch (SQLException e) {
                e.printStackTrace();
                textAreaLog.setText(e.getMessage() + "\n");
            }
        } else {
            Alerts.showAlert("Atenção", null, "Digite a inicial da ID", AlertType.WARNING);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Classes
        pu = new ProcessoUpdate(connection, textAreaLog);
        pdao = new ProdutoDao(connection);

        // Controladores do campo de texto
        Constraints.setTextFieldInteger(textFieldFormatoID);
        Constraints.setTextFieldMaxLength(textFieldFormatoID, 6);
    }
}
