package jacop;

import org.jacop.constraints.XneqY;
import org.jacop.core.*;
import org.jacop.search.*;

import java.util.Arrays;

public class Example {

    public static void main(String[] args) {
        Store store = new Store();
        IntVar[] v = new IntVar[4];
        IntDomain domain = new SmallDenseDomain(1, v.length);
        for (int i = 0; i < v.length; i++) {
            v[i] = new IntVar(store, "v" + i, domain.cloneLight());
        }

        for (int i = 0; i < v.length; i++) {
            IntVar varI = v[i];
            for (int j = i + 1; j < v.length; j++) {
                IntVar varJ = v[j];
                store.impose(new XneqY(varI, varJ));
            }
        }

        Search<IntVar> search = new DepthFirstSearch<>();
        SelectChoicePoint<IntVar> select = new InputOrderSelect<>(store, v, new IndomainMin<>());

        boolean result = search.labeling(store, select);

        if (result) {
            System.out.println(Arrays.toString(v));
        } else {
            System.out.println("No Solution found");
        }
    }

}
