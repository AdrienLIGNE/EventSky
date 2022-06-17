package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.Lieu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe possédant un ensemble de filtres utiles
 */
public class FiltreUtilitaire {

    /**
     * Permet de trier les lieux selon leur capacité. Récupère uniquement les lieux avec une capacité suffisante
     * @param lieux liste de lieux
     * @param capacite_min capacité minimale
     * @return liste de lieux
     */
    public static ObservableList<Lieu> filtreLieuByCapacite(ObservableList<Lieu> lieux, int capacite_min) {
        ObservableList<Lieu> lieux_ok = FXCollections.observableArrayList();

        for(Lieu l : lieux) {
            if(l.getCapacite().getValue() >= capacite_min) {
                lieux_ok.add(l);
            }
        }

        return lieux_ok;
    }

}
