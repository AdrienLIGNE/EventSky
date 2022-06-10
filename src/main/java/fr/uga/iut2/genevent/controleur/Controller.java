package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.MainApplication;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class Controller {

    private MainApplication app;

    public Controller(MainApplication app) {
        this.app = app;
    }

    public MainApplication getApplication() {
        return app;
    }


    // Événements lié au menu

    @FXML
    private void gestionRessourcesClick(ActionEvent event){
        try {
            // Chargement de la nouvelle interface
            FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("manage-content-view.fxml"));
            fxmlLoader.setController(new ManageContentController(new MainApplication()));
            Scene scene = new Scene(fxmlLoader.load());

            // Création d'un nouveau stage
            Stage stage = (Stage) ((Button) event.getTarget()).getScene().getWindow();
            stage.setTitle("Gestion des ressources");
            // affectation de la nouvelle scène au stage et affichage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
