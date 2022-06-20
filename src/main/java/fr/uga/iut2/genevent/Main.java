package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.Controller;
import fr.uga.iut2.genevent.modele.MainModel;
import fr.uga.iut2.genevent.util.Persisteur;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main extends Application {

    public static final int EXIT_ERR_LOAD = 2;
    public static final int EXIT_ERR_SAVE = 3;

    private static Logger LOGGER = Logger.getLogger(Main.class.getPackageName());
    private static LogManager logManager = LogManager.getLogManager();

    static {
        try {
            logManager.readConfiguration(new FileInputStream("conf/log-dev.conf"));
        }
        catch (IOException e) {
            LOGGER.log(Level.WARNING, "Impossible de charger le fichier de configuration du logger");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mainViewLoader = new FXMLLoader(JavaFXGUI.class.getResource("main-view.fxml"));
        Scene mainScene = new Scene(mainViewLoader.load());
        stage.setTitle("GenEvent");
        stage.setScene(mainScene);

        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    LOGGER.log(Level.INFO, "Sauvegarde...");
                    Persisteur.sauverEtat(Controller.getModel());
                }
                catch (IOException ignored) {
                    ignored.printStackTrace();
                    LOGGER.log(Level.WARNING, "Impossible de sauvegarder les données.");
                    System.exit(Main.EXIT_ERR_SAVE);
                }
            }
        });

        stage.show();
    }

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Démarrage de l'application.");

        MainModel app = new MainModel();
        try {
            app = Persisteur.lireEtat();
            Controller.setModel(app);
        }
        catch (ClassNotFoundException | IOException ignored) {
            LOGGER.log(Level.SEVERE, "Impossible de charger les données de sauvegarde.");
            System.exit(Main.EXIT_ERR_LOAD);
        }

        launch();

    }
}
