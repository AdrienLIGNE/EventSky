package fr.uga.iut2.genevent.modele;

public class Materiel extends Reservable {
    private String label;

    /**
     * Constructeur de matériel avec seulement le label, quantité initiale à 0
     * @param label
     */
    public Materiel(String label){
        this.label = label;
    }

    public Materiel(String label, int quantite){
        this.label = label;
        setQuantiteDisponible(quantite);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
