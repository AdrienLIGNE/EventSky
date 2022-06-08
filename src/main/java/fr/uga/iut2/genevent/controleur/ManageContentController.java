package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.MainApplication;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManageContentController extends Controller {

    public ManageContentController(MainApplication app) {
        super(app);
    }

    @FXML
    public void ajoutSalleClic(MouseEvent event){
        try {
            // Chargement de la nouvelle interface
            FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("create-salle.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            // Création d'un nouveau stage
            Stage stage = new Stage();
            stage.setTitle("Création Salle");
            // affectation de la nouvelle scène au stage et affichage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
