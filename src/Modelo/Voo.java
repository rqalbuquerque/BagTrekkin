package Modelo;

import java.util.List;

public class Voo {
	private String numero;
	private List<Mala> malas;
	
	public void Voo(String numero){
		this.numero = numero;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Mala> getMalas() {
		return malas;
	}

	public void setMalas(List<Mala> malas) {
		this.malas = malas;
	}

	public Voo(String numero, List<Mala> malas) {
		super();
		this.numero = numero;
		this.malas = malas;
	}
	
	public String formataStringVoo(){
		String strVoo = "";
		
		strVoo += numero + "\n";
		for(Mala item : malas){
			strVoo += item.getTag() + "\n";
		}
		
		return strVoo;
	}
}
