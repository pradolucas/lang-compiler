grammar GrammarExpression;

@header {
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
}

@members {
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
}

prog
:
	'programa'
	(
		declara
	)* bloco
	{checkUnused();}

	'fimprog.'
	{
		program.setVarTable(symbolTable);
		program.setComandos(stack.pop());	
	}

;

declara
:
	'declare' tipo ID
	{addId();}

	(
		C ID
		{addId();}

	)* SC
;

bloco
:
	{commandStack();}

	(
		cmd
	)+
;

tipo
:
	'NUMERO'
	{ _currentType = DataType.NUM; }

	| 'STRING'
	{ _currentType = DataType.STRING; }

;

cmd
:
	(
		cmdleitura
		| cmdescrita
		| cmdexpr
		| cmdif
		| cmdwhile
	) SC
	{newExpr();}

;

cmdleitura
:
	'leia' AP ID
	{
		checkDeclared();
		markVarInitialized();	
		leitura();
	}

	FP
	{commandLeitura();}

;

cmdescrita
:
	'escreva' AP
	(
		expr
	)
	{
		
		commandEscrita(_exprContent);
		newExpr();
	}

	FP
;

cmdexpr
:
	ID
	{
		checkDeclared();
		exprAtribuicao();
		markVarInitialized();
	}

	ATTR
	{newExpr();}

	expr
	{	
		commandAtribuicao();
		attrExprToId(_exprID, _exprContent);
		newExpr();
	}

;

cmdif
:
	'se' AP expr
	{
		exprDecision(_exprContent);
	} // N está avaliando caso expr maior que um termo
	OP_REL
	{
		exprDecisionAcum(lastToken());
		
	}

	expr
	{
		exprDecisionAcum(_exprContent);
	}

	FP 'entao' AC
	{commandStack();}

	(
		cmd
	)+ FC
	{listaTrueDecision();}

	(
		'senao' AC
		{commandStack();}

		(
			cmd
		)+ FC
		{listaFalseDecision();}

	)?
;

cmdwhile
:
	'do' AC
	{commandStack();}

	(
		cmd
	)+ FC //{listaRepeticao();}

	//{commandStack();}
	'while' AP expr
	{
		exprRepeticao(_exprContent);		
	}

	OP_REL
	{exprRepeticaoAcum(lastToken());}

	expr
	{
		exprRepeticaoAcum(_exprContent);
	}
//{commandStack();}
	{listaRepeticao("DoWhile");}

	FP
	| 'while' AP expr
	{	
		exprRepeticao(_exprContent);
	}

	OP_REL
	{exprRepeticaoAcum(lastToken());}

	expr
	{exprRepeticaoAcum(_exprContent);}

	FP AC
	{commandStack();}

	(
		cmd
	)+ FC
	{listaRepeticao("While");}

;

expr
:
	termo
	(
		(
			OP_SUM
			| OP_SUB
		)
		{inputTermo();}

		termo
	)*
;

termo
:
	fator
	(
		(
			OP_MULT
			| OP_DIV
		)
		{inputTermo();}

		fator
	)*
;

fator
:
	NUMBER
	{inputTermo();}

	| ID
	{
		checkDeclared(); 
	 	checkInitialized();
	 	markVarUsed();
	 	inputTermo();
	}

	| AP
	{inputTermo();}

	expr FP
	{inputTermo();}

	| TEXTO
	{inputTermo();}

;

//---------LEXICO---------

AP
:
	'('
;

FP
:
	')'
;

AC
:
	'{'
;

FC
:
	'}'
;

SC
:
	';'
;

C
:
	','
;

P
:
	'.'
;

OP_SUM
:
	'+'
;

OP_SUB
:
	'-'
;

OP_MULT
:
	'*'
;

OP_DIV
:
	'/'
;

OP_REL
:
	'<'
	| '>'
	| '<='
	| '>='
	| '!='
	| '=='
;

ATTR
:
	':='
;

ID
:
	[a-z]+
	(
		[a-z]
		| [A-Z]
		| [0-9]
	)*
;

NUMBER
:
	[0-9]+
	(
		'.' [0-9]+
	)?
;

TEXTO
:
	'"'
	(
		~['"']
	)* '"'
;

WS
:
	(
		' '
		| '\t'
		| '\n'
		| '\r'
	) -> skip
;