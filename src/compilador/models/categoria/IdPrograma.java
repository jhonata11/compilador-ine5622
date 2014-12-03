package compilador.models.categoria;

import compilador.models.Simbolo;

public class IdPrograma extends Simbolo {
	
	public IdPrograma(String nome) {
		this.nome = nome;
		this.categoria = "programa";
	}

}
