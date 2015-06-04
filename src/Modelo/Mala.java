package Modelo;

public class Mala {
	private String tag;
	private String passageiro;
	
	public Mala(String tag, String passageiro) {
		super();
		this.tag = tag;
		this.passageiro = passageiro;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getPassageiro() {
		return passageiro;
	}
	
	public void setPassageiro(String passageiro) {
		this.passageiro = passageiro;
	}
	
}
