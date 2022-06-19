package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;


public abstract class Controller {

    private static MainModel app;

    static {
        app = new MainModel();
    }

    public MainModel getModel() {
        return app;
    }


    /**
     * Méthode permettant de récupérer le stage d'un élément.
     * @param n élément
     * @return Le stage associé à l'événement.
     */
    public static Stage getStageFromNode(Node n) {
        return (Stage) n.getScene().getWindow();
    }


    @FXML
    private void exitButtonClick(ActionEvent e) {
        Stage stage = Controller.getStageFromNode((Node) e.getTarget());
        stage.close();
    }

    protected void exitStage(Stage stage) {
        stage.close();
    }

}
