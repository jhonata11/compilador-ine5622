package compilador.main;

import compilador.models.Compilador;
import compilador.models.gals.LexicalError;
import compilador.models.gals.SemanticError;
import compilador.models.gals.SyntaticError;
import compilador.properties.CompilerProperties;
import compilador.views.TextEditorGUI;

/**
 * 
 * @author Jhonata da Rocha & Guilherme Schmitt
 *
 */
public class Main {

	public static void main(String[] args) throws LexicalError, SyntaticError, SemanticError {
		
		CompilerProperties cp = new CompilerProperties();
		TextEditorGUI editor = new TextEditorGUI(cp.getProp() );
		editor.setVisible(true);
		
	}

}
