package fr.uga.iut2.genevent.modele;

public class Materiel {
    private String label;
    private int quantite;

    /**
     * Constructeur de matériel avec seulement le label, quantité initiale à 0
     * @param label
     */
    public Materiel(String label){
        this.label = label;
        this.quantite = 0;
    }

    public Materiel(String label, int quantite){
        this.label = label;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void ajoutQuantite(int n){
        this.quantite += n;
    }

    public void retirerQuantite(int n){
        this.quantite -= n;
    }
}
