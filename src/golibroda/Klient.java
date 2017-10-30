package golibroda;

public class Klient implements Runnable {
	private Poczekalnia salon;
	private String name;
	
	public Klient (Poczekalnia salon){
		this.salon = salon;
		name ="";
	}
	
	public Klient(Poczekalnia salon, String name){
		this(salon);
		this.name = name;
	}
	
	
	
	@Override
	public void run(){
		try{
			for(int i=1; i<=10; i++){
				salon.dodaj(new Dane(i, name));
				Thread.sleep((int)(Math.random()*5000));
			}
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
