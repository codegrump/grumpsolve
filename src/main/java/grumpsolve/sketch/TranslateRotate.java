package grumpsolve.sketch;

import com.google.common.base.Preconditions;
import grumpsolve.algebra.Expression;
import grumpsolve.algebra.ExpressionMatrix;
import grumpsolve.algebra.Expressions;

public class TranslateRotate {

    public static TranslateRotate IDENTITY = new TranslateRotate(V2.ORIGIN, Expressions.ZERO);

    public static TranslateRotate compose(TranslateRotate t0, TranslateRotate... ts) {
        TranslateRotate toReturn = t0;
        for (TranslateRotate t : ts) {
            toReturn = toReturn.compose(t);
        }
        return toReturn;
    }

    private final V2 translate;
    private final Expression rotateDegrees;
    private final ExpressionMatrix m;

    public TranslateRotate(Expression x, Expression y, Expression a){
        this(new V2(x, y), a);
    }
    
    public TranslateRotate(V2 translate, Expression rotateDegrees) {
        this(translate, rotateDegrees, makeM(translate, rotateDegrees));
    }

    public TranslateRotate(V2 translate, Expression rotateDegrees, ExpressionMatrix m) {
        Preconditions.checkNotNull(translate);
        this.translate = translate;
        this.rotateDegrees = rotateDegrees;
        this.m = m;
    }

    public TranslateRotate compose(TranslateRotate other) {
        ExpressionMatrix m = this.m.mult(other.m);
        return new TranslateRotate(new V2(m.get(0, 2), m.get(1, 2)), Expressions.add(this.rotateDegrees, other.rotateDegrees));
    }

    public TranslateRotate inverse() {
        TranslateRotate t = this;
        Expression x = Expressions.neg(t.translate.x());
        Expression y = Expressions.neg(t.translate.y());
        Expression a = Expressions.neg(Expressions.toRadians(t.rotateDegrees));
        Expression cos = Expressions.cos(a);
        Expression sin = Expressions.sin(a);
        return new TranslateRotate(new V2(
                Expressions.sub(Expressions.mult(x, cos), Expressions.mult(y, sin)),
                Expressions.add(Expressions.mult(x, sin), Expressions.mult(y, cos))
        ), a);
    }

    public Expression x() {
        return translate.x();
    }

    public Expression y() {
        return translate.y();
    }

    public Expression a() {
        return rotateDegrees;
    }

    @Override
    public String toString() {
        return "TranslateRotate(" +
                translate.x() + "," +
                translate.y() + "," +
                rotateDegrees +
                ')';
    }

    private static ExpressionMatrix makeM(V2 translate, Expression rotateDegrees) {
        ExpressionMatrix m = new ExpressionMatrix(3, 3);

        Expression rotateRadians = Expressions.toRadians(rotateDegrees);
        Expression sin = Expressions.sin(rotateRadians);
        Expression cos = Expressions.cos(rotateRadians);
        m.set(0, 0, cos);
        m.set(1, 0, sin);

        m.set(0, 1, Expressions.neg(sin));
        m.set(1, 1, cos);

        m.set(0, 2, translate.x());
        m.set(1, 2, translate.y());

        m.set(2, 2, 1.0);
        return m;
    }
}
