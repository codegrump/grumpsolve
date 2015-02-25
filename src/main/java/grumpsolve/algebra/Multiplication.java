package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class Multiplication extends BinaryOperator {

    static Expression multiply(@Nonnull Expression l, @Nonnull Expression r) {
        return l == ZERO || r == ZERO ? ZERO
                : l == ONE ? r
                : r == ONE ? l
                : l instanceof Constant && r instanceof Constant ? c(cv(l) * cv(r))
                : new Multiplication(l, r);
    }

    private Multiplication(@Nonnull Expression l, @Nonnull Expression r) {
        super(l, r);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return l.evaluate(solution) * r.evaluate(solution);
    }

    @Override
    public String toString() {
        String ls = toStringWithOptionalParens(l);
        String rs = toStringWithOptionalParens(r);
        return ls + "*" + rs;
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Variable p) {
        Expression da = l.partialWithRespectTo(p);
        Expression db = r.partialWithRespectTo(p);
        //bda + adb
        return add(mult(r, da), mult(l, db));
    }
}
