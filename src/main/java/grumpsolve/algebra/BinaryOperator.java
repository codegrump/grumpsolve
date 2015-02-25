package grumpsolve.algebra;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Set;

abstract class BinaryOperator extends Expression {

    protected final Expression l, r;

    BinaryOperator(@Nonnull Expression l, @Nonnull Expression r) {
        Preconditions.checkNotNull(l, "l cannot be null");
        Preconditions.checkNotNull(r, "r cannot be null");
        this.l = l;
        this.r = r;
    }

    @Override
    public final boolean dependsOn(@Nonnull Variable p) {
        return l.dependsOn(p) || r.dependsOn(p);
    }

    @Override
    protected void addParameters(@Nonnull Set<Variable> variables) {
        l.addParameters(variables);
        r.addParameters(variables);
    }

    @Override
    boolean isConstant() {
        return false; //assuming constants are folded on creation
    }
}
