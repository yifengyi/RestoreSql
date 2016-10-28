package restore.sql.tail;

import javax.swing.*;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import com.intellij.execution.Executor;
import com.intellij.execution.ExecutorRegistry;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;

/**
 * @author ob
 */
public class TailRunExecutor extends Executor {
	public static final Icon ToolWindowRun = IconLoader.getIcon("/restore/sql/data/sql.png");

	public static final String TOOLWINDOWS_ID = "Restore Sql";
	@NonNls
	public static final String EXECUTOR_ID = "RestoreSqlTail";

	@Override
	@NotNull
	public String getStartActionText() {
		return "Restore Sql";
	}

	@Override
	public String getToolWindowId() {
		return TOOLWINDOWS_ID;
	}

	@Override
	public Icon getToolWindowIcon() {
		return ToolWindowRun;
	}

	@Override
	@NotNull
	public Icon getIcon() {
		return AllIcons.Actions.Execute;
	}

	@Override
	public Icon getDisabledIcon() {
		return AllIcons.Process.DisabledRun;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	@NotNull
	public String getActionName() {
		return "Restore Sql";
	}

	@Override
	@NotNull
	public String getId() {
		return EXECUTOR_ID;
	}

	@Override
	public String getContextActionId() {
		return "RestoreSqlLog";
	}

	@Override
	public String getHelpId() {
		return null;
	}

	public static Executor getRunExecutorInstance() {
		return ExecutorRegistry.getInstance().getExecutorById(EXECUTOR_ID);
	}
}
