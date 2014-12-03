package compilador.models.categoria;

import compilador.models.Simbolo;

public class IdParametro extends Simbolo {
	private int descolamento;
	private String mecanismoDePassagem, tipo;
	
	public int getDescolamento() {
		return descolamento;
	}
	public void setDescolamento(int descolamento) {
		this.descolamento = descolamento;
	}
	public String getMecanismoDePassagem() {
		return mecanismoDePassagem;
	}
	public void setMecanismoDePassagem(String mecanismoDePassagem) {
		this.mecanismoDePassagem = mecanismoDePassagem;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
