package grumpsub;

import java.lang.reflect.Field;
import java.util.Arrays;

public class LibraryPaths {

    public static void addLibraryPath(String pathToAdd) throws Exception {
        String path = System.getProperty("java.library.path", "");
        System.setProperty("java.library.path", path + ":" + pathToAdd);
        //set sys_paths to null
        final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true);
        sysPathsField.set(null, null);
    }

    private LibraryPaths() {
    }
}
