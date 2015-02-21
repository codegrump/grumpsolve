package grump;
// Generated from Grump.g4 by ANTLR 4.5
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrumpLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FLOAT=1, INT=2, CONSTRAINT=3, DEFAULT=4, DOMAIN=5, MAX_DOUBLE=6, MIN_DOUBLE=7, 
		PI=8, GOLDEN_RATIO=9, IntegerLiteral=10, FloatingPointLiteral=11, LPAREN=12, 
		RPAREN=13, LBRACE=14, RBRACE=15, LBRACK=16, RBRACK=17, SEMI=18, COMMA=19, 
		DOT=20, EQUALS=21, ADD=22, SUB=23, MUL=24, DIV=25, Symbol=26, WS=27, LINE_COMMENT=28;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"FLOAT", "INT", "CONSTRAINT", "DEFAULT", "DOMAIN", "MAX_DOUBLE", "MIN_DOUBLE", 
		"PI", "GOLDEN_RATIO", "IntegerLiteral", "DecimalIntegerLiteral", "HexIntegerLiteral", 
		"OctalIntegerLiteral", "BinaryIntegerLiteral", "IntegerTypeSuffix", "DecimalNumeral", 
		"Digits", "Digit", "NonZeroDigit", "DigitsAndUnderscores", "DigitOrUnderscore", 
		"Underscores", "HexNumeral", "HexDigits", "HexDigit", "HexDigitsAndUnderscores", 
		"HexDigitOrUnderscore", "OctalNumeral", "OctalDigits", "OctalDigit", "OctalDigitsAndUnderscores", 
		"OctalDigitOrUnderscore", "BinaryNumeral", "BinaryDigits", "BinaryDigit", 
		"BinaryDigitsAndUnderscores", "BinaryDigitOrUnderscore", "FloatingPointLiteral", 
		"DecimalFloatingPointLiteral", "ExponentPart", "ExponentIndicator", "SignedInteger", 
		"Sign", "FloatTypeSuffix", "HexadecimalFloatingPointLiteral", "HexSignificand", 
		"BinaryExponent", "BinaryExponentIndicator", "LPAREN", "RPAREN", "LBRACE", 
		"RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", "EQUALS", "ADD", 
		"SUB", "MUL", "DIV", "Symbol", "LETTER", "LETTER_OR_NUMBER", "WS", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'float'", "'int'", "'constraint'", "'default'", "'domain'", "'MAX_DOUBLE'", 
		"'MIN_DOUBLE'", "'PI'", "'GOLDEN_RATIO'", null, null, "'('", "')'", "'{'", 
		"'}'", "'['", "']'", "';'", "','", "'.'", "'='", "'+'", "'-'", "'*'", 
		"'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FLOAT", "INT", "CONSTRAINT", "DEFAULT", "DOMAIN", "MAX_DOUBLE", 
		"MIN_DOUBLE", "PI", "GOLDEN_RATIO", "IntegerLiteral", "FloatingPointLiteral", 
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", 
		"DOT", "EQUALS", "ADD", "SUB", "MUL", "DIV", "Symbol", "WS", "LINE_COMMENT"
	};
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


	public GrumpLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grump.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\36\u01d3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\5\13\u00d8\n\13\3\f\3\f\5\f\u00dc\n\f\3\r\3\r\5\r\u00e0\n\r"+
		"\3\16\3\16\5\16\u00e4\n\16\3\17\3\17\5\17\u00e8\n\17\3\20\3\20\3\21\3"+
		"\21\3\21\5\21\u00ef\n\21\3\21\3\21\3\21\5\21\u00f4\n\21\5\21\u00f6\n\21"+
		"\3\22\3\22\5\22\u00fa\n\22\3\22\5\22\u00fd\n\22\3\23\3\23\5\23\u0101\n"+
		"\23\3\24\3\24\3\25\6\25\u0106\n\25\r\25\16\25\u0107\3\26\3\26\5\26\u010c"+
		"\n\26\3\27\6\27\u010f\n\27\r\27\16\27\u0110\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\5\31\u0119\n\31\3\31\5\31\u011c\n\31\3\32\3\32\3\33\6\33\u0121\n\33"+
		"\r\33\16\33\u0122\3\34\3\34\5\34\u0127\n\34\3\35\3\35\5\35\u012b\n\35"+
		"\3\35\3\35\3\36\3\36\5\36\u0131\n\36\3\36\5\36\u0134\n\36\3\37\3\37\3"+
		" \6 \u0139\n \r \16 \u013a\3!\3!\5!\u013f\n!\3\"\3\"\3\"\3\"\3#\3#\5#"+
		"\u0147\n#\3#\5#\u014a\n#\3$\3$\3%\6%\u014f\n%\r%\16%\u0150\3&\3&\5&\u0155"+
		"\n&\3\'\3\'\5\'\u0159\n\'\3(\3(\3(\5(\u015e\n(\3(\5(\u0161\n(\3(\5(\u0164"+
		"\n(\3(\3(\3(\5(\u0169\n(\3(\5(\u016c\n(\3(\3(\3(\5(\u0171\n(\3(\3(\3("+
		"\5(\u0176\n(\3)\3)\3)\3*\3*\3+\5+\u017e\n+\3+\3+\3,\3,\3-\3-\3.\3.\3."+
		"\5.\u0189\n.\3/\3/\5/\u018d\n/\3/\3/\3/\5/\u0192\n/\3/\3/\5/\u0196\n/"+
		"\3\60\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66"+
		"\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@"+
		"\7@\u01bb\n@\f@\16@\u01be\13@\3A\3A\3B\3B\3C\6C\u01c5\nC\rC\16C\u01c6"+
		"\3C\3C\3D\3D\7D\u01cd\nD\fD\16D\u01d0\13D\3D\3D\2\2E\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\2\31\2\33\2\35\2\37\2!\2#\2%\2\'\2)\2+"+
		"\2-\2/\2\61\2\63\2\65\2\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\rO\2Q\2S\2"+
		"U\2W\2Y\2[\2]\2_\2a\2c\16e\17g\20i\21k\22m\23o\24q\25s\26u\27w\30y\31"+
		"{\32}\33\177\34\u0081\2\u0083\2\u0085\35\u0087\36\3\2\21\4\2NNnn\3\2\63"+
		";\4\2ZZzz\5\2\62;CHch\3\2\629\4\2DDdd\3\2\62\63\4\2GGgg\4\2--//\6\2FF"+
		"HHffhh\4\2RRrr\6\2&&C\\aac|\7\2&&\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f"+
		"\f\17\17\u01da\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2M\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m"+
		"\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2"+
		"\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2"+
		"\2\3\u0089\3\2\2\2\5\u008f\3\2\2\2\7\u0093\3\2\2\2\t\u009e\3\2\2\2\13"+
		"\u00a6\3\2\2\2\r\u00ad\3\2\2\2\17\u00b8\3\2\2\2\21\u00c3\3\2\2\2\23\u00c6"+
		"\3\2\2\2\25\u00d7\3\2\2\2\27\u00d9\3\2\2\2\31\u00dd\3\2\2\2\33\u00e1\3"+
		"\2\2\2\35\u00e5\3\2\2\2\37\u00e9\3\2\2\2!\u00f5\3\2\2\2#\u00f7\3\2\2\2"+
		"%\u0100\3\2\2\2\'\u0102\3\2\2\2)\u0105\3\2\2\2+\u010b\3\2\2\2-\u010e\3"+
		"\2\2\2/\u0112\3\2\2\2\61\u0116\3\2\2\2\63\u011d\3\2\2\2\65\u0120\3\2\2"+
		"\2\67\u0126\3\2\2\29\u0128\3\2\2\2;\u012e\3\2\2\2=\u0135\3\2\2\2?\u0138"+
		"\3\2\2\2A\u013e\3\2\2\2C\u0140\3\2\2\2E\u0144\3\2\2\2G\u014b\3\2\2\2I"+
		"\u014e\3\2\2\2K\u0154\3\2\2\2M\u0158\3\2\2\2O\u0175\3\2\2\2Q\u0177\3\2"+
		"\2\2S\u017a\3\2\2\2U\u017d\3\2\2\2W\u0181\3\2\2\2Y\u0183\3\2\2\2[\u0185"+
		"\3\2\2\2]\u0195\3\2\2\2_\u0197\3\2\2\2a\u019a\3\2\2\2c\u019c\3\2\2\2e"+
		"\u019e\3\2\2\2g\u01a0\3\2\2\2i\u01a2\3\2\2\2k\u01a4\3\2\2\2m\u01a6\3\2"+
		"\2\2o\u01a8\3\2\2\2q\u01aa\3\2\2\2s\u01ac\3\2\2\2u\u01ae\3\2\2\2w\u01b0"+
		"\3\2\2\2y\u01b2\3\2\2\2{\u01b4\3\2\2\2}\u01b6\3\2\2\2\177\u01b8\3\2\2"+
		"\2\u0081\u01bf\3\2\2\2\u0083\u01c1\3\2\2\2\u0085\u01c4\3\2\2\2\u0087\u01ca"+
		"\3\2\2\2\u0089\u008a\7h\2\2\u008a\u008b\7n\2\2\u008b\u008c\7q\2\2\u008c"+
		"\u008d\7c\2\2\u008d\u008e\7v\2\2\u008e\4\3\2\2\2\u008f\u0090\7k\2\2\u0090"+
		"\u0091\7p\2\2\u0091\u0092\7v\2\2\u0092\6\3\2\2\2\u0093\u0094\7e\2\2\u0094"+
		"\u0095\7q\2\2\u0095\u0096\7p\2\2\u0096\u0097\7u\2\2\u0097\u0098\7v\2\2"+
		"\u0098\u0099\7t\2\2\u0099\u009a\7c\2\2\u009a\u009b\7k\2\2\u009b\u009c"+
		"\7p\2\2\u009c\u009d\7v\2\2\u009d\b\3\2\2\2\u009e\u009f\7f\2\2\u009f\u00a0"+
		"\7g\2\2\u00a0\u00a1\7h\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3\7w\2\2\u00a3"+
		"\u00a4\7n\2\2\u00a4\u00a5\7v\2\2\u00a5\n\3\2\2\2\u00a6\u00a7\7f\2\2\u00a7"+
		"\u00a8\7q\2\2\u00a8\u00a9\7o\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7k\2\2"+
		"\u00ab\u00ac\7p\2\2\u00ac\f\3\2\2\2\u00ad\u00ae\7O\2\2\u00ae\u00af\7C"+
		"\2\2\u00af\u00b0\7Z\2\2\u00b0\u00b1\7a\2\2\u00b1\u00b2\7F\2\2\u00b2\u00b3"+
		"\7Q\2\2\u00b3\u00b4\7W\2\2\u00b4\u00b5\7D\2\2\u00b5\u00b6\7N\2\2\u00b6"+
		"\u00b7\7G\2\2\u00b7\16\3\2\2\2\u00b8\u00b9\7O\2\2\u00b9\u00ba\7K\2\2\u00ba"+
		"\u00bb\7P\2\2\u00bb\u00bc\7a\2\2\u00bc\u00bd\7F\2\2\u00bd\u00be\7Q\2\2"+
		"\u00be\u00bf\7W\2\2\u00bf\u00c0\7D\2\2\u00c0\u00c1\7N\2\2\u00c1\u00c2"+
		"\7G\2\2\u00c2\20\3\2\2\2\u00c3\u00c4\7R\2\2\u00c4\u00c5\7K\2\2\u00c5\22"+
		"\3\2\2\2\u00c6\u00c7\7I\2\2\u00c7\u00c8\7Q\2\2\u00c8\u00c9\7N\2\2\u00c9"+
		"\u00ca\7F\2\2\u00ca\u00cb\7G\2\2\u00cb\u00cc\7P\2\2\u00cc\u00cd\7a\2\2"+
		"\u00cd\u00ce\7T\2\2\u00ce\u00cf\7C\2\2\u00cf\u00d0\7V\2\2\u00d0\u00d1"+
		"\7K\2\2\u00d1\u00d2\7Q\2\2\u00d2\24\3\2\2\2\u00d3\u00d8\5\27\f\2\u00d4"+
		"\u00d8\5\31\r\2\u00d5\u00d8\5\33\16\2\u00d6\u00d8\5\35\17\2\u00d7\u00d3"+
		"\3\2\2\2\u00d7\u00d4\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8"+
		"\26\3\2\2\2\u00d9\u00db\5!\21\2\u00da\u00dc\5\37\20\2\u00db\u00da\3\2"+
		"\2\2\u00db\u00dc\3\2\2\2\u00dc\30\3\2\2\2\u00dd\u00df\5/\30\2\u00de\u00e0"+
		"\5\37\20\2\u00df\u00de\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\32\3\2\2\2\u00e1"+
		"\u00e3\59\35\2\u00e2\u00e4\5\37\20\2\u00e3\u00e2\3\2\2\2\u00e3\u00e4\3"+
		"\2\2\2\u00e4\34\3\2\2\2\u00e5\u00e7\5C\"\2\u00e6\u00e8\5\37\20\2\u00e7"+
		"\u00e6\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\36\3\2\2\2\u00e9\u00ea\t\2\2"+
		"\2\u00ea \3\2\2\2\u00eb\u00f6\7\62\2\2\u00ec\u00f3\5\'\24\2\u00ed\u00ef"+
		"\5#\22\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f4\3\2\2\2\u00f0"+
		"\u00f1\5-\27\2\u00f1\u00f2\5#\22\2\u00f2\u00f4\3\2\2\2\u00f3\u00ee\3\2"+
		"\2\2\u00f3\u00f0\3\2\2\2\u00f4\u00f6\3\2\2\2\u00f5\u00eb\3\2\2\2\u00f5"+
		"\u00ec\3\2\2\2\u00f6\"\3\2\2\2\u00f7\u00fc\5%\23\2\u00f8\u00fa\5)\25\2"+
		"\u00f9\u00f8\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd"+
		"\5%\23\2\u00fc\u00f9\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd$\3\2\2\2\u00fe"+
		"\u0101\7\62\2\2\u00ff\u0101\5\'\24\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3"+
		"\2\2\2\u0101&\3\2\2\2\u0102\u0103\t\3\2\2\u0103(\3\2\2\2\u0104\u0106\5"+
		"+\26\2\u0105\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108*\3\2\2\2\u0109\u010c\5%\23\2\u010a\u010c\7a\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010a\3\2\2\2\u010c,\3\2\2\2\u010d\u010f\7a\2\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2"+
		"\2\2\u0111.\3\2\2\2\u0112\u0113\7\62\2\2\u0113\u0114\t\4\2\2\u0114\u0115"+
		"\5\61\31\2\u0115\60\3\2\2\2\u0116\u011b\5\63\32\2\u0117\u0119\5\65\33"+
		"\2\u0118\u0117\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c"+
		"\5\63\32\2\u011b\u0118\3\2\2\2\u011b\u011c\3\2\2\2\u011c\62\3\2\2\2\u011d"+
		"\u011e\t\5\2\2\u011e\64\3\2\2\2\u011f\u0121\5\67\34\2\u0120\u011f\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123"+
		"\66\3\2\2\2\u0124\u0127\5\63\32\2\u0125\u0127\7a\2\2\u0126\u0124\3\2\2"+
		"\2\u0126\u0125\3\2\2\2\u01278\3\2\2\2\u0128\u012a\7\62\2\2\u0129\u012b"+
		"\5-\27\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012d\5;\36\2\u012d:\3\2\2\2\u012e\u0133\5=\37\2\u012f\u0131\5? \2\u0130"+
		"\u012f\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0134\5="+
		"\37\2\u0133\u0130\3\2\2\2\u0133\u0134\3\2\2\2\u0134<\3\2\2\2\u0135\u0136"+
		"\t\6\2\2\u0136>\3\2\2\2\u0137\u0139\5A!\2\u0138\u0137\3\2\2\2\u0139\u013a"+
		"\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b@\3\2\2\2\u013c"+
		"\u013f\5=\37\2\u013d\u013f\7a\2\2\u013e\u013c\3\2\2\2\u013e\u013d\3\2"+
		"\2\2\u013fB\3\2\2\2\u0140\u0141\7\62\2\2\u0141\u0142\t\7\2\2\u0142\u0143"+
		"\5E#\2\u0143D\3\2\2\2\u0144\u0149\5G$\2\u0145\u0147\5I%\2\u0146\u0145"+
		"\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014a\5G$\2\u0149"+
		"\u0146\3\2\2\2\u0149\u014a\3\2\2\2\u014aF\3\2\2\2\u014b\u014c\t\b\2\2"+
		"\u014cH\3\2\2\2\u014d\u014f\5K&\2\u014e\u014d\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151J\3\2\2\2\u0152\u0155"+
		"\5G$\2\u0153\u0155\7a\2\2\u0154\u0152\3\2\2\2\u0154\u0153\3\2\2\2\u0155"+
		"L\3\2\2\2\u0156\u0159\5O(\2\u0157\u0159\5[.\2\u0158\u0156\3\2\2\2\u0158"+
		"\u0157\3\2\2\2\u0159N\3\2\2\2\u015a\u015b\5#\22\2\u015b\u015d\7\60\2\2"+
		"\u015c\u015e\5#\22\2\u015d\u015c\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0160"+
		"\3\2\2\2\u015f\u0161\5Q)\2\u0160\u015f\3\2\2\2\u0160\u0161\3\2\2\2\u0161"+
		"\u0163\3\2\2\2\u0162\u0164\5Y-\2\u0163\u0162\3\2\2\2\u0163\u0164\3\2\2"+
		"\2\u0164\u0176\3\2\2\2\u0165\u0166\7\60\2\2\u0166\u0168\5#\22\2\u0167"+
		"\u0169\5Q)\2\u0168\u0167\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\3\2\2"+
		"\2\u016a\u016c\5Y-\2\u016b\u016a\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u0176"+
		"\3\2\2\2\u016d\u016e\5#\22\2\u016e\u0170\5Q)\2\u016f\u0171\5Y-\2\u0170"+
		"\u016f\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0176\3\2\2\2\u0172\u0173\5#"+
		"\22\2\u0173\u0174\5Y-\2\u0174\u0176\3\2\2\2\u0175\u015a\3\2\2\2\u0175"+
		"\u0165\3\2\2\2\u0175\u016d\3\2\2\2\u0175\u0172\3\2\2\2\u0176P\3\2\2\2"+
		"\u0177\u0178\5S*\2\u0178\u0179\5U+\2\u0179R\3\2\2\2\u017a\u017b\t\t\2"+
		"\2\u017bT\3\2\2\2\u017c\u017e\5W,\2\u017d\u017c\3\2\2\2\u017d\u017e\3"+
		"\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\5#\22\2\u0180V\3\2\2\2\u0181\u0182"+
		"\t\n\2\2\u0182X\3\2\2\2\u0183\u0184\t\13\2\2\u0184Z\3\2\2\2\u0185\u0186"+
		"\5]/\2\u0186\u0188\5_\60\2\u0187\u0189\5Y-\2\u0188\u0187\3\2\2\2\u0188"+
		"\u0189\3\2\2\2\u0189\\\3\2\2\2\u018a\u018c\5/\30\2\u018b\u018d\7\60\2"+
		"\2\u018c\u018b\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u0196\3\2\2\2\u018e\u018f"+
		"\7\62\2\2\u018f\u0191\t\4\2\2\u0190\u0192\5\61\31\2\u0191\u0190\3\2\2"+
		"\2\u0191\u0192\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0194\7\60\2\2\u0194"+
		"\u0196\5\61\31\2\u0195\u018a\3\2\2\2\u0195\u018e\3\2\2\2\u0196^\3\2\2"+
		"\2\u0197\u0198\5a\61\2\u0198\u0199\5U+\2\u0199`\3\2\2\2\u019a\u019b\t"+
		"\f\2\2\u019bb\3\2\2\2\u019c\u019d\7*\2\2\u019dd\3\2\2\2\u019e\u019f\7"+
		"+\2\2\u019ff\3\2\2\2\u01a0\u01a1\7}\2\2\u01a1h\3\2\2\2\u01a2\u01a3\7\177"+
		"\2\2\u01a3j\3\2\2\2\u01a4\u01a5\7]\2\2\u01a5l\3\2\2\2\u01a6\u01a7\7_\2"+
		"\2\u01a7n\3\2\2\2\u01a8\u01a9\7=\2\2\u01a9p\3\2\2\2\u01aa\u01ab\7.\2\2"+
		"\u01abr\3\2\2\2\u01ac\u01ad\7\60\2\2\u01adt\3\2\2\2\u01ae\u01af\7?\2\2"+
		"\u01afv\3\2\2\2\u01b0\u01b1\7-\2\2\u01b1x\3\2\2\2\u01b2\u01b3\7/\2\2\u01b3"+
		"z\3\2\2\2\u01b4\u01b5\7,\2\2\u01b5|\3\2\2\2\u01b6\u01b7\7\61\2\2\u01b7"+
		"~\3\2\2\2\u01b8\u01bc\5\u0081A\2\u01b9\u01bb\5\u0083B\2\u01ba\u01b9\3"+
		"\2\2\2\u01bb\u01be\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd"+
		"\u0080\3\2\2\2\u01be\u01bc\3\2\2\2\u01bf\u01c0\t\r\2\2\u01c0\u0082\3\2"+
		"\2\2\u01c1\u01c2\t\16\2\2\u01c2\u0084\3\2\2\2\u01c3\u01c5\t\17\2\2\u01c4"+
		"\u01c3\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c7\3\2"+
		"\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01c9\bC\2\2\u01c9\u0086\3\2\2\2\u01ca"+
		"\u01ce\7%\2\2\u01cb\u01cd\n\20\2\2\u01cc\u01cb\3\2\2\2\u01cd\u01d0\3\2"+
		"\2\2\u01ce\u01cc\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d1\3\2\2\2\u01d0"+
		"\u01ce\3\2\2\2\u01d1\u01d2\bD\2\2\u01d2\u0088\3\2\2\2.\2\u00d7\u00db\u00df"+
		"\u00e3\u00e7\u00ee\u00f3\u00f5\u00f9\u00fc\u0100\u0107\u010b\u0110\u0118"+
		"\u011b\u0122\u0126\u012a\u0130\u0133\u013a\u013e\u0146\u0149\u0150\u0154"+
		"\u0158\u015d\u0160\u0163\u0168\u016b\u0170\u0175\u017d\u0188\u018c\u0191"+
		"\u0195\u01bc\u01c6\u01ce\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}