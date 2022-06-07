package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.MainController;
import fr.uga.iut2.genevent.modele.MainApplication;
import fr.uga.iut2.genevent.util.Persisteur;
import java.io.IOException;


public class Main {

    public static final int EXIT_ERR_LOAD = 2;
    public static final int EXIT_ERR_SAVE = 3;

    public static void main(String[] args) {

        MainApplication app = null;

        try {
            app = Persisteur.lireEtat();
        }
        catch (ClassNotFoundException | IOException ignored) {
            System.err.println("Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_LOAD);
        }

        MainController controleur = new MainController(app);
        // `Controleur.demarrer` garde le contrôle de l'exécution tant que
        // l'utilisa·teur/trice n'a pas saisi la commande QUITTER.
        controleur.demarrer();

        try {
            Persisteur.sauverEtat(app);
        }
        catch (IOException ignored) {
            System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_SAVE);
        }
    }
}
