package TPBooking;

public abstract class Personne {
    public int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = Math.abs(java.util.UUID.randomUUID().hashCode());
    }

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    public abstract void afficher();

    @Override
    public String toString() {
        return "Personne{id=" + id + ", nom=" + getNom() + ", prenom=" + getPrenom() + "}";
    }
}