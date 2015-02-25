package grumpsolve.sketch;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Expressions;
import grumpsolve.algebra.Variable;
import grumpsolve.sketch.geometry.Point;
import grumpsolve.system.NewtonSolver;
import grumpsolve.system.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Workspace {

    protected static final Expression ZERO = Expressions.ZERO;
    protected final Sketch root = Sketch.root();

    private final Set<Variable> variables = new HashSet<>();
    private final List<Expression> constraints = new ArrayList<>();
    private long nextVariableId = 1;

    public Workspace() {
        setup();
    }

    protected abstract void setup();

    protected Point point(Expression x, Expression y) {
        return root.point(x, y);
    }

    protected void constrain(Expression l, Expression r, Expression... rest) {
        constraints.add(sub(l, r));
        for (Expression rr : rest) {
            constraints.add(sub(l, rr));
        }
    }

    protected Variable var() {
        return var(null);
    }

    protected Variable var(String name) {
        Variable v = Expressions.var(nextVariableId++, name);
        variables.add(v);
        return v;
    }

    protected static Expression c(double c) {
        return Expressions.c(c);
    }

    protected static Expression neg(Expression e) {
        return Expressions.neg(e);
    }

    protected static Expression div(Expression num, Expression denom) {
        return Expressions.div(num, denom);
    }

    protected static Expression square(Expression e) {
        return Expressions.square(e);
    }

    protected static Expression sqrt(Expression e) {
        return Expressions.sqrt(e);
    }

    protected static Expression add(Expression l, Expression r, Expression... rest) {
        Expression toReturn = Expressions.add(l, r);
        for (Expression e : rest) {
            toReturn = Expressions.add(toReturn, e);
        }
        return toReturn;
    }

    protected static Expression sub(Expression l, Expression r, Expression... rest) {
        Expression toReturn = Expressions.sub(l, r);
        for (Expression e : rest) {
            toReturn = Expressions.sub(toReturn, e);
        }
        return toReturn;
    }

    protected static Expression distance(Point p, Point q) {
        final Expression dx2 = square(sub(p.x(), q.x())),
                dy2 = square(sub(p.y(), q.y()));
        return sqrt(add(dx2, dy2));
    }

    public Solution solve() {
        final Set<Variable> constrainedVariables = constraints.stream().map(Expression::variables)
                .flatMap(s -> s.stream()).collect(Collectors.toSet());
        {
            ImmutableSet<Variable> whereDidTheseComeFrom = Sets.difference(constrainedVariables, variables)
                    .immutableCopy();
            if (!whereDidTheseComeFrom.isEmpty()) {
                throw new IllegalStateException();
            }
        }
        NewtonSolver solver = NewtonSolver.make(constraints);
        Solution zero = Solution.constant(constrainedVariables, 1.0);
        return solver.newtonSolve(zero);
    }

    public void draw(Solution solution) {
        root.draw(solution);
    }
}
