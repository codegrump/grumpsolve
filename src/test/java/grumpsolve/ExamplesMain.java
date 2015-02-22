package grumpsolve;

import grump.Grump;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class ExamplesMain {

    public static void main(String[] args) throws Exception {
        File sketchDir = new File("src/test/resources/grumpsolve/example/sketch");
        Files.walkFileTree(sketchDir.toPath(), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                final GrumpConsoleErrorListener errorListener = new GrumpConsoleErrorListener();
                if (file.toString().endsWith(".grump")) {
                    System.out.println(file);
                    Grump.TopLevelStatements statements;
                    try (FileInputStream in = new FileInputStream(file.toFile())) {
                        statements = Grump.compile(in, errorListener);
                    }
                    new Grump().execute(statements).solveAndPrint();
                    System.out.println(errorListener.error() ? "error": "");
                    System.out.println();
                }
                
                return errorListener.error() ? FileVisitResult.TERMINATE : FileVisitResult.CONTINUE;
            }
        });


    }

}
