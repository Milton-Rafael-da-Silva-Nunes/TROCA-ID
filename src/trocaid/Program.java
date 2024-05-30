package trocaid;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author RafaelNunes
 */
public class Program extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/trocaid/view/ViewFXML.fxml"));
            Parent parent = loader.load();
            
            // Carregar a logo
            Image logo = new Image(getClass().getResourceAsStream("/trocaid/view/img/logoNova.png"));
            primaryStage.getIcons().add(logo);
            
            // Adicionar a imagem de fundo
            Image fundo = new Image(getClass().getResourceAsStream("/trocaid/view/img/fundo.png"));
            ImageView imageView = new ImageView(fundo);
            imageView.setFitWidth(800);
            imageView.setFitHeight(500);
            StackPane stackPane = new StackPane(imageView, parent);
            
            Scene mainScene = new Scene(stackPane);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("LC muda ID Produto");
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
