package golibroda;


public class Dane {
	private int numer;
	private String name;
	
	public Dane(int numer){
		this.numer = numer;
	}
	public Dane(int numer, String name){
		this(numer);
		this.name = name;
	}
	
	@Override
	public String toString(){
		return numer+name;
	}

}
