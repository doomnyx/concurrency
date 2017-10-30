package golibroda;
public class GolibrodaTest {

	public static void main(String[] args) {
		Poczekalnia salon = new Poczekalnia(3);
		new Thread(new Golibroda(salon)).start();
		new Thread(new Klient(salon)).start();
		new Thread(new Klient(salon, "*")).start();
		new Thread(new Klient(salon, "^")).start();
		new Thread(new Klient(salon, "%")).start();
//		new Thread(new Klient(salon, "!")).start();
//		new Thread(new Klient(salon, "@")).start();
//		new Thread(new Klient(salon, "#")).start();
//		new Thread(new Klient(salon, "$")).start();
//		new Thread(new Klient(salon, "&")).start();
//		new Thread(new Klient(salon, "-")).start();

	}

}
