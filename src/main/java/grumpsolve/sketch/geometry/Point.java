package grumpsolve.sketch.geometry;

import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Expressions;
import grumpsolve.sketch.CoordinateSystem;
import grumpsolve.sketch.TranslateRotate;
import grumpsolve.sketch.V2;
import grumpsolve.system.Solution;

public class Point implements Geometry {

    private final CoordinateSystem coordinateSystem;
    private final V2 p;

    public Point(CoordinateSystem coordinateSystem, Expression x, Expression y) {
        this.coordinateSystem = coordinateSystem;
        this.p = new V2(x, y);
    }

    @Override
    public Point asGlobal() {
        TranslateRotate point = coordinateSystem.fromGlobal().compose(new TranslateRotate(new V2(p.x(), p.y()), Expressions.ZERO));
        return new Point(CoordinateSystem.GLOBAL, point.x(), point.y());
    }

    @Override
    public void draw(Solution solution) {
        V2 p = asGlobal().p;
        double x = p.x().evaluate(solution);
        double y = p.y().evaluate(solution);
        System.out.println("[" + x + "," + y + "]");
    }

    public Expression x() {
        return p.x();
    }

    public Expression y() {
        return p.y();
    }

    public static class Builder {

        private final CoordinateSystem coordinateSystem;
        private Expression x = Expressions.ZERO, y = Expressions.ZERO;

        public Builder(CoordinateSystem coordinateSystem) {
            this.coordinateSystem = coordinateSystem;
        }

        public Builder x(Expression x) {
            this.x = x;
            return this;
        }

        public Builder y(Expression y) {
            this.y = y;
            return this;
        }

        public Point build() {
            return new Point(coordinateSystem, x, y);
        }
    }
}
