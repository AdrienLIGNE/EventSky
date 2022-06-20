package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

public enum TypeLieu {
    SALLE("Salle"), GYMNASE("Gymnase"), PLEIN_AIR("Plein air");

    private String libelle;

    private TypeLieu(String libelle) {
        this.libelle = libelle;
    }


    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return getLibelle();
    }
}
