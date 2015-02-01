package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class Sin extends UnaryOperator {

    static Expression sin(Expression e) {
        return e == ZERO ? ZERO
                : e instanceof Constant ? c(Math.sin(cv(e)))
                : new Sin(e);
    }

    private Sin(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Solution solution) {
        return Math.sin(e.evaluate(solution));
    }

    @Override
    public String toString() {
        return "sin(" + e.toString() + ")";
    }

    @Override
    Expression partialWithRespectTo(Parameter p) {
        return mult(cos(e), e.partialWithRespectTo(p));
    }
}
