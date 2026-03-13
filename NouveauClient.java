package TPBooking;

import java.util.Scanner;

/**
 * Nouveau client qui doit s'inscrire. Pas de réduction.
 */
public class NouveauClient extends Client {
    public NouveauClient() {
        super("", "", "");
    }

    @Override
    public Client login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Inscription ---");
        System.out.print("Nom: ");
        this.nom = sc.nextLine();
        System.out.print("Prénom: ");
        this.prenom = sc.nextLine();
        System.out.print("Email: ");
        this.email = sc.nextLine();
        System.out.print("Mot de passe: ");
        this.motDePasse = sc.nextLine();
        this.dateInscription = new java.util.Date();
        System.out.println("Compte créé avec succès! Bienvenue " + prenom + "!");
        return this;
    }

    @Override
    public double getReduction() {
        // offre de bienvenue : 10% 
        System.out.println("Offre de bienvenue: 10% de réduction sur votre première réservation.");
        return 0.10;
    }
}