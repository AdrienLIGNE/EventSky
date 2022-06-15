package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.modele.MainModel;
import fr.uga.iut2.genevent.util.Persisteur;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

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

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Démarrage de l'application.");

        MainModel app = new MainModel();

        try {
            app = Persisteur.lireEtat();
        }
        catch (ClassNotFoundException | IOException ignored) {
            LOGGER.log(Level.SEVERE, "Impossible de charger les données de sauvegarde.");
            System.exit(Main.EXIT_ERR_LOAD);
        }

        IHM ihm = new JavaFXGUI();
        ihm.demarrerInteraction();

        try {
            Persisteur.sauverEtat(app);
        }
        catch (IOException ignored) {
            LOGGER.log(Level.WARNING, "Impossible de sauvegarder les données.");
            System.exit(Main.EXIT_ERR_SAVE);
        }
    }
}
