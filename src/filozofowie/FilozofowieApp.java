package filozofowie;

import java.util.concurrent.Semaphore;

public class FilozofowieApp {

	public static void main(String[] args) {
		new Thread(new Filozof("Sokrates", 0)).start();
		new Thread(new Filozof("Platon", 1)).start();
		new Thread(new Filozof("Arystoteles", 2)).start();
		new Thread(new Filozof("Kartezjusz", 3)).start();
		new Thread(new Filozof("Locke", 4)).start();

	}
}

class Filozof implements Runnable {
	private static final Semaphore miejsce = new Semaphore(4, true);
	private static final Semaphore[] widelce = { new Semaphore(1, true), new Semaphore(1, true), new Semaphore(1, true),
			new Semaphore(1, true), new Semaphore(1, true) };
	private String name;
	private int numer;

	public Filozof(String name, int numer) {
		this.name = name;
		this.numer = numer;
	}

	@Override
	public void run() {

		for (int i = 0; i < 3; ++i) {
			boolean zjadl = false;
			try {

				mysli();
				miejsce.acquire();
				while (!zjadl) {
					if (widelce[numer].tryAcquire()) {
						if (widelce[(numer + 1) % 5].tryAcquire()) {
							je();
							zjadl = true;
							widelce[(numer + 1) % 5].release();
						}
						widelce[numer].release();
					}
					if (!zjadl)
						mysli();
				}
				miejsce.release();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void mysli() throws InterruptedException {
		System.out.println(name + " mysli");
		Thread.sleep((int) (Math.random() * 4000));
	}

	public void je() throws InterruptedException {
		System.out.println(name + " je");
		Thread.sleep((int) (Math.random() * 6000));
		System.out.println(name + " skonczyl jesc");
	}

	@Override
	public String toString() {
		return name;
	}
}
