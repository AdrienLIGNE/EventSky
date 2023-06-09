package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

public class MenuController extends Controller {

    private static MenuController controller;

    private static Scene manageRessourceScene;
    private static Scene manageEventScene;
    private static Scene accueilScene;
    private static Scene statisticsScene;

    static {
        controller = new MenuController();

        FXMLLoader fxmlLoader;

        fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("manage-ressource-view.fxml"));
        fxmlLoader.setController(ManageRessourcesController.getController());

        try {
            manageRessourceScene = new Scene(fxmlLoader.load(), 1223, 600);
        }
        catch (IOException e) {
            Main.LOGGER.log(Level.SEVERE, "Erreur lors de la création de la scène ManageRessources", e);
        }

        fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("manage-event-view.fxml"));
        fxmlLoader.setController(ManageEventController.getController());

        try {
            manageEventScene = new Scene(fxmlLoader.load(), 1223, 600);
        }
        catch (IOException e) {
            Main.LOGGER.log(Level.SEVERE, "Erreur lors de la création de la scène ManageEvent", e);
        }

        fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("main-view.fxml"));
        fxmlLoader.setController(MainController.getController());

        try {
            accueilScene = new Scene(fxmlLoader.load(), 1223, 600);
        }
        catch (IOException e) {
            Main.LOGGER.log(Level.SEVERE, "Erreur lors de la création de la scène Accueil", e);
        }

        fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("statistiques-view.fxml"));
        fxmlLoader.setController(StatisticsController.getController());

        try {
            statisticsScene = new Scene(fxmlLoader.load(), 1223, 600);
        }
        catch (IOException e) {
            Main.LOGGER.log(Level.SEVERE, "Erreur lors de la création de la scène Statistiques", e);
        }

    }

    public static MenuController getController() {
        return controller;
    }

    public static Scene getManageEventScene() {
        return manageEventScene;
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

    @FXML
    private void statisticsClick(ActionEvent e){
        Stage stage = Controller.getStageFromTarget(e.getTarget());

        stage.setScene(statisticsScene);
    }

}
