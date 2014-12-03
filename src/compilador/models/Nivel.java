package compilador.models;

import java.util.HashMap;

public class Nivel {

	private int nivel;
	HashMap<String, Simbolo> hashDeNivel;
	
	public Nivel(int nivel) {
		this.hashDeNivel = new HashMap<String, Simbolo>();
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public HashMap<String, Simbolo> getHashDeNivel() {
		return hashDeNivel;
	}
	
	
	
	
	
	
}
