package compilador.controllers;

import java.util.List;

import compilador.models.Compilador;
import compilador.models.ResultadoLexico;
import compilador.models.gals.LexicalError;
import compilador.models.gals.SemanticError;
import compilador.models.gals.SyntaticError;

/**
 * Classe de controle entre modelo e visão
 * @author Jhonata da Rocha & Guilherme Schmitt
 *
 */
public class Controlador {
	private Compilador compilador;


	/**
	 * Construtor padrão
	 */
	public Controlador() {
		this.compilador = new Compilador();
	}

	/**
	 * Faz somente a análise léxica
	 * @param texto o texto a ser analisado
	 * @return uma string contendo o html de uma linha com os resultados da análise
	 * @throws LexicalError caso ocorra algum erro léxico
	 */
	public String analiseLexica(String texto) throws LexicalError{
		StringBuilder sb = new StringBuilder();
		
		List<ResultadoLexico> listaDeTokens = this.compilador.analiseLexica(texto);
		for(ResultadoLexico i: listaDeTokens){
			sb.append("<tr>");
			sb.append("<td>"+i.getTipo()+"</td>");
			sb.append("<td>"+i.getLexema()+"</td>");
			sb.append("<td>"+i.getPosicao()+"</td>");
			sb.append("</tr>");
		}
		return sb.toString();

	}
	
	/**
	 * Faz somente análise sintática e léxica
	 * @param texto o texto a ser analisado sintáticamente
	 * @throws LexicalError caso ocorra algum erro léxico
	 * @throws SyntaticError caso ocorra algum erro sintático
	 * @throws SemanticError caso ocorra algum erro semântico
	 */
	public void analiseSintatica(String texto) throws LexicalError, SyntaticError, SemanticError{
		this.compilador.analiseSintatica(texto);
	}
	
	

}
