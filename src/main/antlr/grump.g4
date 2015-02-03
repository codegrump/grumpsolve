grammar Grump;

literal
    : IntegerLiteral
    | FloatingPointLiteral
    ;

grump : statements? /* display? */ EOF;

statements
    : statement+
    ;

statement 
    : defineStatement
    | parameterStatement
    | constraintStatement
    | sketchStatement
    ;
    
defineStatement
    : DEFINE Symbol EXTENDS ( Symbol | SKETCH ) parameters? defineBlock? SEMI
    ;
    
defineBlock
    : LBRACE statements? RBRACE
    ;

parameterStatement
    : CONSTANT? PARAMETER parameterAssignment ( COMMA parameterAssignment )* SEMI
    ;
    
parameterAssignment
    : Symbol (ASSIGN expression)?
    ;
   
constraintStatement 
    : CONSTRAINT (Symbol ASSIGN)? anonymousConstraint  SEMI
    ;
    
anonymousConstraint
    : constraintExpression EQUALS constraintExpression
    ;

constraintExpression
    : expression
    ;
    
sketchStatement
    : SKETCH sketchAssignment SEMI
    ;
    
sketchAssignment
    : Symbol ASSIGN Symbol arguments defineBlock?
    ;

expressions
    : expression ( COMMA expression )*
    ;

expression
    : literal
    | reference
    | LPAREN expression RPAREN
    | expression ( ADD | SUB ) expression
    | expression ( MUL | DIV ) expression
    | ( SQUARE | SQRT | SIN | COS | ASIN | ACOS ) LPAREN expression RPAREN
    ;

parameters 
    : LPAREN symbols? RPAREN
    ;
    
arguments
    : LPAREN expressions ? RPAREN
    ;
    
//pointLiteral
//    : LBRACK numericExpression COMMA numericExpression RBRACK
//    ;

    
numericConstant
    : PI
    | GOLDEN_RATIO
    | IntegerLiteral
    | FloatingPointLiteral
    ;
    
//display
//    : DISPLAY symbols? SEMI
//    ;

reference
    : Symbol ( DOT Symbol )*
    ;


symbols
    : Symbol ( COMMA Symbol )*
    ;

CONSTANT        : 'constant';
CONSTRAINT      : 'constraint';
DECLARE         : 'declare';
DEFINE          : 'define';
DISPLAY         : 'display';
PARAMETER       : 'parameter';
POINT          : 'point';
EQUATION        : 'equation';
EXPRESSION      : 'expression';
EXTENDS         : 'extends';
SKETCH          : 'sketch';

SQUARE          : 'square';
SQRT            : 'sqrt';
SIN             : 'sin';
COS             : 'cos';
ASIN            : 'asin';
ACOS            : 'acos';

PI              : 'PI';
GOLDEN_RATIO    : 'GOLDEN_RATIO';
    

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
ASSIGN          : ':=';
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