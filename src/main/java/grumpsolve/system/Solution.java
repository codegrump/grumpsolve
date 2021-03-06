package grumpsolve.system;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import grumpsolve.algebra.Variable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static Solution zero(@Nonnull Set<Variable> variables) {
        return constant(variables, 0.0);
    }

    public static Solution constant(@Nonnull Set<Variable> variables, double constant) {
        final Map<Long, Double> parameterValues = new HashMap<>();
        for (Variable p : variables) {
            parameterValues.put(p.getId(), constant);
        }
        return new Solution(parameterValues);
    }

    public static Builder from(@Nonnull Set<Variable> variables) {
        return zero(variables).builder();
    }

    private final Map<Long, Double> parameterValues;
    private final Set<Long> important;

    public Solution(@Nonnull Map<Long, Double> parameterValues) {
        this(parameterValues, ImmutableSet.<Long>of());
    }

    public Solution(@Nonnull Map<Long, Double> parameterValues, @Nonnull Set<Long> important) {
        this.parameterValues = ImmutableMap.copyOf(parameterValues);
        this.important = ImmutableSet.copyOf(important);
    }

    public double get(long parameterId) {
        check(parameterValues, parameterId);
        return parameterValues.get(parameterId);
    }

    public boolean isImportant(long id) {
        return important.contains(id);
    }

    public Builder builder() {
        return new Builder(parameterValues, important);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Map.Entry<Long, Double> entry : parameterValues.entrySet()) {
            boolean first = stringBuilder.length() == 1;
            if (!first) {
                stringBuilder.append(",");
            }
            stringBuilder.append("x").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return stringBuilder.append("}").toString();
    }

    public static class Builder {

        private final Map<Long, Double> parameterValues;
        private final Set<Long> important;

        private Builder(Map<Long, Double> parameterValues, Set<Long> important) {
            this.parameterValues = new HashMap<>(parameterValues);
            this.important = ImmutableSet.copyOf(important);
        }

        public double add(long parameterId, double value) {
            check(parameterValues, parameterId);

            double newValue = parameterValues.get(parameterId) + value;
            parameterValues.put(parameterId, newValue);
            return newValue;
        }

        public Builder set(@Nonnull Variable p, double v) {
            return set(p.getId(), v);
        }

        public Builder set(long parameterId, double v) {
            check(parameterValues, parameterId);
            parameterValues.put(parameterId, v);
            return this;
        }

        public double add(@Nonnull Variable p, double value) {
            return add(p.getId(), value);
        }

        public double get(long parameterId) {
            check(parameterValues, parameterId);
            return parameterValues.get(parameterId);
        }

        public Solution build() {
            return new Solution(parameterValues, important);
        }
    }

    private static void check(Map<Long, Double> parameterValues, Long parameterId) {
        if (!parameterValues.containsKey(parameterId)) {
            throw new IllegalArgumentException(String.format("parameter %d is not part of the solution", parameterId));
        }
    }
}
