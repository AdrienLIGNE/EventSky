package fr.uga.iut2.genevent.modele;

/**
 * Enumeration utilisée par la classe Personnel, servant à connaitre le type de personnel ainsi que son salaire horaire
 */
public enum TypePersonnel {
    MENAGE("ménage", 11), VIGILE("vigile", 13), BARMAN("barman",12), ;

    private double salaire;
    private String libelle;

    private TypePersonnel(String libelle, double salaire) {
        this.libelle = libelle;
        this.salaire = salaire;
    }

    public double getSalaire() {
        return salaire;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}
