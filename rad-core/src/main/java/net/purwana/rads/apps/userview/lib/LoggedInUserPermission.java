package net.purwana.rads.apps.userview.lib;

import net.purwana.rads.apps.datalist.model.DatalistPermission;
import net.purwana.rads.apps.form.model.FormPermission;
import net.purwana.rads.apps.userview.model.UserviewPermission;
import net.purwana.rads.directory.model.User;

public class LoggedInUserPermission extends UserviewPermission implements FormPermission, DatalistPermission {

    @Override
    public boolean isAuthorize() {
        User user = getCurrentUser();
        if (user != null) {
            return true;
        }
        return false;
    }

    public String getName() {
        return "Logged In User Permission";
    }

    public String getVersion() {
        return "5.0.0";
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "Logged In User";
    }

    public String getClassName() {
        return this.getClass().getName();
    }

    public String getPropertyOptions() {
        return "";
    }
    
}
