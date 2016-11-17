package restore.sql.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Disposer;

import restore.sql.RestoreSqlConfig;
import restore.sql.tail.TailContentExecutor;


/**
 * @author ob
 */
public class ShowLogInConsoleAction extends DumbAwareAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();
    }

    public void showLogInConsole(final Project project) {
        RestoreSqlConfig.running = true;
        final TailContentExecutor executor = new TailContentExecutor(project);
        Disposer.register(project, executor);
        executor.withTitle("Restore Sql");
        executor.withRerun(new Runnable() {
            @Override
            public void run() {
                showLogInConsole(project);
            }
        });
        executor.withStop(new Runnable() {
            @Override
            public void run() {
                RestoreSqlConfig.running = false;
                RestoreSqlConfig.indexNum = 1;
            }
        }, new Computable<Boolean>() {
            @Override
            public Boolean compute() {
                return RestoreSqlConfig.running;
            }
        });
        executor.withFormat(new Runnable() {
            @Override
            public void run() {
                RestoreSqlConfig.sqlFormat = !RestoreSqlConfig.sqlFormat;
            }
        });
        executor.run();
    }

}
