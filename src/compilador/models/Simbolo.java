package compilador.models;

public abstract class Simbolo {
	protected String nome, categoria; 
	
	
	public String getNome(){
		return this.nome;
	}
	
	public String getCategoria(){
		return this.categoria;
	}

}
