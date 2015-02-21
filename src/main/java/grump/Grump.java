package grump;

import com.google.common.collect.BoundType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import grumpsolve.system.ExpressionList;
import grumpsolve.system.NewtonSolver;
import grumpsolve.system.Solution;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static grumpsolve.algebra.Expressions.*;

public class Grump {

    public static TopLevelStatements compile(InputStream in, ANTLRErrorListener errorListener) throws IOException {
        GrumpLexer lexer = new GrumpLexer(new ANTLRInputStream(in));
        GrumpParser parser = new GrumpParser(new CommonTokenStream(lexer));
        parser.addErrorListener(errorListener);
        parser.addParseListener(new GrumpBaseListener() {
            @Override
            public void exitParameterAssignment(GrumpParser.ParameterAssignmentContext ctx) {
                super.exitParameterAssignment(ctx);
            }

            @Override
            public void exitExpression(GrumpParser.ExpressionContext ctx) {
                super.exitExpression(ctx);
            }
        });
        GrumpParser.GrumpContext context = parser.grump();
        return Grump.translate(context);
    }

    public static TopLevelStatements translate(GrumpParser.GrumpContext context) {
        final List<TopLevelStatement> topLevelStatements = context.topLevelStatements().topLevelStatement()
                .stream().map(TopLevelStatement::translate).collect(Collectors.toList());

        return new TopLevelStatements(topLevelStatements);
    }

    public static class TopLevelStatements implements Iterable<TopLevelStatement> {
        final List<TopLevelStatement> topLevelStatements;

        TopLevelStatements(List<TopLevelStatement> topLevelStatements) {
            this.topLevelStatements = ImmutableList.copyOf(topLevelStatements);
        }

        @Override
        public Iterator<TopLevelStatement> iterator() {
            return topLevelStatements.iterator();
        }
    }

    static abstract class TopLevelStatement {

        public static TopLevelStatement translate(GrumpParser.TopLevelStatementContext statement) {
            TopLevelStatement toReturn;
            if (statement.parameterStatement() != null) {
                toReturn = ParameterStatement.translateParameter(statement.parameterStatement());
            } else if (statement.constraintStatement() != null) {
                toReturn = ConstraintStatement.translate(statement.constraintStatement());
            } else {
                throw notSureHowToCompile();
            }
            return toReturn;
        }

        abstract void execute(Runtime.Scope scope);
    }

    static class ParameterStatement extends TopLevelStatement {

        static ParameterStatement translateParameter(GrumpParser.ParameterStatementContext context) {
            final List<ParameterAssignment> assignments = new ArrayList<>();
            for (GrumpParser.ParameterAssignmentContext assignment : context.parameterAssignment()) {
                assignments.add(ParameterAssignment.translate(assignment));
            }
            return new ParameterStatement(assignments);
        }


        final List<ParameterAssignment> assignments;

        ParameterStatement(List<ParameterAssignment> assignments) {
            this.assignments = ImmutableList.copyOf(assignments);
        }

        @Override
        void execute(Runtime.Scope scope) {
            for (ParameterAssignment assignment : assignments) {
                assignment.execute(scope);
            }
        }
    }

    static abstract class ParameterAssignment {

        public static ParameterAssignment translate(GrumpParser.ParameterAssignmentContext assignment) {
            final String name = assignment.Symbol().getText();
            ParameterAssignment toReturn;
            if (assignment.domain() != null) {
                Grump.Domain domain = Grump.Domain.translate(assignment.domain());
                toReturn = new Domain(name, domain);
            } else if (assignment.expression() != null) {
                Expression expression = Expression.translate(assignment.expression());
                toReturn = new Derived(name, expression);
            } else if (assignment.EQUALS() == null) {
                toReturn = new Unspecified(name);
            } else {
                throw notSureHowToCompile();
            }
            return toReturn;
        }

        final String name;

        ParameterAssignment(String name) {
            this.name = name;
        }

        abstract void execute(Runtime.Scope scope);

        static class Unspecified extends ParameterAssignment {
            Unspecified(String name) {
                super(name);
            }

            @Override
            void execute(Runtime.Scope scope) {
                scope.parameters.unspecified(name);
            }
        }

        static class Domain extends ParameterAssignment {

            final Grump.Domain domain;

            Domain(String name, Grump.Domain domain) {
                super(name);
                this.domain = domain;
            }

            @Override
            void execute(Runtime.Scope scope) {
                double leftValue = domain.leftValue.executeForValue(scope);
                double rightValue = domain.rightValue.executeForValue(scope);

                double defaultValue;
                if (domain.defaultValue != null) {
                    defaultValue = domain.defaultValue.executeForValue(scope);
                } else if (BoundType.CLOSED.equals(domain.leftType)) {
                    defaultValue = leftValue;
                } else if (leftValue + 1 < rightValue) {
                    defaultValue = leftValue + 1;
                } else {
                    defaultValue = (leftValue + rightValue) / 2.0;
                }

                scope.parameters.derived(name, defaultValue);
            }
        }

        static class Derived extends ParameterAssignment {

            final Expression e;

            Derived(String name, Expression e) {
                super(name);
                this.e = e;
            }

            @Override
            void execute(Runtime.Scope scope) {
                Set<Expression.Reference> references = e.references();
                scope.parameters.assertValidReferences(references);
                boolean fullyDerived = !scope.parameters.anyParametersUnspecified(references);
                if (fullyDerived) {
                    double value = e.executeForValue(scope);
                    scope.parameters.derived(name, value);
                } else {
                    scope.parameters.unspecified(name);
                    scope.constraints.constrain(new Constraint(ImmutableList.of(new Expression.Reference(name), e)));
                }
            }
        }
    }

    static class ConstraintStatement extends TopLevelStatement {

        static ConstraintStatement translate(GrumpParser.ConstraintStatementContext context) {
            final List<Constraint> constraints = context.constraint().stream()
                    .map(Constraint::translate).collect(Collectors.toList());
            return new ConstraintStatement(constraints);
        }

        final List<Constraint> constraints;

        ConstraintStatement(List<Constraint> constraints) {
            this.constraints = ImmutableList.copyOf(constraints);
        }

        @Override
        void execute(Runtime.Scope scope) {
            for (Constraint constraint : constraints) {
                constraint.execute(scope);
            }
        }
    }

    static class Constraint {

        static Constraint translate(GrumpParser.ConstraintContext context) {
            final List<Expression> expressions = context.expression().stream()
                    .map(Expression::translate).collect(Collectors.toList());
            return new Constraint(expressions);
        }

        final List<Expression> equivalentExpressions;

        Constraint(List<Expression> equivalentExpressions) {
            this.equivalentExpressions = ImmutableList.copyOf(equivalentExpressions);
        }

        void execute(Runtime.Scope scope) {
            Set<Expression.Reference> references = equivalentExpressions
                    .stream().map(Expression::references)
                    .flatMap(s -> s.stream()).collect(Collectors.toSet());
            scope.parameters.assertValidReferences(references);
            scope.constraints.constrain(this);
        }

        Stream<grumpsolve.algebra.Expression> asSolveExpression(Runtime.Scope scope) {
            grumpsolve.algebra.Expression l = equivalentExpressions.get(0).asSolveExpression(scope);
            return equivalentExpressions.subList(1, equivalentExpressions.size()).stream()
                    .map(r -> r.asSolveExpression(scope)).map(r -> sub(l, r));
        }
    }

    static final class Domain {

        static Domain translate(GrumpParser.DomainContext context) {
            BoundType leftType;
            BoundType rightType;
            Expression leftExpression;
            Expression rightExpression;
            Expression defaultValue = null;

            if (context.domainInterval() == null) {
                leftType = BoundType.CLOSED;
                rightType = BoundType.CLOSED;
                leftExpression = new Expression.Constant(Double.MIN_VALUE);
                rightExpression = new Expression.Constant(Double.MAX_VALUE);
            } else {
                GrumpParser.DomainIntervalContext interval = context.domainInterval();
                leftType = interval.LPAREN() != null ? BoundType.OPEN : BoundType.CLOSED;
                rightType = interval.RPAREN() != null ? BoundType.OPEN : BoundType.CLOSED;
                leftExpression = Expression.translate(interval.minExpression().expression());
                rightExpression = Expression.translate(interval.minExpression().expression());
            }
            if (context.defaultValue() != null) {
                defaultValue = Expression.translate(context.defaultValue().expression());
            }
            return new Domain(leftType, leftExpression, rightType, rightExpression, defaultValue);
        }

        final BoundType leftType;
        final Expression leftValue;
        final BoundType rightType;
        final Expression rightValue;
        final Expression defaultValue;

        public Domain(BoundType leftType, Expression leftValue,
                      BoundType rightType, Expression rightValue,
                      Expression defaultValue) {

            this.leftType = leftType;
            this.leftValue = leftValue;
            this.rightType = rightType;
            this.rightValue = rightValue;
            this.defaultValue = defaultValue;
        }
    }

    static abstract class Expression {

        static Expression translate(GrumpParser.ExpressionContext context) {
            Expression expression;
            if (context.expressionTerm() != null) {
                expression = Expression.translateTerm(context.expressionTerm());
            } else if (context.negationExpression() != null) {
                Expression e = Expression.translateTerm(context.negationExpression().expressionTerm());
                expression = new Negation(e);
            } else if (context.functionInvocation() != null) {
                expression = FunctionInvocation.translateFunctionInvocation(context.functionInvocation());
            } else if (context.expression() != null && context.expression().size() == 2) {
                Expression l = Expression.translate(context.expression().get(0));
                Expression r = Expression.translate(context.expression().get(1));

                if (context.MUL() != null) {
                    expression = new Multiplication(l, r);
                } else if (context.DIV() != null) {
                    expression = new Division(l, r);
                } else if (context.ADD() != null) {
                    expression = new Addition(l, r);
                } else if (context.SUB() != null) {
                    expression = new Subtraction(l, r);
                } else {
                    throw notSureHowToCompile();
                }
            } else {
                throw notSureHowToCompile();
            }
            return expression;
        }

        static Expression translateTerm(GrumpParser.ExpressionTermContext context) {
            Expression expression;
            if (context.constant() != null) {
                expression = Constant.translateConstant(context.constant());
            } else if (context.reference() != null) {
                expression = Reference.translateReference(context.reference());
            } else if (context.enclosedExpression() != null) {
                expression = Expression.translate(context.enclosedExpression().expression());
            } else {
                throw notSureHowToCompile();
            }
            return expression;
        }

        final Set<Reference> references() {
            Set<Reference> references = new HashSet<>();
            addReferences(references);
            return ImmutableSet.copyOf(references);
        }

        abstract void addReferences(Set<Reference> references);

        abstract double executeForValue(Runtime.Scope scope);

        abstract grumpsolve.algebra.Expression asSolveExpression(Runtime.Scope scope);

        static class Constant extends Expression {

            static Expression translateConstant(GrumpParser.ConstantContext context) {
                double constant;
                if (context.FloatingPointLiteral() != null) {
                    constant = Double.parseDouble(context.getText());
                } else if (context.IntegerLiteral() != null) {
                    constant = Integer.parseInt(context.getText());
                } else if (context.MAX_DOUBLE() != null) {
                    constant = Double.MAX_VALUE;
                } else if (context.MIN_DOUBLE() != null) {
                    constant = Double.MIN_VALUE;
                } else if (context.PI() != null) {
                    constant = Math.PI;
                } else if (context.GOLDEN_RATIO() != null) {
                    throw new UnsupportedOperationException();
                } else {
                    throw notSureHowToCompile();
                }
                return new Constant(constant);
            }

            final double constant;

            Constant(double constant) {
                this.constant = constant;
            }

            @Override
            void addReferences(Set<Reference> references) {
                //no references here
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                return constant;
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(Runtime.Scope scope) {
                return c(constant);
            }
        }

        static class Reference extends Expression {

            final String name;

            Reference(String name) {
                this.name = name;
            }

            static Expression translateReference(GrumpParser.ReferenceContext reference) {
                return new Reference(reference.Symbol().getText());
            }

            @Override
            public boolean equals(Object o) {
                return this == o || (o instanceof Reference && name.equals(((Reference) o).name));

            }

            @Override
            public int hashCode() {
                return name.hashCode();
            }

            @Override
            void addReferences(Set<Reference> references) {
                references.add(this);
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                scope.parameters.assertValidReference(this);
                return scope.parameters.derivedValue(this);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(Runtime.Scope scope) {
                return scope.parameters.isDerived(this)
                        ? c(scope.parameters.derivedValue(this))
                        : param(name.hashCode());
            }
        }

        static abstract class UnaryOperator extends Expression {

            final Expression e;

            protected UnaryOperator(Expression e) {
                this.e = e;
            }

            @Override
            void addReferences(Set<Reference> references) {
                e.addReferences(references);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(Runtime.Scope scope) {
                return asSolveExpression(e.asSolveExpression(scope));
            }

            abstract grumpsolve.algebra.Expression asSolveExpression(grumpsolve.algebra.Expression e);
        }

        static class Negation extends UnaryOperator {

            protected Negation(Expression e) {
                super(e);
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                return -e.executeForValue(scope);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(grumpsolve.algebra.Expression e) {
                return neg(e);
            }
        }

        static class FunctionInvocation extends Expression {

            static Expression translateFunctionInvocation(GrumpParser.FunctionInvocationContext context) {
                String name = context.Symbol().getText();
                List<Expression> argumentExpressions = context.arguments().expressions().expression()
                        .stream().map(Expression::translate).collect(Collectors.toList());
                return new FunctionInvocation(name, argumentExpressions);
            }

            final String name;
            final List<Expression> argumentExpressions;

            FunctionInvocation(String name, List<Expression> argumentExpressions) {

                this.name = name;
                this.argumentExpressions = argumentExpressions;
            }

            @Override
            void addReferences(Set<Reference> references) {
                for (Expression expression : argumentExpressions) {
                    expression.addReferences(references);
                }
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                Runtime.Function function = scope.functions.lookupFunction(name, argumentExpressions.size());
                double[] arguments = new double[argumentExpressions.size()];
                for (int i = 0; i < argumentExpressions.size(); i++) {
                    arguments[i] = argumentExpressions.get(i).executeForValue(scope);
                }
                return function.eval(arguments);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(Runtime.Scope scope) {
                Runtime.Function function = scope.functions.lookupFunction(name, argumentExpressions.size());
                grumpsolve.algebra.Expression[] arguments = new grumpsolve.algebra.Expression[argumentExpressions.size()];
                for (int i = 0; i < argumentExpressions.size(); i++) {
                    arguments[i] = argumentExpressions.get(i).asSolveExpression(scope);
                }
                return function.asSolveExpression(scope, arguments);
            }
        }

        static abstract class BinaryOperator extends Expression {

            final Expression l, r;

            BinaryOperator(Expression l, Expression r) {
                this.l = l;
                this.r = r;
            }

            @Override
            void addReferences(Set<Reference> references) {
                l.addReferences(references);
                r.addReferences(references);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(Runtime.Scope scope) {
                return asSolveExpression(l.asSolveExpression(scope), r.asSolveExpression(scope));
            }

            abstract grumpsolve.algebra.Expression asSolveExpression(grumpsolve.algebra.Expression l, grumpsolve.algebra.Expression r);

        }

        static class Multiplication extends BinaryOperator {

            Multiplication(Expression l, Expression r) {
                super(l, r);
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                return l.executeForValue(scope) * r.executeForValue(scope);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(grumpsolve.algebra.Expression l, grumpsolve.algebra.Expression r) {
                return mult(l, r);
            }
        }

        static class Division extends BinaryOperator {

            Division(Expression l, Expression r) {
                super(l, r);
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                return l.executeForValue(scope) / r.executeForValue(scope);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(grumpsolve.algebra.Expression l, grumpsolve.algebra.Expression r) {
                return div(l, r);
            }
        }

        static class Addition extends BinaryOperator {

            Addition(Expression l, Expression r) {
                super(l, r);
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                return l.executeForValue(scope) + r.executeForValue(scope);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(grumpsolve.algebra.Expression l, grumpsolve.algebra.Expression r) {
                return add(l, r);
            }
        }

        static class Subtraction extends BinaryOperator {

            Subtraction(Expression l, Expression r) {
                super(l, r);
            }

            @Override
            double executeForValue(Runtime.Scope scope) {
                return l.executeForValue(scope) - r.executeForValue(scope);
            }

            @Override
            grumpsolve.algebra.Expression asSolveExpression(grumpsolve.algebra.Expression l, grumpsolve.algebra.Expression r) {
                return sub(l, r);
            }
        }
    }

    static IllegalStateException notSureHowToCompile() {
        return new IllegalStateException("Not sure how to compile this");
    }

    private final Runtime.Scope scope = new Runtime.Scope();

    public Grump() {
    }

    public Grump execute(TopLevelStatements topLevelStatements) {
        for (TopLevelStatement topLevelStatement : topLevelStatements) {
            topLevelStatement.execute(scope);
        }
        return this;
    }

    public void solve() {
        final Stream<grumpsolve.algebra.Expression> expressions = scope.constraints.asSolveExpressions(scope);

        final ExpressionList expressionList = ExpressionList.from(expressions.collect(Collectors.toList()));
        Set<Long> solveParameters = scope.parameters.unspecified.stream().map(p -> (long) p.hashCode()).collect(Collectors.toSet());
        Solution s0 = Solution.zero(solveParameters);
        Solution s = NewtonSolver.make(expressionList).newtonSolve(s0);
        System.out.println(s);
    }

    static class Runtime {

        static class Scope {

            final Parameters parameters = new Parameters();
            final Constraints constraints = new Constraints();
            final Functions functions = new Functions();
        }

        static class Parameters {

            private final Map<String, Double> derived = new HashMap<>();
            private final Set<String> unspecified = new HashSet<>();

            void unspecified(String name) {
                assertNewParameter(name);
                unspecified.add(name);
            }

            void derived(String name, double value) {
                derived.put(name, value);
            }

            void assertValidReference(Expression.Reference reference) {
                if (!derived.containsKey(reference.name) && !unspecified.contains(reference.name)) {
                    throw new RuntimeException("Invalid reference: " + reference.name);
                }
            }

            void assertValidReferences(Set<Expression.Reference> references) {
                final Sets.SetView<String> validReferences = Sets.union(derived.keySet(), unspecified);
                final Set<String> referenceNames = references.stream().map(r -> r.name).collect(Collectors.toSet());
                final Sets.SetView<String> missingReferences = Sets.difference(referenceNames, validReferences);

                if (!missingReferences.isEmpty()) {
                    throw new RuntimeException("Invalid references: " + missingReferences);
                }
            }

            boolean anyParametersUnspecified(Set<Expression.Reference> references) {
                final Set<String> referenceNames = references.stream().map(r -> r.name).collect(Collectors.toSet());
                return !Sets.intersection(unspecified, referenceNames).isEmpty();
            }

            void assertNewParameter(String name) {
                if (derived.containsKey(name) || unspecified.contains(name)) {
                    throw new RuntimeException(name + " is already defined");
                }
            }

            boolean isDerived(Expression.Reference reference) {
                return derived.containsKey(reference.name);
            }

            double derivedValue(Expression.Reference reference) {
                if (!derived.containsKey(reference.name)) {
                    throw new RuntimeException(reference.name + " is not a derived value");
                }
                return derived.get(reference.name);
            }
        }

        static class Constraints {

            final List<Constraint> constraints = new ArrayList<>();

            void constrain(Constraint constraint) {
                constraints.add(constraint);
            }

            public Stream<grumpsolve.algebra.Expression> asSolveExpressions(Scope scope) {
                return constraints.stream().map(c -> c.asSolveExpression(scope)).flatMap(s -> s);
            }
        }

        static class Functions {

            final Map<String, Function> functions = new HashMap<>();

            Functions() {
                define(Function.Square);
            }

            Functions define(Function function) {
                if (functions.containsKey(function.name)) {
                    throw new IllegalArgumentException("Function already defined: " + function.name);
                }
                functions.put(function.name, function);
                return this;
            }

            Function lookupFunction(String name, int numParameters) {
                Function function = functions.get(name);
                if (function == null) {
                    throw new RuntimeException("Could not find function " + name);
                }
                function.assertNumParameters(numParameters);
                return function;
            }

        }

        static abstract class Function {

            final String name;
            final int numParameters;

            Function(String name, int numParameters) {
                this.name = name;
                this.numParameters = numParameters;
            }

            void assertNumParameters(int numParameters) {
                if (this.numParameters != numParameters) {
                    final String message = String.format("function %s expects %d parameters, %d were given",
                            name, this.numParameters, numParameters);
                    throw new RuntimeException(message);
                }
            }

            double eval(double[] arguments) {
                assertNumParameters(arguments.length);
                return safeEval(arguments);
            }

            abstract double safeEval(double[] arguments);

            abstract grumpsolve.algebra.Expression asSolveExpression(Scope scope, grumpsolve.algebra.Expression[] arguments);


            static final Function Square = new Function("square", 1) {

                @Override
                double safeEval(double[] a) {
                    final double v = a[0];
                    return v * v;
                }

                @Override
                grumpsolve.algebra.Expression asSolveExpression(Scope scope, grumpsolve.algebra.Expression[] a) {
                    return square(a[0]);
                }
            };


        }
    }
}
