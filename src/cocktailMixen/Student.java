package cocktailMixen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Ronja Schöttler, Hans Hartmann
 *
 */
public class Student implements Runnable {

	String name;
	Messbecher messbecher;
	Shaker shaker;
	long beginTime;
	long difference;

	public Student(String name, Messbecher messbecher, Shaker shaker) {
		this.name = name;
		this.messbecher = messbecher;
		this.shaker = shaker;
		difference = 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("StudentNr"+this.name + ".txt"));
			String line;
			while ((line = br.readLine()) != null) {

				String[] bestellungSplit = line.split("\\|");
				
				int OFFSET = Integer.parseInt(bestellungSplit[0]);
				int anzBestellung = Integer.parseInt(bestellungSplit[1]);
				int dauerMessen = Integer.parseInt(bestellungSplit[2]);
				int dauerShaken = Integer.parseInt(bestellungSplit[3]);
				int dauerEinfüllen = Integer.parseInt(bestellungSplit[4]);
				int pause = Integer.parseInt(bestellungSplit[5]);
				
				if (OFFSET > difference) {
					System.out.println(this.name + " muss warten");
					long wartezeit = OFFSET - difference;
					try {
						TimeUnit.MILLISECONDS.sleep(wartezeit);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				beginTime = System.currentTimeMillis();
				doBestellung(anzBestellung, dauerMessen, dauerShaken, dauerEinfüllen, pause);
				difference = System.currentTimeMillis() - beginTime;

				System.out.println("StudentNr."+this.name + " hat folgende Bestellung fertig:");
				for (int j = 0; j < bestellungSplit.length; j++) {
					System.out.print(bestellungSplit[j] + "|");
				}
				System.out.println("");
			}
			br.close();
			System.out.println("StudentNr."+this.name + " is finish");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode beschreibt das mixen eines Cocktails
	 * @param anzBestellung Anzahl der Bestellungen
	 * @param dauerMessen
	 * @param dauerShaken
	 * @param dauerEinfüllen
	 * @param pause
	 */
	public void doBestellung(int anzBestellung, int dauerMessen, int dauerShaken, int dauerEinfüllen, int pause) {
		for (int i = 1; i <= anzBestellung; i++) {
			messbecher.messen(dauerMessen,this.name);
			messbecher.entleeren(shaker, this.name);			
			shaker.shacken(dauerShaken,this.name);
			Glas.einfüllen(dauerEinfüllen, this.name);
			// Pause nach einem Cocktail
			try {
				TimeUnit.MILLISECONDS.sleep(pause);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
