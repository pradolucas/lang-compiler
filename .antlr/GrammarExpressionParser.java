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
	import java.util.ArrayList;

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
		private ArrayList<AbstractCommand> curThread = new ArrayList<AbstractCommand>();

		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;

		
		
		public String lastToken(){
			return ((TokenStream) _input).LT(-1).getText();
		}
		public void addId(){
	//		System.out.println("[TOKEN] "+ lastToken());

			symbolTable.add(lastToken(), new Identifier(lastToken(), _currentType));
		}
		
		public void checkIdExists(){
			if(!symbolTable.containsKey(lastToken())){
				throw new SemanticException("Variável não declarada " + lastToken() + "."); 
			}
		}
		
		public void attrExprToId(String Tid, String value){
	//		TODO Assegurar o token id, não é last value, pois será o lado direito da expressao. 
			symbolTable.get(Tid).setValue(value);
		}
		
		public void checkInitialized(){
	////		TIP para testes comente essa funcao, a atribuicao de valor (funcao a attrExprToId) n foi implementada
	//		if(symbolTable.get(lastToken()).getValue() == null){
	//			throw new SemanticException("Variável " + lastToken() + " não incializada."); 
	//		}
			assert true;
		}
		

		public void checkUnused(){
	//		ERRADO
	//		symbolTable.getValues().stream().forEach((id)->if(id.getValue() == NULL) throw new SemanticException("Variável " + id.getName() + " não incializada."));
			assert true;
		}
		
		public void showTokens(){
			symbolTable.getValues().stream().forEach((id)->System.out.println(id));
		}
		
		public void leitura(){
			_readID = lastToken();
			CommandLeitura cmd = new CommandLeitura(_readID);
			curThread.add(cmd);
		}

		public void escrita(){
			_writeID = lastToken();
			CommandEscrita cmd = new CommandEscrita(_writeID);
			curThread.add(cmd);
		}

		public void exprAtribuicao(){
			_exprID = lastToken();
			_exprContent = "";
			CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
			curThread.add(cmd);
		}

		public void inputTermo(){
			_exprContent += lastToken();
		}

		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()) {
			System.out.println(c);
		}
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
			program.setComandos(curThread);
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
		public TerminalNode P() { return getToken(GrammarExpressionParser.P, 0); }
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
			match(P);
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
		public List<TerminalNode> P() { return getTokens(GrammarExpressionParser.P); }
		public TerminalNode P(int i) {
			return getToken(GrammarExpressionParser.P, i);
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
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(51);
				cmd();
				setState(52);
				match(P);
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
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				cmdexpr();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				cmdif();
				}
				break;
			case T__10:
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				cmdwhile();
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
			setState(71);
			match(T__5);
			setState(72);
			match(AP);
			setState(73);
			match(ID);
			checkIdExists();
			leitura();
			setState(76);
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

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(GrammarExpressionParser.AP, 0); }
		public TerminalNode FP() { return getToken(GrammarExpressionParser.FP, 0); }
		public TerminalNode TEXTO() { return getToken(GrammarExpressionParser.TEXTO, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
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
			setState(78);
			match(T__6);
			setState(79);
			match(AP);
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTO:
				{
				setState(80);
				match(TEXTO);
				}
				break;
			case ID:
				{
				setState(81);
				match(ID);
				checkIdExists();
				escrita();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(86);
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
			setState(88);
			match(ID);
			checkIdExists();
					idAttr = lastToken();
					exprAtribuicao();
				
			setState(90);
			match(ATTR);
			setState(91);
			expr();
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
			setState(93);
			match(T__7);
			setState(94);
			match(AP);
			setState(95);
			expr();
			setState(96);
			match(OP_REL);
			setState(97);
			expr();
			setState(98);
			match(FP);
			setState(99);
			match(T__8);
			setState(100);
			match(AC);
			setState(102); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101);
				cmd();
				}
				}
				setState(104); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(106);
			match(FC);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(107);
				match(T__9);
				setState(108);
				match(AC);
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(109);
					cmd();
					}
					}
					setState(112); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(114);
				match(FC);
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
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(T__10);
				setState(119);
				match(AC);
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120);
					cmd();
					}
					}
					setState(123); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(125);
				match(FC);
				setState(126);
				match(T__11);
				setState(127);
				match(AP);
				setState(128);
				expr();
				setState(129);
				match(OP_REL);
				setState(130);
				expr();
				setState(131);
				match(FP);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(T__11);
				setState(134);
				match(AP);
				setState(135);
				expr();
				setState(136);
				match(OP_REL);
				setState(137);
				expr();
				setState(138);
				match(FP);
				setState(139);
				match(AC);
				setState(141); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(140);
					cmd();
					}
					}
					setState(143); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(145);
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
			setState(149);
			termo();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_SUM || _la==OP_SUB) {
				{
				{
				setState(153);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OP_SUM:
					{
					setState(150);
					match(OP_SUM);
					}
					break;
				case OP_SUB:
					{
					setState(151);
					match(OP_SUB);
					inputTermo();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(155);
				termo();
				}
				}
				setState(160);
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
			setState(161);
			fator();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_MULT || _la==OP_DIV) {
				{
				{
				setState(165);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OP_MULT:
					{
					setState(162);
					match(OP_MULT);
					}
					break;
				case OP_DIV:
					{
					setState(163);
					match(OP_DIV);
					inputTermo();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(167);
				fator();
				}
				}
				setState(172);
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
		public TerminalNode FC() { return getToken(GrammarExpressionParser.FC, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fator);
		try {
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				match(NUMBER);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				match(ID);
				checkIdExists(); checkInitialized(); inputTermo();
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 3);
				{
				setState(176);
				match(AP);
				setState(177);
				expr();
				setState(178);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00b9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\6\2\37\n\2\r\2\16\2 \3\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3/\n\3\f\3\16\3\62\13\3\3\3\3\3"+
		"\3\4\3\4\3\4\6\49\n\4\r\4\16\4:\3\5\3\5\3\5\3\5\5\5A\n\5\3\6\3\6\3\6\3"+
		"\6\3\6\5\6H\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5"+
		"\bW\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\6\ni\n\n\r\n\16\nj\3\n\3\n\3\n\3\n\6\nq\n\n\r\n\16\nr\3\n\3\n\5\nw"+
		"\n\n\3\13\3\13\3\13\6\13|\n\13\r\13\16\13}\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u0090\n\13"+
		"\r\13\16\13\u0091\3\13\3\13\5\13\u0096\n\13\3\f\3\f\3\f\3\f\5\f\u009c"+
		"\n\f\3\f\7\f\u009f\n\f\f\f\16\f\u00a2\13\f\3\r\3\r\3\r\3\r\5\r\u00a8\n"+
		"\r\3\r\7\r\u00ab\n\r\f\r\16\r\u00ae\13\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u00b7\n\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2"+
		"\2\u00c0\2\34\3\2\2\2\4\'\3\2\2\2\68\3\2\2\2\b@\3\2\2\2\nG\3\2\2\2\fI"+
		"\3\2\2\2\16P\3\2\2\2\20Z\3\2\2\2\22_\3\2\2\2\24\u0095\3\2\2\2\26\u0097"+
		"\3\2\2\2\30\u00a3\3\2\2\2\32\u00b6\3\2\2\2\34\36\7\3\2\2\35\37\5\4\3\2"+
		"\36\35\3\2\2\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\5\6\4\2"+
		"#$\b\2\1\2$%\7\4\2\2%&\b\2\1\2&\3\3\2\2\2\'(\7\5\2\2()\5\b\5\2)*\7\35"+
		"\2\2*\60\b\3\1\2+,\7\24\2\2,-\7\35\2\2-/\b\3\1\2.+\3\2\2\2/\62\3\2\2\2"+
		"\60.\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\7\25\2\2"+
		"\64\5\3\2\2\2\65\66\5\n\6\2\66\67\7\25\2\2\679\3\2\2\28\65\3\2\2\29:\3"+
		"\2\2\2:8\3\2\2\2:;\3\2\2\2;\7\3\2\2\2<=\7\6\2\2=A\b\5\1\2>?\7\7\2\2?A"+
		"\b\5\1\2@<\3\2\2\2@>\3\2\2\2A\t\3\2\2\2BH\5\f\7\2CH\5\16\b\2DH\5\20\t"+
		"\2EH\5\22\n\2FH\5\24\13\2GB\3\2\2\2GC\3\2\2\2GD\3\2\2\2GE\3\2\2\2GF\3"+
		"\2\2\2H\13\3\2\2\2IJ\7\b\2\2JK\7\17\2\2KL\7\35\2\2LM\b\7\1\2MN\b\7\1\2"+
		"NO\7\20\2\2O\r\3\2\2\2PQ\7\t\2\2QV\7\17\2\2RW\7\37\2\2ST\7\35\2\2TU\b"+
		"\b\1\2UW\b\b\1\2VR\3\2\2\2VS\3\2\2\2WX\3\2\2\2XY\7\20\2\2Y\17\3\2\2\2"+
		"Z[\7\35\2\2[\\\b\t\1\2\\]\7\34\2\2]^\5\26\f\2^\21\3\2\2\2_`\7\n\2\2`a"+
		"\7\17\2\2ab\5\26\f\2bc\7\33\2\2cd\5\26\f\2de\7\20\2\2ef\7\13\2\2fh\7\21"+
		"\2\2gi\5\n\6\2hg\3\2\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2kl\3\2\2\2lv\7\22"+
		"\2\2mn\7\f\2\2np\7\21\2\2oq\5\n\6\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3"+
		"\2\2\2st\3\2\2\2tu\7\22\2\2uw\3\2\2\2vm\3\2\2\2vw\3\2\2\2w\23\3\2\2\2"+
		"xy\7\r\2\2y{\7\21\2\2z|\5\n\6\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2"+
		"\2~\177\3\2\2\2\177\u0080\7\22\2\2\u0080\u0081\7\16\2\2\u0081\u0082\7"+
		"\17\2\2\u0082\u0083\5\26\f\2\u0083\u0084\7\33\2\2\u0084\u0085\5\26\f\2"+
		"\u0085\u0086\7\20\2\2\u0086\u0096\3\2\2\2\u0087\u0088\7\16\2\2\u0088\u0089"+
		"\7\17\2\2\u0089\u008a\5\26\f\2\u008a\u008b\7\33\2\2\u008b\u008c\5\26\f"+
		"\2\u008c\u008d\7\20\2\2\u008d\u008f\7\21\2\2\u008e\u0090\5\n\6\2\u008f"+
		"\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0094\7\22\2\2\u0094\u0096\3\2\2\2\u0095"+
		"x\3\2\2\2\u0095\u0087\3\2\2\2\u0096\25\3\2\2\2\u0097\u00a0\5\30\r\2\u0098"+
		"\u009c\7\27\2\2\u0099\u009a\7\30\2\2\u009a\u009c\b\f\1\2\u009b\u0098\3"+
		"\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\5\30\r\2\u009e"+
		"\u009b\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\27\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00ac\5\32\16\2\u00a4"+
		"\u00a8\7\31\2\2\u00a5\u00a6\7\32\2\2\u00a6\u00a8\b\r\1\2\u00a7\u00a4\3"+
		"\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\5\32\16\2\u00aa"+
		"\u00a7\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2"+
		"\2\2\u00ad\31\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b7\7\36\2\2\u00b0\u00b1"+
		"\7\35\2\2\u00b1\u00b7\b\16\1\2\u00b2\u00b3\7\17\2\2\u00b3\u00b4\5\26\f"+
		"\2\u00b4\u00b5\7\22\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00af\3\2\2\2\u00b6"+
		"\u00b0\3\2\2\2\u00b6\u00b2\3\2\2\2\u00b7\33\3\2\2\2\23 \60:@GVjrv}\u0091"+
		"\u0095\u009b\u00a0\u00a7\u00ac\u00b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}