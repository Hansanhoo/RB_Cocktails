package cocktailMixen;

import java.util.concurrent.TimeUnit;

/**
 * @author Ronja Schöttler, Hans Hartmann
 *
 */
public class Glas {

	/**
	 * Diese Methode beschreibt das Einfüllen der Cocktails in ein Glas
	 * @param dauer des Einfüllen in das Glas 
	 * @param name des mixenden Studentens
	 */
	public static void einfüllen(int dauer, String name) {
		try {
			TimeUnit.MILLISECONDS.sleep(dauer);
			System.out.println("StudentNr."+name +":  Cocktail in das Glas eingefüllt");
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
