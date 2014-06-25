package fr.intechinfo.doctorj.model.validators;

import javax.tools.*;
import java.util.*;

/**
 * Checks the syntax of a given source code
 */
public class SyntaxValidator {
    public static ValidatorMessage check(String file) {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits =
                fileManager.getJavaFileObjectsFromStrings(Arrays.asList(file));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        String errors = "";

        Formatter formatter = new Formatter();

        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            errors += diagnostic.getKind() + ":\t Line [" + diagnostic.getLineNumber() + "] \t Position [" + diagnostic.getPosition() +
            "] \t" + diagnostic.getMessage(Locale.FRENCH) + "\n";
        }

        ValidatorMessage message;

        if(errors.isEmpty()) {
            message = new ValidatorMessage(true, "Le code est valide syntaxiquement !");
        }
        else {
            message = new ValidatorMessage(false, errors);
        }

        return message;
    }
}
