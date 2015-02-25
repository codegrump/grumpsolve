package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;
import java.util.Set;

@Immutable
final class Constant extends Expression {

    static final Constant ZERO = new Constant(0.0),
    HALF = new Constant(0.5),
    ONE = new Constant(1.0);

    static Constant constant(double v) {
        return v == 0.0 ? Constant.ZERO
                : v == 1.0 ? Constant.ONE
                : v == 0.5 ? Constant.HALF
                : new Constant(v);
    }


    private final double v;

    private Constant(double v) {
        this.v = v;
    }

    @Override
    public boolean dependsOn(@Nonnull Variable p) {
        return false;
    }

    @Override
    public double evaluate(Solution solution) {
        return v;
    }

    @Override
    public String toString() {
        return Double.toString(v);
    }

    @Override
    protected void addParameters(@Nonnull Set<Variable> variables) {
        //no addParameters
    }

    @Override
    Expression partialWithRespectTo(Variable p) {
        return ZERO;
    }

    @Override
    boolean isConstant() {
        return true;
    }

    double getV() {
        return v;
    }
}
