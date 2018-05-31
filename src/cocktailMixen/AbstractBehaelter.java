package cocktailMixen;

import java.util.concurrent.Semaphore;

/**
 * @author Ronja Schöttler, Hans Hartmann
 *
 */
public abstract class AbstractBehaelter{
	
	Semaphore semaphore;
	
	public AbstractBehaelter(int anz){
		this.semaphore = new Semaphore(anz);
	}
	
	/**
	 * Semaphore aquire
	 */
	public void aquire() {
		try {			
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Semaphore release
	 */
	public void release() {
		semaphore.release();
	}

}
