package fr.uga.iut2.genevent.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManageContentController {

    @FXML
    public void ajoutSalleAction(ActionEvent event){
        try {
            // Chargement de la nouvelle interface
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-salle.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
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
