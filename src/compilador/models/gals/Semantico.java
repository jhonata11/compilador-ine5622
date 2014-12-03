package compilador.models.gals;

import java.util.ArrayList;

import compilador.models.Simbolo;
import compilador.models.TabelaDeSimbolos;
import compilador.models.categoria.IdConstante;
import compilador.models.categoria.IdMetodo;
import compilador.models.categoria.IdPrograma;
import compilador.models.categoria.IdVariavel;

public class Semantico implements Constants {
	private String mensagemDeErro = "";
	
	
	private TabelaDeSimbolos ts;
	private int nivelAtual;
	
	private boolean ehRegistro, le_VAR;
	private int deslocamento, npf;
	public String tipoConst, valConst, tipoConst2, tipoElementos;
	public String tipoCte, valCte;
	public String contextoLID, categoriaAtual, tipoAtual, mpp;
	private ArrayList<Simbolo> listaReg, listaVar, listaAux;
	private int limiteInferiorInteiro;
	private String limiteInferiorString;
	
	private Simbolo posid;
	
	public Semantico() {
		this.ts = new TabelaDeSimbolos();
		this.nivelAtual = 0;
		npf = 0;
	}
	
	
	
    public void executeAction(int action, Token token)	throws SemanticError    {
        try {
        	switch(action){
        	case 101: {
        		IdPrograma simbolo = new IdPrograma(token.getLexeme());
        		this.ts.add(simbolo, this.nivelAtual);
        		
        		this.nivelAtual = 0;
        		this.deslocamento = 0;
        		this.ehRegistro = false;
        		this.le_VAR = false;
        		this.listaReg = new ArrayList<Simbolo>();
        		this.listaVar = new ArrayList<Simbolo>();
        		this.listaAux = new ArrayList<Simbolo>();
        		break;
        	}
        	
        	case 102: {
        		if(this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().containsKey(token.getLexeme())){
        			mensagemDeErro = "ID já declarado";
        			
        		} else {
        			IdConstante idConstante = new IdConstante(token.getLexeme());
        			this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().put(idConstante.getNome(), idConstante);
        			this.posid = this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().get(idConstante.getNome());
        		}
        		break;
        	}
        	
        	case 103: {
        		IdConstante idCons =  (IdConstante) posid;
        		
        		//Aqui eu to verificando qual dos dois foi usado, o Const ou o Cte
        		//A ideia seria após usa-los deixa-los null, oque tu acha?
        		if (tipoConst != null && valConst != null){		
        			idCons.setTipo(tipoConst);
        			idCons.setValor(valConst);
        			tipoConst = null;
        			valConst = null;
        		}
        		else{
        			idCons.setTipo(tipoCte);
        			idCons.setValor(valCte);
        			tipoCte = null;
        			valCte = null;
        		}
        		
        		posid = idCons;
        		
        		break;
        	}
        	
        	case 104: {
        		contextoLID = "decl";
        		if(ehRegistro){
        			categoriaAtual = "idCampoDeRegistro";
        		}
        		else{
        			categoriaAtual = "idVariavel";
        		}
        		break;
        	}
        	
        	case 105: {
        		//EITA, TEM QUE VER ESSA PARADA DE LISTAVAR E LISTAREG, EU NÃO ENTENDI
        		// TU PODERIA DAR UMA OLHADA NISSO?
        		
        		break;
        	}
        	
        	case 106: {
        		if(this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().containsKey(token.getLexeme())){
        			mensagemDeErro = "ID já declarado";
        		}
        		else{
        			IdMetodo idM = new IdMetodo(token.getLexeme());
        			this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().put(idM.getNome(), idM);
        			npf = 0;
        			nivelAtual++;
        		}
        		
        		break;
        	}
        	
        	case 107: { 
        		this.ts.setNPF(npf);
        		break;
        	}
        	
        	case 110: {
        		contextoLID = "parFormal";
        		break;
        	}
        	
        	case 111: { 
        		//TODO COMPLEXO
        		break;
        	}
        	
        	case 114: {
        		if(contextoLID.equals("decl")){
        			if(this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().containsKey(token.getLexeme())){
        				throw new SemanticError("Id já declarado");
        			}
        			else{
        				IdVariavel idV = new IdVariavel(token.getLexeme());
        				this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().put(idV.getNome(), idV);
        				posid = this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().get(idV.getNome());
        				
        				if(ehRegistro){
        					listaReg.add(idV);
        				}
        				else{
        					listaVar.add(idV);
        				}
        			}
        		}
        		
        		if(contextoLID.equals("parFormal")){
        			if(this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().containsKey(token.getLexeme())){
        				throw new SemanticError("Id de parâmetro repetido");
        			}
        			else{
        				IdVariavel idV = new IdVariavel(token.getLexeme());
        				this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().put(idV.getNome(), idV);
        				listaAux.add(idV);
        				npf++;
        			}
        		}
        		break;
        	}
        	
        	case 115: { 
        		mpp = "referencia";
        		break;
        	}
        	
        	case 116: {
        		mpp = "valor";
        		break;
        	}
        	
        	case 117: {
        		tipoAtual = "inteiro";
        		break;
        	}
        	
        	case 118: {
        		tipoAtual = "real";
        		break;
        	}
        	
        	case 119: {
        		tipoAtual = "booleano";
        		break;
        	}
        	
        	case 120: {
        		tipoAtual = "caracter";
        		break;
        	}
        	
        	case 121: {
        		if (!tipoConst.equals("inteiro")){
        			throw new SemanticError("esperava-se uma constante inteira!");
        		}
        		else{
        			try {
						int x = Integer.parseInt(valConst);
						if (x > 255){
	        				throw new SemanticError("tam. da cadeia > que o permitido!");
	        			}
	        			else{
	        				tipoAtual = "cadeia";
	        			}
					} catch (NumberFormatException e) {
						throw new SemanticError("não é um valor inteiro");
					}        			
        		}
        		
        		break;
        	}
        	
        	case 122: {
        		if(tipoConst.equals("inteiro") || tipoConst.equals("caracter")){
        			tipoConst2 = tipoConst;
        			if(tipoConst.equals("inteiro")){
        				try {
        					limiteInferiorInteiro = Integer.parseInt(valConst);
						} catch (NumberFormatException e) {
							throw new SemanticError("Era esperado um inteiro");
						}
        				
        			}
        			else{
        				if(valConst.length() > 1)
        					throw new SemanticError("Caracter deve possuir apenas um digito");
        				else
        					limiteInferiorString = valConst;
        			}
        		}
        		else{
        			throw new SemanticError("Tipo de constante inválido!");
        		}
        		
        		break;
        	}
        	
        	case 123: {
        		int x;
        		int y;
        		
        	    if (tipoConst.equals("inteiro")){
        			try {
        				x = Integer.parseInt(valConst);
					} catch (NumberFormatException e) {
						throw new SemanticError("esperava-se um inteiro");
					}	
        	    	
        				y = limiteInferiorInteiro;
        				if(x <= y){
        					throw new SemanticError("Lim. Superior <= que Lim. inferior");
        				}
        			}	
        		else if (tipoConst.equals("caracter")){
        				  				
        			if(valConst.length() > 1){
        				throw new SemanticError("Caracter deve possuir apenas um digito");
        			}
        			else{
        				x = (int) tipoConst.charAt(0);
        				y = (int) limiteInferiorString.charAt(0);
        				if( x <= y){
        					throw new SemanticError("Lim. Superior <= que Lim. inferior");
        				}
        			}
        		}	
        		
        		else{
        		    	throw new SemanticError("Constantes de intervalo diferentes!");
        		    }
        		
        		
        		break;
        	}
        	
        	case 124: {
        		tipoElementos = tipoAtual;
        		tipoAtual = "vetor";
        		break;
        	}
        	
        	case 125: {
        		ehRegistro = true;
        		break;
        	}
        	
        	case 126: {
        		ehRegistro = false;
        		tipoAtual = "registro";
        		break;
        	}
 
        	case 127: {
        		Simbolo s = this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().get(token.getLexeme());
        		if(s == null){
        			throw new SemanticError("ID não declarado!");		
        		}
        		else{
        			//Ele tem que ser do tipo IdConstante
        			if (!this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().get(token.getLexeme()).getCategoria().equals("constante")){
        				throw new SemanticError("Esperava-se um ID Constante!");
        			}
        			else{
        				//Isso aqui vai dar certo? Eu preciso pegar um objeto constante
        				// para setar os valores nas variaveis;
        				IdConstante x = (IdConstante) this.ts.getTabSimbolos().get(nivelAtual).getHashDeNivel().get(token.getLexeme());
        				tipoConst = x.getTipo();
        				valConst = x.getValor();
        			}
        		}	
        		break;
        	}
        	
        	case 175: {
        		// O val eu deixei String, oque tu acha?
        		tipoCte = "num_int";
        		valCte = token.getLexeme();
          		break;
        	}
        	
        	case 176: {
        		tipoCte = "num_real";
        		valCte = token.getLexeme();
          		break;
        	}
        	
        	case 177: {
        		tipoCte = "false";
        		valCte = token.getLexeme();
          		break;
        	}
        	
        	case 178: {
        		tipoCte = "verdadeiro";
        		valCte = token.getLexeme();
          		break;
        	}
        	
        	case 179: {
        		tipoCte = "literal";
        		valCte = token.getLexeme();
          		break;
        	}
        	
        	
        	}
        	

        } catch (Exception e) {
        	throw new SemanticError(mensagemDeErro);
        }
    }



	public TabelaDeSimbolos getTs() {
		return ts;
	}	
    
    
}
