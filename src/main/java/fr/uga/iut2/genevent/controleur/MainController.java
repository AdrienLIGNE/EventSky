package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.MainApplication;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;


public class MainController {

    private final IHM ihm;
    private final MainApplication app;

    public MainController(MainApplication app) {
        this.app = app;
        this.ihm = new JavaFXGUI(this);
    }

    public void demarrer() {
        this.ihm.demarrerInteraction();
    }



}
