package fr.uga.iut2.genevent.modele;

public enum TypeEvenement {
    THEATRE("Théâtre", "Pièce de théâtre"),
    CONCERT("Concert", ""),
    DANSE("Danse", "Spectacle de danse"),
    CIRQUE("Cirque", "Spectacle de cirque"),
    MAGIE("Magie", "Spectacle de magie et illusionnisme"),
    MARIONNETTE("Marionnette", "Spectacle de marionnettes" ),
    CINEMA("Cinéma", "Diffusion d'un film en cinéma intérieur ou cinéma plein air");


    private String nom;
    private String description;

    private TypeEvenement(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}
