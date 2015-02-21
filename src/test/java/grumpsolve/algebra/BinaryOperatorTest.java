package grumpsolve.algebra;

import com.google.common.collect.ImmutableSet;
import grumpsolve.system.Solution;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinaryOperatorTest {

    private static final Parameter x0 = new Parameter(0),
            x1 = new Parameter(1),
            x2 = new Parameter(2);

    @Test
    public void dependsOn() throws Exception {
        Expression e = new Algebra.BinaryOperator(x0, x1) {

            @Override
            public double evaluate(Solution solution) {
                throw new UnsupportedOperationException();
            }

            @Override
            Expression partialWithRespectTo(Parameter p) {
                throw new UnsupportedOperationException();
            }
        };
        assertTrue(e.dependsOn(x0));
        assertTrue(e.dependsOn(x1));
        assertFalse(e.dependsOn(x2));
    }

    @Test
    public void parameters() throws Exception {
        Expression e = new Algebra.BinaryOperator(x0, x1) {

            @Override
            public double evaluate(Solution solution) {
                throw new UnsupportedOperationException();
            }

            @Override
            Expression partialWithRespectTo(Parameter p) {
                throw new UnsupportedOperationException();
            }
        };
        assertEquals(ImmutableSet.of(x0, x1), e.parameters());
    }
}