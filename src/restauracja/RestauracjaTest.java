package restauracja;
//zadanie 2
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestauracjaTest {

	public static void main(String[] args) {
		System.out.println("Restauracja otwarta");
		ExecutorService miejsca = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 40; i++) {
			Runnable klient = new Klient(i + 1);
			miejsca.execute(klient);
		}
		miejsca.shutdown();
		while(!miejsca.isTerminated());
		System.out.println("Restauracja zamknieta");

	}

}

class Klient implements Runnable{
	private int numer;

	
	public Klient(int numer){
		this.numer = numer;
	}
	
	public int getNumer(){
		return numer;
	}
	
	
	
	
	@Override
	public void run(){
		wyczekiwanie(1000);
		System.out.println("K"+numer+" wchodzi i siada");
		wyczekiwanie(1000);		
		System.out.println("K"+numer+" przeglada Menu");
		wyczekiwanie(2000);
		System.out.println("K"+numer+" zamawia danie");
		wyczekiwanie(3000);
		System.out.println("K"+numer+" konsumuje");
		wyczekiwanie(2000);
		System.out.println("K"+numer+" placi i wychodzi");
		wyczekiwanie(1000);
		
		
	}
	
	private void wyczekiwanie(int range){
		try{
			Thread.sleep((int)(Math.random()*range)+1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}	
}
