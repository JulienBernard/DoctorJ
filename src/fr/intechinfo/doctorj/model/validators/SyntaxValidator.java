package fr.intechinfo.doctorj.model.validators;

import javax.tools.*;
import java.util.*;

/**
 * Checks the syntax of a given source code
 */
public class SyntaxValidator {
    public static ValidatorMessage checker(String source) {
        return null;
    }

    public static List<String> check(String file) {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits =
                fileManager.getJavaFileObjectsFromStrings(Arrays.asList(file));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        List<String> messages = new ArrayList<String>();
        Formatter formatter = new Formatter();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            messages.add(diagnostic.getKind() + ":\t Line [" + diagnostic.getLineNumber() + "] \t Position [" + diagnostic.getPosition() +
            "] \t" + diagnostic.getMessage(Locale.FRENCH) + "\n");
        }

        return messages;
    }
}
