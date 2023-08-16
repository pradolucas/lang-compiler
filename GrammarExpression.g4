grammar GrammarExpression;

@header {
	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import exceptions.SemanticException;
}

@members {
	private SymbolTable symbolTable = new SymbolTable();
	private DataType _currentType;
	private String idAttr;
	
	
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
	
	
	
}

prog
:
	'programa'
	(
		declara
	)+ bloco
	{checkUnused();}

	'fimprog.'
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

	FP
;

cmdescrita
:
	'escreva' AP
	(
		TEXTO
		| ID
		{checkIdExists();}

	) FP
;

cmdexpr
:
	ID
	{checkIdExists();
		idAttr = lastToken();
	}

	ATTR expr
//	{attrExprToId(idAttr, );}

;

cmdif
:
	'se' AP expr OP_REL expr FP 'entao' AC
	(
		cmd
	)+ FC
	(
		'senao' AC
		(
			cmd
		)+ FC
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
		) fator
	)*
;

fator
:
	NUMBER
	| ID
	{checkIdExists(); checkInitialized();}

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