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

public class SquareTest {

    private static final Parameter x0 = new Parameter(0);
    private static final Parameter x1 = new Parameter(1);

    private static final Solution SOLUTION_3 = new Solution(ImmutableMap.of(0L, 3.0));

    @Test
    public void squareZero() throws Exception {
        Expression expression = Algebra.Square.square(ZERO);
        assertSame(ZERO, expression);
    }

    @Test
    public void squareOne() throws Exception {
        Expression expression = Algebra.Square.square(ONE);
        assertSame(ONE, expression);
    }

    @Test
    public void constant() throws Exception {
        Expression expression = Algebra.Square.square(HALF);
        assertTrue(expression.isConstant());
        assertEquals(0.25, cv(expression), 1.0e-12);
    }

    @Test
    public void parameter() throws Exception {
        Expression expression = Algebra.Square.square(x0);
        assertTrue(expression instanceof Algebra.Square);
        Algebra.UnaryOperator o = (Algebra.UnaryOperator) expression;
        assertSame(x0, o.e);
        assertEquals("x0^2", expression.toString());
    }

    @Test
    public void evaluate() throws Exception {
        Expression expression = Algebra.Square.square(x0);
        assertEquals(9.0, expression.evaluate(SOLUTION_3), 1.0e-12);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        Expression expression = Algebra.Square.square(x0);
        {
            Expression partial = expression.partialWithRespectTo(x0);
            assertEquals("2.0*x0", partial.toString());
        }
        {
            assertSame(ZERO, expression.partialWithRespectTo(x1));
        }
    }

}