package fr.uga.iut2.genevent.vue;

import java.time.LocalDate;
import java.util.Set;


public abstract class IHM {

    /**
     * Rend actif l'interface Humain-machine.
     *
     * L'appel est bloquant : le contrôle est rendu à l'appelant une fois que
     * l'IHM est fermée.
     *
     */
    public abstract void demarrerInteraction();

}
