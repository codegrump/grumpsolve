package grumpsolve;

import com.google.common.io.Resources;
import grump.GrumpLexer;
import grump.GrumpParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExamplesMain {

    public static void main(String[] args) throws Exception {
        File sketchDir = new File("src/test/resources/grumpsolve/example/sketch");


        Files.walkFileTree(sketchDir.toPath(), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                final AtomicBoolean error = new AtomicBoolean(false);

                if (file.toString().endsWith(".grump")) {
                    try (FileInputStream in = new FileInputStream(file.toFile())) {
                        GrumpLexer lexer = new GrumpLexer(new ANTLRInputStream(in));
                        GrumpParser parser = new GrumpParser(new CommonTokenStream(lexer));

                        parser.addErrorListener(new ConsoleErrorListener());

                        parser.addErrorListener(new ANTLRErrorListener() {
                            @Override
                            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i2, String s, RecognitionException e) {
                                error.set(true);
                            }

                            @Override
                            public void reportAmbiguity(Parser parser, DFA dfa, int i, int i2, boolean b, BitSet bitSet, ATNConfigSet atnConfigs) {
                                error.set(true);
                            }

                            @Override
                            public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i2, BitSet bitSet, ATNConfigSet atnConfigs) {
                                error.set(true);
                            }

                            @Override
                            public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i2, int i3, ATNConfigSet atnConfigs) {
                                error.set(true);
                            }
                        });
                        System.out.println(file);
                        parser.grump();
                        System.out.println();
                        System.out.println();
                    }
                }

                return error.get() ? FileVisitResult.TERMINATE : FileVisitResult.CONTINUE;
            }
        });


    }

}
