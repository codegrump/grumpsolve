package grumpsolve.algebra;

import com.google.common.collect.ImmutableSet;
import grumpsolve.system.Solution;

import java.util.HashSet;
import java.util.Set;

public abstract class Expression {

    public final Set<Parameter> parameters() {
        final Set<Parameter> parameters = new HashSet<>();
        addParameters(parameters);
        return parameters;
    }

    public abstract boolean dependsOn(Parameter p);

    public abstract double evaluate(Solution solution);

    protected abstract void addParameters(Set<Parameter> parameters);

    abstract Expression partialWithRespectTo(Parameter p);

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
