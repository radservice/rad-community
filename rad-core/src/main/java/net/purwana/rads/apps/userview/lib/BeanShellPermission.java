package net.purwana.rads.apps.userview.lib;

import bsh.Interpreter;
import net.purwana.rads.apps.app.service.AppUtil;
import net.purwana.rads.apps.datalist.model.DatalistPermission;
import net.purwana.rads.apps.form.model.FormPermission;
import net.purwana.rads.apps.userview.model.UserviewPermission;
import net.purwana.rads.commons.util.LogUtil;

public class BeanShellPermission extends UserviewPermission implements FormPermission, DatalistPermission {
    @Override
    public boolean isAuthorize() {
        return executeScript();
    }
        
    protected boolean executeScript() {    
        String script = getPropertyString("script");
        
        Object result = null;
        try {
            Interpreter interpreter = new Interpreter();
            interpreter.setClassLoader(getClass().getClassLoader());
            interpreter.set("user", getCurrentUser());
            interpreter.set("requestParams", getRequestParameters());
            
            result = interpreter.eval(script);
            return (Boolean) result;
        } catch (Exception e) {
            LogUtil.error(getClass().getName(), e, "Error executing script");
        }

        return false;
    }

    public String getName() {
        return "Bean Shell Permission";
    }

    public String getVersion() {
        return "5.0.0";
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "Bean Shell Script";
    }

    public String getClassName() {
        return getClass().getName();
    }

    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/userview/beanShellPermission.json", null, true, null);
    }
}
