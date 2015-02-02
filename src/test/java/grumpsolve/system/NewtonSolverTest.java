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

    @Test
    public void tetrahedron_point_at_origin() throws Exception {
        final ExpressionList expressions;
        {
            //tip of tetrahedron of circle at (5, 3.5)
            Parameter x0 = new Parameter(0);
            Parameter y0 = new Parameter(1);
            Parameter z0 = new Parameter(2);

            //south-west corner
            Parameter x1 = new Parameter(10);
            Parameter y1 = new Parameter(11);
            Parameter z1 = new Parameter(12);

            //south-east corner
            Parameter x2 = new Parameter(20);
            Parameter y2 = new Parameter(21);
            Parameter z2 = new Parameter(22);

            //north-west corner
            Parameter x3 = new Parameter(30);
            Parameter y3 = new Parameter(31);
            Parameter z3 = new Parameter(32);

            //north-east corner
            Parameter x4 = new Parameter(40);
            Parameter y4 = new Parameter(41);
            Parameter z4 = new Parameter(42);

            //common expressions
            Expression topToSouthWest = sqrt(add(square(sub(x0, x1)), square(sub(y0, y1)), square(sub(z0, z1))));
            Expression topToSouthEast = sqrt(add(square(sub(x0, x2)), square(sub(y0, y2)), square(sub(z0, z2))));
            Expression topToNorthWest = sqrt(add(square(sub(x0, x3)), square(sub(y0, y3)), square(sub(z0, z3))));
            Expression topToNorthEast = sqrt(add(square(sub(x0, x4)), square(sub(y0, y4)), square(sub(z0, z4))));

            //expressions to solve for
            //zero because they are on origin
            //noinspection UnnecessaryLocalVariable
            Expression e0 = x0;
            //noinspection UnnecessaryLocalVariable
            Expression e1 = y0;
            //noinspection UnnecessaryLocalVariable
            Expression e2 = z0;

            //all sides are equal
            Expression e3 = sub(ONE, topToSouthWest);
            Expression e4 = sub(topToSouthWest, topToSouthEast);
            Expression e5 = sub(topToSouthWest, topToNorthWest);
            Expression e6 = sub(topToSouthWest, topToNorthEast);

            //the base of the tetrahedron are all horizontally aligned
            Expression e7 = sub(y1, y2);
            Expression e8 = sub(y1, y3);
            Expression e9 = sub(y1, y4);

            //sides of base must be aligned
            Expression e10 = sub(x1, x3);

            expressions = ExpressionList.from(e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
        }

        final Set<Parameter> parameters = expressions.parameters();
        //because this is a newton solver, must give a reasonable initial solution
        final Solution s0 = Solution.from(parameters)
                .set(0, 0.0).set(1, 0.0).set(2, 0.0)
                .set(10, -1.0).set(11, -1.0).set(12, -1.0) //south west
                .set(20, 1.0).set(21, -1.0).set(22, -1.0) //south east
                .set(30, -1.0).set(31, -1.0).set(32, 1.0) //north west
                .set(40, 1.0).set(41, -1.0).set(42, 1.0) //north east
                .build();
        final NewtonSolver newtonSolver = NewtonSolver.make(expressions);
        final Solution solution = newtonSolver.newtonSolve(s0);

        assertNotNull(solution);

        //tip of tetrahedron of circle at (5, 3.5)
        double x0 = solution.get(0L);
        double y0 = solution.get(1L);
        double z0 = solution.get(2L);
        assertEquals(0.0, x0, 1.0e-6);
        assertEquals(0.0, y0, 1.0e-6);
        assertEquals(0.0, z0, 1.0e-6);

        double magicNumber = Math.sqrt(1.0/3.0);
        //south-west corner
        double x1 = solution.get(10L);
        double y1 = solution.get(11L);
        double z1 = solution.get(12L);
        assertEquals(-magicNumber, x1, 1.0e-6);
        assertEquals(-magicNumber, y1, 1.0e-6);
        assertEquals(-magicNumber, z1, 1.0e-6);

        //south-east corner
        double x2 = solution.get(20L);
        double y2 = solution.get(21L);
        double z2 = solution.get(22L);
        assertEquals(magicNumber, x2, 1.0e-6);
        assertEquals(-magicNumber, y2, 1.0e-6);
        assertEquals(-magicNumber, z2, 1.0e-6);

        //north-west corner
        double x3 = solution.get(30L);
        double y3 = solution.get(31L);
        double z3 = solution.get(32L);
        assertEquals(-magicNumber, x3, 1.0e-6);
        assertEquals(-magicNumber, y3, 1.0e-6);
        assertEquals(magicNumber, z3, 1.0e-6);

        //north-east corner
        double x4 = solution.get(40L);
        double y4 = solution.get(41L);
        double z4 = solution.get(42L);
        assertEquals(magicNumber, x4, 1.0e-6);
        assertEquals(-magicNumber, y4, 1.0e-6);
        assertEquals(magicNumber, z4, 1.0e-6);
    }

}