package grumpsolve.algebra;

import com.google.common.collect.ImmutableSet;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public abstract class Expression {

    public final Set<Parameter> parameters() {
        final Set<Parameter> parameters = new HashSet<>();
        addParameters(parameters);
        return parameters;
    }

    public abstract boolean dependsOn(@Nonnull Parameter p);

    public abstract double evaluate(@Nonnull Solution solution);

    protected abstract void addParameters(@Nonnull Set<Parameter> parameters);

    abstract Expression partialWithRespectTo(@Nonnull Parameter p);

    abstract boolean isConstant();

    static double cv(Expression e) {
        return ((Constant) e).getV();
    }


    private static Set<Class> excludeParens = ImmutableSet.<Class>of(ArcCos.class, ArcSin.class,
            Constant.class, Cos.class, Parameter.class, Sin.class, SquareRoot.class);

    static String toStringWithOptionalParens(Expression e) {
        return excludeParens.contains(e.getClass())
                ? e.toString()
                : "(" + e.toString() + ")";
    }
}
