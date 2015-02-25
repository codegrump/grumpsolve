package grumpsolve.sketch.geometry;

import grumpsolve.system.Solution;

public interface Geometry {
    
    Geometry asGlobal();

    void draw(Solution solution);
}
