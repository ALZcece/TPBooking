package TPBooking;

public abstract class Personne {
    public int id;
    public String nom;
    public String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = Math.abs(java.util.UUID.randomUUID().hashCode());
    }

    public abstract void afficher();
}