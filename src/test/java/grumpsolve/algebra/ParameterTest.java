package grumpsolve.algebra;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import grumpsolve.system.Solution;
import org.junit.Test;

import static grumpsolve.algebra.Expressions.ONE;
import static grumpsolve.algebra.Expressions.ZERO;
import static org.junit.Assert.*;

public class ParameterTest {

    private static final Parameter x0 = new Parameter(0), x1 = new Parameter(1);

    private static final Solution SOLUTION_3 = new Solution(ImmutableMap.of(0L, 3.0));

    @Test
    public void evaluate() throws Exception {
        assertEquals(3.0, x0.evaluate(SOLUTION_3), 0.0);
    }

    @Test
    public void partialWithRespectTo() throws Exception {
        assertSame(ONE, x0.partialWithRespectTo(x0));
        assertSame(ZERO, x0.partialWithRespectTo(x1));
    }

    @Test
    public void parameters() throws Exception {
        assertEquals(ImmutableSet.of(x0), x0.parameters());
    }

    @Test
    public void dependsOn() throws Exception {
        assertTrue(x0.dependsOn(x0));
        assertFalse(x0.dependsOn(x1));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("x123", new Parameter(123).toString());
    }
}