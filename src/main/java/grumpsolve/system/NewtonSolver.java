package grumpsolve.system;

import com.google.common.collect.ImmutableList;
import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Parameter;
import no.uib.cipr.matrix.*;

import java.util.List;

import static grumpsolve.algebra.Expressions.partialWithRespectTo;
import static grumpsolve.Constants.*;

public class NewtonSolver {

    private static final int NUM_NEWTON_ITER = 50;

    public static NewtonSolver make(ExpressionList expressions) {
        ImmutableList<Parameter> parameters = ImmutableList.copyOf(expressions.parameters());

        int m = expressions.size();
        int n = parameters.size();

        Expression[][] A = new Expression[m][n];
        Expression[] B = new Expression[m];

        for (int i = 0; i < expressions.size(); i++) {
            Expression expression = expressions.get(i);
            B[i] = expression;

            for (int j = 0; j < parameters.size(); j++) {
                Parameter parameter = parameters.get(j);
                A[i][j] = partialWithRespectTo(expression, parameter);
            }
        }
        return new NewtonSolver(m, n, A, B, parameters);
    }

    private final int m, n;
    private final Expression[][] A;
    private final Expression[] B;
    private final List<Parameter> parameters;

    private NewtonSolver(int m, int n, Expression[][] A, Expression[] B, List<Parameter> parameters) {
        this.m = m;
        this.n = n;
        this.A = A;
        this.B = B;
        this.parameters = parameters;
    }

    public Solution newtonSolve(Solution s0) {
        int iter = 0;
        Solution solution = s0;
        boolean converged = false;

        final Workspace workspace = new Workspace();
        do {
            workspace.evaluateJacobian(solution);
            if(!workspace.solveLeastSquares()) break;
            solution = workspace.newtonStep(solution);
            if(solution == null){
                break;
            }
            workspace.reevaluateB(solution);
            converged = workspace.isConverged();
        } while (iter++ < NUM_NEWTON_ITER && !converged);

        return converged ? solution : null;
    }

    private final class Workspace {

        final DenseMatrix a, aat;
        final DenseVector b, scale, z, x;

        private Workspace() {
            a = new DenseMatrix(m, n);
            aat = new DenseMatrix(m, m);
            b = new DenseVector(m);
            scale = new DenseVector(n);
            z = new DenseVector(m);
            x = new DenseVector(n);
        }

        void evaluateJacobian(Solution solution) {
            for (MatrixEntry entry : a) {
                Expression e = A[entry.row()][entry.column()];
                double v = e.evaluate(solution);
                entry.set(v);
            }

            reevaluateB(solution);
        }

        boolean solveLeastSquares() {
            a.transBmult(a, aat);
            try {
                aat.solve(b, z);
            } catch (MatrixSingularException e) {
                return false;
            }
            a.transMult(z, x);
            return true;
        }

        private Solution newtonStep(Solution solution) {
            Solution.Builder newSolution = solution.builder();
            for(int i = 0; i < n; i++) {
                Parameter p = parameters.get(i);
                double value = newSolution.add(p, - x.get(i));
                if(Double.isNaN(value)) {
                    newSolution = null;
                    break;
                }
            }
            return newSolution == null ? null : newSolution.build();
        }

        public void reevaluateB(Solution solution) {
            for (VectorEntry entry : b) {
                Expression expression = B[entry.index()];
                double value = expression.evaluate(solution);
                entry.set(value);
            }
        }

        public boolean isConverged() {
            boolean converged = true;
            for (VectorEntry entry : b) {
                double bi = entry.get();
                if(Double.isNaN(bi) || Math.abs(bi) > NEWTON_CONVERGE_TOLERANCE) {
                    converged = false;
                    break;
                }
            }
            return converged;
        }
    }

}
