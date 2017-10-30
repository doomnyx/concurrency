package golibroda;
public class Golibroda implements Runnable{
	private Poczekalnia salon;
	
	public Golibroda(Poczekalnia salon){
		this.salon = salon;
	}
	
	
	
	
	
	@Override
	public void run(){
		while(true){
			salon.strzyzenie();
		}
	}

}
