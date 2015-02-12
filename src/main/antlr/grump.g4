grammar Grump;

//statement containers
grump : topLevelStatements? EOF;

defineBlock
    : LBRACE statements? RBRACE
    ;

constructor
    : CONSTRUCTOR parameters? LBRACE constructorStatments? RBRACE
    ;

//collections of statements
topLevelStatements
    : topLevelStatement+
    ;

statements
    : statement+
    ;
    
constructorStatments
    : constructorStatement+
    ;

//individual statements
statement
    : constructor
    | topLevelStatement
    ;
    
topLevelStatement 
    : defineStatement
    | parameterStatement
    | sketchStatement
    | constraintStatement
    ;

constructorStatement
    : parameterStatement
    | sketchStatement
    | constraintStatement
    ;

defineStatement
    : DEFINE Symbol EXTENDS defineType defineBlock? SEMI
    ;

constraintStatement 
    : CONSTRAINT constraintAssignment ( COMMA constraintAssignment )*  SEMI
    ;

sketchStatement
    : sketchType sketchAssignment ( COMMA sketchAssignment )* SEMI
    ;    
    
parameterStatement
    : CONSTANT? PARAMETER parameterAssignment ( COMMA parameterAssignment )* SEMI
    ;    

//assignments
   
constraintAssignment
    : (Symbol ASSIGN)? anonymousConstraint
    ;
    
anonymousConstraint
    : constraintExpression EQUALS constraintExpression
    ;

sketchAssignment
    : Symbol (ASSIGN Symbol arguments? defineBlock?)?
    ;
    
parameterAssignment
    : Symbol (ASSIGN expression)?
    ;
  
//collections of expressions
arguments
    : LPAREN expressions ? RPAREN
    ;

expressions
    : expression ( COMMA expression )*
    ;

//individual expressions
constraintExpression
    : expression
    | DISTANCE LPAREN expression COMMA expression RPAREN
    ;

expression
    : constant
    | reference
    | LPAREN expression RPAREN
    | expression ( ADD | SUB ) expression
    | expression ( MUL | DIV ) expression
    | ( SQUARE | SQRT | SIN | COS | ASIN | ACOS ) LPAREN expression RPAREN
    ;

//parameters
parameters 
    : LPAREN ( parameter ( COMMA parameter)* )? RPAREN
    ;

parameter
    : parameterType Symbol
    ;

//types
defineType
    : Symbol
    | builtin
    ; 

parameterType
    : PARAMETER
    | sketchType
    ;

sketchType
    : Symbol
    | builtin
    ;

builtin
    : SKETCH
    | POINT
    ;
    
constant
    : IntegerLiteral
    | FloatingPointLiteral
    | PI
    | GOLDEN_RATIO
    ;

reference
    : Symbol ( DOT Symbol )*
    ;

symbols
    : Symbol ( COMMA Symbol )*
    ;
    


SKETCH          : 'Sketch';
POINT           : 'Point';

DISTANCE        : 'distance';

CONSTANT        : 'constant';
CONSTRAINT      : 'constraint';
CONSTRUCTOR     : 'constructor';
DECLARE         : 'declare';
DEFINE          : 'define';
DISPLAY         : 'display';
PARAMETER       : 'parameter';

EQUATION        : 'equation';
EXPRESSION      : 'expression';
EXTENDS         : 'extends';

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