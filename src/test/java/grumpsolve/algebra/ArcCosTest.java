package grumpsolve.algebra;

import com.google.common.collect.ImmutableMap;
import grumpsolve.system.Solution;
import org.junit.Test;

import static grumpsolve.algebra.Expression.cv;
import static grumpsolve.algebra.Expressions.ONE;
import static grumpsolve.algebra.Expressions.ZERO;
import static org.junit.Assert.*;

public class ArcCosTest {

    private static final Parameter x0 = new Parameter(0);
    private static final Parameter x1 = new Parameter(1);

    private static final Solution SOLUTION_3_4 = new Solution(ImmutableMap.of(0L, 3.0, 1L, 4.0));

    @Test
    public void constant() throws Exception {
        Expression expression = Algebra.ArcCos.arccos(ONE);
        assertTrue(expression.isConstant());
        assertEquals(Math.acos(1.0), cv(expression), 1.0e-12);
    }

    @Test
    public void parameter() throws Exception {
        Expression expression = Algebra.ArcCos.arccos(x0);
        assertTrue(expression instanceof Algebra.ArcCos);
        Algebra.UnaryOperator o = (Algebra.UnaryOperator) expression;
        assertSame(x0, o.e);
        assertEquals("acos(x0)", expression.toString());
    }

    @Test
    public void evaluate() throws Exception {
        Expression expression = Algebra.ArcCos.arccos(x0);
        assertEquals(Math.acos(3.0), expression.evaluate(SOLUTION_3_4), 1.0e-12);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        Expression expression = Algebra.ArcCos.arccos(x0);
        {
            Expression partial = expression.partialWithRespectTo(x0);
            assertEquals("-(1.0/sqrt(1.0-(x0^2)))", partial.toString());
        }
        {
            assertSame(ZERO, expression.partialWithRespectTo(x1));
        }
    }
}