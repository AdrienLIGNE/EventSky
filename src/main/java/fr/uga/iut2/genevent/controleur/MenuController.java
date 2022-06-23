package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Controller {

    private static MenuController controller;

    private static Scene manageRessourceScene;
    private static Scene manageEventScene;
    private static Scene accueilScene;

    static {
        controller = new MenuController();

        FXMLLoader fxmlLoader;

        fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("manage-ressource-view.fxml"));
        fxmlLoader.setController(ManageRessourcesController.getController());

        try {
            manageRessourceScene = new Scene(fxmlLoader.load(), 1200, 600);
        }
        catch (IOException e) {

        }

        fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("manage-event-view.fxml"));
        fxmlLoader.setController(ManageEventController.getController());

        try {
            manageEventScene = new Scene(fxmlLoader.load(), 1200, 600);
        }
        catch (IOException e) {

        }

        fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("main-view.fxml"));
        fxmlLoader.setController(MainController.getController());

        try {
            accueilScene = new Scene(fxmlLoader.load(), 1200, 600);
        }
        catch (IOException e) {

        }
    }

    public static MenuController getController() {
        return controller;
    }

    @FXML
    private void accueilClick(ActionEvent e) {
        Stage stage = Controller.getStageFromTarget(e.getTarget());

        stage.setScene(accueilScene);
    }

    @FXML
    private void manageRessourceClick(ActionEvent e) {
        Stage stage = Controller.getStageFromTarget(e.getTarget());

        stage.setScene(manageRessourceScene);
    }

    @FXML
    private void manageEventClick(ActionEvent e) {
        //System.out.println("test");
        Stage stage = Controller.getStageFromTarget(e.getTarget());

        stage.setScene(manageEventScene);
    }



}
