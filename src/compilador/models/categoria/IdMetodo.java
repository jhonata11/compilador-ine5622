package compilador.models.categoria;

import java.util.ArrayList;

import compilador.models.Simbolo;

public class IdMetodo extends Simbolo {
	private String tipoResultado;
	private ArrayList<Simbolo> listaPar;
	
	public IdMetodo(String nome) {
		this.nome = nome;
		this.categoria = "metodo";
		listaPar = new ArrayList<Simbolo>();
	}

	public String getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}
}
