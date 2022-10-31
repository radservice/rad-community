package net.purwana.rads.apps.app.lib;

import net.purwana.rads.apps.app.model.DefaultHashVariablePlugin;
import net.purwana.rads.apps.app.service.AppPluginUtil;
import net.purwana.rads.apps.app.service.AppUtil;
import net.purwana.rads.workflow.model.WorkflowAssignment;
import net.purwana.rads.workflow.model.service.WorkflowManager;
import org.springframework.context.ApplicationContext;

public class WorkflowVariableHashVariable extends DefaultHashVariablePlugin {

    @Override
    public String processHashVariable(String variableKey) {
        WorkflowAssignment wfAssignment = (WorkflowAssignment) this.getProperty("workflowAssignment");
        if (wfAssignment != null) {
            ApplicationContext appContext = AppUtil.getApplicationContext();
            WorkflowManager workflowManager = (WorkflowManager) appContext.getBean("workflowManager");

            String varVal = workflowManager.getProcessVariable(wfAssignment.getProcessId(), variableKey);
            if (varVal != null) {
                return varVal;
            }
        }
        return null;
    }

    public String getName() {
        return "Workflow Variable Hash Variable";
    }

    public String getPrefix() {
        return "variable";
    }

    public String getVersion() {
        return "5.0.0";
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "Workflow Variable Hash Variable";
    }

    public String getClassName() {
        return this.getClass().getName();
    }
    
    public String getPropertyOptions() {
        return "";
    }
}
