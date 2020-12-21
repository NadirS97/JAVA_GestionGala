import java.io.Serializable;
import java.time.LocalDate;

public class Personnel implements Serializable {
	private int numPersonnel;
	private String nom;
	private String prenom;
	private String numTelephone;
	private String mail;
	private Reservation reservation;
	private int nbPlacRerv=0;
	private String InfoR;
	/**
	 * 
	 * @param numPersonnel
	 * @param nom
	 * @param prenom
	 * @param numTel
	 * @param mail
	 */
	public Personnel(int numPersonnel,String nom, String prenom,String numTel,String mail ) {
		this.numPersonnel=numPersonnel;
		this.nom=nom;
		this.prenom=prenom;
		this.numTelephone=numTel;
		this.mail=mail;
	}
	/**
	 * 
	 * @return le l'identifiant du personnel
	 */
	public int getNumPerssonnel() {
		return numPersonnel;
	}
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNumTelephone() {
		return numTelephone;
	}

	public String getMail() {
		return mail;
	}
	public String toString() {
		return "Num�ros d'identification :" +numPersonnel+
				"\n"+"Nom : "+nom+"\n"+"Pr�nom: "+prenom+"\n"+
				"Num�ro de t�l�phone : "+numTelephone+
				"\n"+"Adresse mail: "+mail;
	}
	/**
	 *  reservationPers(LocalDate date,int num,int nbP):
	 *  cr�er une r�servation 
	 * @param date
	 * @param num
	 * @param nbP
	 */
	public void reservationPers(LocalDate date,int num,int nbP) {
		reservation = new Reservation(date, num, nbP);
		nbPlacRerv=nbP;
	}
	/**
	 * aReserver():
	 * @return true si le personnel � r�server et false sinon.
	 */
	public boolean aReserver() {
		return reservation!=null;
	}
	/**
	 * numTableReservation():
	 * 
	 * @return le num�ros de la table r�server.
	 */
	public int numTableReservation(){
		return reservation.getNumTable();
	}
	/**
	 * getNbPlaceRerv(): 
	 * @return le nombre de place r�server
	 */
	public int getNbPlaceRerv() {
		return nbPlacRerv;
	}
	
	public String InfoRerserPers(String res) {
		String str="";

		str="Nom: "+nom + " "+ res+"--->"+"Table n�"+numTableReservation()+"\n";
		InfoR=str;
		return str;
	}
	/**
	 * getInfoR()
	 * 
	 * @return la chaine de caract�re contenant l'information
	 * de la reservation c'est � dire le nom du personnel + nom de l'invit�
	 * et le num�ro de la table r�server
	 */
	public String getInfoR() {
		return InfoR;
	}

}
