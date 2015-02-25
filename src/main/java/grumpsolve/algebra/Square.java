package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class Square extends UnaryOperator {

    static Expression square(@Nonnull Expression e) {
        return e == ZERO ? ZERO
                : e == ONE ? ONE
                : e instanceof Constant ? pow2(cv(e))
                : new Square(e);
    }

    private Square(@Nonnull Expression e) {
        super(e);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        double v = e.evaluate(solution);
        return v * v;
    }

    @Override
    public String toString() {
        return toStringWithOptionalParens(e)+"^2";
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Variable p) {
        Expression _2a = mult(c(2), e);
        Expression da = e.partialWithRespectTo(p);
        //2ada
        return mult(_2a, da);
    }
}
