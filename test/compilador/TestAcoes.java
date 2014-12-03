package compilador;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import compilador.models.Compilador;
import compilador.models.Nivel;
import compilador.models.categoria.IdConstante;
import compilador.models.categoria.IdPrograma;
import compilador.models.gals.LexicalError;
import compilador.models.gals.SemanticError;
import compilador.models.gals.SyntaticError;

public class TestAcoes {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	Compilador compilador = new Compilador();

	@Before
	public void setUp() {
		this.compilador = new Compilador();
	}

	@Test
	public void test101() throws LexicalError, SyntaticError, SemanticError {

		compilador.analiseSintatica("programa prog; {\n}.    ");
		assertTrue(compilador.getTabelaDeSimbolos().containsKey(0));

		Nivel n = compilador.getTabelaDeSimbolos().get(0);
		assertTrue(n.getHashDeNivel().containsKey("prog"));

		IdPrograma idProg = (IdPrograma) n.getHashDeNivel().get("prog");
		assertTrue(idProg.getNome().equals("prog"));
	}


	@Test 
	public void test102() throws LexicalError, SyntaticError, SemanticError{
		//ta passando e não deveria =/
		
		exception.expect(SemanticError.class);
		compilador.analiseSintatica("programa prog;\n const prog = 10; {\n   }.");
	}

	@Test
	public void test102correto() throws LexicalError, SyntaticError, SemanticError{
		compilador.analiseSintatica("programa prog;\n const constante = 10; {\n   }.");
		Nivel n = compilador.getTabelaDeSimbolos().get(0);
		assertTrue(n.getHashDeNivel().containsKey("constante"));
		assertTrue(n.getHashDeNivel().get("constante").getNome().equals("constante"));

	}

	@Test
	public void test103int() throws LexicalError, SyntaticError, SemanticError{
		//também não roda. não está sendo setado?
		compilador.analiseSintatica("programa prog;\n const idDaConstante = 10; {\n   }.");
		Nivel n = compilador.getTabelaDeSimbolos().get(0);
		IdConstante idCons = (IdConstante)n.getHashDeNivel().get("idDaConstante");

		assertTrue(idCons.getTipo().equals("num_int")); //o tipo não deveria ser IdConstante?
		assertTrue(idCons.getValor().equals("10")); 
	}	

	@Test
	public void test103literal() throws LexicalError, SyntaticError, SemanticError{
		compilador.analiseSintatica("programa prog;\n const idDaConstante = 'bananas de pijamas'; {\n   }.");
		Nivel n = compilador.getTabelaDeSimbolos().get(0);
		IdConstante idCons = (IdConstante)n.getHashDeNivel().get("idDaConstante");

		assertTrue(idCons.getTipo().equals("literal")); 
		assertTrue(idCons.getValor().equals("'bananas de pijamas'")); 
	}

	@Test 
	public void test104variavel() throws LexicalError, SyntaticError, SemanticError{
		compilador.analiseSintatica("programa prog;\n var variavel: inteiro; {\n   }.");

		assertTrue(compilador.getSem().contextoLID.equals("decl")); 
		assertTrue(compilador.getSem().categoriaAtual.equals("idVariavel"));
	}
	
	@Test 
	public void test104registro() throws LexicalError, SyntaticError, SemanticError{
		compilador.analiseSintatica("programa prog;\n var variavel: registro { var variavel: inteiro; }; {\n   }.");

		assertTrue(compilador.getSem().contextoLID.equals("decl")); 
		assertTrue(compilador.getSem().categoriaAtual.equals("idCampoDeRegistro"));
	}
	
	@Test
	public void test105(){
		
	}


}
