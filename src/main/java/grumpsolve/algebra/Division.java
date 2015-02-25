package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
class Division extends BinaryOperator {

    static Expression divide(@Nonnull Expression l, @Nonnull Expression r) {
        return r == ONE ? l
                : l  == ZERO ? ZERO
                : r instanceof Constant ? mult(c(1.0 / cv(r)), l)
                : new Division(l, r);
    }

    private Division(@Nonnull Expression l, @Nonnull Expression r) {
        super(l, r);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return l.evaluate(solution) / r.evaluate(solution);
    }

    @Override
    public String toString() {
        String ls = toStringWithOptionalParens(l);
        String rs = toStringWithOptionalParens(r);
        return ls + "/" + rs;
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Variable p) {
        Expression da = l.partialWithRespectTo(p);
        Expression bda = mult(r, da);

        Expression db = r.partialWithRespectTo(p);
        Expression adb = mult(l, db);
        // (bda - abd)/(r^2)
        return div(sub(bda, adb), square(r));
    }
}
