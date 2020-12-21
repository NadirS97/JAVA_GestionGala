import java.io.Serializable;

public class Table implements Serializable  {
	private int numTable;
	private int nbPlace;
	/**
	 * public Table(int num):
	 *  constructeur de l'objet Table en param�tre 
	 *  le num�ro du table
	 * @param num
	 */
	public Table(int num) {
		numTable=num;
		nbPlace=8;
	}
	/**
	 * public int getNumTable():
	 * 
	 * @return le num�ro de la table.
	 */

	public int getNumTable() {
		return numTable;
	}

	/**
	 * public int getNbPlace():
	 * 
	 * @return le nombre de place de la 
	 * table encore disponible 
	 */
	public int getNbPlace() {
		return nbPlace;
	}
	/**
	 * public void prendPlace(int n):
	 * prend en param�tre un nombre
	 * de place et l'enl�ve au nombre de place disponible 
	 * de la table
	 * @param n
	 */
	public void prendPlace(int n) {
		nbPlace-=n;
	}


}
