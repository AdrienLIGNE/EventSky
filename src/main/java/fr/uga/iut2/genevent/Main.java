package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.MainController;
import fr.uga.iut2.genevent.modele.MainApplication;
import fr.uga.iut2.genevent.util.Persisteur;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final int EXIT_ERR_LOAD = 2;
    public static final int EXIT_ERR_SAVE = 3;

    private static Logger LOGGER = Logger.getLogger("Logger1");

    static {
        LOGGER.setLevel(Level.INFO);
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s [%1$td/%1$tm/%1$tY - %1$tH:%1$tM] %5$s\n");
    }

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Démarrage de l'application");

        MainApplication app = new MainApplication();

        try {
            app = Persisteur.lireEtat();
        }
        catch (ClassNotFoundException | IOException ignored) {
            System.err.println("Erreur irrécupérable pendant le chargement de l'état: fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_LOAD);
        }

        IHM ihm = new JavaFXGUI();
        ihm.demarrerInteraction();

        try {
            Persisteur.sauverEtat(app);
        }
        catch (IOException ignored) {
            System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état: fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_SAVE);
        }
    }
}
