package TPBooking;
// import scanner pour la méthode login
import java.util.Scanner;
import java.util.List;

/**
 * Classe de base partagée par tous les types de clients.
 * Contient les champs communs et un comportement par défaut.
 */
public class Client {
    public String nom;
    public String prenom;
    public int id;
    public String email;
    public String motDePasse;
    public java.util.Date dateInscription;
    public int nbReservations;
    public List<Reservation> reservations = new java.util.ArrayList<>();

    public Client(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateInscription = new java.util.Date();
        this.nbReservations = 0;
        this.id = (int) (Math.random() * 10000); // Générer un ID aléatoire pour le client
    }

    /*public static class CritereRecherche {
        public String destination;
        public java.util.Date arrivee;
        public java.util.Date depart;
        public double prixMax;
        public double prixMin;
        public int capacite;
        public String type;

    }
*/
    public Client login() {
        // non utilisé 
        return this;
    }

    public double getReduction() {
        return 0.0;
    }

    /**
     * Ajoute une réservation au client et incrémente le compteur.
     */
    public void ajouterReservation(Reservation r) {
        reservations.add(r);
        nbReservations++;
    }
}
