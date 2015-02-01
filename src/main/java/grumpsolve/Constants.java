package grumpsolve;

public class Constants {

    public static final double LENGTH_EPS = 1.0e-6;
    public static final double LENGTH_EPS_SQUARED = LENGTH_EPS * LENGTH_EPS;
    public static final double VERY_POSITIVE = 1.0e10;
    public static final double VERY_NEGATIVE = -1.0e10;

    public static final double NEWTON_CONVERGE_TOLERANCE = (LENGTH_EPS/(1e2));

    private Constants() {
    }
}
