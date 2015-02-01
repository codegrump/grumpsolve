package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import static grumpsolve.algebra.Expressions.*;

@Immutable
final class Subtraction extends BinaryOperator {

    static Expression subtract(Expression l, Expression r) {
        return r == ZERO ? l
                : l == ZERO ? neg(r)
                : l instanceof Constant && r instanceof Constant ? c(cv(l) - cv(r))
                : new Subtraction(l, r);
    }

    private Subtraction(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    public double evaluate(Solution solution) {
        return l.evaluate(solution) - r.evaluate(solution);
    }

    @Override
    public String toString() {
        String ls = toStringWithOptionalParens(l);
        String rs = toStringWithOptionalParens(r);
        return ls + "-" + rs;
    }

    @Override
    Expression partialWithRespectTo(Parameter p) {
        //da - db
        return sub(l.partialWithRespectTo(p), r.partialWithRespectTo(p));
    }
}
