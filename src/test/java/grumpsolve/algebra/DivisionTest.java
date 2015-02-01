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

public class DivisionTest {

    private static final Parameter x0 = new Parameter(0);
    private static final Parameter x1 = new Parameter(1);

    private static final Solution SOLUTION_1_2 = new Solution(ImmutableMap.of(0L, 1.0, 1L, 2.0));
    private static final Solution SOLUTION_3_4 = new Solution(ImmutableMap.of(0L, 3.0, 1L, 4.0));

    @Test
    public void divideByOne() throws Exception {
        Expression expression = Division.divide(x0, ONE);
        assertSame(x0, expression);
    }

    @Test
    public void zeroDivideAnything() throws Exception {
        Expression expression = Division.divide(ZERO, x0);
        assertSame(ZERO, expression);
    }


    @Test
    public void divideTwoConstants() throws Exception {
        Expression expression = Division.divide(ONE, HALF);
        assertTrue(expression.isConstant());
        assertEquals(2.0, cv(expression), 0);
    }

    @Test
    public void addTwoExpressions() throws Exception {
        Expression expression = Division.divide(x0, x1);
        assertTrue(expression instanceof Division);
        BinaryOperator o = (BinaryOperator) expression;
        assertSame(x0, o.l);
        assertSame(x1, o.r);
        assertEquals("x0/x1", expression.toString());
    }

    @Test
    public void evaluate() throws Exception {
        Expression expression = Division.divide(x0, x1);
        assertEquals(0.5, expression.evaluate(SOLUTION_1_2), 0.0);
        assertEquals(0.75, expression.evaluate(SOLUTION_3_4), 0.0);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        Expression expression = Division.divide(x0, x1);
        {
            Expression partial = expression.partialWithRespectTo(x0);
            assertEquals("x1/(x1^2)", partial.toString());
        }
        {
            Expression partial = expression.partialWithRespectTo(x1);
            assertEquals("(-x0)/(x1^2)", partial.toString());
        }
    }

}