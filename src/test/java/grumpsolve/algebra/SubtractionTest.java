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

public class SubtractionTest {


    private static final Parameter x0 = new Parameter(0);
    private static final Parameter x1 = new Parameter(1);

    private static final Solution SOLUTION_1_2 = new Solution(ImmutableMap.of(0L, 1.0, 1L, 2.0));
    private static final Solution SOLUTION_3_4 = new Solution(ImmutableMap.of(0L, 3.0, 1L, 4.0));

    @Test
    public void subtractZero() throws Exception {
        Expression expression = Algebra.Subtraction.subtract(x0, ZERO);
        assertSame(x0, expression);
    }

    @Test
    public void subtractFromZero() throws Exception {
        Expression expression = Algebra.Subtraction.subtract(ZERO, x0);
        assertEquals("-x0", expression.toString());
    }

    @Test
    public void subtractConstants() throws Exception {
        Expression expression = Algebra.Subtraction.subtract(ONE, HALF);
        assertSame(HALF, expression);
    }

    @Test
    public void subtractTwoExpressions() throws Exception {
        Expression expression = Algebra.Subtraction.subtract(x0, x1);
        assertTrue(expression instanceof Algebra.Subtraction);
        Algebra.BinaryOperator o = (Algebra.BinaryOperator) expression;
        assertSame(x0, o.l);
        assertSame(x1, o.r);
        assertEquals("x0-x1", expression.toString());
    }

    @Test
    public void evaluate() throws Exception {
        Expression expression = Algebra.Subtraction.subtract(x0, x1);
        assertEquals(-1.0, expression.evaluate(SOLUTION_1_2), 0.0);
        assertEquals(-1.0, expression.evaluate(SOLUTION_3_4), 0.0);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        {
            Expression expression = Algebra.Subtraction.subtract(x0, ONE);
            assertSame(ONE, expression.partialWithRespectTo(x0));
            assertSame(ZERO, expression.partialWithRespectTo(x1));
        }
        {
            Expression expression = Algebra.Subtraction.subtract(ONE, x0);
            assertEquals(-1.0, cv(expression.partialWithRespectTo(x0)), 0.0);
            assertSame(ZERO, expression.partialWithRespectTo(x1));
        }
    }

}