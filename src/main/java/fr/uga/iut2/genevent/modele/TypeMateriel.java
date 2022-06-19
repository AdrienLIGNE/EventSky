package fr.uga.iut2.genevent.modele;

public enum TypeMateriel {
    MOBILIER("Mobilier"), VEHICULE("Véhicule"), MATERIEL_MUSICAL("Matériel musical"), DECORATION("Décoration"), OTHER("Autre");

    private double salaire;
    private String libelle;

    private TypeMateriel(String libelle) {
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
