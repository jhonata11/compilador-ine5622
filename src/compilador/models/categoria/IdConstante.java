package compilador.models.categoria;

import compilador.models.Simbolo;

public class IdConstante extends Simbolo {
	private String tipo, valor;
	
	
	
	public IdConstante(String nome) {
		this.nome = nome;
		this.categoria = "constante";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
