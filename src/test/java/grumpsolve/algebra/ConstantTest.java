package grumpsolve.algebra;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstantTest {

    public static final Parameter x0 = new Parameter(0);

    @Test
    public void zero() throws Exception {
        assertSame(Algebra.Constant.ZERO, Algebra.Constant.constant(0.0));
    }

    @Test
    public void one() throws Exception {
        assertSame(Algebra.Constant.ONE, Algebra.Constant.constant(1.0));
    }

    @Test
    public void half() throws Exception {
        assertSame(Algebra.Constant.HALF, Algebra.Constant.constant(0.5));
    }

    @Test
    public void dependsOn() throws Exception {
        assertFalse(Algebra.Constant.ONE.dependsOn(null));
    }

    @Test
    public void evaluate() throws Exception {
        assertEquals(123.0, Algebra.Constant.constant(123.0).evaluate(null), 0.0);
    }

    @Test
    public void parameters() throws Exception {
        assertTrue(Algebra.Constant.ONE.parameters().isEmpty());
    }

    @Test
    public void partialWithRespectToo() throws Exception {
        assertSame(Algebra.Constant.ZERO, Algebra.Constant.constant(234).partialWithRespectTo(x0));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("1.5", Algebra.Constant.constant(1.5).toString());
    }
}