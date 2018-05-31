package cocktailMixen;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Ronja Schöttler, Hans Hartmann
 *
 */
public class Messbecher extends AbstractBehaelter {

	public Messbecher(int anz) {
		super(anz);
	}
	
	/**
	 * Diese Methode beschreibt das messen der benötigten Flüssigkeiten.
	 * Es handelt sich um einen kritischen Abschnitt, der Messbecher muss aquired sein
	 * @param dauer des Messens
	 * @param name des mixenden Studentens
	 */
	public void messen(int dauer, String name) {
		try {
			this.aquire();
			TimeUnit.MILLISECONDS.sleep(dauer);
			System.out.println("StudentNr."+name +":  Spiritosen abmessen  || Semaphore Messbecher available Permits "+semaphore.availablePermits());
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();			
		} 
	}
	
	/**
	 * Diese Methode beschreibt das entleeren des Messbechers in den Cocktailshaker.
	 * Es handelt sich um einen kritischen Abschnitt, der Messbecher, sowie der Shaker müssen aquired sein.
	 * @param shaker 
	 * @param name des mixenden Studentens
	 */
	public void entleeren(Shaker shaker, String name){		
		try {
			shaker.befüllen(name);						
			TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1,5));	
			System.out.println("StudentNr."+name +"  Messbecher entleeren  || Semaphore Messbecher available Permits "+semaphore.availablePermits());
			this.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} 
	}
}
