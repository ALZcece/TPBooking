package TPBooking;

public class Notes implements IAffichable {
    public int note;
    public String commentaire;
    public Client auteur;
    public PeriodesDispo dates;

    public Notes(int note, String commentaire, Client auteur, PeriodesDispo dates) {
        this.note = note;
        this.commentaire = commentaire;
        this.auteur = auteur;
        this.dates = dates;
    }

    @Override
    public void afficher() {
        System.out.println("Note: " + note);
        System.out.println("Commentaire: " + commentaire);
        System.out.println("Auteur: " + auteur.getNom() + " " + auteur.getPrenom());
        System.out.println("Dates de séjour: du " + dates.debut + " au " + dates.fin);
    }

    @Override
    public String toString() {
        return "Notes{note=" + note +
            ", commentaire='" + commentaire + '\'' +
            ", auteur=" + (auteur != null ? auteur.getNom() + " " + auteur.getPrenom() : "null") +
            ", dates=[" + dates.debut + " - " + dates.fin + "]}";
    }
}
