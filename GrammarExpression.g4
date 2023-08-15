grammar GrammarExpression;

prog
:
	'programa' bloco 'fimprog.'
;

declara
:
	'declare' ID
	(
		C ID
	)* P
;

bloco
:
	(
		cmd P
	)+
;

cmd
:
	cmdleitura
	| cmdescrita
	| cmdexpr
	| cmdif
;

cmdleitura
:
	'leia' AP ID FP
;

cmdescrita
:
	'escreva' AP
	(
		TEXTO
		| ID
	) FP
;

cmdexpr
:
	ID ATTR expr
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

expr
:
	termo
	(
		(OP_SUM|OP_SUB) termo
	)*
;

termo
:
	fator
	(
		(OP_MULT|OP_DIV) fator
	)*
;

fator
:
	NUMBER
	| ID
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

OP
:
	OP_S
	| OP_M
;

OP_S
:
	'+'
	| '-'
;

OP_M
:
	'*'
	| '/'
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