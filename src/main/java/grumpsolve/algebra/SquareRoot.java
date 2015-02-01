package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class SquareRoot extends UnaryOperator {

    static Expression squareRoot(Expression e) {
        return e == ZERO ? ZERO
                : e == ONE ? ONE
                : e instanceof Constant ? c(Math.sqrt(cv(e)))
                : new SquareRoot(e);
    }

    private SquareRoot(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Solution solution) {
        return Math.sqrt(e.evaluate(solution));
    }

    @Override
    public String toString() {
        return "sqrt(" + e.toString() + ")";
    }

    @Override
    Expression partialWithRespectTo(Parameter p) {
        // 0.5 e^(-1/2) da =>
        // (0.5 / sqrt(e)) da
        return mult(div(HALF, this), e.partialWithRespectTo(p));
    }
}
