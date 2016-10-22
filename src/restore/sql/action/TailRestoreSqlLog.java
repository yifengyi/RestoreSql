package restore.sql.action;

import java.io.File;
import java.io.IOException;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;

/**
 * @author ob
 */
public class TailRestoreSqlLog extends DumbAwareAction {
	@Override
	public void actionPerformed(AnActionEvent e) {
		try {
			final File sqlFile = new File(getEventProject(e).getBasePath(), "restore.sql");
			if(!sqlFile.exists()) {
                sqlFile.createNewFile();
            }
			new OpenFileInConsoleAction().openFileInConsole(getEventProject(e), sqlFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
