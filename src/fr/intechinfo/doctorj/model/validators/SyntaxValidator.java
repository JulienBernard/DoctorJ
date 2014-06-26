package fr.intechinfo.doctorj.model.validators;

import org.eclipse.jdt.internal.compiler.tool.EclipseCompiler;

import javax.tools.*;
import java.util.*;

/**
 * Checks the syntax of a given source code
 */
public class SyntaxValidator {
    public static ValidatorMessage check(String file) {
        JavaCompiler compiler = new EclipseCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits =
                fileManager.getJavaFileObjectsFromStrings(Arrays.asList(file));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        List<ValidatorMessageElement> elements = new ArrayList<>();

        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            ValidatorConstants vc;

            if(diagnostic.getKind() == Diagnostic.Kind.WARNING) {
                vc = ValidatorConstants.WARNING;
            } else if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                vc = ValidatorConstants.ERROR;
            } else {
                vc = ValidatorConstants.BLANK;
            }

            elements.add(
                    new ValidatorMessageElement(
                            diagnostic.getKind() + ":\t Line [" + diagnostic.getLineNumber() + "] \t Position [" + diagnostic.getPosition() +
                                    "] \t" + diagnostic.getMessage(Locale.FRENCH) + "\n", vc
                    )
            );
        }

        ValidatorMessage message;

        if(elements.isEmpty()) {
            message = new ValidatorMessage(true, elements);
        }
        else {
            message = new ValidatorMessage(false, elements);
        }

        return message;
    }
}
