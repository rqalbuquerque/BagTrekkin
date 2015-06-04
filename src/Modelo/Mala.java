package Modelo;

public class Mala {
	int tag;
	String passageiro;
	
	public Mala(int tag, String passageiro) {
		super();
		this.tag = tag;
		this.passageiro = passageiro;
	}
	
	public int getTag() {
		return tag;
	}
	
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public String getPassageiro() {
		return passageiro;
	}
	
	public void setPassageiro(String passageiro) {
		this.passageiro = passageiro;
	}
	
}
