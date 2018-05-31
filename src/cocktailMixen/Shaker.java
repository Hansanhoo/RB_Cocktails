package cocktailMixen;


import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Ronja Schöttler, Hans Hartmann
 *
 */
public class Shaker extends AbstractBehaelter {

	public Shaker(int anz) {
		super(anz);
	}	

	/**
	 * Diese Methode beschreibt das shaken der Cocktails. 
	 * Es handelt sich um einen kritischen Abschnitt, der Cocktailshaker muss aquired sein.
	 * @param dauer des Shakens
	 * @param name des mixenden Studentens
	 */
	public void shacken(int dauer,String name) {
		try {
			TimeUnit.MILLISECONDS.sleep(dauer);
			System.out.println("StudentNr."+name +":  Cocktails shaken || Semaphore Shaker available Permits "+semaphore.availablePermits());
			this.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} 
	}
	
	/**
	 * Diese Methode beschreibt das befüllen des Cocktailshakers. 
	 * Es handelt sich um einen kritischen Abschnitt, der Messbecher, sowie der Cocktailshaker müssen aquired sein.
	 * @param name des mixenden Studentens
	 */
	public void befüllen(String name) {
		try {			
			this.aquire();					
			TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1,5));	
			System.out.println("StudentNr."+name +"  Shaker befüllen  || Semaphore Shaker available Permits "+semaphore.availablePermits());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}	
}
