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

public class MultiplicationTest {

    private static final Parameter x0 = new Parameter(0);
    private static final Parameter x1 = new Parameter(1);

    private static final Solution SOLUTION_1_2 = new Solution(ImmutableMap.of(0L, 1.0, 1L, 2.0));
    private static final Solution SOLUTION_3_4 = new Solution(ImmutableMap.of(0L, 3.0, 1L, 4.0));

    @Test
    public void multiplyZeroRight() throws Exception {
        Expression expression = Multiplication.multiply(x0, ZERO);
        assertSame(ZERO, expression);
    }

    @Test
    public void multiplyZeroLeft() throws Exception {
        Expression expression = Multiplication.multiply(ZERO, x0);
        assertSame(ZERO, expression);
    }

    @Test
    public void multiplyOneRight() throws Exception {
        Expression expression = Multiplication.multiply(x0, ONE);
        assertSame(x0, expression);
    }

    @Test
    public void multiplyOneLeft() throws Exception {
        Expression expression = Multiplication.multiply(ONE, x0);
        assertSame(x0, expression);
    }

    @Test
    public void multiplyTwoConstants() throws Exception {
        Expression expression = Multiplication.multiply(HALF, HALF);
        assertTrue(expression.isConstant());
        assertEquals(0.25, cv(expression), 0.0);
    }

    @Test
    public void multiplyTwoExpressions() throws Exception {
        Expression expression = Multiplication.multiply(x0, x1);
        assertTrue(expression instanceof Multiplication);
        BinaryOperator o = (BinaryOperator) expression;
        assertSame(x0, o.l);
        assertSame(x1, o.r);
        assertEquals("x0*x1", expression.toString());
    }

    @Test
    public void evaluate() throws Exception {
        Expression expression = Multiplication.multiply(x0, x1);
        assertEquals(2.0, expression.evaluate(SOLUTION_1_2), 0.0);
        assertEquals(12.0, expression.evaluate(SOLUTION_3_4), 0.0);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        Expression expression = Multiplication.multiply(x0, x1);
        {
            Expression partial = expression.partialWithRespectTo(x0);
            assertEquals("x1", partial.toString());
        }
        {
            Expression partial = expression.partialWithRespectTo(x1);
            assertEquals("x0", partial.toString());
        }
    }

}