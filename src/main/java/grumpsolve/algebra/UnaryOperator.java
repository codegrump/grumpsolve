package grumpsolve.algebra;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Set;

public abstract class UnaryOperator extends Expression {

    protected final Expression e;

    protected UnaryOperator(@Nonnull Expression e) {
        Preconditions.checkNotNull(e, "e cannot be null");
        this.e = e;
    }

    @Override
    public final boolean dependsOn(@Nonnull Parameter p) {
        return e.dependsOn(p);
    }

    @Override
    protected void addParameters(@Nonnull Set<Parameter> parameters) {
        e.addParameters(parameters);
    }

    @Override
    boolean isConstant() {
        return false; // since constants are folded on creation
    }
}
