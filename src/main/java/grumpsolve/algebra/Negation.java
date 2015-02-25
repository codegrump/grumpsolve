package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
final class Negation extends UnaryOperator {

    static Expression negate(@Nonnull Expression e) {
        return e == ZERO ? ZERO
                : e instanceof Constant ? c(-cv(e))
                : e instanceof Negation ? ((Negation) e).e
                : new Negation(e);
    }

    private Negation(@Nonnull Expression e) {
        super(e);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return -e.evaluate(solution);
    }

    @Override
    public String toString() {
        return "-" + toStringWithOptionalParens(e);
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Variable p) {
        return negate(e.partialWithRespectTo(p));
    }
}
