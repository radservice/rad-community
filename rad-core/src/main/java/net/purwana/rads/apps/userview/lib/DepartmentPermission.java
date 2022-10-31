package net.purwana.rads.apps.userview.lib;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.purwana.rads.apps.app.service.AppUtil;
import net.purwana.rads.apps.datalist.model.DatalistPermission;
import net.purwana.rads.apps.form.model.FormPermission;
import net.purwana.rads.apps.userview.model.UserviewPermission;
import net.purwana.rads.commons.util.LogUtil;
import net.purwana.rads.commons.util.ResourceBundleUtil;
import net.purwana.rads.directory.model.Department;
import net.purwana.rads.directory.model.Employment;
import net.purwana.rads.directory.model.Organization;
import net.purwana.rads.directory.model.User;
import net.purwana.rads.directory.model.service.ExtDirectoryManager;
import net.purwana.rads.plugin.base.PluginWebSupport;
import net.purwana.rads.workflow.model.service.WorkflowUserManager;
import net.purwana.rads.workflow.util.WorkflowUtil;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;

public class DepartmentPermission extends UserviewPermission implements PluginWebSupport, FormPermission, DatalistPermission {

    @Override
    public boolean isAuthorize() {
        User user = getCurrentUser();

        if (user != null && user.getEmployments() != null && user.getEmployments().size() > 0) {
            Set<String> allowedDeptIds = new HashSet<String>(Arrays.asList(getPropertyString("allowedDeptIds").split(";")));
            
            for (Employment e : (Set<Employment>) user.getEmployments()) {
                if (e.getDepartmentId() != null && allowedDeptIds.contains(e.getDepartmentId())) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getName() {
        return "Department Permission";
    }

    public String getVersion() {
        return "5.0.0";
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "Department";
    }

    public String getClassName() {
        return getClass().getName();
    }

    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/userview/departmentPermission.json", null, true, "message/userview/departmentPermission");
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
        } else if ("getDepts".equals(action)) {
            String orgId = request.getParameter("orgId");

            if ("null".equals(orgId) || "".equals(orgId)) {
                orgId = null;
            }

            try {
                JSONArray jsonArray = new JSONArray();

                ApplicationContext ac = AppUtil.getApplicationContext();
                ExtDirectoryManager directoryManager = (ExtDirectoryManager) ac.getBean("directoryManager");

                Collection<Department> deptList = directoryManager.getDepartmentsByOrganizationId(null, orgId, "name", false, null, null);

                for (Department d : deptList) {
                    Map<String, String> option = new HashMap<String, String>();
                    option.put("value", d.getId());
                    option.put("label", ((d.getTreeStructure() != null) ? d.getTreeStructure() + " " : "") + d.getName());
                    jsonArray.put(option);
                }

                jsonArray.write(response.getWriter());
            } catch (Exception ex) {
                LogUtil.error(this.getClass().getName(), ex, "Get departments options Error!");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
