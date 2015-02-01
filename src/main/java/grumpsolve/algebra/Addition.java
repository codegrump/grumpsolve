package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import static grumpsolve.algebra.Expressions.*;

@Immutable
final class Addition extends BinaryOperator {

    static Expression add(Expression l, Expression r) {
        return l == ZERO ? r
                : r == ZERO ? l
                : l instanceof Constant && r instanceof Constant ? c(cv(l) + cv(r))
                : new Addition(l, r);
    }

    private Addition(Expression l, Expression r) {
        super(l, r);
    }

    @Override
    public double evaluate(Solution solution) {
        return l.evaluate(solution) + r.evaluate(solution);
    }

    @Override
    public String toString() {
        String ls = toStringWithOptionalParens(l);
        String rs = toStringWithOptionalParens(r);
        return ls + "+" + rs;
    }

    @Override
    Expression partialWithRespectTo(Parameter p) {
        //da + db
        return add(l.partialWithRespectTo(p), r.partialWithRespectTo(p));
    }
}
