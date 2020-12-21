import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author GUINDO Mouctar Ousseini
 * @author SAIAH Nadir
 * @author NGUYEN Danh
 *
 */
public class Etudiant implements Serializable{
	private int numEtudiant;
	private String nom;
	private String prenom;
	private String numTelephone;
	private String mail;
	private int nivFormation;
	private Reservation reservation;
	private int nbPlacRerv=0;
	private String InfoR;
	private boolean confir=false;
	
	/**
	 * 
	 * @param numEtudiant
	 * @param nom
	 * @param prenom
	 * @param numTel
	 * @param mail
	 * @param niv
	 */
	public Etudiant(int numEtudiant,String nom, String prenom,String numTel,String mail,int niv ) {
		this.numEtudiant=numEtudiant;
		this.nom=nom;
		this.prenom=prenom;
		this.numTelephone=numTel;
		this.mail=mail;
		this.nivFormation=niv;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumEtudiant() {
		return numEtudiant;
	}

	
	/**
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * 
	 * @return
	 */
	public String getPrenom() {
		return prenom;
	}


	/**
	 * 
	 * @return
	 */
	public String getNumTelephone() {
		return numTelephone;
	}

	/**
	 * 
	 * @return
	 */

	public String getMail() {
		return mail;
	}


	/**
	 * 
	 * @return
	 */
	public int getNivFormation() {
		return nivFormation;
	}



	public String toString() {
		return "Num�ro d'etudiant: "+getNumEtudiant() + "\n"+
				"Nom: "+getNom()+"\n"+"Prenom: "+getPrenom()+
				"\n"+"Numero de t�l�phone: "+getNumTelephone()+"\n"+
				"Adresse email: "+getMail()+"\n"+"Niveau: "+getNivFormation();


	}

	

	/**
	 * 
	 * @return
	 */
	public int tableEtu() {
		return reservation.getNumTable();
	}
	/**
	 * 
	 * @param res
	 * @return
	 */
	public String InfoRerserEtu(String res) {
		String str="";

		str="Nom: "+nom + " "+ res+"--->"+"Table n�"+numTableReservation()+"\n";
		InfoR=str;

		return str;
	}
	/**
	 * 
	 * @param date
	 * @param nbP
	 */

	public void reservationEtu(LocalDate date,int nbP) {
		reservation = new Reservation(date,nbP);
		nbPlacRerv=nbP;
	}
	/**
	 * 
	 * @param date
	 * @param num
	 * @param nbP
	 */
	public void reservationEtu(LocalDate date,int num,int nbP) {
		reservation = new Reservation(date, num, nbP);
		nbPlacRerv=nbP;
	}
	/**
	 * 
	 * @return
	 */
	public boolean aReserver() {
		return reservation!=null;
	}
	/**
	 * 
	 * @return
	 */
	public int numTableReservation(){
		return reservation.getNumTable();
	}
	/**
	 * 
	 * @return
	 */
	public int getNbPlaceRerv() {
		return nbPlacRerv;
	}
	public void setNbPlaceRerv(int n) {
		 nbPlacRerv=n;
	}
	/**
	 * 
	 * @return
	 */
	public String getInfoR() {
		return InfoR;
	}
	/**
	 * 
	 * @param infoR
	 */
	public void setInfoR(String infoR) {
		InfoR = infoR;
	}
	/**
	 * public boolean isConfirmer().
	 * @return true si l'�tudiant � confirmer 
	 * sa r�servation et false sinon
	 */
	public boolean isConfirmer() {
		return confir;
	}
	public void setIsConfirmer(boolean b) {
		confir=b;
	}
	/**
	 * 
	 * @param n
	 */
	public void setnbPlacRerv(int n) {
		nbPlacRerv=n;
	}
}
