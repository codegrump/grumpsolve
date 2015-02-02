package grumpsolve.system;

import com.google.common.collect.ImmutableList;
import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Parameter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ExpressionList {

    public static ExpressionList from(Expression... e) {
        return new ExpressionList(ImmutableList.copyOf(e));
    }

    private final List<Expression> expressions;

    private ExpressionList(List<Expression> expressions) {
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
