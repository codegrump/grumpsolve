package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class ArcSin extends UnaryOperator {

    static Expression arcsin(@Nonnull Expression e) {
        return e instanceof Constant ? c(Math.asin(cv(e)))
                : new ArcSin(e);
    }

    private ArcSin(@Nonnull Expression e) {
        super(e);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return Math.asin(e.evaluate(solution));
    }

    @Override
    public String toString() {
        return "asin(" + e.toString() + ")";
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Variable p) {
        // (1/sqrt(1 - e^2))da
        Expression e2 = square(e);
        Expression de = e.partialWithRespectTo(p);

        Expression root = sqrt(sub(ONE, e2));
        Expression oneOverRoot = div(ONE, root);
        return mult(oneOverRoot, de);
    }
}
