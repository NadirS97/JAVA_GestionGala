import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author GUINDO Mouctar Ousseini
 * @author SAIAH Nadir
 * @author NGUYEN Danh
 *
 */
public class Reservation implements Serializable  {
	private LocalDate dateReservation;
	private Table table;
	public Reservation(LocalDate date,int num,int nbP) {
		dateReservation=date;
		table =new Table(num);
		table.prendPlace(nbP);

	}
	public Reservation(LocalDate date,int nbP) {
		dateReservation=date;
		table =new Table(0);
		
	}
	public int getNumTable() {
		return table.getNumTable();
	}
	public int getNbPlace() {
		return table.getNbPlace();
	}
	
}
