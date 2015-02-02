package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class Sin extends UnaryOperator {

    static Expression sin(@Nonnull Expression e) {
        return e == ZERO ? ZERO
                : e instanceof Constant ? c(Math.sin(cv(e)))
                : new Sin(e);
    }

    private Sin(@Nonnull Expression e) {
        super(e);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return Math.sin(e.evaluate(solution));
    }

    @Override
    public String toString() {
        return "sin(" + e.toString() + ")";
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Parameter p) {
        return mult(cos(e), e.partialWithRespectTo(p));
    }
}
