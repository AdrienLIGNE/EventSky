package fr.uga.iut2.genevent.test;

import fr.uga.iut2.genevent.util.TextUtilitaire;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTest extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXGUI.class.getResource("event-infos-view.fxml"));
        fxmlLoader.setController(new TestController());

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
