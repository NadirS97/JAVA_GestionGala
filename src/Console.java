import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 
 * @author GUINDO Mouctar Ousseini
 * @author SAIAH Nadir
 * @author NGUYEN Danh
 *
 */
public class Console {

	public void acceuil() {
		System.out.println("");
	}
	/**
	 * 
	 *Permet aux utilisateurs de indiquer si il est etudiant ou personnel 
	 *ou s'il veut quitter l'application 
	 *
	 * @return le choix de l'utilisateur
	 */
	public int choixAcceuil() {
		System.out.println("1: Personnel");
		System.out.println("2: Etudiant");
		System.out.println("3: Quitter(Sauvegarder)");
		Scanner in =new Scanner(System.in);
		int c=in.nextInt();
		return c;
	}


	/**
	 * 
	 * demande la saisi du numeros d'identication
	 * @return le numeros
	 * 
	 * 
	 */
	public int getId() {
		System.out.print("Entr�e votre Id : ");
		Scanner in =new Scanner(System.in);
		int c=in.nextInt();
		return c;
	}
	/**
	 * placeRestant(int n): affiche le nombre de place restant
	 * @param n
	 */
	public void placeRestant(int n) {
		System.out.println("Le nombre de place restant est : "+n);
	}
	/**
	 * 
	 * affiche le toString de l'etudiant passe en param�tre
	 * @param e
	 */
	public void AffichageToStringEtu(Etudiant e) {
		System.out.println("********************************");
		System.out.println(e.toString());
		System.out.println("********************************");
	}
	/**
	 * 
	 * affiche le toString du personnel passe en param�tre
	 * @param p
	 */

	public void AffichageToStringPers(Personnel p) {
		System.out.println("********************************");
		System.out.println(p.toString());
		System.out.println("********************************");
	}

	/**
	 * 
	 * demande le nombre de place souhaiter pour la reservation
	 * et cette valeur
	 * @return 
	 */

	public int nbPlaceSouhaiter() {
		System.out.print("Combien de place souhaiter vous r�server: ");
		Scanner in =new Scanner(System.in);
		int c=in.nextInt();
		return c;
	}



	public void dateDuProchainGala(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		String res="La date du prochain gala est le";
		System.out.println(res+" "+dtf.format(date));

	}

	/**
	 *
	 * demande le numero de table souhaiter pour la reservation
	 * des etudiants
	 * @return cette valeur
	 */
	public int numTableSouhaiterEtu() {
		System.out.print("Saisir le numeros de table pour "
				+ "�tudiant de la table n�11 � 21");
		Scanner in =new Scanner(System.in);
		int n=in.nextInt();
		return n;
	}
	/**
	 * 
	 * demande le numero de table souhaite pour la r�servation
	 * du personnel
	 * @return
	 */
	public int numTableSouhaiterPers() {
		System.out.print("Saisir le numeros de table Pour"
				+ " personnel de la table n�1 � 10");
		Scanner in =new Scanner(System.in);
		int n=in.nextInt();
		return n;
	}
	/**
	 * 
	 * @param nb
	 * demande le nom de ou des invite
	 * @return le nom de ou des invite
	 */
	public String inFoInviter(int nb) {
		Scanner in=new Scanner(System.in);
		String res="";
		for (int i = 0; i < nb ;i++) {
			System.out.print("Nom du inviter "+(i+1)+" ");
			String nom=in.nextLine();
			res+="Invit� : "+nom + "\n";
		}
		return res;


	}
	/**
	 * 
	 * demande de s'insrire ou de quitter
	 * @return la valeur du choix
	 */

	public int etatRservation() {
		Scanner in =new Scanner(System.in);
		System.out.println("1: S'inscrire");
		System.out.println("2: Quitter");
		int n=in.nextInt();
		return n;
	}
	/**
	 * public int choixGerer()
	 * demande si l'utilisateur souhaite gerer les palces du d�ner
	 * ,se desinscrire ou de quitter
	 * @return
	 */
	public int choixGerer() {
		Scanner in =new Scanner(System.in);
		System.out.println("1- G�rer les places du d�ner");
		System.out.println("2- Se d�sinscrire");
		System.out.println("3- Quitter");
		int n=in.nextInt();
		return n;
	}
	/**
	 * public int choixGererE():
	 * c'est sous menu etudiant apr�s l'inscription
	 * @return
	 */
	public int choixGererE() {
		Scanner in =new Scanner(System.in);
		System.out.println("1- Saisir le nombre de place");
		System.out.println("2- Se d�sinscrire");
		System.out.println("3- Quitter");
		int n=in.nextInt();
		return n;
	}
	/**
	 * public void nbPlaceRe(int n):
	 * affiche le nombre place que l'utilisateur peut
	 * reservez au maximum.
	 * @param n
	 */
	public void nbPlaceRe(int n) {
		System.out.println("Vous devez r�server entre 1 et " +n+" places");
	}
	/**
	 * public int choixRPers():
	 * le sous menus Consulter le plan des tables ou non.
	 * @return
	 */
	public int choixRPers() {
		Scanner in =new Scanner(System.in);
		System.out.println("1: Consulter le plan des tables");
		System.out.println("2: une table disponible vous seras attribuez ");
		int n=in.nextInt();
		return n;
	}
	/**
	 * 
	 * @param n
	 */
	public void pasAsserDePlace(int n) {
		System.out.println("La table n�"+n +" n'a pas assez de place");
		System.out.println("De nouveau");
	}
	/**
	 * 
	 * @param numT
	 * @param nbp
	 */
	public void infoReservPers(int numT,int nbp) {
		System.out.println("Votre num�ros table est: "+"la table n�"+numT);
		System.out.println("Vous avez r�servez :"+nbp +" place");
	}
	/**
	 * 
	 * @param nbp
	 */
	public void infoReservEtu(int nbp) {
		System.out.println("Vous avez r�servez :"+nbp +" places");
	}
	/**
	 * public int confirmer():
	 * menu pour la demande de confirmation de la 
	 * r�servation.
	 * @return
	 */
	public int confirmer() {
		Scanner in =new Scanner(System.in);
		System.out.println("1: Confirmer votre demande");
		System.out.println("2: Annulez");
		int n=in.nextInt();
		return n;
	}

	/**
	 * public void prixTotale(int prix):
	 * affiche le prix totale de la r�servation.
	 * @param prix
	 */

	public void prixTotale(int prix) {
		System.out.println("Prix totale: "+prix+"Euros");
		System.out.println();
	}
	/**
	 * 
	 * @param str
	 */
	public void planReserPers(String str) {
		System.out.println(str);

	}
	/**
	 * 	public void pasDePlace():
	 * affiche le m�ssage s'il n'ya plus 
	 * de place disponible pour les personnels.
	 * 
	 */
	public void pasDePlace() {
		System.out.println();
		System.out.println("D�sol� il n'ya pas assez de place"
				+" pour effectuer votre r�servation");
		System.out.println();
	}
	/**
	 * public int annulerReser():
	 * menu d'annulation de la r�servation
	 * @return
	 */
	public int annulerReser() {
		Scanner in =new Scanner(System.in);
		System.out.println("1: Annulez la reservation (possible au plus tard 10 jours avant la date du gala)");
		System.out.println("2: Quitter");
		int n=in.nextInt();
		return n;
	}
	/**
	 * public void impossAnn() :
	 * affiche un message si l'annulation n'est pas possible.
	 */
	public void impossAnn() {
		System.out.println("Impossible d'annuler la r�servation");
	}
	/**
	 * public void choixImpossi():
	 * affiche un message d'erreur 
	 * si l'utilisateur entre une mauvaise valeur.
	 */
	public void choixImpossi() {
		System.out.println("Choix non disponible");
	}
	/**
	 * nbPlaceImpo():
	 * message d'erreur si l'utilisateur souhaite reserver un
	 * nombre de place non disponible.
	 */
	public void nbPlaceImpo() {
		System.out.println("Impossible de r�server ce nombre de places"+"\n");
	}
	public void formatIncorrect() {
		System.out.println("Format incorrect veuillez saisir un entier");
	}
	
	public void attente() {
		System.out.println("****************************");
		System.out.println("Votre demande est en attente");
		System.out.println("****************************");
	}
}
