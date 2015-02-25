package grumpsolve.sketch;

import grumpsolve.algebra.Expression;
import grumpsolve.algebra.Expressions;
import grumpsolve.sketch.geometry.Geometry;
import grumpsolve.sketch.geometry.LinearRing;
import grumpsolve.sketch.geometry.Point;
import grumpsolve.system.Solution;

import java.util.ArrayList;
import java.util.List;

public class Sketch implements CoordinateSystem, Geometry {

    public static Sketch root() {
        return new Sketch(CoordinateSystem.GLOBAL, TranslateRotate.IDENTITY);
    }

    private final CoordinateSystem parent;
    private final TranslateRotate translateRotate;
    private final List<Geometry> geometries = new ArrayList<>();

    public Sketch(CoordinateSystem parent, TranslateRotate translateRotate) {
        this.parent = parent;
        this.translateRotate = translateRotate;
    }

    @Override
    public CoordinateSystem parent() {
        return parent;
    }

    @Override
    public TranslateRotate fromParent() {
        return translateRotate;
    }

    public Point point(Expression x, Expression y) {
        Point p = new Point(this, x, y);
        geometries.add(p);
        return p;
    }

    public LinearRing linearRing(List<Point> points) {
        LinearRing l = new LinearRing(this, points);
        geometries.add(l);
        return l;
    }

    public LinearRing.Builder LinearRing() {
        return new LinearRing.Builder(this);
    }

    public Sketch sketch(TranslateRotate translateRotate) {
        Sketch sketch = new Sketch(this, translateRotate);
        geometries.add(sketch);
        return sketch;
    }

    @Override
    public Sketch asGlobal() {
        return new Sketch(GLOBAL, fromGlobal()  );
    }

    public void draw(Solution solution) {
        for (Geometry geometry : geometries) {
            geometry.draw(solution);
        }
    }

    public Sketch sketch(double x, double y, double a) {
        return sketch(new TranslateRotate(Expressions.c(x), Expressions.c(y), Expressions.c(a)));
    }

    public Sketch sketch(double x, double y, Expression a) {
        return sketch(new TranslateRotate(Expressions.c(x), Expressions.c(y), a));
    }

    public Sketch sketch(Expression x, double y, Expression a) {
        return sketch(new TranslateRotate(x, Expressions.c(y), a));
    }
}
