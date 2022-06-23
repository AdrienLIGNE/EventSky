package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.MainModel;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;


public abstract class Controller {

    private static MainModel app;


    public static void setModel(MainModel a) {
        app = a;
    }

    public static MainModel getModel() {
        return app;
    }

    /**
     * Méthode permettant de récupérer le stage d'un élément.
     * @param e élément
     * @return Le stage associé à l'événement.
     */
    public static Stage getStageFromTarget(EventTarget e) {
        return (Stage) ((Node) e).getScene().getWindow();
    }


    @FXML
    private void exitButtonClick(ActionEvent e) {
        Stage stage = Controller.getStageFromTarget(e.getTarget());
        stage.close();
    }

    protected void exitStage(Stage stage) {
        stage.close();
    }

}
