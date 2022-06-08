package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.MainApplication;

public abstract class Controller {

    private MainApplication app;

    public Controller(MainApplication app) {
        this.app = app;
    }

    public MainApplication getApplication() {
        return app;
    }
}
