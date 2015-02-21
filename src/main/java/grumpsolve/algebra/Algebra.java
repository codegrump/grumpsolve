package grumpsolve.algebra;

import com.google.common.base.Preconditions;
import grumpsolve.Immutable;
import grumpsolve.system.Solution;

import javax.annotation.Nonnull;
import java.util.Set;

import static grumpsolve.algebra.Expressions.*;

class Algebra {

    public abstract static class UnaryOperator extends Expression {

        protected final Expression e;

        protected UnaryOperator(@Nonnull Expression e) {
            Preconditions.checkNotNull(e, "e cannot be null");
            this.e = e;
        }

        @Override
        public final boolean dependsOn(@Nonnull Parameter p) {
            return e.dependsOn(p);
        }

        @Override
        protected void addParameters(@Nonnull Set<Parameter> parameters) {
            e.addParameters(parameters);
        }

        @Override
        boolean isConstant() {
            return false; // since constants are folded on creation
        }
    }

    @Immutable
    static final class Addition extends BinaryOperator {

        static Expression add(@Nonnull Expression l, @Nonnull Expression r) {
            return l == ZERO ? r
                    : r == ZERO ? l
                    : l instanceof Constant && r instanceof Constant ? c(cv(l) + cv(r))
                    : new Addition(l, r);
        }

        private Addition(@Nonnull Expression l, @Nonnull Expression r) {
            super(l, r);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return l.evaluate(solution) + r.evaluate(solution);
        }

        @Override
        public String toString() {
            String ls = toStringWithOptionalParens(l);
            String rs = toStringWithOptionalParens(r);
            return ls + "+" + rs;
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            //da + db
            return add(l.partialWithRespectTo(p), r.partialWithRespectTo(p));
        }
    }

    @Immutable
    static class ArcSin extends UnaryOperator {

        static Expression arcsin(@Nonnull Expression e) {
            return e instanceof Constant ? c(Math.asin(cv(e)))
                    : new ArcSin(e);
        }

        private ArcSin(@Nonnull Expression e) {
            super(e);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return Math.asin(e.evaluate(solution));
        }

        @Override
        public String toString() {
            return "asin(" + e.toString() + ")";
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            // (1/sqrt(1 - e^2))da
            Expression e2 = square(e);
            Expression de = e.partialWithRespectTo(p);

            Expression root = sqrt(sub(ONE, e2));
            Expression oneOverRoot = div(ONE, root);
            return mult(oneOverRoot, de);
        }
    }

    @Immutable
    static class ArcCos extends UnaryOperator {

        static Expression arccos(@Nonnull Expression e) {
            return e instanceof Constant ? c(Math.acos(cv(e)))
                    : new ArcCos(e);
        }

        private ArcCos(@Nonnull Expression e) {
            super(e);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return Math.acos(e.evaluate(solution));
        }

        @Override
        public String toString() {
            return "acos(" + e.toString() + ")";
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            Expression e2 = square(e);
            Expression de = e.partialWithRespectTo(p);

            Expression root = sqrt(sub(ONE, e2));
            Expression oneOverRoot = div(ONE, root);
            return mult(neg(oneOverRoot), de);
        }
    }

    abstract static class BinaryOperator extends Expression {

        protected final Expression l, r;

        BinaryOperator(@Nonnull Expression l, @Nonnull Expression r) {
            Preconditions.checkNotNull(l, "l cannot be null");
            Preconditions.checkNotNull(r, "r cannot be null");
            this.l = l;
            this.r = r;
        }

        @Override
        public final boolean dependsOn(@Nonnull Parameter p) {
            return l.dependsOn(p) || r.dependsOn(p);
        }

        @Override
        protected void addParameters(@Nonnull Set<Parameter> parameters) {
            l.addParameters(parameters);
            r.addParameters(parameters);
        }

        @Override
        boolean isConstant() {
            return false; //assuming constants are folded on creation
        }
    }

    @Immutable
    static final class Constant extends Expression {

        static final Constant ZERO = new Constant(0.0),
                HALF = new Constant(0.5),
                ONE = new Constant(1.0);

        static Constant constant(double v) {
            return v == 0.0 ? Constant.ZERO
                    : v == 1.0 ? Constant.ONE
                    : v == 0.5 ? Constant.HALF
                    : new Constant(v);
        }


        private final double v;

        private Constant(double v) {
            this.v = v;
        }

        @Override
        public boolean dependsOn(@Nonnull Parameter p) {
            return false;
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return v;
        }

        @Override
        public String toString() {
            return Double.toString(v);
        }

        @Override
        protected void addParameters(@Nonnull Set<Parameter> parameters) {
            //no addParameters
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            return ZERO;
        }

        @Override
        boolean isConstant() {
            return true;
        }

        double getV() {
            return v;
        }
    }

    @Immutable
    static class Cos extends UnaryOperator {

        static Expression cos(@Nonnull Expression e) {
            return e == ZERO ? ONE
                    : e instanceof Constant ? c(Math.cos(cv(e)))
                    : new Cos(e);
        }

        public Cos(@Nonnull Expression e) {
            super(e);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return Math.cos(e.evaluate(solution));
        }

        @Override
        public String toString() {
            return "cos(" + e.toString() + ")";
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            //-sin(e)da
            return neg(mult(sin(e), e.partialWithRespectTo(p)));
        }
    }

    @Immutable
    static class Division extends BinaryOperator {

        static Expression divide(@Nonnull Expression l, @Nonnull Expression r) {
            return r == ONE ? l
                    : l == ZERO ? ZERO
                    : r instanceof Constant ? mult(c(1.0 / cv(r)), l)
                    : new Division(l, r);
        }

        private Division(@Nonnull Expression l, @Nonnull Expression r) {
            super(l, r);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return l.evaluate(solution) / r.evaluate(solution);
        }

        @Override
        public String toString() {
            String ls = toStringWithOptionalParens(l);
            String rs = toStringWithOptionalParens(r);
            return ls + "/" + rs;
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            Expression da = l.partialWithRespectTo(p);
            Expression bda = mult(r, da);

            Expression db = r.partialWithRespectTo(p);
            Expression adb = mult(l, db);
            // (bda - abd)/(r^2)
            return div(sub(bda, adb), square(r));
        }
    }

    @Immutable
    static class Multiplication extends BinaryOperator {

        static Expression multiply(@Nonnull Expression l, @Nonnull Expression r) {
            return l == ZERO || r == ZERO ? ZERO
                    : l == ONE ? r
                    : r == ONE ? l
                    : l instanceof Constant && r instanceof Constant ? c(cv(l) * cv(r))
                    : new Multiplication(l, r);
        }

        private Multiplication(@Nonnull Expression l, @Nonnull Expression r) {
            super(l, r);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return l.evaluate(solution) * r.evaluate(solution);
        }

        @Override
        public String toString() {
            String ls = toStringWithOptionalParens(l);
            String rs = toStringWithOptionalParens(r);
            return ls + "*" + rs;
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            Expression da = l.partialWithRespectTo(p);
            Expression db = r.partialWithRespectTo(p);
            //bda + adb
            return add(mult(r, da), mult(l, db));
        }
    }

    @Immutable
    static final class Negation extends UnaryOperator {

        static Expression negate(@Nonnull Expression e) {
            return e == ZERO ? ZERO
                    : e instanceof Constant ? c(-cv(e))
                    : e instanceof Negation ? ((Negation) e).e
                    : new Negation(e);
        }

        private Negation(@Nonnull Expression e) {
            super(e);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return -e.evaluate(solution);
        }

        @Override
        public String toString() {
            return "-" + toStringWithOptionalParens(e);
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            return negate(e.partialWithRespectTo(p));
        }
    }

    @Immutable
    static class Sin extends UnaryOperator {

        static Expression sin(@Nonnull Expression e) {
            return e == Expressions.ZERO ? Expressions.ZERO
                    : e instanceof Constant ? c(Math.sin(cv(e)))
                    : new Sin(e);
        }

        private Sin(@Nonnull Expression e) {
            super(e);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return Math.sin(e.evaluate(solution));
        }

        @Override
        public String toString() {
            return "sin(" + e.toString() + ")";
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            return mult(cos(e), e.partialWithRespectTo(p));
        }
    }

    @Immutable
    static class Square extends UnaryOperator {

        static Expression square(@Nonnull Expression e) {
            return e == Expressions.ZERO ? Expressions.ZERO
                    : e == Expressions.ONE ? Expressions.ONE
                    : e instanceof Constant ? pow2(cv(e))
                    : new Square(e);
        }

        private Square(@Nonnull Expression e) {
            super(e);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            double v = e.evaluate(solution);
            return v * v;
        }

        @Override
        public String toString() {
            return toStringWithOptionalParens(e) + "^2";
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            Expression _2a = mult(c(2), e);
            Expression da = e.partialWithRespectTo(p);
            //2ada
            return mult(_2a, da);
        }
    }

    @Immutable
    static class SquareRoot extends UnaryOperator {

        static Expression squareRoot(@Nonnull Expression e) {
            return e == Expressions.ZERO ? Expressions.ZERO
                    : e == Expressions.ONE ? Expressions.ONE
                    : e instanceof Constant ? c(Math.sqrt(cv(e)))
                    : new SquareRoot(e);
        }

        private SquareRoot(@Nonnull Expression e) {
            super(e);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return Math.sqrt(e.evaluate(solution));
        }

        @Override
        public String toString() {
            return "sqrt(" + e.toString() + ")";
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            // 0.5 e^(-1/2) da =>
            // (0.5 / sqrt(e)) da
            return mult(div(HALF, this), e.partialWithRespectTo(p));
        }
    }

    @Immutable
    static final class Subtraction extends BinaryOperator {

        static Expression subtract(@Nonnull Expression l, @Nonnull Expression r) {
            return r == Expressions.ZERO ? l
                    : l == Expressions.ZERO ? neg(r)
                    : l instanceof Constant && r instanceof Constant ? c(cv(l) - cv(r))
                    : new Subtraction(l, r);
        }

        private Subtraction(@Nonnull Expression a, @Nonnull Expression b) {
            super(a, b);
        }

        @Override
        public double evaluate(@Nonnull Solution solution) {
            return l.evaluate(solution) - r.evaluate(solution);
        }

        @Override
        public String toString() {
            String ls = toStringWithOptionalParens(l);
            String rs = toStringWithOptionalParens(r);
            return ls + "-" + rs;
        }

        @Override
        Expression partialWithRespectTo(@Nonnull Parameter p) {
            //da - db
            return sub(l.partialWithRespectTo(p), r.partialWithRespectTo(p));
        }
    }
}
