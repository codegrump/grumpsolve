package grumpsolve.algebra;

import com.google.common.collect.ImmutableMap;
import grumpsolve.system.Solution;
import org.junit.Test;

import static grumpsolve.algebra.Expression.cv;
import static grumpsolve.algebra.Expressions.HALF;
import static grumpsolve.algebra.Expressions.ONE;
import static grumpsolve.algebra.Expressions.ZERO;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SquareRootTest {

    private static final Parameter x0 = new Parameter(0);
    private static final Parameter x1 = new Parameter(1);

    private static final Solution SOLUTION_3 = new Solution(ImmutableMap.of(0L, 3.0));

    @Test
    public void squareRootZero() throws Exception {
        Expression expression = SquareRoot.squareRoot(ZERO);
        assertSame(ZERO, expression);
    }

    @Test
    public void squareRootOne() throws Exception {
        Expression expression = SquareRoot.squareRoot(ONE);
        assertSame(ONE, expression);
    }

    @Test
    public void constant() throws Exception {
        Expression expression = SquareRoot.squareRoot(HALF);
        assertTrue(expression.isConstant());
        assertEquals(Math.sqrt(0.5), cv(expression), 1.0e-12);
    }

    @Test
    public void parameter() throws Exception {
        Expression expression = SquareRoot.squareRoot(x0);
        assertTrue(expression instanceof SquareRoot);
        UnaryOperator o = (UnaryOperator) expression;
        assertSame(x0, o.e);
        assertEquals("sqrt(x0)", expression.toString());
    }

    @Test
    public void evaluate() throws Exception {
        Expression expression = SquareRoot.squareRoot(x0);
        assertEquals(Math.sqrt(3.0), expression.evaluate(SOLUTION_3), 1.0e-12);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        Expression expression = SquareRoot.squareRoot(x0);
        {
            Expression partial = expression.partialWithRespectTo(x0);
            assertEquals("0.5/sqrt(x0)", partial.toString());
        }
        {
            assertSame(ZERO, expression.partialWithRespectTo(x1));
        }
    }

}