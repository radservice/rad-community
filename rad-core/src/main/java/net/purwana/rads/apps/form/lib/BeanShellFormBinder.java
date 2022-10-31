package net.purwana.rads.apps.form.lib;

import bsh.Interpreter;
import java.util.HashMap;
import java.util.Map;
import net.purwana.rads.apps.app.service.AppUtil;
import net.purwana.rads.apps.form.model.Element;
import net.purwana.rads.apps.form.model.FormAjaxOptionsBinder;
import net.purwana.rads.apps.form.model.FormBinder;
import net.purwana.rads.apps.form.model.FormData;
import net.purwana.rads.apps.form.model.FormLoadBinder;
import net.purwana.rads.apps.form.model.FormLoadElementBinder;
import net.purwana.rads.apps.form.model.FormLoadMultiRowElementBinder;
import net.purwana.rads.apps.form.model.FormLoadOptionsBinder;
import net.purwana.rads.apps.form.model.FormRowSet;
import net.purwana.rads.apps.form.model.FormStoreBinder;
import net.purwana.rads.apps.form.model.FormStoreElementBinder;
import net.purwana.rads.apps.form.model.FormStoreMultiRowElementBinder;
import net.purwana.rads.commons.util.LogUtil;
import net.purwana.rads.commons.util.SecurityUtil;

public class BeanShellFormBinder extends FormBinder implements FormLoadBinder, FormStoreBinder, FormLoadElementBinder, FormStoreElementBinder, FormLoadOptionsBinder, FormLoadMultiRowElementBinder, FormStoreMultiRowElementBinder, FormAjaxOptionsBinder {

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    public String getName() {
        return "Bean Shell Form Binder";
    }

    public String getVersion() {
        return "5.0.0";
    }

    public String getDescription() {
        return "Executes standard Java syntax";
    }

    public FormRowSet load(Element element, String primaryKey, FormData formData) {
        Map properties = new HashMap();
        properties.putAll(getProperties());
        properties.put("element", element);
        properties.put("primaryKey", primaryKey);
        properties.put("formData", formData);
        properties.put("values", new String[]{});
        return executeScript(getPropertyString("script"), properties, false);
    }

    public FormRowSet store(Element element, FormRowSet rows, FormData formData) {
        Map properties = new HashMap();
        properties.putAll(getProperties());
        properties.put("element", element);
        properties.put("rows", rows);
        properties.put("formData", formData);
        return executeScript(getPropertyString("script"), properties, true);
    }

    public String getLabel() {
        return "Bean Shell Form Binder";
    }

    public String getPropertyOptions() {
        String useAjax = "";
        if (SecurityUtil.getDataEncryption() != null && SecurityUtil.getNonceGenerator() != null) {
            useAjax = ",{name:'useAjax',label:'@@form.beanshellformbinder.useAjax@@',description:'@@form.beanshellformbinder.useAjax.desc@@',type:'checkbox',value :'false',options :[{value :'true',label :''}]}";
        }
        
        Object[] arguments = new Object[]{useAjax};
        
        return AppUtil.readPluginResource(getClass().getName(), "/properties/form/beanShellFormBinder.json", arguments, true, "message/form/beanShellFormBinder");
    }
    
    protected FormRowSet executeScript(String script, Map properties, boolean throwException) throws RuntimeException {
        Object result = null;
        try {
            Interpreter interpreter = new Interpreter();
            interpreter.setClassLoader(getClass().getClassLoader());
            for (Object key : properties.keySet()) {
                interpreter.set(key.toString(), properties.get(key));
            }
            LogUtil.debug(getClass().getName(), "Executing script " + script);
            result = interpreter.eval(script);
            return (FormRowSet) result;
        } catch (Exception e) {
            LogUtil.error(getClass().getName(), e, "Error executing script");
            if (throwException) {
                throw new RuntimeException("Error executing script");
            }
        }
        return null;
    }

    public boolean useAjax() {
        return "true".equalsIgnoreCase(getPropertyString("useAjax"));
    }

    public FormRowSet loadAjaxOptions(String[] dependencyValues) {
        Map properties = new HashMap();
        properties.putAll(getProperties());
        properties.put("values", (dependencyValues == null)? new String[]{}: dependencyValues);
        return executeScript(getPropertyString("script"), properties, false);
    }
}
