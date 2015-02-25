package grumpsolve.sketch.geometry;

import com.google.common.collect.ImmutableList;
import grumpsolve.sketch.CoordinateSystem;
import grumpsolve.system.Solution;

import java.util.List;
import java.util.stream.Collectors;

public class LinearRing implements Geometry {

    private final CoordinateSystem coordinateSystem;
    private final List<Point> points;

    public LinearRing(CoordinateSystem coordinateSystem, List<Point> points) {
        this.coordinateSystem = coordinateSystem;
        if (points.size() < 3) {
            throw new IllegalArgumentException("Linear Ring must be 3 or more points");
        }

        //while first point equals last point, remove last point
        while (points.get(0).equals(points.get(points.size() - 1))) {
            points = points.subList(0, points.size() - 1);
        }
        this.points = ImmutableList.copyOf(points);
    }

    public List<Point> points() {
        return points;
    }

    @Override
    public LinearRing asGlobal() {
        List<Point> points = this.points.stream().map(Point::asGlobal).collect(Collectors.toList());
        return new LinearRing(CoordinateSystem.GLOBAL, points);
    }

    @Override
    public void draw(Solution solution) {
        System.out.println("Ring(");
        for (Point point : points) {
            point.draw(solution);
        }
        System.out.println(")");
    }

    public static class Builder {

        private final CoordinateSystem coordinateSystem;
        private final ImmutableList.Builder<Point> points = new ImmutableList.Builder<>();

        public Builder(CoordinateSystem coordinateSystem) {
            this.coordinateSystem = coordinateSystem;
        }

        public Builder p(Point p) {
            points.add(p);
            return this;
        }

        public LinearRing build() {
            return new LinearRing(coordinateSystem, points.build());
        }
    }
}
