package compilador.views;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * Classe de interface para exibir o resultado de análises
 * @author Jhonata
 *
 */
public class SecondFrameGUI {
	
	private JFrame frame;
	private JScrollPane jsp;
	private JEditorPane jep;
	
	
	/**
	 *  Construtor padrão
	 * @param html o texto a ser exibido
	 */
	public SecondFrameGUI(String html, int largura, int altura) {
		this.frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.jep = new JEditorPane();
		this.jsp = new JScrollPane();
		jep.setContentType("text/html");
		jep.setPreferredSize(new Dimension(largura,altura));
		this.jep.setText(html);
		this.jep.setEditable(false);
		
		this.jsp.setViewportView(jep);
		this.frame.add(jsp);
		this.frame.pack();
		this.frame.setVisible(true);
		
	}
}
