package grumpsolve.algebra;

import com.google.common.base.Preconditions;

import java.util.Set;

abstract class BinaryOperator extends Expression {

    protected final Expression l, r;

    BinaryOperator(Expression l, Expression r) {
        Preconditions.checkNotNull(l, "l cannot be null");
        Preconditions.checkNotNull(r, "r cannot be null");
        this.l = l;
        this.r = r;
    }

    @Override
    public final boolean dependsOn(Parameter p) {
        return l.dependsOn(p) || r.dependsOn(p);
    }

    @Override
    protected void addParameters(Set<Parameter> parameters) {
        l.addParameters(parameters);
        r.addParameters(parameters);
    }

    @Override
    boolean isConstant() {
        return false; // since constants are folded on creation
    }
}
