package grumpsub;

import java.io.File;

public class Natives {

    public static void addLWJGL() {
        try {
            LibraryPaths.addLibraryPath(new File("lib/native/macosx/x64").getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Natives() {
    }
}
