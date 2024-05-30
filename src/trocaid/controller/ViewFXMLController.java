
package trocaid.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
