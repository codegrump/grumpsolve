package grumpsolve.system;

import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Parameter;
import org.junit.Test;

import java.util.Set;

import static grumpsolve.algebra.Expressions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NewtonSolverTest {

    @Test
    public void simple_fullyConstrained() throws Exception {
        final ExpressionList expressions;
        {
            Parameter x0 = new Parameter(0);
            Parameter x1 = new Parameter(1);

            Expression e0 = sub(x0, 5);  // x0 = 5
            Expression e1 = sub(mult(2, x0), x1); // 2x0 = x1
            expressions = ExpressionList.from(e0, e1);
        }

        final Set<Parameter> parameters = expressions.parameters();
        final Solution s0 = Solution.from(parameters).set(0, 0.5).set(1, 1).build();
        final NewtonSolver newtonSolver = NewtonSolver.make(expressions);
        final Solution solution = newtonSolver.newtonSolve(s0);

        assertNotNull(solution); //assert we are converged
        assertEquals(5.0, solution.get(0L), 1.0e-6);
        assertEquals(10.0, solution.get(1L), 1.0e-6);
    }

    @Test
    public void pointOnACircle_almostFullyConstrained() throws Exception {
        final ExpressionList expressions;
        {
            Parameter x0 = new Parameter(0);
            Parameter x1 = new Parameter(1);

            Expression e0 = sub(add(square(x0), square(x1)), 1);
            Expression e1 = sub(x0, 0.5); // 2x0 = x1
            expressions = ExpressionList.from(e0, e1);
        }

        final Set<Parameter> parameters = expressions.parameters();
        final Solution s0 = Solution.from(parameters).set(0, 0.2).set(1, 0.1).build();
        final NewtonSolver newtonSolver = NewtonSolver.make(expressions);
        final Solution solution = newtonSolver.newtonSolve(s0);

        assertNotNull(solution);
        assertEquals(0.5, solution.get(0L), 1.0e-6);
        assertEquals(Math.sqrt(1 - 0.25), Math.abs(solution.get(1L)), 1.0e-6); //point could be above or below x axis
    }

    @Test
    public void pointOnACircle_underConstrained() throws Exception {
        final ExpressionList expressions;
        {
            Parameter x0 = new Parameter(0);
            Parameter x1 = new Parameter(1);

            Expression e0 = sub(add(square(x0), square(x1)), 1);
            expressions = ExpressionList.from(e0);
        }

        final Set<Parameter> parameters = expressions.parameters();
        final Solution s0 = Solution.from(parameters).set(0, 0.2).set(1, 0.1).build();
        final NewtonSolver newtonSolver = NewtonSolver.make(expressions);
        final Solution solution = newtonSolver.newtonSolve(s0);

        assertNotNull(solution);
        double x0 = solution.get(0L);
        double x1 = solution.get(1L);
        assertEquals(1.0, Math.sqrt(x0 * x0 + x1 * x1), 1.0e-6);
    }

    @Test
    public void pointOnATranslatedCircle_underConstrained() throws Exception {
        final ExpressionList expressions;
        {
            //center of circle at (5, 3.5)
            Parameter x0 = new Parameter(0);
            Parameter x1 = new Parameter(1);

            Expression e0 = sub(x0, 5);
            Expression e1 = sub(x1, 3.5);

            //point on the circle is .5 away from center in the x direction, radius is 1
            Parameter x2 = new Parameter(2);
            Parameter x3 = new Parameter(3);

            Expression e2 = sub(x2, add(x0, 0.5));
            Expression e3 = sub(sqrt(add(square(sub(x2, x0)), square(sub(x3, x1)))), 1);

            expressions = ExpressionList.from(e0, e1, e2, e3);
        }

        final Set<Parameter> parameters = expressions.parameters();
        final Solution s0 = Solution.from(parameters).set(0, 0.2).set(1, 0.1).build();
        final NewtonSolver newtonSolver = NewtonSolver.make(expressions);
        final Solution solution = newtonSolver.newtonSolve(s0);

        assertNotNull(solution);

        //assert center of the circle
        double x0 = solution.get(0L);
        double x1 = solution.get(1L);
        assertEquals(5.0, x0, 1.0e-6);
        assertEquals(3.5, x1, 1.0e-6);

        //point on the circle
        double x2 = solution.get(2L);
        double x3 = solution.get(3L);
        assertEquals(0.5 + 5.0, x2, 1.0e-6);
        assertEquals(Math.sqrt(0.75), Math.abs(x3 - 3.5), 1.0e-6 ); //point could be above or below the center of circle
    }

}