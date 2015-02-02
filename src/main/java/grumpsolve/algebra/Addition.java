package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;

import static grumpsolve.algebra.Expressions.*;

@Immutable
final class Addition extends BinaryOperator {

    static Expression add(@Nonnull Expression l, @Nonnull Expression r) {
        return l == ZERO ? r
                : r == ZERO ? l
                : l instanceof Constant && r instanceof Constant ? c(cv(l) + cv(r))
                : new Addition(l, r);
    }

    private Addition(@Nonnull Expression l, @Nonnull Expression r) {
        super(l, r);
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return l.evaluate(solution) + r.evaluate(solution);
    }

    @Override
    public String toString() {
        String ls = toStringWithOptionalParens(l);
        String rs = toStringWithOptionalParens(r);
        return ls + "+" + rs;
    }

    @Override
    Expression partialWithRespectTo(@Nonnull Parameter p) {
        //da + db
        return add(l.partialWithRespectTo(p), r.partialWithRespectTo(p));
    }
}
