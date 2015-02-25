package grumpsolve.sketch.geometry;

import com.google.common.collect.ImmutableList;
import grumpsolve.sketch.CoordinateSystem;
import grumpsolve.system.Solution;

import java.util.List;

public class LineSegment implements Geometry {

    private final CoordinateSystem coordinateSystem;
    private final Point p;
    private final Point q;

    public LineSegment(CoordinateSystem coordinateSystem, Point p, Point q) {
        this.coordinateSystem = coordinateSystem;
        this.p = p;
        this.q = q;
    }

    public List<Point> points() {
        return ImmutableList.of(p, q);
    }

    public Point p() {
        return p;
    }

    public Point q() {
        return q;
    }

    @Override
    public LineSegment asGlobal() {
        return new LineSegment(CoordinateSystem.GLOBAL, p.asGlobal(), q.asGlobal());
    }

    @Override
    public void draw(Solution solution) {
        System.out.println("Segment(");
        p.draw(solution);
        p.draw(solution);
        System.out.println(")");
    }
}
