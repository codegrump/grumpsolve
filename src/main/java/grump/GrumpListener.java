package grump;
// Generated from Grump.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrumpParser}.
 */
public interface GrumpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrumpParser#grump}.
	 * @param ctx the parse tree
	 */
	void enterGrump(GrumpParser.GrumpContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#grump}.
	 * @param ctx the parse tree
	 */
	void exitGrump(GrumpParser.GrumpContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#topLevelStatements}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelStatements(GrumpParser.TopLevelStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#topLevelStatements}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelStatements(GrumpParser.TopLevelStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#topLevelStatement}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelStatement(GrumpParser.TopLevelStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#topLevelStatement}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelStatement(GrumpParser.TopLevelStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#parameterStatement}.
	 * @param ctx the parse tree
	 */
	void enterParameterStatement(GrumpParser.ParameterStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#parameterStatement}.
	 * @param ctx the parse tree
	 */
	void exitParameterStatement(GrumpParser.ParameterStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#constraintStatement}.
	 * @param ctx the parse tree
	 */
	void enterConstraintStatement(GrumpParser.ConstraintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#constraintStatement}.
	 * @param ctx the parse tree
	 */
	void exitConstraintStatement(GrumpParser.ConstraintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#parameterAssignment}.
	 * @param ctx the parse tree
	 */
	void enterParameterAssignment(GrumpParser.ParameterAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#parameterAssignment}.
	 * @param ctx the parse tree
	 */
	void exitParameterAssignment(GrumpParser.ParameterAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(GrumpParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(GrumpParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#domain}.
	 * @param ctx the parse tree
	 */
	void enterDomain(GrumpParser.DomainContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#domain}.
	 * @param ctx the parse tree
	 */
	void exitDomain(GrumpParser.DomainContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#domainInterval}.
	 * @param ctx the parse tree
	 */
	void enterDomainInterval(GrumpParser.DomainIntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#domainInterval}.
	 * @param ctx the parse tree
	 */
	void exitDomainInterval(GrumpParser.DomainIntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(GrumpParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(GrumpParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#minExpression}.
	 * @param ctx the parse tree
	 */
	void enterMinExpression(GrumpParser.MinExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#minExpression}.
	 * @param ctx the parse tree
	 */
	void exitMinExpression(GrumpParser.MinExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#maxExpression}.
	 * @param ctx the parse tree
	 */
	void enterMaxExpression(GrumpParser.MaxExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#maxExpression}.
	 * @param ctx the parse tree
	 */
	void exitMaxExpression(GrumpParser.MaxExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(GrumpParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(GrumpParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrumpParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrumpParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#expressionTerm}.
	 * @param ctx the parse tree
	 */
	void enterExpressionTerm(GrumpParser.ExpressionTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#expressionTerm}.
	 * @param ctx the parse tree
	 */
	void exitExpressionTerm(GrumpParser.ExpressionTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#enclosedExpression}.
	 * @param ctx the parse tree
	 */
	void enterEnclosedExpression(GrumpParser.EnclosedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#enclosedExpression}.
	 * @param ctx the parse tree
	 */
	void exitEnclosedExpression(GrumpParser.EnclosedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#negationExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegationExpression(GrumpParser.NegationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#negationExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegationExpression(GrumpParser.NegationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(GrumpParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(GrumpParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(GrumpParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(GrumpParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(GrumpParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(GrumpParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrumpParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(GrumpParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrumpParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(GrumpParser.ReferenceContext ctx);
}