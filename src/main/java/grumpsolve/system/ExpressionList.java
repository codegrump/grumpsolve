package grumpsolve.system;

import com.google.common.collect.ImmutableList;
import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Variable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ExpressionList {

    public static ExpressionList from(@Nonnull Expression e, Expression... ex) {
        ImmutableList<Expression> list = ImmutableList
                .<Expression>builder().add(e)
                .addAll(ImmutableList.copyOf(ex))
                .build();
        return new ExpressionList(list);
    }

    private final List<Expression> expressions;

    ExpressionList(@Nonnull List<Expression> expressions) {
        this.expressions = ImmutableList.copyOf(expressions);
    }

    public Set<Variable> parameters() {
        final TreeSet<Variable> variables = new TreeSet<>();
        for (Expression expression : expressions) {
            variables.addAll(expression.variables());
        }
        return variables;
    }

    public Expression get(int index) {
        return expressions.get(index);
    }

    public int size() {
        return expressions.size();
    }
}
