package grumpsolve.sketch;

import grumpsolve.algebra.Expression;

public interface R2 {
    
    Expression x();

    Expression y();
    
    R2 plus(R2 other);
    
    R2 minus(R2 other);

}
