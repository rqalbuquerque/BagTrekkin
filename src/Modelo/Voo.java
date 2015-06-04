package Modelo;

import java.util.List;

public class Voo {
	int numero;
	List<Mala> malas;
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Mala> getMalas() {
		return malas;
	}

	public void setMalas(List<Mala> malas) {
		this.malas = malas;
	}

	public Voo(int numero, List<Mala> malas) {
		super();
		this.numero = numero;
		this.malas = malas;
	}

}
