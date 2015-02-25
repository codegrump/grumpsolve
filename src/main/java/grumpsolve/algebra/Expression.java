package grumpsolve.algebra;

import com.google.common.collect.ImmutableSet;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public abstract class Expression {

    public final Set<Variable> variables() {
        final Set<Variable> variables = new HashSet<>();
        addParameters(variables);
        return variables;
    }

    public abstract boolean dependsOn(@Nonnull Variable p);

    public abstract double evaluate(@Nonnull Solution solution);

    protected abstract void addParameters(@Nonnull Set<Variable> variables);

    abstract Expression partialWithRespectTo(@Nonnull Variable p);

    abstract boolean isConstant();

    static double cv(Expression e) {
        return ((Constant) e).getV();
    }


    private static Set<Class> excludeParens = ImmutableSet.<Class>of(ArcCos.class, ArcSin.class,
            Constant.class, Cos.class, Variable.class, Sin.class, SquareRoot.class);

    static String toStringWithOptionalParens(Expression e) {
        return excludeParens.contains(e.getClass())
                ? e.toString()
                : "(" + e.toString() + ")";
    }
}
