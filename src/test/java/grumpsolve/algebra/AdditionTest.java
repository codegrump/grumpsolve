package grumpsolve.algebra;

import com.google.common.collect.ImmutableMap;
import grumpsolve.system.Solution;
import org.junit.Test;

import static grumpsolve.algebra.Expression.cv;
import static grumpsolve.algebra.Expressions.*;
import static org.junit.Assert.*;

public class AdditionTest {

    private static final Parameter x0 = new Parameter(0);
    private static final Parameter x1 = new Parameter(1);

    private static final Solution SOLUTION_1_2 = new Solution(ImmutableMap.of(0L, 1.0, 1L, 2.0));
    private static final Solution SOLUTION_3_4 = new Solution(ImmutableMap.of(0L, 3.0, 1L, 4.0));

    @Test
    public void addWithZeroOnRight() throws Exception {
        Expression expression = Algebra.Addition.add(x0, ZERO);
        assertSame(x0, expression);
    }

    @Test
    public void addWithZeroOnLeft() throws Exception {
        Expression expression = Algebra.Addition.add(ZERO, x0);
        assertSame(x0, expression);
    }

    @Test
    public void addTwoConstants() throws Exception {
        Expression expression = Algebra.Addition.add(ONE, HALF);
        assertTrue(expression.isConstant());
        assertEquals(1.5, cv(expression), 0.0);
    }

    @Test
    public void addTwoExpressions() throws Exception {
        Expression expression = Algebra.Addition.add(x0, x1);
        assertTrue(expression instanceof Algebra.Addition);
        Algebra.BinaryOperator o = (Algebra.BinaryOperator) expression;
        assertSame(x0, o.l);
        assertSame(x1, o.r);
        assertEquals("x0+x1", expression.toString());
    }

    @Test
     public void evaluate() throws Exception {
        Expression expression = Algebra.Addition.add(x0, x1);
        assertEquals(3.0, expression.evaluate(SOLUTION_1_2), 0.0);
        assertEquals(7.0, expression.evaluate(SOLUTION_3_4), 0.0);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        {
            Expression expression = Algebra.Addition.add(x0, ONE);
            assertSame(ONE, expression.partialWithRespectTo(x0));
            assertSame(ZERO, expression.partialWithRespectTo(x1));
        }
        {
            Expression expression = Algebra.Addition.add(ONE, x0);
            assertSame(ONE, expression.partialWithRespectTo(x0));
            assertSame(ZERO, expression.partialWithRespectTo(x1));
        }
    }
}