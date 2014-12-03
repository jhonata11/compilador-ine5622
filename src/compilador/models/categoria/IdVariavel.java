package compilador.models.categoria;

import compilador.models.Simbolo;

public class IdVariavel extends Simbolo {
	private int deslocamento;
	private String tipo;
	
	public IdVariavel(String nome){
		this.nome = nome;
		this.categoria = "variavel";
	}
	
	public int getDeslocamento() {
		return deslocamento;
	}
	public void setDeslocamento(int deslocamento) {
		this.deslocamento = deslocamento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
