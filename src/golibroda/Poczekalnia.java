package golibroda;

import java.util.LinkedList;

public class Poczekalnia {
	private LinkedList<Dane> kolejka;
	private int limit;
	private boolean strzyze;

	public Poczekalnia(int limit) {
		this.limit = limit;
		kolejka = new LinkedList<>();
		strzyze = false;
	}

	public void dodaj(Dane dane) {
		synchronized (kolejka) {
			if (kolejka.size() == limit) {
				System.out.println("Brak miejsca w poczekalni. Klient " + dane + " rezygnuje");
				return;
			}
			kolejka.offer(dane);
			if (kolejka.size() == 1 && !strzyze) {
				System.out.println("Klient " + dane + " budzi golibrode");
				kolejka.notify();
			} else {
				System.out.println("Klient " + dane + " idzie do kolejki");
				kolejka.notify();
			}
		}
	}

	public void strzyzenie() {
		synchronized (kolejka) {
			if (kolejka.size() == 0) {
				strzyze = false;
				System.out.println("Brak klientow. Golibroda idzie spac");

				try {
					kolejka.wait();
				} catch (InterruptedException e) {
				}
			}

			System.out.println("Golibroda strzyze Klienta " + kolejka.poll());
			strzyze = true;
		}
		try {
			Thread.sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
		}

	}
}
