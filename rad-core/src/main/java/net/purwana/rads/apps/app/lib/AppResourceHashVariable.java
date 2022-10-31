package net.purwana.rads.apps.app.lib;

import javax.servlet.http.HttpServletRequest;
import net.purwana.rads.apps.app.dao.AppResourceDao;
import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.apps.app.model.DefaultHashVariablePlugin;
import net.purwana.rads.apps.app.service.AppUtil;
import static net.purwana.rads.apps.app.service.AppUtil.getCurrentAppDefinition;
import net.purwana.rads.workflow.util.WorkflowUtil;

public class AppResourceHashVariable extends DefaultHashVariablePlugin {
    @Override
    public String processHashVariable(String variableKey) {
        AppResourceDao dao = (AppResourceDao) AppUtil.getApplicationContext().getBean("appResourceDao");
        AppDefinition appDef = getCurrentAppDefinition();
        
        if (dao.loadById(variableKey, appDef) != null) {
            HttpServletRequest request = WorkflowUtil.getHttpServletRequest();
            if (appDef != null && request != null) {
                String path = request.getContextPath() + "/web/app/" + appDef.getAppId() + "/resources/";
                return path + variableKey;
            }
        }
        return null;
    }

    public String getName() {
        return "App Resource Hash Variable";
    }

    public String getPrefix() {
        return "appResource";
    }

    public String getVersion() {
        return "6.0.0";
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "App Resource Hash Variable";
    }

    public String getClassName() {
        return this.getClass().getName();
    }
    
    public String getPropertyOptions() {
        return "";
    }
}
