grammar Grump;

grump : sketchHeader? sketchBody? EOF;
    
sketchHeader : SKETCH SEMI;
sketchBody : sketchLine+;
sketchLine
    : constLine SEMI
    | declareLine SEMI
    | constraintLineNumeric SEMI
    ;

constLine
    : CONST constAssignment ( COMMA constAssignment ) * 
    ;
constAssignment
    : Symbol ASSIGN numericExpression
    | Symbol ASSIGN pointExpression
    ;
declareLine
    : DECLARE Symbol ( COMMA Symbol )*
    ;
constraintLineNumeric
    : numericExpression EQUALS numericExpression
    ;

pointExpression
    : pointLiteral
    | Symbol
    | LPAREN pointExpression RPAREN
    | pointExpression ( ADD | SUB ) 
    | pointExpression ( MUL | DIV ) numericExpression
    | numericExpression ( MUL | DIV ) pointExpression
    ;
    
pointLiteral
    : LBRACK numericExpression COMMA numericExpression RBRACK
    ;

numericExpression
    : numericConstant
    | Symbol
    | LPAREN numericExpression RPAREN
//    | '-' NUMERIC_CONSTANT_EXPRESSION
    | numericExpression ( ADD | SUB ) numericExpression
    | numericExpression ( MUL | DIV ) numericExpression
    | ( SQUARE | SQRT ) LPAREN numericExpression RPAREN
    ;
    
numericConstant
    : PI
    | GOLDEN_RATIO
    | IntegerLiteral
    | FloatingPointLiteral
    ;

SKETCH          : 'sketch';
CONST           : 'const';
DECLARE         : 'declare';

SQUARE          : 'square';
SQRT            : 'sqrt';

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