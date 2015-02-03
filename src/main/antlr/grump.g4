grammar Grump;

grump : sketchHeader? sketchBody? EOF;
    
sketchHeader : SKETCH SEMI;
sketchBody : sketchLine+;
sketchLine
    : constLineNumeric
    | declareLine
    | constraintLineNumeric
    ;

constLineNumeric
    : CONST Symbol ASSIGN numericExpression SEMI
    ;
declareLine
    : DECLARE Symbol SEMI
    ;
constraintLineNumeric
    : numericExpression EQUALS numericExpression SEMI
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
    ;

SKETCH          : 'sketch';
CONST           : 'const';
DECLARE         : 'declare';

SQUARE          : 'square';
SQRT            : 'sqrt';

PI              : 'PI';
GOLDEN_RATIO    : 'GOLDEN_RATIO';

Symbol : LETTER LETTER_OR_NUMBER* ;

fragment
LETTER          : [a-zA-Z$_];

fragment
LETTER_OR_NUMBER : [a-zA-Z0-9$_];

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

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

LINE_COMMENT
    :   '#' ~[\r\n]* -> skip
    ;