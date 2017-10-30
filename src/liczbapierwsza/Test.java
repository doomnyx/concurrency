package liczbapierwsza;//zadanie 4

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int wartosc = 0;
		int liczba;
		int przedzial = 20;
		System.out.println("Podaj liczbe do sprawdzenia ");
		liczba = in.nextInt();
		
		if(liczba>1){
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for(int i=0; (i*przedzial)<liczba; ++i){
			Tester watek = new Tester(liczba,i*przedzial, (i+1)*przedzial);
			Future<Integer> fut = exec.submit(watek);
			try{
				wartosc+=fut.get();
			}
			catch (InterruptedException | ExecutionException ex){
				System.err.println("Blad");
			}
		}
		
		exec.shutdown();
		while(!exec.isTerminated());
		System.out.println("Obliczenia zakonczone");
		if(wartosc==0)
			System.out.println("Liczba "+liczba+" jest liczba pierwsza");
		else
			System.out.println("Liczba "+liczba+" nie jest liczba pierwsza");
		}
		
		in.close();
	}

}

class Tester implements Callable<Integer>{
	private int poczatek;
	private int koniec;
	private int liczba;
	public Tester(int liczba, int poczatek, int koniec){
		if(poczatek == 0)	this.poczatek=2;
		else				this.poczatek=poczatek;
		if(koniec>liczba)	this.koniec=liczba;
		else				this.koniec = koniec;
		this.liczba = liczba;
	}
	public Integer call() throws Exception {
		int wynik =0;
		for(int i=poczatek; i<koniec; ++i){
			if (liczba%i==0) 
				wynik++;
			try{
				Thread.sleep(5);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		System.out.println(Thread.currentThread().getName());
		return wynik;
	}
}

