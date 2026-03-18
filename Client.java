package TPBooking;

import java.util.List;
import java.util.Scanner;

public abstract class Client extends Personne {
    public String email;
    public String motDePasse;
    public java.util.Date dateInscription;
    public int nbReservations;
    public List<Reservation> reservations = new java.util.ArrayList<>();

    public Client(String nom, String prenom, String email) {
        super(nom, prenom);
        this.email = email;
        this.dateInscription = new java.util.Date();
        this.nbReservations = 0;
    }

    public abstract Client login(Scanner sc);

    public abstract double getReduction();

    @Override
    public void afficher() {
        System.out.println("--- Client ---");
        System.out.println("ID: " + id);
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Email: " + email);
        System.out.println("Date inscription: " + dateInscription);
        System.out.println("Réservations: " + nbReservations);
        System.out.println("----------------");
    }

    public void ajouterReservation(Reservation r) {
        if (r != null) {
            reservations.add(r);
            nbReservations++;
        }
    }
}
