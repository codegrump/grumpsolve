package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
final class Subtraction extends BinaryOperator {

    static Expression subtract(@Nonnull Expression l, @Nonnull Expression r) {
        return r == ZERO ? l
                : l == ZERO ? neg(r)
                : l instanceof Constant && r instanceof Constant ? c(cv(l) - cv(r))
                : new Subtraction(l, r);
    }

    private Subtraction(@Nonnull Expression a, @Nonnull Expression b) {
        super(a, b);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return l.evaluate(solution) - r.evaluate(solution);
    }

    @Override
    public String toString() {
        String ls = toStringWithOptionalParens(l);
        String rs = toStringWithOptionalParens(r);
        return ls + "-" + rs;
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Variable p) {
        //da - db
        return sub(l.partialWithRespectTo(p), r.partialWithRespectTo(p));
    }
}
