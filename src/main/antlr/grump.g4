grammar Grump;

//statement containers
grump : topLevelStatements EOF;


//collections of statements
topLevelStatements
    : topLevelStatement*
    ;

topLevelStatement 
    : parameterStatement
    | constraintStatement
    ;

parameterStatement
    : FLOAT parameterAssignment ( COMMA parameterAssignment )* SEMI
    ;

constraintStatement
    : CONSTRAINT constraint ( COMMA constraint )*  SEMI
    ;
    
//assignments
parameterAssignment
    : Symbol (EQUALS (expression | domain))?
    ;
    
constraint
    : expression ( EQUALS expression )+
    ;

//individual expressions
domain
    : domainInterval defaultValue?
    | defaultValue
    ;
    
domainInterval
    : DOMAIN (LPAREN | LBRACK) minExpression COMMA maxExpression (RPAREN | RBRACK)
    ;
    
defaultValue
    : DEFAULT expression
    ;

minExpression
    : expression
    ;

maxExpression
    : expression
    ;

expressions
    : expression ( COMMA expression )*
    ;

expression
    : expressionTerm
    | expression (MUL | DIV) expression
    | expression (ADD | SUB) expression
    | negationExpression
    | functionInvocation
    ;

expressionTerm
    : constant
    | reference
    | enclosedExpression
    ;
    
enclosedExpression
    : LPAREN expression RPAREN
    ;

negationExpression
    : SUB expressionTerm
    ;
    
functionInvocation
    : Symbol arguments
    ;

arguments
    : LPAREN expressions ? RPAREN
    ;

//parameters
//parameters
//    : LPAREN ( parameter ( COMMA parameter)* )? RPAREN
//    ;

//parameter
//    : parameterType Symbol
//    ;

//types
//defineType
//    : Symbol
//    | builtin
//    ;

//parameterType
//    : PARAMETER
//    | sketchType
//    ;

//sketchType
//    : Symbol
//    | builtin
//    ;

//builtin
//    : SKETCH
//    | POINT
//    ;
    
constant
    : IntegerLiteral
    | FloatingPointLiteral
    | PI
    | GOLDEN_RATIO
    | MAX_DOUBLE
    | MIN_DOUBLE
    ;

//symbols
//    : Symbol ( COMMA Symbol )*
//    ;
    

FLOAT           : 'float';
INT             : 'int';

//DISTANCE        : 'distance';

CONSTRAINT      : 'constraint';
//CONSTRUCTOR     : 'constructor';
DEFAULT         : 'default';
//DECLARE         : 'declare';
//DEFINE          : 'define';
//DISPLAY         : 'display';
DOMAIN          : 'domain';
//PARAMETER       : 'parameter';

//EQUATION        : 'equation';
//EXPRESSION      : 'expression';
//EXTENDS         : 'extends';

//SQUARE          : 'square';
//SQRT            : 'sqrt';
//SIN             : 'sin';
//COS             : 'cos';
//ASIN            : 'asin';
//ACOS            : 'acos';

MAX_DOUBLE      : 'MAX_DOUBLE';
MIN_DOUBLE      : 'MIN_DOUBLE';
PI              : 'PI';
GOLDEN_RATIO    : 'GOLDEN_RATIO';
    
reference
    : Symbol // ( DOT Symbol )*
    ;

//Integer Literals

IntegerLiteral
	:	DecimalIntegerLiteral
	|	HexIntegerLiteral
	|	OctalIntegerLiteral
	|	BinaryIntegerLiteral
	;

fragment
DecimalIntegerLiteral
	:	DecimalNumeral IntegerTypeSuffix?
	;

fragment
HexIntegerLiteral
	:	HexNumeral IntegerTypeSuffix?
	;

fragment
OctalIntegerLiteral
	:	OctalNumeral IntegerTypeSuffix?
	;

fragment
BinaryIntegerLiteral
	:	BinaryNumeral IntegerTypeSuffix?
	;

fragment
IntegerTypeSuffix
	:	[lL]
	;

fragment
DecimalNumeral
	:	'0'
	|	NonZeroDigit (Digits? | Underscores Digits)
	;

fragment
Digits
	:	Digit (DigitsAndUnderscores? Digit)?
	;

fragment
Digit
	:	'0'
	|	NonZeroDigit
	;

fragment
NonZeroDigit
	:	[1-9]
	;

fragment
DigitsAndUnderscores
	:	DigitOrUnderscore+
	;

fragment
DigitOrUnderscore
	:	Digit
	|	'_'
	;

fragment
Underscores
	:	'_'+
	;

fragment
HexNumeral
	:	'0' [xX] HexDigits
	;

fragment
HexDigits
	:	HexDigit (HexDigitsAndUnderscores? HexDigit)?
	;

fragment
HexDigit
	:	[0-9a-fA-F]
	;

fragment
HexDigitsAndUnderscores
	:	HexDigitOrUnderscore+
	;

fragment
HexDigitOrUnderscore
	:	HexDigit
	|	'_'
	;

fragment
OctalNumeral
	:	'0' Underscores? OctalDigits
	;

fragment
OctalDigits
	:	OctalDigit (OctalDigitsAndUnderscores? OctalDigit)?
	;

fragment
OctalDigit
	:	[0-7]
	;

fragment
OctalDigitsAndUnderscores
	:	OctalDigitOrUnderscore+
	;

fragment
OctalDigitOrUnderscore
	:	OctalDigit
	|	'_'
	;

fragment
BinaryNumeral
	:	'0' [bB] BinaryDigits
	;

fragment
BinaryDigits
	:	BinaryDigit (BinaryDigitsAndUnderscores? BinaryDigit)?
	;

fragment
BinaryDigit
	:	[01]
	;

fragment
BinaryDigitsAndUnderscores
	:	BinaryDigitOrUnderscore+
	;

fragment
BinaryDigitOrUnderscore
	:	BinaryDigit
	|	'_'
	;

//Floating-Point Literals

FloatingPointLiteral
	:	DecimalFloatingPointLiteral
	|	HexadecimalFloatingPointLiteral
	;

fragment
DecimalFloatingPointLiteral
	:	Digits '.' Digits? ExponentPart? FloatTypeSuffix?
	|	'.' Digits ExponentPart? FloatTypeSuffix?
	|	Digits ExponentPart FloatTypeSuffix?
	|	Digits FloatTypeSuffix
	;

fragment
ExponentPart
	:	ExponentIndicator SignedInteger
	;

fragment
ExponentIndicator
	:	[eE]
	;

fragment
SignedInteger
	:	Sign? Digits
	;

fragment
Sign
	:	[+-]
	;

fragment
FloatTypeSuffix
	:	[fFdD]
	;

fragment
HexadecimalFloatingPointLiteral
	:	HexSignificand BinaryExponent FloatTypeSuffix?
	;

fragment
HexSignificand
	:	HexNumeral '.'?
	|	'0' [xX] HexDigits? '.' HexDigits
	;

fragment
BinaryExponent
	:	BinaryExponentIndicator SignedInteger
	;

fragment
BinaryExponentIndicator
	:	[pP]
	;

LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';

EQUALS          : '=';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';

Symbol : LETTER LETTER_OR_NUMBER* ;

fragment
LETTER          : [a-zA-Z$_];

fragment
LETTER_OR_NUMBER : [a-zA-Z0-9$_];

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

LINE_COMMENT
    :   '#' ~[\r\n]* -> skip
    ;