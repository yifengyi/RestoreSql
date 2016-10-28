package restore.sql.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;

/**
 * @author ob
 */
public class TailRestoreSqlLog extends DumbAwareAction {
	@Override
	public void actionPerformed(AnActionEvent e) {
		new ShowLogInConsoleAction().showLogInConsole(getEventProject(e));
	}
}
