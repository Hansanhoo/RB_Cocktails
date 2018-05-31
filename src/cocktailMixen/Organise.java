package cocktailMixen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;


/**
 * @author Ronja Schöttler, Hans Hartmann
 *
 */
public class Organise {
	
	static Shaker shaker;
	static Messbecher messbecher;

	/**
	 *  Die Main Methode holt sich von dem Benutzer die Anzahl der mixenden Studenten, die Anzahl der Messbecher und der Cocktailshaker
	 *  erstellt dementsprechend die jeweiligen Objekte und startet die Threads/ Studenten
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bitte geben Sie die Anzahl Studierender an");
		int anzStudierende = scanner.nextInt();

		System.out.println("Bitte geben sie die Anzahl der Messbecher an");
		int anzMessbecher = scanner.nextInt();

		System.out.println("Bitte geben sie die Anzahl der Cocktailshaker an");
		int anzShaker = scanner.nextInt();
		System.out.println("Lets Shake!");
		scanner.close();

		shaker = new Shaker(anzShaker);
		messbecher = new Messbecher(anzMessbecher);
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 1; i <= anzStudierende; i++) {
			bestellungenErstellen(String.valueOf(i), ThreadLocalRandom.current().nextInt(1, 5));
		}
		for (int i = 1; i <= anzStudierende; i++) {
			executor.execute(new Student(String.valueOf(i), messbecher, shaker));
		}
	}

	/**
	 * Diese Methode erstellt eine Text Datei zu einem Studenten, in der seine Bestellungen hinterlegt sind
	 * @param dateiName entspricht den Namen/Nummer des Studentens
	 * @param anzBestellungen Anzahl der Bestellungen
	 */
	public static void bestellungenErstellen(String dateiName, int anzBestellungen) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("StudentNr"+dateiName + ".txt"));
			for (int i = 1; i <= anzBestellungen; i++) {
				bw.write(ThreadLocalRandom.current().nextInt(200, 500) + "|" + ThreadLocalRandom.current().nextInt(1, 5)
						+ "|" + ThreadLocalRandom.current().nextInt(20, 50) + "|"
						+ ThreadLocalRandom.current().nextInt(150, 200) + "|"
						+ ThreadLocalRandom.current().nextInt(30, 50) + "|"
						+ ThreadLocalRandom.current().nextInt(3, 10));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode führt den Ablauf mit den beiden Studenten Kai und Susanne aus
	 * Beispiel aus der Aufgabenstellung 
	 */
	public static void onDefault() {
		shaker = new Shaker(1);
		messbecher = new Messbecher(1);
		ExecutorService executor = Executors.newCachedThreadPool();

		bestellungenErstellen("susanne", ThreadLocalRandom.current().nextInt(1, 5));
		bestellungenErstellen("kai", ThreadLocalRandom.current().nextInt(1, 5));

		Student susanne = new Student("susanne", messbecher, shaker);
		Student kai = new Student("kai", messbecher, shaker);

		executor.execute(kai);
		executor.execute(susanne);
	}
}
