package compilador.views;
/*
 * TextEditorGUI.java
 *
 * Created on 1 wrzesieÅ„ 2008, 22:00
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import compilador.controllers.Controlador;
import compilador.models.gals.LexicalError;
import compilador.models.gals.SemanticError;
import compilador.models.gals.SyntaticError;

public class TextEditorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Dimension SCREEN_SIZE = new Dimension(700,500);

	private File fileName = new File("noname");

	private Properties prop;
	private Controlador controlador;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JEditorPane editorPane;
	private JMenu menuFile;
	private JMenuBar menuBar;
	private JMenuItem loadMenu;
	private JMenuItem saveAsMenu;
	private JMenuItem saveMenu;

	private JMenu menuAnalise;
	private JMenuItem analiseLexica;
	private JMenuItem analiseSintatica;

	private JMenu menuAjuda;
	private JMenuItem faq;

	private JScrollPane jScrollPane1;



	/** Creates new form TextEditorGUI */
	public TextEditorGUI(Properties cp) {
		this.prop = cp;
		initComponents();
		this.controlador = new Controlador();
	}


	private void initComponents() {
		this.jScrollPane1 = new JScrollPane();
		this.editorPane = new JEditorPane();
		this.menuBar = new JMenuBar();
		this.menuFile = new JMenu();
		this.loadMenu = new JMenuItem();
		this.saveMenu = new JMenuItem();
		this.saveAsMenu = new JMenuItem();

		this.menuAnalise = new JMenu();
		this.analiseLexica = new JMenuItem();
		this.analiseSintatica = new JMenuItem();
		this.menuAnalise.setText(this.prop.getProperty("analiseMenu"));

		this.menuAjuda = new JMenu();
		this.menuAjuda.setText(this.prop.getProperty("ajudaMenu"));
		this.faq = new JMenuItem();


		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(this.prop.getProperty("applicationName"));
		setName("mainFrame"); 


		this.jScrollPane1.setHorizontalScrollBar(null);

		this.editorPane.setBackground(new Color(75, 75, 75));
		this.editorPane.setForeground(new Color(226,226,226));
		this.editorPane.setMargin(new Insets(3, 20, 3, 20));
		this.editorPane.setCaretColor(new Color(226,226,226));

		this.jScrollPane1.setViewportView(editorPane);


		this.menuFile.setText(this.prop.getProperty("fileMenu"));

		this.loadMenu.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
		this.loadMenu.setText(this.prop.getProperty("open"));
		this.loadMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadMenuActionPerformed(evt);
			}
		});
		this.menuFile.add(this.loadMenu);

		this.saveMenu.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
		this.saveMenu.setText(this.prop.getProperty("save"));
		this.saveMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveMenuActionPerformed(evt);
			}
		});
		this.menuFile.add(saveMenu);

		this.saveAsMenu.setText(this.prop.getProperty("saveAs"));
		this.saveAsMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveAsMenuActionPerformed(evt);
			}
		});

		this.analiseLexica.setText(this.prop.getProperty("analiseLexica"));
		this.analiseLexica.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				analiseLexicaActionPerformed(evt);
			}
		});

		this.analiseSintatica.setText(this.prop.getProperty("analiseSintatica"));
		this.analiseSintatica.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				analiseSintaticaActionPerformed(evt);
			}
		});

		this.faq.setText(this.prop.getProperty("faqMenu"));
		this.faq.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ajudaActionPerformed(evt);
			}


		});


		this.menuFile.add(this.saveAsMenu);
		this.menuAnalise.add(this.analiseLexica);
		this.menuAnalise.add(this.analiseSintatica);
		this.menuAjuda.add(this.faq);	

		this.menuBar.add(this.menuFile);
		this.menuBar.add(this.menuAnalise);
		this.menuBar.add(this.menuAjuda);


		this.setJMenuBar(this.menuBar);

		this.add(this.jScrollPane1);

		Document doc = editorPane.getDocument();
		if (doc instanceof PlainDocument) {
			doc.putProperty(PlainDocument.tabSizeAttribute, 2);
		}

		TextLineNumber tln = new TextLineNumber(this.editorPane);
		jScrollPane1.setRowHeaderView( tln );
		this.setPreferredSize(SCREEN_SIZE);



		pack();
	}






	private void saveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuActionPerformed
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(this.fileName));
			writer.write(this.editorPane.getText());
			writer.close();
		}
		catch (IOException ioe) {
			this.editorPane.setText("Pardon. Can't write file. Please contact with: pkrawczak@gmail.com");
		}
	}

	private void loadMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

			BufferedReader reader;
			StringBuilder stringBuilder = new StringBuilder();
			try {
				reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
				while (reader.ready()) {
					stringBuilder.append(reader.readLine() + "\n");
				}
				reader.close();
				this.editorPane.setText(stringBuilder.toString());
				this.fileName = fileChooser.getSelectedFile();
			}
			catch (IOException ioe) {
				this.editorPane.setText("Pardon. Can't open file. Please contact with: pkrawczak@gmail.com");
			}
		}
	}


	private void saveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
				writer.write(this.editorPane.getText());
				writer.close();
			}
			catch (IOException ioe) {
			}
		}
	}

	private void analiseLexicaActionPerformed(java.awt.event.ActionEvent evt){
		StringBuilder sb = new StringBuilder();

		sb.append("<table border='1'> "
				+ "<th>"+ this.prop.getProperty("idHeader")+"</th>"
				+ "<th>"+this.prop.getProperty("lexemeHeader")+"</th>"
				+ "<th>"+this.prop.getProperty("posicaoHeader")+"</th>");

		try {
			sb.append(this.controlador.analiseLexica(this.editorPane.getText()));
			sb.append("</table>");
			new SecondFrameGUI(sb.toString(),250,400);
		} catch (LexicalError e) {
			this.editorPane.setCaretPosition(e.getPosition());
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	private void analiseSintaticaActionPerformed(java.awt.event.ActionEvent evt){
		try {
			this.controlador.analiseSintatica(this.editorPane.getText());
			JOptionPane.showMessageDialog(this, "Analise concluída");
		} catch (LexicalError e) {
			this.editorPane.setCaretPosition(e.getPosition());
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (SyntaticError e) {
			this.editorPane.setCaretPosition(e.getPosition());
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (SemanticError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ajudaActionPerformed(ActionEvent evt) {
		new SecondFrameGUI(
				"<h1>FAQ</h1>"
				+ "<h2>1ª Entrega</h2>"
				+ "<div>"
				+ "<h3>Arquivo</h3>"
				+ "<li>Para abrir um arquivo basta clicar em:<br> Arquivo &gt; Abrir</li>"
				+ "<li>Para salvar o arquivo que está aberto, clice em: <br> Arquivo &gt; Salvar </li>"
				+ "<li>Para salvar como, clique em:	<br> Arquivo &gt; Salvar Como...</li>"
				+ "</div>"
				+ "<div>"
				+ "<h3>Análise</h3>"
				+ "<li>Para fazer a análise léxica, basta inserir um código(tanto um arquivo salvo como algo digitado)"
				+ ", depois clicar em:<br> Análise &gt; Léxica.	<br> "
				+ "Uma nova janela contendo os tokens identificados, sua categoria e sua posição no código vai aparecer.<br>"
				+ " Caso o programa esteja léxicamente errado, uma mensagem de erro vai aparecer em tela	</li>"
				+ "	<li>Para fazer a análise sintática, basca inserir um código(tanto um arquivo salvo como algo digitado),"
				+ " depois clicar em:<br> Análise &gt; Sintática<br> "
				+ "Se o código estiver sintaticamente correto, o programa deve abrir uma janela informando sucesso ao usuário.<br> "
				+ "Em caso de erro, uma janela de erro deve aparecer na tela. </li>"
				+ "</div>"

				, 500,400);

	}


}
