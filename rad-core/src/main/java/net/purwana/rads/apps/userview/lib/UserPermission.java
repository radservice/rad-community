package net.purwana.rads.apps.userview.lib;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.purwana.rads.apps.app.service.AppUtil;
import net.purwana.rads.apps.datalist.model.DatalistPermission;
import net.purwana.rads.apps.form.model.FormPermission;
import net.purwana.rads.apps.userview.model.UserviewPermission;
import net.purwana.rads.commons.util.LogUtil;
import net.purwana.rads.commons.util.ResourceBundleUtil;
import net.purwana.rads.directory.model.Organization;
import net.purwana.rads.directory.model.User;
import net.purwana.rads.directory.model.service.DirectoryUtil;
import net.purwana.rads.directory.model.service.ExtDirectoryManager;
import net.purwana.rads.plugin.base.PluginWebSupport;
import net.purwana.rads.workflow.model.service.WorkflowUserManager;
import net.purwana.rads.workflow.util.WorkflowUtil;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;

public class UserPermission extends UserviewPermission implements PluginWebSupport, FormPermission, DatalistPermission {

    @Override
    public boolean isAuthorize() {
        User user = getCurrentUser();

        if (user != null) {
            StringTokenizer strToken = new StringTokenizer(getPropertyString("allowedUsernames"), ";");
            while (strToken.hasMoreTokens()) {
                String username = (String) strToken.nextElement();
                if (username.equals(user.getUsername())) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getName() {
        return "User Permission";
    }

    public String getVersion() {
        return "5.0.0";
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "User";
    }

    public String getClassName() {
        return getClass().getName();
    }

    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/userview/userPermission.json", null, true, "message/userview/userPermission");
    }

    public void webService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        boolean isAdmin = WorkflowUtil.isCurrentUserInRole(WorkflowUserManager.ROLE_ADMIN);
        if (!isAdmin) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        String action = request.getParameter("action");

        if ("getOrgs".equals(action)) {
            try {
                JSONArray jsonArray = new JSONArray();

                ApplicationContext ac = AppUtil.getApplicationContext();
                ExtDirectoryManager directoryManager = (ExtDirectoryManager) ac.getBean("directoryManager");

                Map<String, String> empty = new HashMap<String, String>();
                empty.put("value", "");
                empty.put("label", ResourceBundleUtil.getMessage("console.directory.user.empty.option.label"));
                jsonArray.put(empty);

                Collection<Organization> orgList = directoryManager.getOrganizationsByFilter(null, "name", false, null, null);

                for (Organization o : orgList) {
                    Map<String, String> option = new HashMap<String, String>();
                    option.put("value", o.getId());
                    option.put("label", o.getName());
                    jsonArray.put(option);
                }

                jsonArray.write(response.getWriter());
            } catch (Exception ex) {
                LogUtil.error(this.getClass().getName(), ex, "Get Organization options Error!");
            }
        } else if ("getUsers".equals(action)) {
            String orgId = request.getParameter("orgId");

            if ("null".equals(orgId) || "".equals(orgId)) {
                orgId = null;
            }

            try {
                JSONArray jsonArray = new JSONArray();

                ApplicationContext ac = AppUtil.getApplicationContext();
                ExtDirectoryManager directoryManager = (ExtDirectoryManager) ac.getBean("directoryManager");

                Collection<User> userList = directoryManager.getUsers(null, orgId, null, null, null, null, null, "firstName", false, null, null);

                for (User u : userList) {
                    Map<String, String> option = new HashMap<String, String>();
                    option.put("value", u.getUsername());
                    option.put("label", DirectoryUtil.getUserFullName(u) + "(" + u.getUsername() + ")");
                    jsonArray.put(option);
                }

                jsonArray.write(response.getWriter());
            } catch (Exception ex) {
                LogUtil.error(this.getClass().getName(), ex, "Get Users options Error!");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
