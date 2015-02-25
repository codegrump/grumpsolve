package grumpsolve.sketch;

import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Expressions;

public class V2 implements R2 {

    public static final V2 ORIGIN = new V2(Expressions.c(0.0), Expressions.c(0.0));

    private final Expression x, y;

    public V2(Expression x, Expression y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Expression x() {
        return x;
    }

    @Override
    public Expression y() {
        return y;
    }

    @Override
    public V2 plus(R2 other) {
        return new V2(Expressions.add(x, other.x()), Expressions.add(y, other.y()));
    }

    @Override
    public V2 minus(R2 other) {
        return new V2(Expressions.sub(x, other.x()), Expressions.sub(y, other.y()));
    }
}
