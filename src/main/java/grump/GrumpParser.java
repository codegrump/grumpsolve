package grump;
// Generated from Grump.g4 by ANTLR 4.5
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrumpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FLOAT=1, INT=2, CONSTRAINT=3, DEFAULT=4, DOMAIN=5, MAX_DOUBLE=6, MIN_DOUBLE=7, 
		PI=8, GOLDEN_RATIO=9, IntegerLiteral=10, FloatingPointLiteral=11, LPAREN=12, 
		RPAREN=13, LBRACE=14, RBRACE=15, LBRACK=16, RBRACK=17, SEMI=18, COMMA=19, 
		DOT=20, EQUALS=21, ADD=22, SUB=23, MUL=24, DIV=25, Symbol=26, WS=27, LINE_COMMENT=28;
	public static final int
		RULE_grump = 0, RULE_topLevelStatements = 1, RULE_topLevelStatement = 2, 
		RULE_parameterStatement = 3, RULE_constraintStatement = 4, RULE_parameterAssignment = 5, 
		RULE_constraint = 6, RULE_domain = 7, RULE_domainInterval = 8, RULE_defaultValue = 9, 
		RULE_minExpression = 10, RULE_maxExpression = 11, RULE_expressions = 12, 
		RULE_expression = 13, RULE_expressionTerm = 14, RULE_enclosedExpression = 15, 
		RULE_negationExpression = 16, RULE_functionInvocation = 17, RULE_arguments = 18, 
		RULE_constant = 19, RULE_reference = 20;
	public static final String[] ruleNames = {
		"grump", "topLevelStatements", "topLevelStatement", "parameterStatement", 
		"constraintStatement", "parameterAssignment", "constraint", "domain", 
		"domainInterval", "defaultValue", "minExpression", "maxExpression", "expressions", 
		"expression", "expressionTerm", "enclosedExpression", "negationExpression", 
		"functionInvocation", "arguments", "constant", "reference"
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

	@Override
	public String getGrammarFileName() { return "Grump.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrumpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GrumpContext extends ParserRuleContext {
		public TopLevelStatementsContext topLevelStatements() {
			return getRuleContext(TopLevelStatementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrumpParser.EOF, 0); }
		public GrumpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grump; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterGrump(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitGrump(this);
		}
	}

	public final GrumpContext grump() throws RecognitionException {
		GrumpContext _localctx = new GrumpContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grump);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			topLevelStatements();
			setState(43);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopLevelStatementsContext extends ParserRuleContext {
		public List<TopLevelStatementContext> topLevelStatement() {
			return getRuleContexts(TopLevelStatementContext.class);
		}
		public TopLevelStatementContext topLevelStatement(int i) {
			return getRuleContext(TopLevelStatementContext.class,i);
		}
		public TopLevelStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelStatements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterTopLevelStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitTopLevelStatements(this);
		}
	}

	public final TopLevelStatementsContext topLevelStatements() throws RecognitionException {
		TopLevelStatementsContext _localctx = new TopLevelStatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_topLevelStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FLOAT || _la==CONSTRAINT) {
				{
				{
				setState(45);
				topLevelStatement();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopLevelStatementContext extends ParserRuleContext {
		public ParameterStatementContext parameterStatement() {
			return getRuleContext(ParameterStatementContext.class,0);
		}
		public ConstraintStatementContext constraintStatement() {
			return getRuleContext(ConstraintStatementContext.class,0);
		}
		public TopLevelStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterTopLevelStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitTopLevelStatement(this);
		}
	}

	public final TopLevelStatementContext topLevelStatement() throws RecognitionException {
		TopLevelStatementContext _localctx = new TopLevelStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_topLevelStatement);
		try {
			setState(53);
			switch (_input.LA(1)) {
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				parameterStatement();
				}
				break;
			case CONSTRAINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				constraintStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterStatementContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(GrumpParser.FLOAT, 0); }
		public List<ParameterAssignmentContext> parameterAssignment() {
			return getRuleContexts(ParameterAssignmentContext.class);
		}
		public ParameterAssignmentContext parameterAssignment(int i) {
			return getRuleContext(ParameterAssignmentContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(GrumpParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(GrumpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrumpParser.COMMA, i);
		}
		public ParameterStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterParameterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitParameterStatement(this);
		}
	}

	public final ParameterStatementContext parameterStatement() throws RecognitionException {
		ParameterStatementContext _localctx = new ParameterStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parameterStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(FLOAT);
			setState(56);
			parameterAssignment();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(57);
				match(COMMA);
				setState(58);
				parameterAssignment();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintStatementContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() { return getToken(GrumpParser.CONSTRAINT, 0); }
		public List<ConstraintContext> constraint() {
			return getRuleContexts(ConstraintContext.class);
		}
		public ConstraintContext constraint(int i) {
			return getRuleContext(ConstraintContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(GrumpParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(GrumpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrumpParser.COMMA, i);
		}
		public ConstraintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraintStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterConstraintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitConstraintStatement(this);
		}
	}

	public final ConstraintStatementContext constraintStatement() throws RecognitionException {
		ConstraintStatementContext _localctx = new ConstraintStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constraintStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(CONSTRAINT);
			setState(67);
			constraint();
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(68);
				match(COMMA);
				setState(69);
				constraint();
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterAssignmentContext extends ParserRuleContext {
		public TerminalNode Symbol() { return getToken(GrumpParser.Symbol, 0); }
		public TerminalNode EQUALS() { return getToken(GrumpParser.EQUALS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DomainContext domain() {
			return getRuleContext(DomainContext.class,0);
		}
		public ParameterAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterParameterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitParameterAssignment(this);
		}
	}

	public final ParameterAssignmentContext parameterAssignment() throws RecognitionException {
		ParameterAssignmentContext _localctx = new ParameterAssignmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parameterAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(Symbol);
			setState(83);
			_la = _input.LA(1);
			if (_la==EQUALS) {
				{
				setState(78);
				match(EQUALS);
				setState(81);
				switch (_input.LA(1)) {
				case MAX_DOUBLE:
				case MIN_DOUBLE:
				case PI:
				case GOLDEN_RATIO:
				case IntegerLiteral:
				case FloatingPointLiteral:
				case LPAREN:
				case SUB:
				case Symbol:
					{
					setState(79);
					expression(0);
					}
					break;
				case DEFAULT:
				case DOMAIN:
					{
					setState(80);
					domain();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(GrumpParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(GrumpParser.EQUALS, i);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			expression(0);
			setState(88); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(86);
				match(EQUALS);
				setState(87);
				expression(0);
				}
				}
				setState(90); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EQUALS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainContext extends ParserRuleContext {
		public DomainIntervalContext domainInterval() {
			return getRuleContext(DomainIntervalContext.class,0);
		}
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public DomainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterDomain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitDomain(this);
		}
	}

	public final DomainContext domain() throws RecognitionException {
		DomainContext _localctx = new DomainContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_domain);
		int _la;
		try {
			setState(97);
			switch (_input.LA(1)) {
			case DOMAIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				domainInterval();
				setState(94);
				_la = _input.LA(1);
				if (_la==DEFAULT) {
					{
					setState(93);
					defaultValue();
					}
				}

				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				defaultValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainIntervalContext extends ParserRuleContext {
		public TerminalNode DOMAIN() { return getToken(GrumpParser.DOMAIN, 0); }
		public MinExpressionContext minExpression() {
			return getRuleContext(MinExpressionContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(GrumpParser.COMMA, 0); }
		public MaxExpressionContext maxExpression() {
			return getRuleContext(MaxExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(GrumpParser.LPAREN, 0); }
		public TerminalNode LBRACK() { return getToken(GrumpParser.LBRACK, 0); }
		public TerminalNode RPAREN() { return getToken(GrumpParser.RPAREN, 0); }
		public TerminalNode RBRACK() { return getToken(GrumpParser.RBRACK, 0); }
		public DomainIntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainInterval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterDomainInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitDomainInterval(this);
		}
	}

	public final DomainIntervalContext domainInterval() throws RecognitionException {
		DomainIntervalContext _localctx = new DomainIntervalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_domainInterval);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(DOMAIN);
			setState(100);
			_la = _input.LA(1);
			if ( !(_la==LPAREN || _la==LBRACK) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(101);
			minExpression();
			setState(102);
			match(COMMA);
			setState(103);
			maxExpression();
			setState(104);
			_la = _input.LA(1);
			if ( !(_la==RPAREN || _la==RBRACK) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(GrumpParser.DEFAULT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(DEFAULT);
			setState(107);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MinExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MinExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterMinExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitMinExpression(this);
		}
	}

	public final MinExpressionContext minExpression() throws RecognitionException {
		MinExpressionContext _localctx = new MinExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_minExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MaxExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MaxExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterMaxExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitMaxExpression(this);
		}
	}

	public final MaxExpressionContext maxExpression() throws RecognitionException {
		MaxExpressionContext _localctx = new MaxExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_maxExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GrumpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrumpParser.COMMA, i);
		}
		public ExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitExpressions(this);
		}
	}

	public final ExpressionsContext expressions() throws RecognitionException {
		ExpressionsContext _localctx = new ExpressionsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			expression(0);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(114);
				match(COMMA);
				setState(115);
				expression(0);
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionTermContext expressionTerm() {
			return getRuleContext(ExpressionTermContext.class,0);
		}
		public NegationExpressionContext negationExpression() {
			return getRuleContext(NegationExpressionContext.class,0);
		}
		public FunctionInvocationContext functionInvocation() {
			return getRuleContext(FunctionInvocationContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(GrumpParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(GrumpParser.DIV, 0); }
		public TerminalNode ADD() { return getToken(GrumpParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(GrumpParser.SUB, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(122);
				expressionTerm();
				}
				break;
			case 2:
				{
				setState(123);
				negationExpression();
				}
				break;
			case 3:
				{
				setState(124);
				functionInvocation();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(135);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(133);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(127);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(128);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(129);
						expression(5);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(130);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(131);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(132);
						expression(4);
						}
						break;
					}
					} 
				}
				setState(137);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionTermContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public EnclosedExpressionContext enclosedExpression() {
			return getRuleContext(EnclosedExpressionContext.class,0);
		}
		public ExpressionTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterExpressionTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitExpressionTerm(this);
		}
	}

	public final ExpressionTermContext expressionTerm() throws RecognitionException {
		ExpressionTermContext _localctx = new ExpressionTermContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expressionTerm);
		try {
			setState(141);
			switch (_input.LA(1)) {
			case MAX_DOUBLE:
			case MIN_DOUBLE:
			case PI:
			case GOLDEN_RATIO:
			case IntegerLiteral:
			case FloatingPointLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				constant();
				}
				break;
			case Symbol:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				reference();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				enclosedExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnclosedExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(GrumpParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GrumpParser.RPAREN, 0); }
		public EnclosedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enclosedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterEnclosedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitEnclosedExpression(this);
		}
	}

	public final EnclosedExpressionContext enclosedExpression() throws RecognitionException {
		EnclosedExpressionContext _localctx = new EnclosedExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_enclosedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(LPAREN);
			setState(144);
			expression(0);
			setState(145);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationExpressionContext extends ParserRuleContext {
		public TerminalNode SUB() { return getToken(GrumpParser.SUB, 0); }
		public ExpressionTermContext expressionTerm() {
			return getRuleContext(ExpressionTermContext.class,0);
		}
		public NegationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negationExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterNegationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitNegationExpression(this);
		}
	}

	public final NegationExpressionContext negationExpression() throws RecognitionException {
		NegationExpressionContext _localctx = new NegationExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_negationExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(SUB);
			setState(148);
			expressionTerm();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionInvocationContext extends ParserRuleContext {
		public TerminalNode Symbol() { return getToken(GrumpParser.Symbol, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public FunctionInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterFunctionInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitFunctionInvocation(this);
		}
	}

	public final FunctionInvocationContext functionInvocation() throws RecognitionException {
		FunctionInvocationContext _localctx = new FunctionInvocationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functionInvocation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(Symbol);
			setState(151);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(GrumpParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GrumpParser.RPAREN, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(LPAREN);
			setState(155);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAX_DOUBLE) | (1L << MIN_DOUBLE) | (1L << PI) | (1L << GOLDEN_RATIO) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << LPAREN) | (1L << SUB) | (1L << Symbol))) != 0)) {
				{
				setState(154);
				expressions();
				}
			}

			setState(157);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode IntegerLiteral() { return getToken(GrumpParser.IntegerLiteral, 0); }
		public TerminalNode FloatingPointLiteral() { return getToken(GrumpParser.FloatingPointLiteral, 0); }
		public TerminalNode PI() { return getToken(GrumpParser.PI, 0); }
		public TerminalNode GOLDEN_RATIO() { return getToken(GrumpParser.GOLDEN_RATIO, 0); }
		public TerminalNode MAX_DOUBLE() { return getToken(GrumpParser.MAX_DOUBLE, 0); }
		public TerminalNode MIN_DOUBLE() { return getToken(GrumpParser.MIN_DOUBLE, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitConstant(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAX_DOUBLE) | (1L << MIN_DOUBLE) | (1L << PI) | (1L << GOLDEN_RATIO) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode Symbol() { return getToken(GrumpParser.Symbol, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrumpListener ) ((GrumpListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(Symbol);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\36\u00a6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\3\7\3\61\n\3\f"+
		"\3\16\3\64\13\3\3\4\3\4\5\48\n\4\3\5\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\7\6I\n\6\f\6\16\6L\13\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\5\7T\n\7\5\7V\n\7\3\b\3\b\3\b\6\b[\n\b\r\b\16\b\\\3\t\3\t\5\ta\n"+
		"\t\3\t\5\td\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\16\7\16w\n\16\f\16\16\16z\13\16\3\17\3\17\3\17\3\17\5"+
		"\17\u0080\n\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0088\n\17\f\17\16\17"+
		"\u008b\13\17\3\20\3\20\3\20\5\20\u0090\n\20\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\24\3\24\5\24\u009e\n\24\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\26\2\3\34\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*\2\7\4\2\16\16\22\22\4\2\17\17\23\23\3\2\32\33\3\2\30\31\3\2\b\r\u00a1"+
		"\2,\3\2\2\2\4\62\3\2\2\2\6\67\3\2\2\2\b9\3\2\2\2\nD\3\2\2\2\fO\3\2\2\2"+
		"\16W\3\2\2\2\20c\3\2\2\2\22e\3\2\2\2\24l\3\2\2\2\26o\3\2\2\2\30q\3\2\2"+
		"\2\32s\3\2\2\2\34\177\3\2\2\2\36\u008f\3\2\2\2 \u0091\3\2\2\2\"\u0095"+
		"\3\2\2\2$\u0098\3\2\2\2&\u009b\3\2\2\2(\u00a1\3\2\2\2*\u00a3\3\2\2\2,"+
		"-\5\4\3\2-.\7\2\2\3.\3\3\2\2\2/\61\5\6\4\2\60/\3\2\2\2\61\64\3\2\2\2\62"+
		"\60\3\2\2\2\62\63\3\2\2\2\63\5\3\2\2\2\64\62\3\2\2\2\658\5\b\5\2\668\5"+
		"\n\6\2\67\65\3\2\2\2\67\66\3\2\2\28\7\3\2\2\29:\7\3\2\2:?\5\f\7\2;<\7"+
		"\25\2\2<>\5\f\7\2=;\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?"+
		"\3\2\2\2BC\7\24\2\2C\t\3\2\2\2DE\7\5\2\2EJ\5\16\b\2FG\7\25\2\2GI\5\16"+
		"\b\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\7\24"+
		"\2\2N\13\3\2\2\2OU\7\34\2\2PS\7\27\2\2QT\5\34\17\2RT\5\20\t\2SQ\3\2\2"+
		"\2SR\3\2\2\2TV\3\2\2\2UP\3\2\2\2UV\3\2\2\2V\r\3\2\2\2WZ\5\34\17\2XY\7"+
		"\27\2\2Y[\5\34\17\2ZX\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\17\3\2"+
		"\2\2^`\5\22\n\2_a\5\24\13\2`_\3\2\2\2`a\3\2\2\2ad\3\2\2\2bd\5\24\13\2"+
		"c^\3\2\2\2cb\3\2\2\2d\21\3\2\2\2ef\7\7\2\2fg\t\2\2\2gh\5\26\f\2hi\7\25"+
		"\2\2ij\5\30\r\2jk\t\3\2\2k\23\3\2\2\2lm\7\6\2\2mn\5\34\17\2n\25\3\2\2"+
		"\2op\5\34\17\2p\27\3\2\2\2qr\5\34\17\2r\31\3\2\2\2sx\5\34\17\2tu\7\25"+
		"\2\2uw\5\34\17\2vt\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\33\3\2\2\2z"+
		"x\3\2\2\2{|\b\17\1\2|\u0080\5\36\20\2}\u0080\5\"\22\2~\u0080\5$\23\2\177"+
		"{\3\2\2\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0089\3\2\2\2\u0081\u0082\f"+
		"\6\2\2\u0082\u0083\t\4\2\2\u0083\u0088\5\34\17\7\u0084\u0085\f\5\2\2\u0085"+
		"\u0086\t\5\2\2\u0086\u0088\5\34\17\6\u0087\u0081\3\2\2\2\u0087\u0084\3"+
		"\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\35\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u0090\5(\25\2\u008d\u0090\5*\26"+
		"\2\u008e\u0090\5 \21\2\u008f\u008c\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u008e"+
		"\3\2\2\2\u0090\37\3\2\2\2\u0091\u0092\7\16\2\2\u0092\u0093\5\34\17\2\u0093"+
		"\u0094\7\17\2\2\u0094!\3\2\2\2\u0095\u0096\7\31\2\2\u0096\u0097\5\36\20"+
		"\2\u0097#\3\2\2\2\u0098\u0099\7\34\2\2\u0099\u009a\5&\24\2\u009a%\3\2"+
		"\2\2\u009b\u009d\7\16\2\2\u009c\u009e\5\32\16\2\u009d\u009c\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\7\17\2\2\u00a0\'\3\2\2"+
		"\2\u00a1\u00a2\t\6\2\2\u00a2)\3\2\2\2\u00a3\u00a4\7\34\2\2\u00a4+\3\2"+
		"\2\2\21\62\67?JSU\\`cx\177\u0087\u0089\u008f\u009d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}