package grumpsolve.algebra;

import com.google.common.collect.ImmutableMap;
import grumpsolve.system.Solution;
import org.junit.Test;

import static grumpsolve.algebra.Expression.cv;
import static grumpsolve.algebra.Expressions.ONE;
import static grumpsolve.algebra.Expressions.ZERO;
import static org.junit.Assert.*;

public class NegationTest {

    private static final Parameter x0 = new Parameter(0), x1 = new Parameter(1);

    private static final Solution SOLUTION_3 = new Solution(ImmutableMap.of(0L, 3.0));

    @Test
    public void negateZero() throws Exception {
        Expression expression = Algebra.Negation.negate(ZERO);
        assertSame(ZERO, expression);
    }

    @Test
    public void negateConstant() throws Exception {
        Expression expression = Algebra.Negation.negate(ONE);
        assertTrue(expression.isConstant());
        assertEquals(-1.0, cv(expression), 0.0);
    }

    @Test
    public void negateExpression() throws Exception {
        Expression expression = Algebra.Negation.negate(x0);
        assertTrue(expression instanceof Algebra.Negation);
        assertEquals("-x0", expression.toString());
    }

    @Test
    public void evaluate() throws Exception {
        Expression expression = Algebra.Negation.negate(x0);
        assertEquals(-3.0, expression.evaluate(SOLUTION_3), 0.0);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        Expression expression = Algebra.Negation.negate(x0);
        {
            Expression partial = expression.partialWithRespectTo(x0);
            assertEquals("-1.0", partial.toString());
        }
        {
            Expression partial = expression.partialWithRespectTo(x1);
            assertSame(ZERO, partial);
        }
    }

}