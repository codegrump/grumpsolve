package grumpsolve.sketch;

import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Variable;
import grumpsolve.sketch.geometry.Point;
import grumpsolve.system.Solution;

public class Example {

    public static void main(String[] args) {
        Workspace workspace = new Workspace() {
            Variable height;
            protected void setup() {
                Expression side = c(1.0);
                height = var("height");
                Expression halfSide = div(side, c(2.0));

                Point bl = point(neg(halfSide), ZERO);
                Point br = point(halfSide, ZERO);
                Point top = point(ZERO, height);

                constrain(distance(bl, top), side);
            }
        };

        
        Solution solution = workspace.solve();
        if(solution == null){
            throw new RuntimeException("not converged");
        }
        System.out.println(solution);
        workspace.draw(solution);
    }
}
