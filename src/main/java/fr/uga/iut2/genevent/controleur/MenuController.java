package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Controller {


    @FXML
    private void manageRessourceClick(ActionEvent e) {
        Stage stage = Controller.getStageFromNode((Node) e.getTarget());

        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("manage-content-view.fxml"));

        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void manageEventClick(ActionEvent e) {
        System.out.println("test");
        Stage stage = Controller.getStageFromNode((Node) e.getTarget());

        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("event-manager-view.fxml"));

        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
