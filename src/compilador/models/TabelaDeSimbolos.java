package compilador.models;

import java.util.HashMap;

public class TabelaDeSimbolos {

	HashMap<Integer, Nivel> tabSimbolos;
	int npf;

	public TabelaDeSimbolos() {
		this.tabSimbolos = new HashMap<Integer, Nivel>();	
		this.npf = 0;
	}

	public void add(Simbolo s, int nivel){

		if(!this.tabSimbolos.containsKey(nivel)){

			Nivel n = new Nivel(nivel);

			n.hashDeNivel.put(s.getNome(), s);
			this.tabSimbolos.put(nivel, n);

		} else {
			this.tabSimbolos.get(nivel).hashDeNivel.put(s.getNome(), s);
		}
	}

	public HashMap<Integer, Nivel> getTabSimbolos() {
		return tabSimbolos;
	}
	
	public void setNPF(int n){
		this.npf = n;
	}
	
	
	
}
