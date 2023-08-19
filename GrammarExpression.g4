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
		stack.peek().add(cmd);
	}

	public void escrita(){
		_writeID = lastToken();
		CommandEscrita cmd = new CommandEscrita(_writeID);
		stack.peek().add(cmd);
	}

	public void exprAtribuicao(){
		_exprID = lastToken();
		_exprContent = "";
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
	
}

prog
:
	'programa'
	(
		declara
	)+ bloco
	{checkUnused();}

	'fimprog.'
	{
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

	)* P
;

bloco
:
	{commandStack();}
	(
		cmd P
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
	cmdleitura
	| cmdescrita
	| cmdexpr
	| cmdif
	| cmdwhile
;

cmdleitura
:
	'leia' AP ID
	{checkIdExists();}
	{leitura();}

	FP
;

cmdescrita
:
	'escreva' AP
	(
		TEXTO
		| ID
		{checkIdExists();}
		{escrita();}

	) FP
;

cmdexpr
:
	ID
	{checkIdExists();
		idAttr = lastToken();
		exprAtribuicao();
	}

	ATTR expr
//	{attrExprToId(idAttr, );}

;

cmdif
:
	'se' AP
	expr {exprDecision();}
	OP_REL {exprDecisionAcum();}
	expr {exprDecisionAcum();}
	FP 

	
	'entao' AC
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
	(
		cmd
	)+ FC 'while' AP expr OP_REL expr FP
	| 'while' AP expr OP_REL expr FP AC
	(
		cmd
	)+ FC
;

expr
:
	termo
	(
		(
			OP_SUM
			| OP_SUB
			{inputTermo();}
		) termo
	)*
;

termo
:
	fator
	(
		(
			OP_MULT
			| OP_DIV
			{inputTermo();}
		) fator
	)*
;

fator
:
	NUMBER
	| ID
	{checkIdExists(); checkInitialized(); inputTermo();}

	| AP expr FC
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

DBQ
:
	'"'
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

//OP
//:
//	OP_S
//	| OP_M
//;

//OP_S
//:
//	'+'
//	| '-'
//;

//OP_M
//:
//	'*'
//	| '/'
//;

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
	DBQ
	(
		[0-9]
		| [a-z]
		| [A-Z]
		| ' '
	)+ DBQ
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