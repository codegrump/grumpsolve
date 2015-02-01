package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import static grumpsolve.algebra.Expressions.*;

@Immutable
final class Negation extends UnaryOperator {

    static Expression negate(Expression e) {
        return e == ZERO ? ZERO
                : e instanceof Constant ? c(-cv(e))
                : e instanceof Negation ? ((Negation) e).e
                : new Negation(e);
    }

    private Negation(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Solution solution) {
        return -e.evaluate(solution);
    }

    @Override
    public String toString() {
        return "-" + toStringWithOptionalParens(e);
    }

    @Override
    Expression partialWithRespectTo(Parameter p) {
        return negate(e.partialWithRespectTo(p));
    }
}
