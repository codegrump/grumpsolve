package grumpsolve.sketch;

public interface CoordinateSystem {

    CoordinateSystem parent();
    
    TranslateRotate fromParent();

    default TranslateRotate fromGlobal() {
        TranslateRotate parentFromGlobal = parent().fromGlobal();
        return parentFromGlobal.compose(fromParent());
    }
    
    public static final CoordinateSystem GLOBAL = new CoordinateSystem() {
        @Override
        public CoordinateSystem parent() {
            return this;
        }

        @Override
        public TranslateRotate fromParent() {
            return TranslateRotate.IDENTITY;
        }

        @Override
        public TranslateRotate fromGlobal() {
            return TranslateRotate.IDENTITY;
        }
    };
}
