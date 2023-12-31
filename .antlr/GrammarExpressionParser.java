// Generated from c:\u005Cufabc\Compiladores\lang-compiler\GrammarExpression.g4 by ANTLR 4.9.2

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, AC=15, FC=16, SC=17, C=18, 
		P=19, OP_SUM=20, OP_SUB=21, OP_MULT=22, OP_DIV=23, OP_REL=24, ATTR=25, 
		ID=26, NUMBER=27, TEXTO=28, WS=29;
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

	public GrammarExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

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
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(27);
				declara();
				}
				}
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(33);
			bloco();
			checkUnused();
			setState(35);
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
	}

	public final DeclaraContext declara() throws RecognitionException {
		DeclaraContext _localctx = new DeclaraContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__2);
			setState(39);
			tipo();
			setState(40);
			match(ID);
			addId();
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==C) {
				{
				{
				setState(42);
				match(C);
				setState(43);
				match(ID);
				addId();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			commandStack();
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(53);
				cmd();
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
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

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__3);
				 _currentType = DataType.NUM; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
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
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(64);
				cmdleitura();
				}
				break;
			case T__6:
				{
				setState(65);
				cmdescrita();
				}
				break;
			case ID:
				{
				setState(66);
				cmdexpr();
				}
				break;
			case T__7:
				{
				setState(67);
				cmdif();
				}
				break;
			case T__10:
			case T__11:
				{
				setState(68);
				cmdwhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(71);
			match(SC);
			newExpr();
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

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__5);
			setState(75);
			match(AP);
			setState(76);
			match(ID);

					checkDeclared();
					markVarInitialized();	
					leitura();
				
			setState(78);
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

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__6);
			setState(82);
			match(AP);
			{
			setState(83);
			expr();
			}

					
					commandEscrita(_exprContent);
					newExpr();
				
			setState(85);
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
	}

	public final CmdexprContext cmdexpr() throws RecognitionException {
		CmdexprContext _localctx = new CmdexprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdexpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(ID);

					checkDeclared();
					exprAtribuicao();
					markVarInitialized();
				
			setState(89);
			match(ATTR);
			newExpr();
			setState(91);
			expr();
				
					commandAtribuicao();
					attrExprToId(_exprID, _exprContent);
					newExpr();
				
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
	}

	public final CmdifContext cmdif() throws RecognitionException {
		CmdifContext _localctx = new CmdifContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(T__7);
			setState(95);
			match(AP);
			setState(96);
			expr();

					exprDecision(_exprContent);
				
			setState(98);
			match(OP_REL);

					exprDecisionAcum(lastToken());
					
				
			setState(100);
			expr();

					exprDecisionAcum(_exprContent);
				
			setState(102);
			match(FP);
			setState(103);
			match(T__8);
			setState(104);
			match(AC);
			commandStack();
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106);
				cmd();
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(111);
			match(FC);
			listaTrueDecision();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(113);
				match(T__9);
				setState(114);
				match(AC);
				commandStack();
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(116);
					cmd();
					}
					}
					setState(119); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(121);
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
	}

	public final CmdwhileContext cmdwhile() throws RecognitionException {
		CmdwhileContext _localctx = new CmdwhileContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdwhile);
		int _la;
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(T__10);
				setState(127);
				match(AC);
				commandStack();
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(129);
					cmd();
					}
					}
					setState(132); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(134);
				match(FC);
				setState(135);
				match(T__11);
				setState(136);
				match(AP);
				setState(137);
				expr();

						exprRepeticao(_exprContent);		
					
				setState(139);
				match(OP_REL);
				exprRepeticaoAcum(lastToken());
				setState(141);
				expr();

						exprRepeticaoAcum(_exprContent);
					
				listaRepeticao("DoWhile");
				setState(144);
				match(FP);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				match(T__11);
				setState(147);
				match(AP);
				setState(148);
				expr();
					
						exprRepeticao(_exprContent);
					
				setState(150);
				match(OP_REL);
				exprRepeticaoAcum(lastToken());
				setState(152);
				expr();
				exprRepeticaoAcum(_exprContent);
				setState(154);
				match(FP);
				setState(155);
				match(AC);
				commandStack();
				setState(158); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(157);
					cmd();
					}
					}
					setState(160); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(162);
				match(FC);
				listaRepeticao("While");
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
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			termo();
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_SUM || _la==OP_SUB) {
				{
				{
				setState(168);
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
				setState(170);
				termo();
				}
				}
				setState(175);
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
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_termo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			fator();
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_MULT || _la==OP_DIV) {
				{
				{
				setState(177);
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
				setState(179);
				fator();
				}
				}
				setState(184);
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
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fator);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(NUMBER);
				inputTermo();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
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
				setState(189);
				match(AP);
				inputTermo();
				setState(191);
				expr();
				setState(192);
				match(FP);
				inputTermo();
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u00ca\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\7\2\37\n\2\f\2\16\2\"\13\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13"+
		"\3\3\3\3\3\3\4\3\4\6\49\n\4\r\4\16\4:\3\5\3\5\3\5\3\5\5\5A\n\5\3\6\3\6"+
		"\3\6\3\6\3\6\5\6H\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\6\nn\n\n\r\n\16\no\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\6\nx\n\n\r\n\16\ny\3\n\3\n\3\n\5\n\177\n\n\3\13\3\13\3\13\3\13\6\13\u0085"+
		"\n\13\r\13\16\13\u0086\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\6\13\u00a1\n\13\r\13\16\13\u00a2\3\13\3\13\3\13\5\13\u00a8\n\13\3"+
		"\f\3\f\3\f\3\f\7\f\u00ae\n\f\f\f\16\f\u00b1\13\f\3\r\3\r\3\r\3\r\7\r\u00b7"+
		"\n\r\f\r\16\r\u00ba\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u00c8\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\2\4\3\2\26\27\3\2\30\31\2\u00cf\2\34\3\2\2\2\4(\3\2\2\2\6\66\3"+
		"\2\2\2\b@\3\2\2\2\nG\3\2\2\2\fL\3\2\2\2\16S\3\2\2\2\20Y\3\2\2\2\22`\3"+
		"\2\2\2\24\u00a7\3\2\2\2\26\u00a9\3\2\2\2\30\u00b2\3\2\2\2\32\u00c7\3\2"+
		"\2\2\34 \7\3\2\2\35\37\5\4\3\2\36\35\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2"+
		" !\3\2\2\2!#\3\2\2\2\" \3\2\2\2#$\5\6\4\2$%\b\2\1\2%&\7\4\2\2&\'\b\2\1"+
		"\2\'\3\3\2\2\2()\7\5\2\2)*\5\b\5\2*+\7\34\2\2+\61\b\3\1\2,-\7\24\2\2-"+
		".\7\34\2\2.\60\b\3\1\2/,\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2"+
		"\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7\23\2\2\65\5\3\2\2\2\668\b\4\1\2"+
		"\679\5\n\6\28\67\3\2\2\29:\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\7\3\2\2\2<=\7"+
		"\6\2\2=A\b\5\1\2>?\7\7\2\2?A\b\5\1\2@<\3\2\2\2@>\3\2\2\2A\t\3\2\2\2BH"+
		"\5\f\7\2CH\5\16\b\2DH\5\20\t\2EH\5\22\n\2FH\5\24\13\2GB\3\2\2\2GC\3\2"+
		"\2\2GD\3\2\2\2GE\3\2\2\2GF\3\2\2\2HI\3\2\2\2IJ\7\23\2\2JK\b\6\1\2K\13"+
		"\3\2\2\2LM\7\b\2\2MN\7\17\2\2NO\7\34\2\2OP\b\7\1\2PQ\7\20\2\2QR\b\7\1"+
		"\2R\r\3\2\2\2ST\7\t\2\2TU\7\17\2\2UV\5\26\f\2VW\b\b\1\2WX\7\20\2\2X\17"+
		"\3\2\2\2YZ\7\34\2\2Z[\b\t\1\2[\\\7\33\2\2\\]\b\t\1\2]^\5\26\f\2^_\b\t"+
		"\1\2_\21\3\2\2\2`a\7\n\2\2ab\7\17\2\2bc\5\26\f\2cd\b\n\1\2de\7\32\2\2"+
		"ef\b\n\1\2fg\5\26\f\2gh\b\n\1\2hi\7\20\2\2ij\7\13\2\2jk\7\21\2\2km\b\n"+
		"\1\2ln\5\n\6\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\7\22"+
		"\2\2r~\b\n\1\2st\7\f\2\2tu\7\21\2\2uw\b\n\1\2vx\5\n\6\2wv\3\2\2\2xy\3"+
		"\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\22\2\2|}\b\n\1\2}\177\3\2\2\2"+
		"~s\3\2\2\2~\177\3\2\2\2\177\23\3\2\2\2\u0080\u0081\7\r\2\2\u0081\u0082"+
		"\7\21\2\2\u0082\u0084\b\13\1\2\u0083\u0085\5\n\6\2\u0084\u0083\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088"+
		"\3\2\2\2\u0088\u0089\7\22\2\2\u0089\u008a\7\16\2\2\u008a\u008b\7\17\2"+
		"\2\u008b\u008c\5\26\f\2\u008c\u008d\b\13\1\2\u008d\u008e\7\32\2\2\u008e"+
		"\u008f\b\13\1\2\u008f\u0090\5\26\f\2\u0090\u0091\b\13\1\2\u0091\u0092"+
		"\b\13\1\2\u0092\u0093\7\20\2\2\u0093\u00a8\3\2\2\2\u0094\u0095\7\16\2"+
		"\2\u0095\u0096\7\17\2\2\u0096\u0097\5\26\f\2\u0097\u0098\b\13\1\2\u0098"+
		"\u0099\7\32\2\2\u0099\u009a\b\13\1\2\u009a\u009b\5\26\f\2\u009b\u009c"+
		"\b\13\1\2\u009c\u009d\7\20\2\2\u009d\u009e\7\21\2\2\u009e\u00a0\b\13\1"+
		"\2\u009f\u00a1\5\n\6\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0"+
		"\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\22\2\2"+
		"\u00a5\u00a6\b\13\1\2\u00a6\u00a8\3\2\2\2\u00a7\u0080\3\2\2\2\u00a7\u0094"+
		"\3\2\2\2\u00a8\25\3\2\2\2\u00a9\u00af\5\30\r\2\u00aa\u00ab\t\2\2\2\u00ab"+
		"\u00ac\b\f\1\2\u00ac\u00ae\5\30\r\2\u00ad\u00aa\3\2\2\2\u00ae\u00b1\3"+
		"\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\27\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b2\u00b8\5\32\16\2\u00b3\u00b4\t\3\2\2\u00b4\u00b5\b"+
		"\r\1\2\u00b5\u00b7\5\32\16\2\u00b6\u00b3\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\31\3\2\2\2\u00ba\u00b8\3\2\2"+
		"\2\u00bb\u00bc\7\35\2\2\u00bc\u00c8\b\16\1\2\u00bd\u00be\7\34\2\2\u00be"+
		"\u00c8\b\16\1\2\u00bf\u00c0\7\17\2\2\u00c0\u00c1\b\16\1\2\u00c1\u00c2"+
		"\5\26\f\2\u00c2\u00c3\7\20\2\2\u00c3\u00c4\b\16\1\2\u00c4\u00c8\3\2\2"+
		"\2\u00c5\u00c6\7\36\2\2\u00c6\u00c8\b\16\1\2\u00c7\u00bb\3\2\2\2\u00c7"+
		"\u00bd\3\2\2\2\u00c7\u00bf\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\33\3\2\2"+
		"\2\20 \61:@Goy~\u0086\u00a2\u00a7\u00af\u00b8\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}