package grumpsolve.algebra;

import javax.annotation.Nonnull;

@SuppressWarnings("UnusedDeclaration")
public final class Expressions {

    public static final Expression ZERO = Algebra.Constant.ZERO,
            ONE = Algebra.Constant.ONE,
            HALF = Algebra.Constant.HALF;

    public static Expression add(@Nonnull Expression l, @Nonnull Expression r) {
        return Algebra.Addition.add(l, r);
    }
    public static Expression add(double l, @Nonnull Expression r) {
        return Algebra.Addition.add(c(l), r);
    }
    public static Expression add(@Nonnull Expression l, double r) {
        return Algebra.Addition.add(l, c(r));
    }
    public static Expression add(double l, double r) {
        return Algebra.Addition.add(c(l), c(r));
    }

    public static Expression add(@Nonnull Expression l, @Nonnull Expression r, @Nonnull Expression rr) {
        return add(Algebra.Addition.add(l, r), rr);
    }

    public static Expression arccos(@Nonnull Expression e) {
        return Algebra.ArcCos.arccos(e);
    }
    public static Expression arccos(double e) {
        return Algebra.ArcCos.arccos(c(e));
    }

    public static Expression arcsin(@Nonnull Expression e) {
        return Algebra.ArcSin.arcsin(e);
    }
    public static Expression arcsin(double e) {
        return Algebra.ArcSin.arcsin(c(e));
    }

    public static Expression c(double v) {
        return Algebra.Constant.constant(v);
    }

    public static Expression cos(@Nonnull Expression e) {
        return Algebra.Cos.cos(e);
    }
    public static Expression cos(double e) {
        return Algebra.Cos.cos(c(e));
    }

    public static Expression div(@Nonnull Expression l, @Nonnull Expression r) {
        return Algebra.Division.divide(l, r);
    }
    public static Expression div(double l, @Nonnull Expression r) {
        return Algebra.Division.divide(c(l), r);
    }
    public static Expression div(@Nonnull Expression l, double r) {
        return Algebra.Division.divide(l, c(r));
    }
    public static Expression div(double l, double r) {
        return Algebra.Division.divide(c(l), c(r));
    }

    public static Expression minus(@Nonnull Expression l, @Nonnull Expression r) {
        return sub(l, r);
    }
    public static Expression minus(double l, @Nonnull Expression r) {
        return sub(c(l), r);
    }
    public static Expression minus(double l, double r) {
        return sub(c(l), c(l));
    }

    public static Expression mult(@Nonnull Expression l, @Nonnull Expression r) {
        return Algebra.Multiplication.multiply(l, r);
    }
    public static Expression mult(double l, @Nonnull Expression r) {
        return Algebra.Multiplication.multiply(c(l), r);
    }
    public static Expression mult(@Nonnull Expression l, double r) {
        return Algebra.Multiplication.multiply(l, c(r));
    }
    public static Expression mult(double l, double r) {
        return Algebra.Multiplication.multiply(c(l), c(r));
    }


    public static Expression neg(@Nonnull Expression e) {
        return Algebra.Negation.negate(e);
    }
    public static Expression neg(double e) {
        return Algebra.Negation.negate(c(e));
    }

    public static Parameter param(long id) {
        return new Parameter(id);
    }

    public static Expression partialWithRespectTo(@Nonnull Expression e, @Nonnull Parameter p) {
        return e.dependsOn(p) ? e.partialWithRespectTo(p) : ZERO;
    }

    public static Expression pow2(@Nonnull Expression e) {
        return square(e);
    }
    public static Expression pow2(double v) {
        return square(v);
    }

    public static Expression sin(@Nonnull Expression e) {
        return Algebra.Sin.sin(e);
    }
    public static Expression sin(double e) {
        return Algebra.Sin.sin(c(e));
    }

    public static Expression square(@Nonnull Expression e) {
        return Algebra.Square.square(e);
    }
    public static Expression square(double v) {
        return c(v * v);
    }

    public static Expression sqrt(@Nonnull Expression e) {
        return Algebra.SquareRoot.squareRoot(e);
    }
    public static Expression sqrt(double e) {
        return Algebra.SquareRoot.squareRoot(c(e));
    }

    public static Expression sub(@Nonnull Expression l, @Nonnull Expression r) {
        return Algebra.Subtraction.subtract(l, r);
    }
    public static Expression sub(double l, @Nonnull Expression r) {
        return Algebra.Subtraction.subtract(c(l), r);
    }
    public static Expression sub(@Nonnull Expression l, double r) {
        return Algebra.Subtraction.subtract(l, c(r));
    }
    public static Expression sub(double l, double r) {
        return Algebra.Subtraction.subtract(c(l), c(r));
    }

    Expressions() {
    }
}
