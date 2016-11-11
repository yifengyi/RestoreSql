package restore.sql;

import org.jetbrains.annotations.Nullable;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;

import restore.sql.hibernate.BasicFormatterImpl;
import restore.sql.hibernate.Formatter;
import restore.sql.hibernate.StringHelper;
import restore.sql.tail.TailContentExecutor;

import static restore.sql.RestoreSqlConfig.sqlFormat;

/**
 * @author ob
 */
public class RestoreSqlFilter implements Filter {
    private final Project project;
    private static String prevLine = "";

    public RestoreSqlFilter(Project project) {
        this.project = project;
    }

    @Nullable
    @Override
    public Result applyFilter(final String currentLine, int endPoint) {
        if(RestoreSqlConfig.running) {
            if(currentLine.contains("Parameters:") && StringHelper.isNotEmpty(prevLine) && prevLine.contains("Preparing:")) {
                String preStr = currentLine.split("Parameters:")[0].trim();
                String restoreSql = RestoreSqlUtil.restoreSql(prevLine, currentLine);
                println(preStr, ConsoleViewContentType.USER_INPUT);
                if(sqlFormat) {
                    restoreSql = format(restoreSql);
                }
                println(restoreSql);
                println("------------------------------------------------------------------------------------------------------------------------", ConsoleViewContentType.USER_INPUT);
            }
            prevLine = currentLine;
        }
        return null;
    }

    public void println(String line, ConsoleViewContentType consoleViewContentType) {
        if(TailContentExecutor.consoleView != null) {
            TailContentExecutor.consoleView.print(line + "\n", consoleViewContentType);
        }
    }

    public void println(String line) {
        this.println(line, ConsoleViewContentType.ERROR_OUTPUT);
    }

    public static String format(String sql) {
        Formatter formatter = new BasicFormatterImpl();
        return formatter.format(sql);
    }
}
