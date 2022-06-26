package fr.uga.iut2.genevent.modele;

/**
 * Enumeration utilisée par la classe Personnel, servant à connaitre le type de personnel ainsi que son salaire horaire
 */
public enum TypePersonnel {
    MENAGE("Agent de ménage", 110), VIGILE("Agent de sécurité", 115), BARMAN("Barman",113) ;

    private double salaire; // salaire à la journée
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

    @Override
    public String toString() {
        return getLibelle();
    }
}
