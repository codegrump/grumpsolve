package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;
import static grumpsolve.algebra.Expressions.mult;

@Immutable
class ArcCos extends UnaryOperator {

    static Expression arccos(@Nonnull Expression e) {
        return e instanceof Constant ? c(Math.acos(cv(e)))
                : new ArcCos(e);
    }

    private ArcCos(@Nonnull Expression e) {
        super(e);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return Math.acos(e.evaluate(solution));
    }

    @Override
    public String toString() {
        return "acos(" + e.toString() + ")";
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Variable p) {
        Expression e2 = square(e);
        Expression de = e.partialWithRespectTo(p);

        Expression root = sqrt(sub(ONE, e2));
        Expression oneOverRoot = div(ONE, root);
        return mult(neg(oneOverRoot), de);
    }
}
