package compilador.models;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import compilador.models.gals.LexicalError;
import compilador.models.gals.Lexico;
import compilador.models.gals.SemanticError;
import compilador.models.gals.Semantico;
import compilador.models.gals.Sintatico;
import compilador.models.gals.SyntaticError;
import compilador.models.gals.Token;
/**
 * Classe do compilador
 * @author Jhonata da Rocha & Guilherme Schmitt
 *
 */
public class Compilador {

	private Lexico lex;
	private Sintatico sin;
	private Semantico sem;

	
	/**
	 * Construtor padr�o
	 */
	public Compilador() {
		this.lex = new Lexico();
		this.sin = new Sintatico();
		this.sem = new Semantico();
	}

	
	/**
	 * Faz somente a an�lise l�xica
	 * @param texto o texto a ser analisado
	 * @return uma lista de resultados l�xicos
	 * @throws LexicalError caso ocorra algum erro l�xico
	 */
	public List<ResultadoLexico> analiseLexica(String texto) throws LexicalError{
		
		List<ResultadoLexico> listaDeTokens = new ArrayList<ResultadoLexico>();
		this.lex.setInput(new StringReader(texto) );
		Token t = null;
		while ( (t = this.lex.nextToken()) != null ) {
			listaDeTokens.add(new ResultadoLexico(t.getId(), t.getLexeme(), t.getPosition()));
		}
		
		return listaDeTokens;
	}
	
	/**
	 * Faz somente an�lise sint�tica e l�xica
	 * @param texto o texto a ser analisado sint�ticamente
	 * @throws LexicalError caso ocorra algum erro l�xico
	 * @throws SyntaticError caso ocorra algum erro sint�tico
	 * @throws SemanticError caso ocorra algum erro sem�ntico
	 */
	public void analiseSintatica(String texto) throws LexicalError, SyntaticError, SemanticError{
		this.lex.setInput(new StringReader(texto) );
		this.sin.parse(this.lex, this.sem);
	}

	public HashMap<Integer, Nivel> getTabelaDeSimbolos(){
		return this.sem.getTs().getTabSimbolos();
	}
	
	public Semantico getSem() {
		return sem;
	}
}
