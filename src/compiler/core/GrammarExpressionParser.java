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
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, AC=15, FC=16, SC=17, C=18, 
		P=19, DBQ=20, OP_SUM=21, OP_SUB=22, OP_MULT=23, OP_DIV=24, OP_REL=25, 
		ATTR=26, ID=27, NUMBER=28, TEXTO=29, WS=30;
	public static final int
		RULE_prog = 0, RULE_declara = 1, RULE_bloco = 2, RULE_tipo = 3, RULE_cmd = 4, 
		RULE_cmdleitura = 5, RULE_cmdescrita = 6, RULE_cmdexpr = 7, RULE_cmdif = 8, 
		RULE_cmdwhile = 9, RULE_expr = 10, RULE_termo = 11, RULE_fator = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "declara", "bloco", "tipo", "cmd", "cmdleitura", "cmdescrita", 
			"cmdexpr", "cmdif", "cmdwhile", "expr", "termo", "fator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'declare'", "'NUMERO'", "'STRING'", 
			"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'do'", "'while'", 
			"'('", "')'", "'{'", "'}'", "';'", "','", "'.'", "'\"'", "'+'", "'-'", 
			"'*'", "'/'", null, "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AP", "FP", "AC", "FC", "SC", "C", "P", "DBQ", "OP_SUM", "OP_SUB", 
			"OP_MULT", "OP_DIV", "OP_REL", "ATTR", "ID", "NUMBER", "TEXTO", "WS"
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

	@Override
	public String getGrammarFileName() { return "GrammarExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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
			symbolTable.add(lastToken(), new Identifier(lastToken(), _currentType));
		}
		
		public void	markVarUsed(){
			symbolTable.get(lastToken()).setUsed();
		}
		
		public void	markVarInitialized(){
			symbolTable.get(lastToken()).setInitialized();
		}
		
		public void attrExprToId(String Tid, String value){
			symbolTable.get(Tid).setValue(value);
		}

		public void checkDeclared(){
			if(!symbolTable.containsKey(lastToken())){
				throw new SemanticException("Variável não declarada " + lastToken() + "."); 
			}
		}

		public void checkInitialized(){
			if(!symbolTable.get(lastToken()).getInitialized()){
				throw new SemanticException("Variável " + lastToken() + " não inicializada."); 
			}
		}
		
		public void checkUnused(){
			symbolTable.getValues().stream().forEach((id) -> {
			    if (!id.getUsed()) {
			        throw new SemanticException("Variável " + id.getName() + " não utilizada.");
			    }
			});
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


	public GrammarExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public List<DeclaraContext> declara() {
			return getRuleContexts(DeclaraContext.class);
		}
		public DeclaraContext declara(int i) {
			return getRuleContext(DeclaraContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__0);
			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				declara();
				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(32);
			bloco();
			checkUnused();
			setState(34);
			match(T__1);

					program.setVarTable(symbolTable);
					program.setComandos(stack.pop());	
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaraContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(GrammarExpressionParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GrammarExpressionParser.ID, i);
		}
		public TerminalNode SC() { return getToken(GrammarExpressionParser.SC, 0); }
		public List<TerminalNode> C() { return getTokens(GrammarExpressionParser.C); }
		public TerminalNode C(int i) {
			return getToken(GrammarExpressionParser.C, i);
		}
		public DeclaraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterDeclara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitDeclara(this);
		}
	}

	public final DeclaraContext declara() throws RecognitionException {
		DeclaraContext _localctx = new DeclaraContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(T__2);
			setState(38);
			tipo();
			setState(39);
			match(ID);
			addId();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==C) {
				{
				{
				setState(41);
				match(C);
				setState(42);
				match(ID);
				addId();
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			commandStack();
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				cmd();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 134224320L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(T__3);
				 _currentType = DataType.NUM; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(T__4);
				 _currentType = DataType.STRING; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public TerminalNode SC() { return getToken(GrammarExpressionParser.SC, 0); }
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdexprContext cmdexpr() {
			return getRuleContext(CmdexprContext.class,0);
		}
		public CmdifContext cmdif() {
			return getRuleContext(CmdifContext.class,0);
		}
		public CmdwhileContext cmdwhile() {
			return getRuleContext(CmdwhileContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(63);
				cmdleitura();
				}
				break;
			case T__6:
				{
				setState(64);
				cmdescrita();
				}
				break;
			case ID:
				{
				setState(65);
				cmdexpr();
				}
				break;
			case T__7:
				{
				setState(66);
				cmdif();
				}
				break;
			case T__10:
			case T__11:
				{
				setState(67);
				cmdwhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(70);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__5);
			setState(73);
			match(AP);
			setState(74);
			match(ID);

					checkDeclared();
					markVarInitialized();	
					leitura();
				
			setState(76);
			match(FP);
			commandLeitura();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public TerminalNode TEXTO() { return getToken(GrammarExpressionParser.TEXTO, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__6);
			setState(80);
			match(AP);
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTO:
				{
				setState(81);
				match(TEXTO);
				}
				break;
			case ID:
				{
				setState(82);
				match(ID);

							checkDeclared();
						 	markVarUsed();
						
				escrita();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(87);
			match(FP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdexprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(GrammarExpressionParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CmdexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdexpr(this);
		}
	}

	public final CmdexprContext cmdexpr() throws RecognitionException {
		CmdexprContext _localctx = new CmdexprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdexpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(ID);

					checkDeclared();
					exprAtribuicao();
					markVarInitialized();
				
			setState(91);
			match(ATTR);
			contentAtribuicao();
			setState(93);
			expr();
				
					commandAtribuicao();
					attrExprToId(_exprID,_exprContent );
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdifContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_REL() { return getToken(GrammarExpressionParser.OP_REL, 0); }
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public List<TerminalNode> AC() { return getTokens(GrammarExpressionParser.AC); }
		public TerminalNode AC(int i) {
			return getToken(GrammarExpressionParser.AC, i);
		}
		public List<TerminalNode> FC() { return getTokens(GrammarExpressionParser.FC); }
		public TerminalNode FC(int i) {
			return getToken(GrammarExpressionParser.FC, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdif(this);
		}
	}

	public final CmdifContext cmdif() throws RecognitionException {
		CmdifContext _localctx = new CmdifContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__7);
			setState(97);
			match(AP);
			setState(98);
			expr();
			exprDecision();
			setState(100);
			match(OP_REL);
			exprDecisionAcum();
			setState(102);
			expr();
			exprDecisionAcum();
			setState(104);
			match(FP);
			setState(105);
			match(T__8);
			setState(106);
			match(AC);
			commandStack();
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				cmd();
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 134224320L) != 0) );
			setState(113);
			match(FC);
			listaTrueDecision();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(115);
				match(T__9);
				setState(116);
				match(AC);
				commandStack();
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(118);
					cmd();
					}
					}
					setState(121); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 134224320L) != 0) );
				setState(123);
				match(FC);
				listaFalseDecision();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdwhileContext extends ParserRuleContext {
		public TerminalNode AC() { return getToken(GrammarExpressionParser.AC, 0); }
		public TerminalNode FC() { return getToken(GrammarExpressionParser.FC, 0); }
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_REL() { return getToken(GrammarExpressionParser.OP_REL, 0); }
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdwhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdwhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdwhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdwhile(this);
		}
	}

	public final CmdwhileContext cmdwhile() throws RecognitionException {
		CmdwhileContext _localctx = new CmdwhileContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdwhile);
		int _la;
		try {
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(T__10);
				setState(129);
				match(AC);
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(130);
					cmd();
					}
					}
					setState(133); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 134224320L) != 0) );
				setState(135);
				match(FC);
				setState(136);
				match(T__11);
				setState(137);
				match(AP);
				setState(138);
				expr();
				setState(139);
				match(OP_REL);
				setState(140);
				expr();
				setState(141);
				match(FP);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(T__11);
				setState(144);
				match(AP);
				setState(145);
				expr();
				setState(146);
				match(OP_REL);
				setState(147);
				expr();
				setState(148);
				match(FP);
				setState(149);
				match(AC);
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(150);
					cmd();
					}
					}
					setState(153); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 134224320L) != 0) );
				setState(155);
				match(FC);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP_SUM() { return getTokens(GrammarExpressionParser.OP_SUM); }
		public TerminalNode OP_SUM(int i) {
			return getToken(GrammarExpressionParser.OP_SUM, i);
		}
		public List<TerminalNode> OP_SUB() { return getTokens(GrammarExpressionParser.OP_SUB); }
		public TerminalNode OP_SUB(int i) {
			return getToken(GrammarExpressionParser.OP_SUB, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			termo();
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_SUM || _la==OP_SUB) {
				{
				{
				setState(160);
				_la = _input.LA(1);
				if ( !(_la==OP_SUM || _la==OP_SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				inputTermo();
				setState(162);
				termo();
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public List<FatorContext> fator() {
			return getRuleContexts(FatorContext.class);
		}
		public FatorContext fator(int i) {
			return getRuleContext(FatorContext.class,i);
		}
		public List<TerminalNode> OP_MULT() { return getTokens(GrammarExpressionParser.OP_MULT); }
		public TerminalNode OP_MULT(int i) {
			return getToken(GrammarExpressionParser.OP_MULT, i);
		}
		public List<TerminalNode> OP_DIV() { return getTokens(GrammarExpressionParser.OP_DIV); }
		public TerminalNode OP_DIV(int i) {
			return getToken(GrammarExpressionParser.OP_DIV, i);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_termo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			fator();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_MULT || _la==OP_DIV) {
				{
				{
				setState(169);
				_la = _input.LA(1);
				if ( !(_la==OP_MULT || _la==OP_DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				inputTermo();
				setState(171);
				fator();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FatorContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(GrammarExpressionParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public TerminalNode TEXTO() { return getToken(GrammarExpressionParser.TEXTO, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fator);
		try {
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				match(NUMBER);
				inputTermo();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(ID);

						checkDeclared(); 
					 	checkInitialized();
					 	markVarUsed();
					 	inputTermo();
					
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 3);
				{
				setState(181);
				match(AP);
				inputTermo();
				setState(183);
				expr();
				setState(184);
				match(FP);
				inputTermo();
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(187);
				match(TEXTO);
				inputTermo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001e\u00c0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0001\u0000\u0001\u0000\u0004\u0000\u001d\b\u0000\u000b"+
		"\u0000\f\u0000\u001e\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0005\u0001-\b\u0001\n\u0001\f\u00010\t\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0004\u00026\b\u0002\u000b"+
		"\u0002\f\u00027\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003>\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004E\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006V\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0004\bn\b\b\u000b\b\f\bo\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0004\bx\b\b\u000b\b\f\by\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u007f\b\b\u0001\t\u0001\t\u0001\t\u0004\t\u0084\b\t\u000b\t\f\t\u0085"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004\t\u0098"+
		"\b\t\u000b\t\f\t\u0099\u0001\t\u0001\t\u0003\t\u009e\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0005\n\u00a4\b\n\n\n\f\n\u00a7\t\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00ad\b\u000b\n\u000b\f\u000b"+
		"\u00b0\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00be\b\f\u0001\f\u0000"+
		"\u0000\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u0000\u0002\u0001\u0000\u0015\u0016\u0001\u0000\u0017\u0018\u00c6\u0000"+
		"\u001a\u0001\u0000\u0000\u0000\u0002%\u0001\u0000\u0000\u0000\u00043\u0001"+
		"\u0000\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\bD\u0001\u0000\u0000"+
		"\u0000\nH\u0001\u0000\u0000\u0000\fO\u0001\u0000\u0000\u0000\u000eY\u0001"+
		"\u0000\u0000\u0000\u0010`\u0001\u0000\u0000\u0000\u0012\u009d\u0001\u0000"+
		"\u0000\u0000\u0014\u009f\u0001\u0000\u0000\u0000\u0016\u00a8\u0001\u0000"+
		"\u0000\u0000\u0018\u00bd\u0001\u0000\u0000\u0000\u001a\u001c\u0005\u0001"+
		"\u0000\u0000\u001b\u001d\u0003\u0002\u0001\u0000\u001c\u001b\u0001\u0000"+
		"\u0000\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e\u001c\u0001\u0000"+
		"\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000"+
		"\u0000 !\u0003\u0004\u0002\u0000!\"\u0006\u0000\uffff\uffff\u0000\"#\u0005"+
		"\u0002\u0000\u0000#$\u0006\u0000\uffff\uffff\u0000$\u0001\u0001\u0000"+
		"\u0000\u0000%&\u0005\u0003\u0000\u0000&\'\u0003\u0006\u0003\u0000\'(\u0005"+
		"\u001b\u0000\u0000(.\u0006\u0001\uffff\uffff\u0000)*\u0005\u0012\u0000"+
		"\u0000*+\u0005\u001b\u0000\u0000+-\u0006\u0001\uffff\uffff\u0000,)\u0001"+
		"\u0000\u0000\u0000-0\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000"+
		"./\u0001\u0000\u0000\u0000/1\u0001\u0000\u0000\u00000.\u0001\u0000\u0000"+
		"\u000012\u0005\u0011\u0000\u00002\u0003\u0001\u0000\u0000\u000035\u0006"+
		"\u0002\uffff\uffff\u000046\u0003\b\u0004\u000054\u0001\u0000\u0000\u0000"+
		"67\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u000078\u0001\u0000\u0000"+
		"\u00008\u0005\u0001\u0000\u0000\u00009:\u0005\u0004\u0000\u0000:>\u0006"+
		"\u0003\uffff\uffff\u0000;<\u0005\u0005\u0000\u0000<>\u0006\u0003\uffff"+
		"\uffff\u0000=9\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>\u0007"+
		"\u0001\u0000\u0000\u0000?E\u0003\n\u0005\u0000@E\u0003\f\u0006\u0000A"+
		"E\u0003\u000e\u0007\u0000BE\u0003\u0010\b\u0000CE\u0003\u0012\t\u0000"+
		"D?\u0001\u0000\u0000\u0000D@\u0001\u0000\u0000\u0000DA\u0001\u0000\u0000"+
		"\u0000DB\u0001\u0000\u0000\u0000DC\u0001\u0000\u0000\u0000EF\u0001\u0000"+
		"\u0000\u0000FG\u0005\u0011\u0000\u0000G\t\u0001\u0000\u0000\u0000HI\u0005"+
		"\u0006\u0000\u0000IJ\u0005\r\u0000\u0000JK\u0005\u001b\u0000\u0000KL\u0006"+
		"\u0005\uffff\uffff\u0000LM\u0005\u000e\u0000\u0000MN\u0006\u0005\uffff"+
		"\uffff\u0000N\u000b\u0001\u0000\u0000\u0000OP\u0005\u0007\u0000\u0000"+
		"PU\u0005\r\u0000\u0000QV\u0005\u001d\u0000\u0000RS\u0005\u001b\u0000\u0000"+
		"ST\u0006\u0006\uffff\uffff\u0000TV\u0006\u0006\uffff\uffff\u0000UQ\u0001"+
		"\u0000\u0000\u0000UR\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000"+
		"WX\u0005\u000e\u0000\u0000X\r\u0001\u0000\u0000\u0000YZ\u0005\u001b\u0000"+
		"\u0000Z[\u0006\u0007\uffff\uffff\u0000[\\\u0005\u001a\u0000\u0000\\]\u0006"+
		"\u0007\uffff\uffff\u0000]^\u0003\u0014\n\u0000^_\u0006\u0007\uffff\uffff"+
		"\u0000_\u000f\u0001\u0000\u0000\u0000`a\u0005\b\u0000\u0000ab\u0005\r"+
		"\u0000\u0000bc\u0003\u0014\n\u0000cd\u0006\b\uffff\uffff\u0000de\u0005"+
		"\u0019\u0000\u0000ef\u0006\b\uffff\uffff\u0000fg\u0003\u0014\n\u0000g"+
		"h\u0006\b\uffff\uffff\u0000hi\u0005\u000e\u0000\u0000ij\u0005\t\u0000"+
		"\u0000jk\u0005\u000f\u0000\u0000km\u0006\b\uffff\uffff\u0000ln\u0003\b"+
		"\u0004\u0000ml\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000om\u0001"+
		"\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000"+
		"qr\u0005\u0010\u0000\u0000r~\u0006\b\uffff\uffff\u0000st\u0005\n\u0000"+
		"\u0000tu\u0005\u000f\u0000\u0000uw\u0006\b\uffff\uffff\u0000vx\u0003\b"+
		"\u0004\u0000wv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000yw\u0001"+
		"\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000"+
		"{|\u0005\u0010\u0000\u0000|}\u0006\b\uffff\uffff\u0000}\u007f\u0001\u0000"+
		"\u0000\u0000~s\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000"+
		"\u007f\u0011\u0001\u0000\u0000\u0000\u0080\u0081\u0005\u000b\u0000\u0000"+
		"\u0081\u0083\u0005\u000f\u0000\u0000\u0082\u0084\u0003\b\u0004\u0000\u0083"+
		"\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085"+
		"\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0005\u0010\u0000\u0000\u0088"+
		"\u0089\u0005\f\u0000\u0000\u0089\u008a\u0005\r\u0000\u0000\u008a\u008b"+
		"\u0003\u0014\n\u0000\u008b\u008c\u0005\u0019\u0000\u0000\u008c\u008d\u0003"+
		"\u0014\n\u0000\u008d\u008e\u0005\u000e\u0000\u0000\u008e\u009e\u0001\u0000"+
		"\u0000\u0000\u008f\u0090\u0005\f\u0000\u0000\u0090\u0091\u0005\r\u0000"+
		"\u0000\u0091\u0092\u0003\u0014\n\u0000\u0092\u0093\u0005\u0019\u0000\u0000"+
		"\u0093\u0094\u0003\u0014\n\u0000\u0094\u0095\u0005\u000e\u0000\u0000\u0095"+
		"\u0097\u0005\u000f\u0000\u0000\u0096\u0098\u0003\b\u0004\u0000\u0097\u0096"+
		"\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u0097"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009b"+
		"\u0001\u0000\u0000\u0000\u009b\u009c\u0005\u0010\u0000\u0000\u009c\u009e"+
		"\u0001\u0000\u0000\u0000\u009d\u0080\u0001\u0000\u0000\u0000\u009d\u008f"+
		"\u0001\u0000\u0000\u0000\u009e\u0013\u0001\u0000\u0000\u0000\u009f\u00a5"+
		"\u0003\u0016\u000b\u0000\u00a0\u00a1\u0007\u0000\u0000\u0000\u00a1\u00a2"+
		"\u0006\n\uffff\uffff\u0000\u00a2\u00a4\u0003\u0016\u000b\u0000\u00a3\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u0015"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00ae"+
		"\u0003\u0018\f\u0000\u00a9\u00aa\u0007\u0001\u0000\u0000\u00aa\u00ab\u0006"+
		"\u000b\uffff\uffff\u0000\u00ab\u00ad\u0003\u0018\f\u0000\u00ac\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u0017\u0001"+
		"\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005"+
		"\u001c\u0000\u0000\u00b2\u00be\u0006\f\uffff\uffff\u0000\u00b3\u00b4\u0005"+
		"\u001b\u0000\u0000\u00b4\u00be\u0006\f\uffff\uffff\u0000\u00b5\u00b6\u0005"+
		"\r\u0000\u0000\u00b6\u00b7\u0006\f\uffff\uffff\u0000\u00b7\u00b8\u0003"+
		"\u0014\n\u0000\u00b8\u00b9\u0005\u000e\u0000\u0000\u00b9\u00ba\u0006\f"+
		"\uffff\uffff\u0000\u00ba\u00be\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005"+
		"\u001d\u0000\u0000\u00bc\u00be\u0006\f\uffff\uffff\u0000\u00bd\u00b1\u0001"+
		"\u0000\u0000\u0000\u00bd\u00b3\u0001\u0000\u0000\u0000\u00bd\u00b5\u0001"+
		"\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u0019\u0001"+
		"\u0000\u0000\u0000\u000f\u001e.7=DUoy~\u0085\u0099\u009d\u00a5\u00ae\u00bd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}