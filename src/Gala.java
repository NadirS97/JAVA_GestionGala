import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * 
 * @author GUINDO Mouctar Ousseini
 * @author SAIAH Nadir
 * @author NGUYEN Danh
 *
 */
public class Gala implements Serializable {
	private LocalDate dateGala;
	private int tarif1=10;
	private int tarif2=15;
	private int tarif3=20;
	private int nbPlaceEtu=120;
	private int nbPlacePerso=80;
	private final int nbPlaceTotalP=80;
	SortedMap<Integer, Etudiant> listeEtu;
	SortedMap<Integer, Etudiant> listEtuInscrit;
	SortedMap<Integer, Personnel> listePers;
	SortedMap<Integer, Personnel> listPersInscrit;
	SortedMap<Integer, Table> listeTable;
	Map<Integer, String> lesDemandAcpp;
	Deque<Integer> listAttente;
	List<Personnel> listReserPers;
	List<Etudiant> listReserEtu;
	
	/**
	 * 
	 * @param dat
	 * @param fichierEtu
	 * @param fichierPers
	 * @throws IOException
	 * @public Gala() c'est le constructeur de l'objet gala 
	 * qui prend en paramètre la date du gala et le nom des deux
	 * fichiers en charger(personnel.csv et etudiant.csv) 
	 */
	public Gala(LocalDate dat,String fichierEtu,String fichierPers) throws IOException {
		this.dateGala=dat;
		listeEtu=new TreeMap<>();
		listePers=new TreeMap<>();
		listeEtu=chargerEtudiant(fichierEtu, listeEtu);
		listePers=chargerPersonnel(fichierPers, listePers);
		listeTable=chargerTable(21);
		listEtuInscrit =new TreeMap<>();
		listPersInscrit=new TreeMap<>();
//		listAttente=new TreeMap<>(Collections.reverseOrder());
		listAttente=new ArrayDeque();
		lesDemandAcpp=new TreeMap<>();
		listReserPers=new ArrayList<>();
		listReserEtu=new ArrayList<>();

	}
	/**
	 * 
	 * @param f
	 * @param l
	 * @return
	 * @throws IOException
	 * cette methode chargerEtudiant lit dans le fichier etudiant.csv et 
	 * le charge dans un Map de type treeMap et retourne ce dernier
	 */
	private SortedMap<Integer, Etudiant> chargerEtudiant(String f,SortedMap<Integer, Etudiant> l)throws IOException{
		String ligne="";
		BufferedReader br;

		br=new BufferedReader(new FileReader(f));
		while((ligne=br.readLine())!=null) {
			Scanner in=new Scanner(ligne);
			while(in.hasNext()) {
				String num=in.next();
				String nom=in.next();
				String pren=in.next();
				String numTel=in.next();
				String email=in.next();
				String niv=in.next();
				Etudiant e=new Etudiant(Integer.parseInt(num), nom, pren, numTel, email,Integer.parseInt(niv));
				l.putIfAbsent(e.getNumEtudiant(), e);
			} in.close();

		}
		br.close();


		return l;

	}
	/**
	 * 
	 * @param f
	 * @param l
	 * @return
	 * @throws IOException
	 * cette methode chargerPersonnel lit dans le fichier personnel.csv et 
	 * le charge dans un Map de type treeMap et retourne ce dernier
	 */
	private SortedMap<Integer, Personnel> chargerPersonnel(String f,SortedMap<Integer, Personnel> l) throws IOException{
		String ligne="";
		BufferedReader br;

		br=new BufferedReader(new FileReader(f));
		while((ligne=br.readLine())!=null) {
			Scanner in=new Scanner(ligne);
			while(in.hasNext()) {
				String num=in.next();
				String nom=in.next();
				String pren=in.next();
				String numTel=in.next();
				String email=in.next();

				Personnel e=new Personnel(Integer.parseInt(num), nom, pren, numTel, email);
				l.putIfAbsent(e.getNumPerssonnel(), e);
			} in.close();

		}
		br.close();




		return l;

	}
	/**
	 * 
	 * @param nbtable
	 * @return
	 */
	private SortedMap<Integer, Table>chargerTable(int nbtable){
		SortedMap<Integer,Table>listeTable=new TreeMap<>();
		for (int i = 0; i < nbtable; i++) {
			listeTable.put(i+1, new Table(i+1));

		}
		return listeTable;
	}
	public int getTarif1() {
		return tarif1;
	}
	public int getTarif2() {
		return tarif2;
	}

	public int getTarif3() {
		return tarif3;
	}

	/**
	 * 
	 * @param num
	 * @return l'etudiant correspondant au numeros num dans la liste des
	 * etudiant
	 */
	public Etudiant getEtudiant(int num) {
		return listeEtu.get(num);
	}
	/**
	 * 
	 * @param num
	 * @return le niveau de formation de l'etudiant
	 * dont le numeros d'identifiant est passe en 
	 * paramètre
	 */
	public int getNiveau(int num) {
		return listeEtu.get(num).getNivFormation();
	}
	/**
	 * 
	 * @param num
	 * @return le personnel correspondant au numeros (num) dans la liste du
	 * personnel
	 */

	public Personnel getPersonnel(int num) {
		return listePers.get(num);
	}
	/**
	 * return le nombre de place totale reserver aux etudiants
	 */
	public int getNbPlaceEtu() {
		return nbPlaceEtu;
	}
		public void setNbPlaceEtu(int nbPlaceEtu) {
		this.nbPlaceEtu = nbPlaceEtu;
	}
	/**
	 * 
	 * @return le nombre de place totale reserver aux personnels
	 */
	public int getNbPlacePerso() {
		return nbPlacePerso;
	}
	public void setNbPlacePerso(int nbPlacePerso) {
		this.nbPlacePerso = nbPlacePerso;
	}
	/**
	 * 
	 * @param num
	 * @return la table correspondant au numeros (num) dans la liste des collections
	 * des tables
	 */
	public Table choixTable(int num) {
		return  listeTable.get(num);
	}	
	/**
	 * 
	 * @param num
	 * @param nbp
	 * @return 
	 */
	public boolean possedeAssezPlace(int num,int nbp) {
		return listeTable.get(num).getNbPlace()>=nbp;
	}

	public int attribuerTable(int nbp) {
		int n=0;
		for (int i = 1; i < 11; i++) {
			if(possedeAssezPlace(i, nbp)){
				return i;

			}
		}
		return -1;
	}
	/**
	 * 
	 * @param nbp
	 * @return un numero de table disponible reserver aux etudiants
	 * 
	 * 
	 */
	public int attribuerTableEtu(int nbp) {
		int n=0;
		for (int i = 11; i < 22; i++) {
			if(possedeAssezPlace(i, nbp)){
				return i;

			}
		}
		return -1;
	}
	/**
	 * 
	 * @param num
	 * @return le niveau de formation d'un etudiant
	 */
	public int getNiveu(int num) {
		return listeEtu.get(num).getNivFormation();
	}
	public LocalDate getDateGala() {
		return dateGala;
	}
	/**
	 * public boolean unMoisAvGala().
	 * cette methode 
	 * @return true si  la date du jour est inferieur ou egale à 
	 * un mois avant la date du gala sinon elle retourne false;
	 */
	public boolean unMoisAvGala() {
		LocalDate ld= LocalDate.now();
		Period pd =Period.between(ld,dateGala);
		double nbjour=(pd.getMonths()*30)+pd.getDays();
		return nbjour<32;
	}
	/**
	 *  public boolean annulerReserPossible().
	 *  
	 * @return retourne vraie s’il reste au moins dix 
	 * avant la date du gala et faux sinon. 
	 * Cette methode permet de savoir si  le participant peut ou pas 
	 * annuler sa reservation dejà enregistrer.
	 */
	public boolean annulerReserPossible() {
		LocalDate ld= LocalDate.now();
		Period pd =Period.between(ld,dateGala);
		double nbjour=(pd.getMonths()*30)+pd.getDays();
		return nbjour>=10;
	}
	/**
	 * 
	 * :alimente l’ensemble des etudiants dont la demande aurai ete accepter 
	 * il lit dans une dictionnaire liste d’attende de type 
	 * Map -->TreeMap qui lui enregistre les etudiants 
	 * par ordre decroissant de façons 
	 * à accepter les etudiants de dernier en priorite
	 * 
	 */
	public void demandeAccepter() {
		
		Iterator<Integer> it=listAttente.iterator();
		int nb=0;
		Set<Integer>set=lesDemandAcpp.keySet();
		Iterator<Integer>itnb=set.iterator();
		while (itnb.hasNext()) {
			int num=itnb.next();
			Etudiant e=listeEtu.get(num);
			nb+=e.getNbPlaceRerv();
			
		}
		if(nb<=nbPlaceEtu) {
		while(it.hasNext() && nb<=nbPlaceEtu) {
			int num=it.next();
			System.out.println();
			Etudiant e=listEtuInscrit.get(num);
			nb+=e.getNbPlaceRerv();
			if(nb<=nbPlaceEtu) {
			lesDemandAcpp.put(num,e.getInfoR());
			}else break;

		}
	}
	}
	/**
	 * 
	 * @param num
	 * @param nb
	 * @return le montant de la reservation d'un 
	 * etudiant dont le numeros est passe en paramètre (num)
	 */
	public int getTarifReserEtu(int num,int nb) {
		if(listEtuInscrit.get(num).getNivFormation()<5)return getTarif2()*nb;
		return getTarif1()*nb;
	}
	/**
	 * 
	 * @param num
	 * @param nb
	 * @return le montant de la reservation d'un 
	 * personnel dont le numeros est passe en paramètre (num)
	 */
	public int getTarifPers(int num,int nb) {
		return getTarif3()*nb;
	}
	/**
	 * 
	 * @return
	 */
	public String infoInscritPers() {
		String res="";
		for (Personnel personnel : listReserPers) {
			res+=personnel.getInfoR()+"\n";
		}
		return res;
	}
	/**
	 * 
	 * @return
	 */
	public String infoInscritEtu() {
		String res="";
		for (Etudiant etudiant : listReserEtu) {
			res+=etudiant.getInfoR()+"\n";
		}
		return res;
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean placeDispoPers(int n){

		return getNbPlacePerso()>=n;

	}
	/**
	 * 
	 * @return
	 */
	public int getNbPlaceTotalP() {
		return nbPlaceTotalP;
	}
	/**
	 * 
	 */
	public void supprimerReserPers(int num) {
		listPersInscrit.remove(num);
		Personnel p =listPersInscrit.get(num);
		listReserPers.remove(p);
	}
	/**
	 * 
	 * @param num
	 */
	public void supprimerReserEtu(int num) {
		listAttente.remove(num);
		listEtuInscrit.remove(num);
	}
	/**
	 * desinscrirePers(int num). desinscrire un personnel  inscrit
	 * @param num
	 */
	public void desinscrirePers(int num) {
		listPersInscrit.remove(num);
	}
	/**
	 * desinscrireEtu(int num). desinscrire un etudiant inscrit  
	 * @param num 
	 * 
	 * 
	 */
	public void desinscrireEtu(int num) {
		listEtuInscrit.remove(num);
	}



}



