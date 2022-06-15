package fr.uga.iut2.genevent.modele;

public class Materiel extends Reservable {

    private String label;
    private TypeMateriel type;

    /**
     * Constructeur de matériel avec seulement le label, quantité initiale à 0
     * @param label
     */
    public Materiel(String label, TypeMateriel type){
        this(label, type, 1);
    }

    public Materiel(String label, TypeMateriel type, int quantite){
        setLabel(label);
        setType(type);
        setQuantiteDisponible(quantite);
    }

    public void setType(TypeMateriel type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
