import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		  LocalDate dateAujourdhui= LocalDate.now();	
		  LocalDate datl= LocalDate.of(2019, Month.FEBRUARY,3);	
		  Controleur c=null;
		try {
			c = new Controleur(datl);
		} catch (IOException e2) {
	
			e2.printStackTrace();
		}
		int n = 0;
		int cpt=0;
		do {
			
		try {
			c.ctlPrincipale(n=c.ctlAcceuil(),cpt);
			cpt++;
		}catch(IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
		}catch(InputMismatchException e3) {
			System.out.println("Veuillez saisir un nombre entier");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		}while(n!=3);
	}

}
