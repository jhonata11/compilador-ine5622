package compilador.controllers;

import java.util.List;

import compilador.models.Compilador;
import compilador.models.ResultadoLexico;
import compilador.models.gals.LexicalError;
import compilador.models.gals.SemanticError;
import compilador.models.gals.SyntaticError;

/**
 * Classe de controle entre modelo e vis�o
 * @author Jhonata da Rocha & Guilherme Schmitt
 *
 */
public class Controlador {
	private Compilador compilador;


	/**
	 * Construtor padr�o
	 */
	public Controlador() {
		this.compilador = new Compilador();
	}

	/**
	 * Faz somente a an�lise l�xica
	 * @param texto o texto a ser analisado
	 * @return uma string contendo o html de uma linha com os resultados da an�lise
	 * @throws LexicalError caso ocorra algum erro l�xico
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
	 * Faz somente an�lise sint�tica e l�xica
	 * @param texto o texto a ser analisado sint�ticamente
	 * @throws LexicalError caso ocorra algum erro l�xico
	 * @throws SyntaticError caso ocorra algum erro sint�tico
	 * @throws SemanticError caso ocorra algum erro sem�ntico
	 */
	public void analiseSintatica(String texto) throws LexicalError, SyntaticError, SemanticError{
		this.compilador.analiseSintatica(texto);
	}
	
	

}
