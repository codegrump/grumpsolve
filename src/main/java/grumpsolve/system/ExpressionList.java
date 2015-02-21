package grumpsolve.system;

import com.google.common.collect.ImmutableList;
import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Parameter;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class ExpressionList {

    public static ExpressionList from(@Nonnull Expression e, Expression... ex) {
        ImmutableList<Expression> list = ImmutableList
                .<Expression>builder().add(e)
                .addAll(ImmutableList.copyOf(ex))
                .build();
        return new ExpressionList(list);
    }

    public static ExpressionList from(List<Expression> expressions) {
        return new ExpressionList(expressions);
    }

    private final List<Expression> expressions;

    private ExpressionList(@Nonnull List<Expression> expressions) {
        this.expressions = ImmutableList.copyOf(expressions);
    }

    public Set<Parameter> parameters() {
        final TreeSet<Parameter> parameters = new TreeSet<>();
        for (Expression expression : expressions) {
            parameters.addAll(expression.parameters());
        }
        return parameters;
    }

    public Expression get(int index) {
        return expressions.get(index);
    }

    public int size() {
        return expressions.size();
    }
}
