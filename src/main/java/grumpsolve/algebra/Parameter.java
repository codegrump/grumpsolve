package grumpsolve.algebra;

import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;
import java.util.Set;

import static grumpsolve.algebra.Constant.ONE;
import static grumpsolve.algebra.Constant.ZERO;

@Immutable
public final class Parameter extends Expression implements Comparable<Parameter> {

    private final long id;

    public Parameter(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean dependsOn(@Nonnull Parameter p) {
        return id == p.id;
    }

    @Override
    public double evaluate(@Nonnull Solution solution) {
        return solution.get(id);
    }

    @Override
    public Expression partialWithRespectTo(@Nonnull Parameter p) {
        return (id == p.id ? ONE : ZERO);
    }

    @Override
    public int compareTo(@Nonnull Parameter o) {
        return Long.compare(id, o.id);
    }

    @Override
    public String toString() {
        return "x" + id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Parameter && dependsOn((Parameter) o);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    protected void addParameters(@Nonnull Set<Parameter> parameters) {
        parameters.add(this);
    }

    @Override
    boolean isConstant() {
        return false; // since constants are folded on creation
    }
}
