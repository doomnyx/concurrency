package aplikacjaABC;

import java.util.concurrent.Semaphore;

public class ABCApp {

	public static void main(String[] args) {
		new Litera("A", 0).start();
		;
		new Litera("B", 1).start();
		new Litera("C", 2).start();

	}

}

class Litera extends Thread {
	private static Semaphore[] sem = new Semaphore[3];
	private int numer;

	public Litera(String nazwa, int numer) {
		super(nazwa);
		this.numer = numer;
		sem[0] = new Semaphore(1, true);
		for (int i = 1; i < sem.length; ++i)
			sem[i] = new Semaphore(0, true);
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; ++i) {
			try {
				sem[numer].acquire();
				System.out.print(super.getName());
				sleep((int) (Math.random() * 2000));
				sem[(numer + 1) % 3].release();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (numer == 2)
				System.out.println();
		}

	}

}