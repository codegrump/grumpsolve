package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;
import java.util.Set;

import static grumpsolve.algebra.Constant.ONE;
import static grumpsolve.algebra.Constant.ZERO;

@Immutable
public final class Variable extends Expression implements Comparable<Variable> {

    private final long id;
    private final String display;

    public Variable(long id) {
        this(id, null);
    }

    public Variable(long id, String display) {
        this.id = id;
        this.display = display == null
                ? "x" + id 
                : display;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean dependsOn(@Nonnull Variable p) {
        return id == p.id;
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return solution.get(id);
    }

    @Override
    public Expression partialWithRespectTo(@Nonnull Variable p) {
        return (id == p.id ? ONE : ZERO);
    }

    @Override
    public int compareTo(@Nonnull Variable o) {
        return Long.compare(id, o.id);
    }

    @Override
    public String toString() {
        return display;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Variable && dependsOn((Variable) o);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    protected void addParameters(@Nonnull Set<Variable> variables) {
        variables.add(this);
    }

    @Override
    boolean isConstant() {
        return false; // since constants are folded on creation
    }
}
