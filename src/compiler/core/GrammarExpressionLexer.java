// Generated from GrammarExpression.g4 by ANTLR 4.13.0
package compiler.core;

	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import exceptions.SemanticException;
	import abstract_syntax_tree.Program;
	import abstract_syntax_tree.AbstractCommand;
	import abstract_syntax_tree.CommandLeitura;
	import abstract_syntax_tree.CommandEscrita;
	import abstract_syntax_tree.CommandAtribuicao;
	import abstract_syntax_tree.CommandDecisao;
	import abstract_syntax_tree.CommandRepeticao;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, AC=15, FC=16, SC=17, C=18, 
		P=19, OP_SUM=20, OP_SUB=21, OP_MULT=22, OP_DIV=23, OP_REL=24, ATTR=25, 
		ID=26, NUMBER=27, TEXTO=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "AP", "FP", "AC", "FC", "SC", "C", "P", "OP_SUM", 
			"OP_SUB", "OP_MULT", "OP_DIV", "OP_REL", "ATTR", "ID", "NUMBER", "TEXTO", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'declare'", "'NUMERO'", "'STRING'", 
			"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'do'", "'while'", 
			"'('", "')'", "'{'", "'}'", "';'", "','", "'.'", "'+'", "'-'", "'*'", 
			"'/'", null, "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AP", "FP", "AC", "FC", "SC", "C", "P", "OP_SUM", "OP_SUB", "OP_MULT", 
			"OP_DIV", "OP_REL", "ATTR", "ID", "NUMBER", "TEXTO", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		private SymbolTable symbolTable = new SymbolTable();
		private DataType _currentType;
		private String idAttr;
		private Program program = new Program();
		private ArrayList<AbstractCommand> curThread;

		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;

		private String _exprRepeticao;
	    private ArrayList<AbstractCommand> listaCmd;
		private String tipoWhile;

		public String lastToken() {
			return ((TokenStream) _input).LT(-1).getText();
		}

		public void addId() {
			symbolTable.add(lastToken(), new Identifier(lastToken(), _currentType));
		}

		public void markVarUsed() {
			symbolTable.get(lastToken()).setUsed();
		}

		public void markVarInitialized() {
			symbolTable.get(lastToken()).setInitialized();
		}

		public void attrExprToId(String Tid, String value) {
			symbolTable.get(Tid).setValue(value);
		}

		public void checkDeclared() {
			if (!symbolTable.containsKey(lastToken())) {
				throw new SemanticException("Variável não declarada " + lastToken() + ".");
			}
		}

		public void checkInitialized() {
			if (!symbolTable.get(lastToken()).getInitialized()) {
				throw new SemanticException("Variável " + lastToken() + " não inicializada.");
			}
		}

		public void checkUnused() {
			symbolTable.getValues().stream().forEach((id) -> {
				if (!id.getUsed()) {
					throw new SemanticException("Variável " + id.getName() + " não utilizada.");
				}
			});
		}

		public void showTokens() {
			symbolTable.getValues().stream().forEach((id) -> System.out.println(id));
		}
		
		public void exibeComandos() {
			for (AbstractCommand c : program.getComandos()) {
				System.out.println(c);
			}
		}
		
		public void commandLeitura() {
			Identifier var = (Identifier) symbolTable.get(_readID);
			CommandLeitura cmd = new CommandLeitura(_readID, var);
			stack.peek().add(cmd);
		}
		
		public void commandAtribuicao() {
			CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			stack.peek().add(cmd);
		}

		public void commandEscrita(String _content) {
			CommandEscrita cmd = new CommandEscrita(_content);
			stack.peek().add(cmd);
		}
		
		public void leitura() {
			_readID = lastToken();
		}


		public void exprAtribuicao() {
			_exprID = lastToken();
		}

		public void newExpr() {
			_exprContent = "";
		}

		
		public void listaTrueDecision() {
			listaTrue = stack.pop();
		}

		public void listaFalseDecision() {
			listaFalse = stack.pop();
			CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			stack.peek().add(cmd);
		}

		public void listaRepeticao(String _tipoWhile){
	        listaCmd = stack.pop();
			CommandRepeticao cmd = new CommandRepeticao(_exprRepeticao, listaCmd,_tipoWhile);
	        stack.peek().add(cmd);
	    }

		public void exprDecision(String _content) {
			_exprDecision = String.valueOf(_content);
			newExpr();
		}

		public void exprDecisionAcum(String _content) {
			_exprDecision += String.valueOf(_content);
			newExpr();
		}

		public void exprRepeticao(String _content) {
			_exprRepeticao = String.valueOf(_content);
			newExpr();
		}

		public void exprRepeticaoAcum(String _content) {
			_exprRepeticao += String.valueOf(_content);
			newExpr();
		}

		public void inputTermo() {
			_exprContent += lastToken();
		}

		public void commandStack() {
			curThread = new ArrayList<AbstractCommand>();
			stack.push(curThread);
		}

		public void generateCode(String lang){
			if(lang.equals("java")){
			program.generateTargetJava();
			}else{
			program.generateTargetPython();
			}
			System.out.println(lang);
		}


	public GrammarExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GrammarExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001d\u00d1\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u00a8\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0004\u0019\u00ae\b\u0019"+
		"\u000b\u0019\f\u0019\u00af\u0001\u0019\u0005\u0019\u00b3\b\u0019\n\u0019"+
		"\f\u0019\u00b6\t\u0019\u0001\u001a\u0004\u001a\u00b9\b\u001a\u000b\u001a"+
		"\f\u001a\u00ba\u0001\u001a\u0001\u001a\u0004\u001a\u00bf\b\u001a\u000b"+
		"\u001a\f\u001a\u00c0\u0003\u001a\u00c3\b\u001a\u0001\u001b\u0001\u001b"+
		"\u0005\u001b\u00c7\b\u001b\n\u001b\f\u001b\u00ca\t\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0000\u0000\u001d"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"/\u00181\u00193\u001a5\u001b7\u001c9\u001d\u0001\u0000\u0006\u0002\u0000"+
		"<<>>\u0001\u0000az\u0003\u000009AZaz\u0001\u000009\u0002\u0000\"\"\'\'"+
		"\u0003\u0000\t\n\r\r  \u00da\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000"+
		"\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000"+
		"\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0001;"+
		"\u0001\u0000\u0000\u0000\u0003D\u0001\u0000\u0000\u0000\u0005M\u0001\u0000"+
		"\u0000\u0000\u0007U\u0001\u0000\u0000\u0000\t\\\u0001\u0000\u0000\u0000"+
		"\u000bc\u0001\u0000\u0000\u0000\rh\u0001\u0000\u0000\u0000\u000fp\u0001"+
		"\u0000\u0000\u0000\u0011s\u0001\u0000\u0000\u0000\u0013y\u0001\u0000\u0000"+
		"\u0000\u0015\u007f\u0001\u0000\u0000\u0000\u0017\u0082\u0001\u0000\u0000"+
		"\u0000\u0019\u0088\u0001\u0000\u0000\u0000\u001b\u008a\u0001\u0000\u0000"+
		"\u0000\u001d\u008c\u0001\u0000\u0000\u0000\u001f\u008e\u0001\u0000\u0000"+
		"\u0000!\u0090\u0001\u0000\u0000\u0000#\u0092\u0001\u0000\u0000\u0000%"+
		"\u0094\u0001\u0000\u0000\u0000\'\u0096\u0001\u0000\u0000\u0000)\u0098"+
		"\u0001\u0000\u0000\u0000+\u009a\u0001\u0000\u0000\u0000-\u009c\u0001\u0000"+
		"\u0000\u0000/\u00a7\u0001\u0000\u0000\u00001\u00a9\u0001\u0000\u0000\u0000"+
		"3\u00ad\u0001\u0000\u0000\u00005\u00b8\u0001\u0000\u0000\u00007\u00c4"+
		"\u0001\u0000\u0000\u00009\u00cd\u0001\u0000\u0000\u0000;<\u0005p\u0000"+
		"\u0000<=\u0005r\u0000\u0000=>\u0005o\u0000\u0000>?\u0005g\u0000\u0000"+
		"?@\u0005r\u0000\u0000@A\u0005a\u0000\u0000AB\u0005m\u0000\u0000BC\u0005"+
		"a\u0000\u0000C\u0002\u0001\u0000\u0000\u0000DE\u0005f\u0000\u0000EF\u0005"+
		"i\u0000\u0000FG\u0005m\u0000\u0000GH\u0005p\u0000\u0000HI\u0005r\u0000"+
		"\u0000IJ\u0005o\u0000\u0000JK\u0005g\u0000\u0000KL\u0005.\u0000\u0000"+
		"L\u0004\u0001\u0000\u0000\u0000MN\u0005d\u0000\u0000NO\u0005e\u0000\u0000"+
		"OP\u0005c\u0000\u0000PQ\u0005l\u0000\u0000QR\u0005a\u0000\u0000RS\u0005"+
		"r\u0000\u0000ST\u0005e\u0000\u0000T\u0006\u0001\u0000\u0000\u0000UV\u0005"+
		"N\u0000\u0000VW\u0005U\u0000\u0000WX\u0005M\u0000\u0000XY\u0005E\u0000"+
		"\u0000YZ\u0005R\u0000\u0000Z[\u0005O\u0000\u0000[\b\u0001\u0000\u0000"+
		"\u0000\\]\u0005S\u0000\u0000]^\u0005T\u0000\u0000^_\u0005R\u0000\u0000"+
		"_`\u0005I\u0000\u0000`a\u0005N\u0000\u0000ab\u0005G\u0000\u0000b\n\u0001"+
		"\u0000\u0000\u0000cd\u0005l\u0000\u0000de\u0005e\u0000\u0000ef\u0005i"+
		"\u0000\u0000fg\u0005a\u0000\u0000g\f\u0001\u0000\u0000\u0000hi\u0005e"+
		"\u0000\u0000ij\u0005s\u0000\u0000jk\u0005c\u0000\u0000kl\u0005r\u0000"+
		"\u0000lm\u0005e\u0000\u0000mn\u0005v\u0000\u0000no\u0005a\u0000\u0000"+
		"o\u000e\u0001\u0000\u0000\u0000pq\u0005s\u0000\u0000qr\u0005e\u0000\u0000"+
		"r\u0010\u0001\u0000\u0000\u0000st\u0005e\u0000\u0000tu\u0005n\u0000\u0000"+
		"uv\u0005t\u0000\u0000vw\u0005a\u0000\u0000wx\u0005o\u0000\u0000x\u0012"+
		"\u0001\u0000\u0000\u0000yz\u0005s\u0000\u0000z{\u0005e\u0000\u0000{|\u0005"+
		"n\u0000\u0000|}\u0005a\u0000\u0000}~\u0005o\u0000\u0000~\u0014\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0005d\u0000\u0000\u0080\u0081\u0005o\u0000\u0000"+
		"\u0081\u0016\u0001\u0000\u0000\u0000\u0082\u0083\u0005w\u0000\u0000\u0083"+
		"\u0084\u0005h\u0000\u0000\u0084\u0085\u0005i\u0000\u0000\u0085\u0086\u0005"+
		"l\u0000\u0000\u0086\u0087\u0005e\u0000\u0000\u0087\u0018\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0005(\u0000\u0000\u0089\u001a\u0001\u0000\u0000\u0000"+
		"\u008a\u008b\u0005)\u0000\u0000\u008b\u001c\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0005{\u0000\u0000\u008d\u001e\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0005}\u0000\u0000\u008f \u0001\u0000\u0000\u0000\u0090\u0091\u0005;"+
		"\u0000\u0000\u0091\"\u0001\u0000\u0000\u0000\u0092\u0093\u0005,\u0000"+
		"\u0000\u0093$\u0001\u0000\u0000\u0000\u0094\u0095\u0005.\u0000\u0000\u0095"+
		"&\u0001\u0000\u0000\u0000\u0096\u0097\u0005+\u0000\u0000\u0097(\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\u0005-\u0000\u0000\u0099*\u0001\u0000\u0000"+
		"\u0000\u009a\u009b\u0005*\u0000\u0000\u009b,\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0005/\u0000\u0000\u009d.\u0001\u0000\u0000\u0000\u009e\u00a8\u0007"+
		"\u0000\u0000\u0000\u009f\u00a0\u0005<\u0000\u0000\u00a0\u00a8\u0005=\u0000"+
		"\u0000\u00a1\u00a2\u0005>\u0000\u0000\u00a2\u00a8\u0005=\u0000\u0000\u00a3"+
		"\u00a4\u0005!\u0000\u0000\u00a4\u00a8\u0005=\u0000\u0000\u00a5\u00a6\u0005"+
		"=\u0000\u0000\u00a6\u00a8\u0005=\u0000\u0000\u00a7\u009e\u0001\u0000\u0000"+
		"\u0000\u00a7\u009f\u0001\u0000\u0000\u0000\u00a7\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a3\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a80\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005:\u0000\u0000\u00aa"+
		"\u00ab\u0005=\u0000\u0000\u00ab2\u0001\u0000\u0000\u0000\u00ac\u00ae\u0007"+
		"\u0001\u0000\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001"+
		"\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001"+
		"\u0000\u0000\u0000\u00b0\u00b4\u0001\u0000\u0000\u0000\u00b1\u00b3\u0007"+
		"\u0002\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b54\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b9\u0007\u0003\u0000\u0000\u00b8\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000"+
		"\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00c2\u0001\u0000"+
		"\u0000\u0000\u00bc\u00be\u0005.\u0000\u0000\u00bd\u00bf\u0007\u0003\u0000"+
		"\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000"+
		"\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c1\u00c3\u0001\u0000\u0000\u0000\u00c2\u00bc\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c36\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c8\u0005\"\u0000\u0000\u00c5\u00c7\b\u0004\u0000\u0000\u00c6"+
		"\u00c5\u0001\u0000\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9"+
		"\u00cb\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb"+
		"\u00cc\u0005\"\u0000\u0000\u00cc8\u0001\u0000\u0000\u0000\u00cd\u00ce"+
		"\u0007\u0005\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0006\u001c\u0000\u0000\u00d0:\u0001\u0000\u0000\u0000\t\u0000\u00a7"+
		"\u00af\u00b2\u00b4\u00ba\u00c0\u00c2\u00c8\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}