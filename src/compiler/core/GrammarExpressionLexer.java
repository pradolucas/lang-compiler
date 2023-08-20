// Generated from GrammarExpression.g4 by ANTLR 4.4

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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, AP=13, FP=14, AC=15, FC=16, SC=17, C=18, P=19, 
		DBQ=20, OP_SUM=21, OP_SUB=22, OP_MULT=23, OP_DIV=24, OP_REL=25, ATTR=26, 
		ID=27, NUMBER=28, TEXTO=29, WS=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'"
	};
	public static final String[] ruleNames = {
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "AP", "FP", "AC", "FC", "SC", "C", "P", "DBQ", 
		"OP_SUM", "OP_SUB", "OP_MULT", "OP_DIV", "OP_REL", "ATTR", "ID", "NUMBER", 
		"TEXTO", "WS"
	};


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



		public String lastToken(){
			return ((TokenStream) _input).LT(-1).getText();
		}
		public void addId(){
			//			System.out.println("[TOKEN] "+ lastToken());
			symbolTable.add(lastToken(), new Identifier(lastToken(), _currentType));
		}

		public void checkIdExists(){
			if(!symbolTable.containsKey(lastToken())){
				throw new SemanticException("Variável não declarada " + lastToken() + "."); 
			}
		}

		public void attrExprToId(String Tid, String value){
			symbolTable.get(Tid).setValue(value);
		}

		public void checkInitialized(){
			if(symbolTable.get(lastToken()).getValue() == null){
				throw new SemanticException("Variável " + lastToken() + " não incializada."); 
			}
			assert true;
		}
		
		public void checkUnused(){
			//			ERRADO
			//			symbolTable.getValues().stream().forEach((id)->if(id.getValue() == NULL) throw new SemanticException("Variável " + id.getName() + " não incializada."));
			assert true;
		}

		public void showTokens(){
			symbolTable.getValues().stream().forEach((id)->System.out.println(id));
		}

		public void leitura(){
			_readID = lastToken();

		}

		public void commandLeitura(){
			Identifier var = (Identifier)symbolTable.get(_readID);
			CommandLeitura cmd = new CommandLeitura(_readID, var);
			stack.peek().add(cmd);
		}

		public void escrita(){
			_writeID = lastToken();
			CommandEscrita cmd = new CommandEscrita(_writeID);
			stack.peek().add(cmd);
		}

		public void exprAtribuicao(){
			_exprID = lastToken();
		}

		public void contentAtribuicao(){
			_exprContent = "";
		}

		public void commandAtribuicao(){
			CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			stack.peek().add(cmd);
		}

		public void listaTrueDecision(){
			listaTrue = stack.pop();
		}

		public void listaFalseDecision(){
			listaFalse = stack.pop();
			CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			stack.peek().add(cmd);
		}


		public void exprDecision(){
			_exprDecision = lastToken();
		}

		public void exprDecisionAcum(){
			_exprDecision += lastToken();
		}

		public void inputTermo(){
			_exprContent += lastToken();
		}


		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()) {
				System.out.println(c);
			}
		}

		public void commandStack(){
			curThread = new ArrayList<AbstractCommand>();
			stack.push(curThread);
		}

		public void generateCode(){
			program.generateTarget();
		}



	public GrammarExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GrammarExpression.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2 \u00d6\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00ae\n\32\3\33"+
		"\3\33\3\33\3\34\6\34\u00b4\n\34\r\34\16\34\u00b5\3\34\7\34\u00b9\n\34"+
		"\f\34\16\34\u00bc\13\34\3\35\6\35\u00bf\n\35\r\35\16\35\u00c0\3\35\3\35"+
		"\6\35\u00c5\n\35\r\35\16\35\u00c6\5\35\u00c9\n\35\3\36\3\36\6\36\u00cd"+
		"\n\36\r\36\16\36\u00ce\3\36\3\36\3\37\3\37\3\37\3\37\2\2 \3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= \3\2\b\4\2"+
		">>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5\2DDFFSS\5\2\13\f\17\17\"\"\u00df\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\3?\3\2\2\2\5F\3\2\2\2\7I\3\2\2\2\tQ\3\2\2\2\13X\3\2\2\2"+
		"\r^\3\2\2\2\17a\3\2\2\2\21j\3\2\2\2\23p\3\2\2\2\25v\3\2\2\2\27~\3\2\2"+
		"\2\31\u0087\3\2\2\2\33\u008c\3\2\2\2\35\u008e\3\2\2\2\37\u0090\3\2\2\2"+
		"!\u0092\3\2\2\2#\u0094\3\2\2\2%\u0096\3\2\2\2\'\u0098\3\2\2\2)\u009a\3"+
		"\2\2\2+\u009c\3\2\2\2-\u009e\3\2\2\2/\u00a0\3\2\2\2\61\u00a2\3\2\2\2\63"+
		"\u00ad\3\2\2\2\65\u00af\3\2\2\2\67\u00b3\3\2\2\29\u00be\3\2\2\2;\u00ca"+
		"\3\2\2\2=\u00d2\3\2\2\2?@\7U\2\2@A\7V\2\2AB\7T\2\2BC\7K\2\2CD\7P\2\2D"+
		"E\7I\2\2E\4\3\2\2\2FG\7u\2\2GH\7g\2\2H\6\3\2\2\2IJ\7g\2\2JK\7u\2\2KL\7"+
		"e\2\2LM\7t\2\2MN\7g\2\2NO\7x\2\2OP\7c\2\2P\b\3\2\2\2QR\7P\2\2RS\7W\2\2"+
		"ST\7O\2\2TU\7G\2\2UV\7T\2\2VW\7Q\2\2W\n\3\2\2\2XY\7u\2\2YZ\7g\2\2Z[\7"+
		"p\2\2[\\\7c\2\2\\]\7q\2\2]\f\3\2\2\2^_\7f\2\2_`\7q\2\2`\16\3\2\2\2ab\7"+
		"h\2\2bc\7k\2\2cd\7o\2\2de\7r\2\2ef\7t\2\2fg\7q\2\2gh\7i\2\2hi\7\60\2\2"+
		"i\20\3\2\2\2jk\7g\2\2kl\7p\2\2lm\7v\2\2mn\7c\2\2no\7q\2\2o\22\3\2\2\2"+
		"pq\7y\2\2qr\7j\2\2rs\7k\2\2st\7n\2\2tu\7g\2\2u\24\3\2\2\2vw\7f\2\2wx\7"+
		"g\2\2xy\7e\2\2yz\7n\2\2z{\7c\2\2{|\7t\2\2|}\7g\2\2}\26\3\2\2\2~\177\7"+
		"r\2\2\177\u0080\7t\2\2\u0080\u0081\7q\2\2\u0081\u0082\7i\2\2\u0082\u0083"+
		"\7t\2\2\u0083\u0084\7c\2\2\u0084\u0085\7o\2\2\u0085\u0086\7c\2\2\u0086"+
		"\30\3\2\2\2\u0087\u0088\7n\2\2\u0088\u0089\7g\2\2\u0089\u008a\7k\2\2\u008a"+
		"\u008b\7c\2\2\u008b\32\3\2\2\2\u008c\u008d\7*\2\2\u008d\34\3\2\2\2\u008e"+
		"\u008f\7+\2\2\u008f\36\3\2\2\2\u0090\u0091\7}\2\2\u0091 \3\2\2\2\u0092"+
		"\u0093\7\177\2\2\u0093\"\3\2\2\2\u0094\u0095\7=\2\2\u0095$\3\2\2\2\u0096"+
		"\u0097\7.\2\2\u0097&\3\2\2\2\u0098\u0099\7\60\2\2\u0099(\3\2\2\2\u009a"+
		"\u009b\7$\2\2\u009b*\3\2\2\2\u009c\u009d\7-\2\2\u009d,\3\2\2\2\u009e\u009f"+
		"\7/\2\2\u009f.\3\2\2\2\u00a0\u00a1\7,\2\2\u00a1\60\3\2\2\2\u00a2\u00a3"+
		"\7\61\2\2\u00a3\62\3\2\2\2\u00a4\u00ae\t\2\2\2\u00a5\u00a6\7>\2\2\u00a6"+
		"\u00ae\7?\2\2\u00a7\u00a8\7@\2\2\u00a8\u00ae\7?\2\2\u00a9\u00aa\7#\2\2"+
		"\u00aa\u00ae\7?\2\2\u00ab\u00ac\7?\2\2\u00ac\u00ae\7?\2\2\u00ad\u00a4"+
		"\3\2\2\2\u00ad\u00a5\3\2\2\2\u00ad\u00a7\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ae\64\3\2\2\2\u00af\u00b0\7<\2\2\u00b0\u00b1\7?\2\2"+
		"\u00b1\66\3\2\2\2\u00b2\u00b4\t\3\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00ba\3\2\2\2\u00b7"+
		"\u00b9\t\4\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb8\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00bf"+
		"\t\5\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00c8\3\2\2\2\u00c2\u00c4\7\60\2\2\u00c3\u00c5\t"+
		"\5\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c2\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9:\3\2\2\2\u00ca\u00cc\5)\25\2\u00cb\u00cd\n\6\2\2\u00cc\u00cb"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00d1\5)\25\2\u00d1<\3\2\2\2\u00d2\u00d3\t\7\2\2"+
		"\u00d3\u00d4\3\2\2\2\u00d4\u00d5\b\37\2\2\u00d5>\3\2\2\2\13\2\u00ad\u00b5"+
		"\u00b8\u00ba\u00c0\u00c6\u00c8\u00ce\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}