package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class Cos extends UnaryOperator {

    static Expression cos(@Nonnull Expression e) {
        return e == ZERO ? ONE
                : e instanceof Constant ? c(Math.cos(cv(e)))
                : new Cos(e);
    }

    public Cos(@Nonnull Expression e) {
        super(e);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return Math.cos(e.evaluate(solution));
    }

    @Override
    public String toString() {
        return "cos(" + e.toString() + ")";
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Parameter p) {
        //-sin(e)da
        return neg(mult(sin(e), e.partialWithRespectTo(p)));
    }
}
