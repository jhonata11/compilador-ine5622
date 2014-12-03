package compilador.models;

/**
 * Classe de tokens, contendo seu id, lexeme e sua posição
 * @author Jhonata da Rocha e Guilherme Schmitt
 */
public class ResultadoLexico {
	private int id;
	private String lexeme;
	private int posicao;
	

	/**
	 * Construtor padrão
	 * @param id o id do token
	 * @param lexeme seu lexeme
	 * @param posicao sua posicao
	 */
	public ResultadoLexico(int id, String lexeme, int posicao) {
		super();
		this.id = id;
		this.lexeme = lexeme;
		this.posicao = posicao;
	}
	
	public String getLexema() {
		return lexeme;
	}
	public int getPosicao() {
		return posicao;
	}
	public String getTipo() {
		if (id == 0){
			return "EPSILON";
		}
		else if (id == 1){
			return "DOLLAR";
		}
		else if (id == 2){
			return "ID";
		}
		else if (id == 3){
			return "NUM_INT";
		}
		else if (id == 4){
			return "NUM_REAL";
		}
		else if(id == 5){
			return "STRING LITERAL";
		}
		else if (id > 33){
			return "SIMBOLO ESPECIAL";
		}
		else if (id > 5){
			return "PALAVRA RESERVADA";
		}
		else{
			return "ERRO LÉXICO";
		}
		
	}
	

}
